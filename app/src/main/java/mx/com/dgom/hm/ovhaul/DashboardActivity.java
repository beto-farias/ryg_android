package mx.com.dgom.hm.ovhaul;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import mx.com.dgom.hm.ovhaul.adapter.DashboardAdapter;
import mx.com.dgom.hm.ovhaul.app.AppController;
import mx.com.dgom.hm.ovhaul.app.AppData;
import mx.com.dgom.hm.ovhaul.app.MessageResponse;
import mx.com.dgom.hm.ovhaul.app.MessageResponseInterface;
import mx.com.dgom.hm.ovhaul.to.DashboardRespTO;
import mx.com.dgom.hm.ovhaul.to.DashboardTO;
import mx.com.dgom.hm.ovhaul.utils.CustomGridView;

public class DashboardActivity extends Fragment {

    private DashboardAdapter adapter;
    private ArrayList<DashboardTO> datos = new ArrayList<>();
    private GridView dashboard;
    private AppController controller;
    private MainActivity activity;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dashboard, container, false);


        activity = (MainActivity) getActivity();
        controller = activity.getController();
        dashboard = view.findViewById(R.id.list_dashboard);
        adapter = new DashboardAdapter( getContext(), datos);

        dashboard.setAdapter(adapter);


        loadDashboard();
        return view;
    }


    private void loadDashboard(){
        activity.addCover();

        controller.getDashboard(getContext(), AppData.usuario.getTxt_token(), new MessageResponseInterface<DashboardRespTO>() {
            @Override
            public void response(String noInternetError, MessageResponse errorResponse, MessageResponse<DashboardRespTO> responseMessage) {
                ((MainActivity)getActivity()).removeCover();
                if(!activity.validateResponse(noInternetError, errorResponse)){
                    return;
                }
                DashboardRespTO dashboardResp = responseMessage.getData();
                //activity.slideUpDialogNotification(responseMessage.getMessage());

                DashboardTO to = new DashboardTO();
                to.setCantidad(dashboardResp.getCasos_audiencia());
                to.setDescripcion(getResources().getString(R.string.casos_en_audiencia));
                datos.add(to);

                to = new DashboardTO();
                to.setCantidad(dashboardResp.getConsignaciones_proximas());
                to.setDescripcion(getResources().getString(R.string.consignaciones_proximas));
                datos.add(to);

                to = new DashboardTO();
                to.setCantidad(dashboardResp.getLocalidades_registradas());
                to.setDescripcion(getResources().getString(R.string.localidades_registradas));
                datos.add(to);

                to = new DashboardTO();
                to.setCantidad(dashboardResp.getMis_casos_audiencia());
                to.setDescripcion(getResources().getString(R.string.mis_casos_en_audiencia));
                datos.add(to);

                to = new DashboardTO();
                to.setCantidad(dashboardResp.getMis_tareas_pendientes());
                to.setDescripcion(getResources().getString(R.string.mis_tareas_pendientes));
                datos.add(to);

                to = new DashboardTO();
                to.setCantidad(dashboardResp.getTareas_pendientes());
                to.setDescripcion(getResources().getString(R.string.tareas_pendientes));
                datos.add(to);

                adapter.setDatos(datos);


            }
        });
    }

}
