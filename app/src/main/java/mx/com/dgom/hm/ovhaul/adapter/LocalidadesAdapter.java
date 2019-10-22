package mx.com.dgom.hm.ovhaul.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

import mx.com.dgom.hm.ovhaul.R;
import mx.com.dgom.hm.ovhaul.app.AppConstantes;
import mx.com.dgom.hm.ovhaul.to.EntEstatusTO;
import mx.com.dgom.hm.ovhaul.to.EstatusTO;
import mx.com.dgom.hm.ovhaul.to.LocalidadTO;
import mx.com.dgom.hm.ovhaul.utils.DateUtils;

public class LocalidadesAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<LocalidadTO> datos;
    private ArrayList<LocalidadTO> listDataFiltered = new ArrayList<>();
    private boolean orderAsc;


    public int getCount() {
        return this.listDataFiltered.size();
    }

    public Object getItem(int i) {
        return this.listDataFiltered.get(i);
    }

    public void setDatos(ArrayList<LocalidadTO> arrayList) {
        this.datos = arrayList;
        this.listDataFiltered.addAll(datos);
        notifyDataSetChanged();
    }

    public LocalidadesAdapter(Context context, ArrayList<LocalidadTO> arrayList) {
        super(context, R.layout.localidad_item, arrayList);
        this.context = context;
        this.datos = arrayList;
        this.listDataFiltered.addAll(datos);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {

        LocalidadTO to = this.listDataFiltered.get(i);

        view = View.inflate(this.context, R.layout.localidad_item, null);
        TextView txtResponsable = (TextView) view.findViewById(R.id.txt_responsable);
        ImageView imageResponsable = (ImageView) view.findViewById(R.id.img_usuario);
        TextView txtFecha = (TextView) view.findViewById(R.id.txt_fecha_asig);
        TextView txtCms = (TextView) view.findViewById(R.id.txt_cms);
        TextView txtLocalidad = (TextView) view.findViewById(R.id.txt_localidad);
        TextView txtEstatus = (TextView) view.findViewById(R.id.txt_estatus);
        TextView txtUltimoEstatus = (TextView) view.findViewById(R.id.txt_ultimo_estatus);

        if(to.getUsuarioResponsable() != null && to.getUsuarioResponsable().getTxt_token() != null) {
            String url = AppConstantes.WEB_REST_URL_DEV + "profiles/" + to.getUsuarioResponsable().getTxt_token() + "/" + to.getUsuarioResponsable().getTxt_imagen();
            Glide.with(context).asBitmap().load(url).into(imageResponsable);
        }

        txtResponsable.setText("Nombre del responsable");
        if(to.getUsuarioResponsable() != null && to.getUsuarioResponsable().getTxt_username() != null && to.getUsuarioResponsable().getTxt_apellido_paterno() != null) {
            txtResponsable.setText(to.getUsuarioResponsable().getTxt_username() + " " + to.getUsuarioResponsable().getTxt_apellido_paterno());
        }
        txtFecha.setText(DateUtils.convertDateToFullString(to.getFch_asignacion()));
        txtCms.setText(to.getCms());
        txtLocalidad.setText(to.getTxt_nombre());
        txtEstatus.setText(to.getbStatusLocalidad().getTxt_nombre());

        if(to.getEntEstatuses().size()>0){
            EntEstatusTO estatus = to.getEntEstatuses().get(to.getEntEstatuses().size()-1);
            txtUltimoEstatus.setText(estatus.getTxt_estatus());
        }else{
            txtUltimoEstatus.setVisibility(View.GONE);
        }

        return view;

    }


    public void filtrar(String txtSearch, String txtFiltro, boolean orderAsc){
        //Lista vacia
        ArrayList<LocalidadTO> localidadList = new ArrayList<>();

        //Recorre la lista para filtro de texto
        for(int i = 0; i<datos.size();i++){
            LocalidadTO item = datos.get(i);
            //Si el filtro está vacio agrega todos
            if(txtSearch == ""){
                localidadList.add(item);
                continue;
            }

            //Filtro por CMS
            if( item.getCms().toUpperCase().contains(txtSearch) ){
                localidadList.add(item);
                continue;
            }

            //Filtro por Nombre localidad
            if( item.getTxt_nombre().toUpperCase().contains(txtSearch) ){
                localidadList.add(item);
                continue;
            }
        }

        //Aplica el filtro de opciones
        ArrayList<LocalidadTO> localidadList2 = new ArrayList<>();

        for(int i = 0; i<localidadList.size();i++){
            LocalidadTO item = localidadList.get(i);
            //Si el filtro está vacio agrega todos
            if(txtFiltro == ""){
                localidadList2.add(item);
                continue;
            }

            //Filtro por CMS
            if( item.getbStatusLocalidad().getTxt_nombre().toUpperCase().contains(txtFiltro) ){
                localidadList2.add(item);
                continue;
            }

        }

        listDataFiltered.clear();
        listDataFiltered.addAll(localidadList2);

        ordenar(orderAsc);
    }


    public void ordenar(boolean orderAsc){
        if(orderAsc){
           Collections.sort(listDataFiltered, new SortAscendente());
        }else if(!orderAsc){
            Collections.sort(listDataFiltered, new SortDescendente());
        }

        notifyDataSetChanged();
    }

    public void buscar(String search){


    }

    public class SortAscendente implements Comparator
    {
        public int compare(Object o1, Object o2)
        {

            LocalidadTO dd1 = (LocalidadTO)o1;// where FBFriends_Obj is your object class
            LocalidadTO dd2 = (LocalidadTO)o2;
            return dd1.getTxt_nombre().compareToIgnoreCase(dd2.getTxt_nombre());//where uname is field name
        }

    }

    public class SortDescendente implements Comparator
    {
        public int compare(Object o1, Object o2)
        {

            LocalidadTO dd1 = (LocalidadTO)o1;// where FBFriends_Obj is your object class
            LocalidadTO dd2 = (LocalidadTO)o2;
            return dd2.getTxt_nombre().compareToIgnoreCase(dd1.getTxt_nombre());//where uname is field name
        }

    }
}

