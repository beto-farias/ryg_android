package mx.com.dgom.hm.ovhaul.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mx.com.dgom.hm.ovhaul.R;
import mx.com.dgom.hm.ovhaul.to.EntEstatusTO;
import mx.com.dgom.hm.ovhaul.to.EstatusTO;
import mx.com.dgom.hm.ovhaul.utils.DateUtils;

public class EstatusAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<EntEstatusTO> datos;

    public int getCount() {
        return this.datos.size();
    }

    public Object getItem(int i) {
        return this.datos.get(i);
    }

    public void setDatos(ArrayList<EntEstatusTO> arrayList) {
        this.datos = arrayList;
        notifyDataSetChanged();
    }

    public EstatusAdapter(Context context, ArrayList<EntEstatusTO> arrayList) {
        super(context, R.layout.estatus_item, arrayList);
        this.context = context;
        this.datos = arrayList;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {

        EntEstatusTO  to = this.datos.get(i);

        view = View.inflate(this.context, R.layout.estatus_item, null);
        TextView txtFecha = (TextView) view.findViewById(R.id.txt_fecha_estatus);
        TextView txtEstatus = (TextView) view.findViewById(R.id.txt_desc_estatus);
        txtFecha.setText(DateUtils.convertDateToFullString(to.getFch_creacion()));
        txtEstatus.setText(to.getTxt_estatus());
        return view;

    }
}