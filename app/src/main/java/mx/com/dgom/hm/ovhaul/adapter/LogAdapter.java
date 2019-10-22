package mx.com.dgom.hm.ovhaul.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import mx.com.dgom.hm.ovhaul.R;
import mx.com.dgom.hm.ovhaul.app.AppConstantes;
import mx.com.dgom.hm.ovhaul.app.AppData;
import mx.com.dgom.hm.ovhaul.to.ComentariosTareasTO;
import mx.com.dgom.hm.ovhaul.to.LogTO;
import mx.com.dgom.hm.ovhaul.utils.DateUtils;

public class LogAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<LogTO> datos;

    public int getCount() {
        return this.datos.size();
    }

    public Object getItem(int i) {
        return this.datos.get(i);
    }

    public void setDatos(ArrayList<LogTO> arrayList) {
        this.datos = arrayList;
        notifyDataSetChanged();
    }

    public LogAdapter(Context context, ArrayList<LogTO> arrayList) {
        super(context, R.layout.notificacion_item, arrayList);
        this.context = context;
        this.datos = arrayList;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {

        LogTO  to = this.datos.get(i);

        view = View.inflate(this.context, R.layout.notificacion_item, null);
        TextView txtTitle = (TextView) view.findViewById(R.id.txt_title);
        TextView txtBody = (TextView) view.findViewById(R.id.txt_body);
        TextView txtDate = (TextView) view.findViewById(R.id.txt_date);
        CircleImageView imageResponsable = (CircleImageView) view.findViewById(R.id.img_avatar);

        txtTitle.setText(to.getTxt_accion());

        switch(to.getId_tipo_evento()){
            case 1:
                txtBody.setText(to.getTxt_area());
                break;
            case 2:
                txtBody.setText(to.getTxt_area());
                break;
            case 3:
                txtBody.setText(to.getTxt_localidad());
                break;
            default:
                txtBody.setText(to.getTxt_area());
                break;
        }

        txtDate.setText(DateUtils.convertDateToFullString(to.getFch_evento()));

        String url = AppConstantes.WEB_REST_URL_DEV  + to.getTxt_user_avatar();

        Glide.with(context).asBitmap().load(url).into(imageResponsable);


        return view;

    }
}
