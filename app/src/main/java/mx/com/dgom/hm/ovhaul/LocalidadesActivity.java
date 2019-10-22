package mx.com.dgom.hm.ovhaul;

import android.content.Intent;
import android.support.v4.app.Fragment;
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
import mx.com.dgom.hm.ovhaul.app.AppController;
import mx.com.dgom.hm.ovhaul.app.AppData;
import mx.com.dgom.hm.ovhaul.app.ListResponse;
import mx.com.dgom.hm.ovhaul.app.MessageListResponseInterface;
import mx.com.dgom.hm.ovhaul.app.MessageResponse;
import mx.com.dgom.hm.ovhaul.app.MessageResponseInterface;
import mx.com.dgom.hm.ovhaul.to.DashboardRespTO;
import mx.com.dgom.hm.ovhaul.to.DashboardTO;
import mx.com.dgom.hm.ovhaul.to.LocalidadTO;

public class LocalidadesActivity extends Fragment {
    private MainActivity activity;
    private AppController controller;
    private ArrayList<LocalidadTO> localidades = new ArrayList<>();
    private LocalidadesAdapter adapter;
    private ListView localidadesList;


    //layout de filtros
    private LinearLayout searchHeader;
    private ListView filtroList;
    private ArrayList<String> filtros = new ArrayList<>();
    private FiltrosAdapter filtrosAdapter;
    private Button btnFiltrar;
    private Button btnOrdenar;
    private Button btnBuscar;
    private TextView tvSearch;
    private String txtSearch="";
    private String txtFiltro="";
    private boolean ordenAsc;
    private boolean isFiltered;
    private ImageButton btnCloseSearch;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_localidades, container, false);


        activity = (MainActivity) getActivity();
        controller = activity.getController();

        localidadesList = view.findViewById(R.id.list_localidades);
        filtroList = view.findViewById(R.id.list_filtros);
        btnFiltrar = view.findViewById(R.id.btn_filtrar);
        btnOrdenar = view.findViewById(R.id.btn_ordenar);
        btnBuscar = view.findViewById(R.id.btn_buscar);
        tvSearch = view.findViewById(R.id.txt_search);
        searchHeader = view.findViewById(R.id.search_header);
        btnCloseSearch = view.findViewById(R.id.btn_close_search);



        adapter = new LocalidadesAdapter(getContext(), localidades);
        filtrosAdapter = new FiltrosAdapter(getContext(), filtros);
       // ordenarAdapter = new FiltrosAdapter(getContext(), ordenarArray);

        localidadesList.setAdapter(adapter);
        filtroList.setAdapter(filtrosAdapter);


        setUpBtns();
        loadLocalidades();
        return view;
    }

    //----------------NETWORK

    private void loadLocalidades(){
        ((MainActivity)getActivity()).addCover();

        controller.getLocalidadesList(getContext(), AppData.usuario.getTxt_token(), new MessageListResponseInterface<LocalidadTO>() {
            @Override
            public void response(String noInternetError, MessageResponse errorResponse, ListResponse<LocalidadTO> responseMessage) {
                ((MainActivity)getActivity()).removeCover();
                if(!activity.validateResponse(noInternetError, errorResponse)){
                    return;
                }
                localidades = responseMessage.getResults();
                adapter.setDatos(localidades);

            }
        });
    }

    //------------FUNCIONES BOTONES


    private void setUpBtns(){
        btnCloseSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchHeader.setVisibility(View.GONE);
            }
        });

        this.tvSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtSearch = tvSearch.getText().toString().toUpperCase();
                adapter.filtrar(txtSearch, txtFiltro, ordenAsc);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        this.tvSearch.clearFocus();

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchHeader.setVisibility(View.VISIBLE);

            }
        });


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

                adapter.filtrar(txtSearch, txtFiltro, ordenAsc);

                filtroList.setVisibility(View.GONE);
            }
        });

        localidadesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), DetalleLocalidadActivity.class);
                intent.putExtra("LOCALIDAD", (LocalidadTO)adapter.getItem(position));
                startActivity(intent);
            }
        });
    }

    public void btnFiltrarAction() {

        filtros.clear();

        filtros.add("RENOVACIÓN");
        filtros.add("REGULARIZACIÓN");

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
}
