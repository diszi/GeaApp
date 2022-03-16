package hu.ace.geaapp.ui.view.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.ace.geaapp.R;
import hu.ace.geaapp.data.model.Asset;
import hu.ace.geaapp.singleton.HolderSingleton;
import hu.ace.geaapp.ui.view.inspection.fragment.AccessoriesFragment;
import hu.ace.geaapp.ui.view.inspection.fragment.DamageFragment;
import hu.ace.geaapp.ui.view.inspection.fragment.GeneralInfoFragment;
import hu.ace.geaapp.ui.view.inspection.fragment.StructFeaturesFragment;
import hu.ace.geaapp.ui.view.inspection.fragment.VignetteFragment;
import hu.ace.geaapp.ui.view.main.SearchActivity;

public class InspectionMainActivity extends AppCompatActivity {

   /* @BindView(R.id.recyclerview_inspection_main)
    RecyclerView recyclerViewInspectionMain;
    @BindView(R.id.textview_title)
    TextView title;
    private InspectionMainAdapter adapter;
    private BadgeBottomNavigation badgeBottomNavigation;
    private final int SEARCH = 0;
    private final int DETAILS = 1;
    private final int DAMAGE = 2; //sérülés

    private int selectedId = 0;*/


    //private ActionBar toolBar;

    @BindView(R.id.frame_main_inspection)
    FrameLayout mainFramelayout;
    @BindView(R.id.bottomnav_main_insp)
    BottomNavigationView mainBottomNav;


    private Asset vehicle = null;
    private int activityFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_inspection);//kereso felulet
        ButterKnife.bind(this);

       // toolBar = getSupportActionBar();
       /* BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bottomnav_main_insp);
        navigationView.setOnNavigationItemReselectedListener((BottomNavigationView.OnNavigationItemReselectedListener) mOnNavigationItemSelectedListener);*/

        /*BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomnav_main_insp);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);*/

        vehicle = (Asset) getIntent().getExtras().getSerializable(Asset.SERIALIZABLE_NAME);
       // activityFlag = (int) getIntent().getSerializableExtra("ACTIVITY_FLAG");

        HolderSingleton.getInstance().setVehicleAsset(vehicle);


        mainBottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //loadFragment(new GeneralInfoFragment());
        loadFragment(GeneralInfoFragment.newInstance());

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {

                case R.id.navigation_details:
                    //fragment = new GeneralInfoFragment(vehicle);
                   // System.out.println(" NAV_DETAILS");
//                    fragment = GeneralInfoFragment.newInstance(vehicle);
                    fragment = GeneralInfoFragment.newInstance();
                    loadFragment(fragment);
                    //toolBar.setTitle("Details");
                    /*fragment = new GiftsFragment();
                    loadFragment(fragment);*/
                    return true;
                case R.id.navigation_damage:
                   // fragment = new DamageFragment();
                    fragment = DamageFragment.newInstance();
                    loadFragment(fragment);
                    //toolBar.setTitle("Damage");
                   /* fragment = new CartFragment();
                    loadFragment(fragment);*/
                    return true;
                case R.id.navigation_accesssories:
                    fragment = AccessoriesFragment.newInstance();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_spec:
                    fragment = StructFeaturesFragment.newInstance();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_vignette:
                    fragment = VignetteFragment.newInstance();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment){
        // load fragment
        //System.out.println(" LOAD FRAGMENT");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main_inspection, fragment);
        transaction.addToBackStack(null);
        transaction.commit();


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SearchActivity.class);
       // intent.putExtra("ACTIVITY_FLAG",activityFlag);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        //super.onBackPressed();
        /*System.out.println(" onBackPressed --> InspectionMainActivity");
        Intent intent = new Intent(this, SearchActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }

    /*  private void setupRecyclerView(){
        Context context = getApplicationContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        this.adapter = new InspectionMainAdapter(this);
        this.recyclerViewInspectionMain.setLayoutManager(layoutManager);
        this.recyclerViewInspectionMain.setAdapter(this.adapter);
    }*/

    /*private void initBottomItems(){

        BottomItem searchItem= new BottomItem(SEARCH,R.drawable.ic_baseline_search_24,"Search",false);
        BottomItem detailsItem= new BottomItem(DETAILS,R.drawable.ic_baseline_car_24,"Details",false);
        BottomItem damageItem= new BottomItem(DAMAGE,R.drawable.ic_baseline_car_repair_24,"Damage",false);

        badgeBottomNavigation.addBottomItem(searchItem);
        badgeBottomNavigation.addBottomItem(detailsItem);
        badgeBottomNavigation.addBottomItem(damageItem);

        badgeBottomNavigation.apply(selectedId,getString(R.color.colorPrimary),getString(R.color.maximoLightBlue),this);
        itemSelect(selectedId);

    }*/


   /* @Override
    public void itemSelect(int itemId) {
        Toast.makeText(InspectionMainActivity.this, "Selected id: " + itemId, Toast.LENGTH_LONG).show();

        Intent intent = null;

        if (itemId == 0){
            System.out.println(" SEARCH - main");
            intent = new Intent(this,InspectionMainActivity.class);
        }
        if (itemId == 1){
            System.out.println(" LOAD details view");
            intent = new Intent(this,MainDetailsActivity.class);

        }

        if (itemId == 2){
            System.out.println(" LOAD serulesek oldalt");
        }

        startActivity(intent);
    }*/


}
