package mx.com.dgom.hm.ovhaul;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import mx.com.dgom.hm.ovhaul.adapter.EstatusAdapter;
import mx.com.dgom.hm.ovhaul.to.EntEstatusTO;
import mx.com.dgom.hm.ovhaul.to.EstatusTO;

public class EstatusLocalidadFragment extends Fragment {
    private ListView listEstatus;
    private ArrayList<EntEstatusTO> estatusArray;
    private EstatusAdapter adapter;
    private Intent intent;

    public EstatusLocalidadFragment(){
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_estatus_localidad_fragment, container, false);

        listEstatus = view.findViewById(R.id.listEstatus);

        estatusArray = new ArrayList<>();
        intent= getActivity().getIntent();

        adapter = new EstatusAdapter(getContext(), estatusArray);
        listEstatus.setAdapter(adapter);


        setUpEstatus();
        return view;
    }

    public static EstatusLocalidadFragment newInstance(ArrayList<EntEstatusTO> estatusArray) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("ESTATUS", estatusArray);

        EstatusLocalidadFragment fragment = new EstatusLocalidadFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    private void setUpEstatus(){
        ArrayList<EntEstatusTO> estatusTOS= (ArrayList<EntEstatusTO>)getArguments().getSerializable("ESTATUS");
        adapter.setDatos(estatusTOS);

    }
}
