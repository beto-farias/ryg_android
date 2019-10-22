package mx.com.dgom.hm.ovhaul;

import android.content.Intent;
import android.media.Image;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import mx.com.dgom.hm.ovhaul.adapter.ComentariosAdapter;
import mx.com.dgom.hm.ovhaul.adapter.PageAdapter;
import mx.com.dgom.hm.ovhaul.app.AppConstantes;
import mx.com.dgom.hm.ovhaul.to.EntEstatusTO;
import mx.com.dgom.hm.ovhaul.to.LocalidadTO;
import mx.com.dgom.hm.ovhaul.utils.DateUtils;

public class DetalleLocalidadActivity extends App2GomActivity {
    private PageAdapter pageAdapter;
    private ViewPager container;
    private TabLayout tabs;
    private TextView txtResponsable;
    private TextView txtFechaAsig;
    private TextView txtLocalidad;
    private TextView txtCMS;
    private CircleImageView imgUsuario;
    private Intent intent;
    private  LocalidadTO to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_localidad);

        intent=getIntent();
        to = (LocalidadTO)intent.getSerializableExtra("LOCALIDAD");
        container = findViewById(R.id.container);
        tabs = findViewById(R.id.tabLayoutLocalidad);
        txtResponsable = findViewById(R.id.txt_responsable);
        txtFechaAsig = findViewById(R.id.txt_fecha_asig);
        txtLocalidad= findViewById(R.id.txt_localidad);
        txtCMS = findViewById(R.id.txt_cms);
        imgUsuario = findViewById(R.id.img_usuario);

        pageAdapter = new PageAdapter(getSupportFragmentManager());
        setupViewPager(container);


        setUpView();
        tabs.setupWithViewPager(container);
    }

    private void setUpView(){

        String responsable = "Nombre del responsable";
        if(to.getUsuarioResponsable() != null && to.getUsuarioResponsable().getTxt_username() != null && to.getUsuarioResponsable().getTxt_apellido_paterno() != null){
            responsable = to.getUsuarioResponsable().getTxt_username() + " " + to.getUsuarioResponsable().getTxt_apellido_paterno();
        }

        String fechaAsignacion = DateUtils.convertDateToFullString(to.getFch_asignacion());
        String localidad = to.getTxt_nombre();
        String cms = to.getCms();
        String estatus;
        String url = AppConstantes.WEB_REST_URL_DEV + "profiles/" + to.getUsuarioResponsable().getTxt_token() + "/"+ to.getUsuarioResponsable().getTxt_imagen();

        Glide.with(getApplicationContext()).asBitmap().load(url).into(imgUsuario);

        if(to.getEntEstatuses().size()>0){
            EntEstatusTO estatusTO = to.getEntEstatuses().get(to.getEntEstatuses().size()-1);
            estatus = estatusTO.getTxt_estatus();
        }else{
            estatus = null;
        }

        txtResponsable.setText(responsable);
        txtFechaAsig.setText(fechaAsignacion);
        txtLocalidad.setText(localidad);
        txtCMS.setText(cms);




    }

    private void setupViewPager(ViewPager pager){
        pageAdapter.addFragment(EstatusLocalidadFragment.newInstance(to.getEntEstatuses()), "Estatus");
        pageAdapter.addFragment(DetallesLocalidadFragment.newInstance(to), "Detalles");
        pageAdapter.addFragment(ComentariosLocalidadFragment.newInstance(to.getTxt_token()), "Comentarios");

        pager.setAdapter(pageAdapter);
    }

}
