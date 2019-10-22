package mx.com.dgom.hm.ovhaul.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mx.com.dgom.hm.ovhaul.R;
import mx.com.dgom.hm.ovhaul.to.DashboardTO;

public class DashboardAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<DashboardTO> datos;


    public DashboardAdapter(Context context, ArrayList<DashboardTO> arrayList) {
        super(context, R.layout.dashboard_item, arrayList);
        this.context = context;
        this.datos = arrayList;
    }

    public int getCount() {
        return this.datos.size();
    }

    public Object getItem(int i) {
        return this.datos.get(i);
    }

    public void setDatos(ArrayList<DashboardTO> arrayList) {
        this.datos = arrayList;
        notifyDataSetChanged();
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        DashboardTO to = (DashboardTO) this.datos.get(i);

        view = View.inflate(this.context, R.layout.dashboard_item, null);

        TextView txtCantidas = (TextView) view.findViewById(R.id.txt_cantidad);
        TextView txtDesc = (TextView)view.findViewById(R.id.txt_desc);

        txtCantidas.setText("" + to.getCantidad());
        txtDesc.setText(to.getDescripcion());


        return view;
    }

}