package mx.com.dgom.hm.ovhaul;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import mx.com.dgom.hm.ovhaul.adapter.ComentariosAdapter;
import mx.com.dgom.hm.ovhaul.app.AppController;
import mx.com.dgom.hm.ovhaul.app.ListResponse;
import mx.com.dgom.hm.ovhaul.app.MessageListResponseInterface;
import mx.com.dgom.hm.ovhaul.app.MessageResponse;
import mx.com.dgom.hm.ovhaul.app.MessageResponseInterface;
import mx.com.dgom.hm.ovhaul.to.ComentariosLocalidadTO;
import mx.com.dgom.hm.ovhaul.to.LocalidadTO;

public class ComentariosLocalidadFragment extends Fragment {
    private ListView listComentarios;
    private ComentariosAdapter adapter;
    private ArrayList<ComentariosLocalidadTO> comentarios;
    private AppController controller;

    //Agregar comentario
    private TextView txtComentario;
    private ImageButton btnEnviar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_comentarios_localidad_fragment, container, false);

        listComentarios = view.findViewById(R.id.listComentarios);
        txtComentario = view.findViewById(R.id.txt_add_comentario);
        btnEnviar = view.findViewById(R.id.btn_add_comentario);

        comentarios = new ArrayList<ComentariosLocalidadTO>();

        adapter = new ComentariosAdapter(getContext(), comentarios);
        controller = ((DetalleLocalidadActivity)getActivity()).getController();

        listComentarios.setAdapter(adapter);

        setUpAddComentario();
        setUpComentarios();
        return view;
    }

    public static ComentariosLocalidadFragment newInstance(String tokenLocalidad) {
        Bundle bundle = new Bundle();
        bundle.putString("TOKEN_LOCALIDAD", tokenLocalidad);

        ComentariosLocalidadFragment fragment = new ComentariosLocalidadFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    private void setUpAddComentario(){
        final DetalleLocalidadActivity activity = (DetalleLocalidadActivity)getActivity();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comentario = txtComentario.getText().toString();

                if(comentario.isEmpty()){
                    activity.slideUpDialogNotification("Debe escribir un comentario");
                    return;
                }

                activity.addCover();
                String tokenLocalidad = getArguments().getString("TOKEN_LOCALIDAD");
                controller.addComentarioLocalidad(getContext(), tokenLocalidad, comentario,  new MessageResponseInterface() {
                    @Override
                    public void response(String noInternetError, MessageResponse errorResponse, MessageResponse responseMessage) {

                        activity.removeCover();
                        if(!activity.validateResponse(noInternetError, errorResponse)){
                            return;
                        }
                        activity.slideUpDialogNotification(responseMessage.getMessage());
                        setUpComentarios();
                        txtComentario.setText("");
                    }
                });

            }
        });
    }

    private void setUpComentarios(){
        final DetalleLocalidadActivity activity = (DetalleLocalidadActivity)getActivity();

        activity.addCover();
        String tokenLocalidad = getArguments().getString("TOKEN_LOCALIDAD");
        controller.getComentariosLocalidadesList(getContext(), tokenLocalidad, new MessageListResponseInterface<ComentariosLocalidadTO>() {
            @Override
            public void response(String noInternetError, MessageResponse errorResponse, ListResponse<ComentariosLocalidadTO> responseMessage) {

                activity.removeCover();
                if(!activity.validateResponse(noInternetError, errorResponse)){
                    return;
                }
                comentarios = responseMessage.getResults();
                adapter.setDatos(comentarios);
            }
        });
    }
}
