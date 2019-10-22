package mx.com.dgom.hm.ovhaul.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import mx.com.dgom.hm.ovhaul.R;
import mx.com.dgom.hm.ovhaul.app.AppConstantes;
import mx.com.dgom.hm.ovhaul.to.EntEstatusTO;
import mx.com.dgom.hm.ovhaul.to.LocalidadTO;

public class FiltrosAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<String > datos;

    public int getCount() {
        return this.datos.size();
    }

    public Object getItem(int i) {
        return this.datos.get(i);
    }

    public void setDatos(ArrayList<String> arrayList) {
        this.datos = arrayList;
         notifyDataSetChanged();
    }

    public FiltrosAdapter(Context context, ArrayList<String> arrayList) {
        super(context, R.layout.filtro_item, arrayList);
        this.context = context;
        this.datos = arrayList;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {

        String  to = this.datos.get(i);

        view = View.inflate(this.context, R.layout.filtro_item, null);
        TextView txtFiltro = (TextView) view.findViewById(R.id.txt_filtro);
        txtFiltro.setText(to);
        return view;

    }
}