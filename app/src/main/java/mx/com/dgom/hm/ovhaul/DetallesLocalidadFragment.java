package mx.com.dgom.hm.ovhaul;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mx.com.dgom.hm.ovhaul.adapter.DashboardAdapter;
import mx.com.dgom.hm.ovhaul.to.EntEstatusTO;
import mx.com.dgom.hm.ovhaul.to.LocalidadTO;

public class DetallesLocalidadFragment extends Fragment {
    private TextView txtArrendador;
    private TextView txtBeneficiario;
    private TextView txtDomicilio;
    private TextView txtAntecendentes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_detalles_localidad_fragment, container, false);

        txtAntecendentes = view.findViewById(R.id.txt_antecedentes);
        txtArrendador = view.findViewById(R.id.txt_arrendador);
        txtBeneficiario = view.findViewById(R.id.txt_beneficiario);
        txtDomicilio = view.findViewById(R.id.txt_domicilio);


        setUpDetalle();
        return view;
    }

    public static DetallesLocalidadFragment newInstance(LocalidadTO localidad) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("DETALLE", localidad);

        DetallesLocalidadFragment fragment = new DetallesLocalidadFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    private void setUpDetalle(){
        LocalidadTO to = (LocalidadTO)getArguments().getSerializable("DETALLE");

        String antecedentes = to.getTxt_antecedentes();
        String beneficiario = to.getTxt_beneficiario();
        String domicilio = to.getTxt_calle() + " " + to.getTxt_colonia() +  " "  +to.getTxt_municipio() + " " + to.getTxt_cp();
        String arrendador = to.getTxt_arrendador();

        txtAntecendentes.setText(antecedentes);
        txtBeneficiario.setText(beneficiario);
        txtDomicilio.setText(domicilio);
        txtArrendador.setText(arrendador);

    }
}
