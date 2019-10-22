package mx.com.dgom.hm.ovhaul;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mx.com.dgom.hm.ovhaul.adapter.PageAdapter;

public class MainActivity extends App2GomActivity {
    private PageAdapter pageAdapter;
    private ViewPager container;
    private TabLayout tabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.container);
        tabs = findViewById(R.id.tabLayout);

        pageAdapter = new PageAdapter(getSupportFragmentManager());
        setupViewPager(container);

        tabs.setupWithViewPager(container);
        setupTabIcons();
    }

    private void setupViewPager(ViewPager pager){
        pageAdapter.addFragment(new DashboardActivity(), "");
        pageAdapter.addFragment(new LocalidadesActivity(), "");
        pageAdapter.addFragment(new TareasPendientesActivity(), "");
        pageAdapter.addFragment(new NotificacionesActivity(), "");

        pager.setAdapter(pageAdapter);
    }

    private void setupTabIcons() {
        tabs.getTabAt(0).setIcon(R.drawable.ico_sort_3x);
        tabs.getTabAt(1).setIcon(R.drawable.ico_locations_3x);
        tabs.getTabAt(2).setIcon(R.drawable.ico_edit_3x);
        tabs.getTabAt(3).setIcon(R.drawable.ico_bell_3x);
    }

    public void exitAction(View view){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
