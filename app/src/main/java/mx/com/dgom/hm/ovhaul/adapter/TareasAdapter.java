package mx.com.dgom.hm.ovhaul.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import de.hdodenhof.circleimageview.CircleImageView;
import mx.com.dgom.hm.ovhaul.R;
import mx.com.dgom.hm.ovhaul.app.AppConstantes;
import mx.com.dgom.hm.ovhaul.to.EntEstatusTO;
import mx.com.dgom.hm.ovhaul.to.LocalidadTO;
import mx.com.dgom.hm.ovhaul.to.TareasTO;
import mx.com.dgom.hm.ovhaul.utils.DateUtils;

public class TareasAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<TareasTO> datos;
    private ArrayList<TareasTO> listDataFiltered = new ArrayList<>();
    private boolean orderAsc;


    public int getCount() {
        return this.listDataFiltered.size();
    }

    public Object getItem(int i) {
        return this.listDataFiltered.get(i);
    }

    public void setDatos(ArrayList<TareasTO> arrayList) {
        this.datos = arrayList;
        this.listDataFiltered.addAll(datos);
        notifyDataSetChanged();
    }

    public TareasAdapter(Context context, ArrayList<TareasTO> arrayList) {
        super(context, R.layout.tarea_item, arrayList);
        this.context = context;
        this.datos = arrayList;
        this.listDataFiltered.addAll(datos);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {

        TareasTO to = this.listDataFiltered.get(i);

        view = View.inflate(this.context, R.layout.tarea_item, null);
         CircleImageView imageResponsable = (CircleImageView) view.findViewById(R.id.img_usuario);
        TextView txtTarea = (TextView) view.findViewById(R.id.txt_tarea);
        TextView txtLocalidad = (TextView) view.findViewById(R.id.txt_localidad);
        TextView txtFecha = (TextView) view.findViewById(R.id.txt_fecha);

        if(to.getUsuarioResponsableTarea() != null && to.getUsuarioResponsableTarea().getTxt_token() != null) {
            String url = AppConstantes.WEB_REST_URL_DEV + "profiles/" + to.getUsuarioResponsableTarea().getTxt_token() + "/" + to.getUsuarioResponsableTarea().getTxt_imagen();
            Glide.with(context).asBitmap().load(url).into(imageResponsable);
        }



        txtTarea.setText(to.getTxt_nombre());
        txtFecha.setText(DateUtils.convertDateToFullString(to.getFch_creacion()));
        txtLocalidad.setText(to.getTxt_localidad());

        return view;

    }


    public void filtrar(String txtSearch, String txtFiltro, boolean orderAsc) {
        //Lista vacia
        ArrayList<TareasTO> tareasList = new ArrayList<>();

        //Recorre la lista para filtro de texto
        for (int i = 0; i < datos.size(); i++) {
            TareasTO item = datos.get(i);
            //Si el filtro está vacio agrega todos
            if (txtSearch == "") {
                tareasList.add(item);
                continue;
            }

            //Filtro por CMS
            if (((TareasTO) item).getTxt_descripcion().toUpperCase().contains(txtSearch)) {
                tareasList.add(item);
                continue;
            }

            //Filtro por Nombre localidad
            if (item.getTxt_nombre().toUpperCase().contains(txtSearch)) {
                tareasList.add(item);
                continue;
            }
        }

        //Aplica el filtro de opciones
        ArrayList<TareasTO> tareasList2 = new ArrayList<>();

        for (int i = 0; i < tareasList.size(); i++) {
            TareasTO item = tareasList.get(i);
            //Si el filtro está vacio agrega todos
            if (txtFiltro == "") {
                tareasList2.add(item);
                continue;
            }

            //Filtro por CMS
            if (item.getTxt_localidad().toUpperCase().contains(txtFiltro)) {
                tareasList2.add(item);
                continue;
            }

        }

        listDataFiltered.clear();
        listDataFiltered.addAll(tareasList2);

        ordenar(orderAsc);
    }


    public void ordenar(boolean orderAsc) {
        if (orderAsc) {
            Collections.sort(listDataFiltered, new TareasAdapter.SortAscendente());
        } else if (!orderAsc) {
            Collections.sort(listDataFiltered, new TareasAdapter.SortDescendente());
        }

        notifyDataSetChanged();
    }

    public void buscar(String search) {


    }

    public class SortAscendente implements Comparator {
        public int compare(Object o1, Object o2) {

            TareasTO dd1 = (TareasTO) o1;// where FBFriends_Obj is your object class
            TareasTO dd2 = (TareasTO) o2;
            return dd1.getTxt_localidad().compareToIgnoreCase(dd2.getTxt_localidad());//where uname is field name
        }

    }

    public class SortDescendente implements Comparator {
        public int compare(Object o1, Object o2) {

            TareasTO dd1 = (TareasTO) o1;// where FBFriends_Obj is your object class
            TareasTO dd2 = (TareasTO) o2;
            return dd2.getTxt_localidad().compareToIgnoreCase(dd1.getTxt_localidad());//where uname is field name
        }

    }
}
