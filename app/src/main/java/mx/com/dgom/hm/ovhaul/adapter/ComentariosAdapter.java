package mx.com.dgom.hm.ovhaul.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mx.com.dgom.hm.ovhaul.R;
import mx.com.dgom.hm.ovhaul.to.ComentariosLocalidadTO;
import mx.com.dgom.hm.ovhaul.to.EntEstatusTO;
import mx.com.dgom.hm.ovhaul.utils.DateUtils;

public class ComentariosAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<ComentariosLocalidadTO> datos;

    public int getCount() {
        return this.datos.size();
    }

    public Object getItem(int i) {
        return this.datos.get(i);
    }

    public void setDatos(ArrayList<ComentariosLocalidadTO> arrayList) {
        this.datos = arrayList;
        notifyDataSetChanged();
    }

    public ComentariosAdapter(Context context, ArrayList<ComentariosLocalidadTO> arrayList) {
        super(context, R.layout.comentario_item, arrayList);
        this.context = context;
        this.datos = arrayList;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {

        ComentariosLocalidadTO  to = this.datos.get(i);

        view = View.inflate(this.context, R.layout.comentario_item, null);
        TextView txtNombre = (TextView) view.findViewById(R.id.txt_nombre_comentario);
        TextView txtComentario = (TextView) view.findViewById(R.id.txt_comentario);
        TextView txtFecha = (TextView) view.findViewById(R.id.txt_fecha_comentario);
        txtNombre.setText(to.getUsuario().getTxt_username()+ " " + to.getUsuario().getTxt_apellido_paterno());
        txtComentario.setText(to.getTxt_comentario());
        txtFecha.setText(DateUtils.convertDateToFullString(to.getFch_comentario()));
        return view;

    }
}