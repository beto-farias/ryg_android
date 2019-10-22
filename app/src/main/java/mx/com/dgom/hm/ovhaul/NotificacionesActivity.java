package mx.com.dgom.hm.ovhaul;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import mx.com.dgom.hm.ovhaul.adapter.LogAdapter;
import mx.com.dgom.hm.ovhaul.app.ListResponse;
import mx.com.dgom.hm.ovhaul.app.MessageListResponseInterface;
import mx.com.dgom.hm.ovhaul.app.MessageResponse;
import mx.com.dgom.hm.ovhaul.to.LogTO;

public class NotificacionesActivity extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private ListView notificacionesList;
    private ArrayList<LogTO> logs = new ArrayList<>();
    private LogAdapter adapter;
    private MainActivity activity;
    private SwipeRefreshLayout swipeView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_notificaciones, container, false);

        notificacionesList = view.findViewById(R.id.list_notificaciones);
        swipeView = (SwipeRefreshLayout) view.findViewById(R.id.swipe_view);
        swipeView.setOnRefreshListener(this);


        adapter = new LogAdapter(getContext(), logs);
        notificacionesList.setAdapter(adapter);

        activity = (MainActivity)getActivity();
        loadLogs();

        return view;
    }

    private void loadLogs(){
        activity.addCover();
        activity.getController().getLog(getContext(), new MessageListResponseInterface<LogTO>() {
            @Override
            public void response(String noInternetError, MessageResponse errorResponse, ListResponse<LogTO> responseMessage) {
                activity.removeCover();
                swipeView.setRefreshing(false);
                if(!activity.validateResponse(noInternetError,errorResponse)){
                    activity.slideUpDialogNotification("Error al obtener las notificaciones");
                    return;
                }

                logs = responseMessage.getResults();
                adapter.setDatos(logs);
            }
        });
    }

    @Override
    public void onRefresh() {
        this.swipeView.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeView.setRefreshing(true);
                loadLogs();

            }
        }, 1000);
    }
}
