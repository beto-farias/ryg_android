package mx.com.dgom.hm.ovhaul;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import mx.com.dgom.hm.ovhaul.adapter.FiltrosAdapter;
import mx.com.dgom.hm.ovhaul.adapter.LocalidadesAdapter;
import mx.com.dgom.hm.ovhaul.adapter.TareasAdapter;
import mx.com.dgom.hm.ovhaul.app.AppController;
import mx.com.dgom.hm.ovhaul.app.AppData;
import mx.com.dgom.hm.ovhaul.app.ListResponse;
import mx.com.dgom.hm.ovhaul.app.MessageListResponseInterface;
import mx.com.dgom.hm.ovhaul.app.MessageResponse;
import mx.com.dgom.hm.ovhaul.to.LocalidadTO;
import mx.com.dgom.hm.ovhaul.to.TareasTO;

public class TareasPendientesActivity extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private MainActivity activity;
    private AppController controller;
    private ArrayList<TareasTO> tareas = new ArrayList<>();
    private TareasAdapter adapter;
    private ListView tareasList;

    private SwipeRefreshLayout swipeView;


    //layout de filtros
     private ListView filtroList;
    private ArrayList<String> filtros = new ArrayList<>();
    private FiltrosAdapter filtrosAdapter;
    private Button btnFiltrar;
    private Button btnOrdenar;
    private String txtFiltro="";
    private boolean ordenAsc;
    private boolean isFiltered;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tareas_pendientes, container, false);
        activity = (MainActivity) getActivity();
        controller = activity.getController();

        tareasList = view.findViewById(R.id.list_tareas);
        filtroList = view.findViewById(R.id.list_filtros);
        btnFiltrar = view.findViewById(R.id.btn_filtrar);
        btnOrdenar = view.findViewById(R.id.btn_ordenar);
        swipeView = (SwipeRefreshLayout) view.findViewById(R.id.swipe_view);
        swipeView.setOnRefreshListener(this);




        adapter = new TareasAdapter(getContext(), tareas);
        filtrosAdapter = new FiltrosAdapter(getContext(), filtros);
        // ordenarAdapter = new FiltrosAdapter(getContext(), ordenarArray);

        tareasList.setAdapter(adapter);
        filtroList.setAdapter(filtrosAdapter);



        loadTareas();
        setUpBtns();
        return view;
    }

    //----------------NETWORK

    private void loadTareas(){
        ((MainActivity)getActivity()).addCover();

        controller.getTareasResponsablesList(getContext(),  new MessageListResponseInterface<TareasTO>() {
            @Override
            public void response(String noInternetError, MessageResponse errorResponse, ListResponse<TareasTO> responseMessage) {
                ((MainActivity)getActivity()).removeCover();
                swipeView.setRefreshing(false);
                if(!activity.validateResponse(noInternetError, errorResponse)){
                    return;
                }
                tareas = responseMessage.getResults();
                adapter.setDatos(tareas);
                setUpBtns();


            }
        });
    }

    //------------FUNCIONES BOTONES


    private void setUpBtns(){

        btnFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFiltered=true;
                btnFiltrarAction();
            }
        });

        btnOrdenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFiltered=false;
                btnOrdenarAction();
            }
        });

        filtroList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(isFiltered)
                    if(txtFiltro.equals(((String)filtrosAdapter.getItem(position)).toUpperCase()))
                        txtFiltro="";
                    else
                        txtFiltro = ((String)filtrosAdapter.getItem(position)).toUpperCase();
                else{
                    String order = ((String)filtrosAdapter.getItem(position)).toUpperCase();
                    if(order.equals("ASCENDENTE")){
                        ordenAsc = true;
                    }else{
                        ordenAsc = false;
                    }
                }

                adapter.filtrar("", txtFiltro, ordenAsc);

                filtroList.setVisibility(View.GONE);
            }
        });

        tareasList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), TareasDetalleActivity.class);
                    intent.putExtra("TAREA", (TareasTO)adapter.getItem(position));
                    startActivityForResult(intent, 0 );
            }
        });
    }

    public void btnFiltrarAction() {

        filtros.clear();

        for(int i=0;i <tareas.size();i++){
            if(!filtros.contains(tareas.get(i).getTxt_localidad().toUpperCase())){
                filtros.add(tareas.get(i).getTxt_localidad().toUpperCase());
            }
        }
        filtrosAdapter.setDatos(filtros);
        filtroList.setVisibility(View.VISIBLE);

    }

    public void btnOrdenarAction() {

        filtros.clear();

        filtros.add("ASCENDENTE");
        filtros.add("DESCENDENTE");
        filtrosAdapter.setDatos(filtros);
        filtroList.setVisibility(View.VISIBLE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 0) {
            loadTareas();
        }
    }


    @Override
    public void onRefresh() {
        this.swipeView.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeView.setRefreshing(true);
                loadTareas();

            }
        }, 1000);
    }

}
