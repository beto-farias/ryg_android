package mx.com.dgom.hm.ovhaul.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mx.com.dgom.hm.ovhaul.R;
import mx.com.dgom.hm.ovhaul.app.AppData;
import mx.com.dgom.hm.ovhaul.to.ComentariosLocalidadTO;
import mx.com.dgom.hm.ovhaul.to.ComentariosTareasTO;
import mx.com.dgom.hm.ovhaul.utils.DateUtils;

public class ComentariosTareasAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<ComentariosTareasTO> datos;

    public int getCount() {
        return this.datos.size();
    }

    public Object getItem(int i) {
        return this.datos.get(i);
    }

    public void setDatos(ArrayList<ComentariosTareasTO> arrayList) {
        this.datos = arrayList;
        notifyDataSetChanged();
    }

    public ComentariosTareasAdapter(Context context, ArrayList<ComentariosTareasTO> arrayList) {
        super(context, R.layout.comentarios_tareas_item, arrayList);
        this.context = context;
        this.datos = arrayList;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {

        ComentariosTareasTO  to = this.datos.get(i);

        view = View.inflate(this.context, R.layout.comentarios_tareas_item, null);
        TextView txtFrom = (TextView) view.findViewById(R.id.txt_from);
        TextView txtMessage = (TextView) view.findViewById(R.id.txt_msg);
        TextView txtDate = (TextView) view.findViewById(R.id.txt_date);

        txtFrom.setText(to.getUsuario().getTxt_apellido_paterno() + " escribio:");
        txtMessage.setText(to.getTxt_comentario());
        txtDate.setText(DateUtils.convertDateToFullString(to.getFch_comentario()));

        if(to.getUsuario().getId_usuario() == AppData.usuario.getId_usuario()){
            txtFrom.setText( "Tu escribiste: ");
            txtFrom.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            txtMessage.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            txtDate.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        }else{
            txtFrom.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            txtMessage.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            txtDate.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);

        }
        return view;

    }
}
