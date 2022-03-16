package hu.ace.geaapp.ui.view.line.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

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
import hu.ace.geaapp.ui.view.inspection.fragment.GeneralInfoFragment;
import hu.ace.geaapp.ui.view.line.fragment.LineAccessoriesFragment;
import hu.ace.geaapp.ui.view.line.fragment.LineDamageFragment;
import hu.ace.geaapp.ui.view.main.SearchActivity;

public class LineMainActivity extends AppCompatActivity {

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

    @BindView(R.id.frame_main_line)
    FrameLayout mainFramelayout;
    @BindView(R.id.bottomnav_main_line)
    BottomNavigationView mainBottomNav;

    @BindView(R.id.text_vehicleMainDetails)
    TextView title;
    @BindView(R.id.text_authenticatedUser)
    TextView authUser;


    private Asset vehicle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_line);//kereso felulet
        ButterKnife.bind(this);

        vehicle = (Asset) getIntent().getExtras().getSerializable(Asset.SERIALIZABLE_NAME);
        System.out.println(" VEHICLE = "+vehicle);

        title.setText(" Vehicle: "+vehicle.getAceAssetGJK().getLicensePlate());
        authUser.setText(" Átadó: "+ HolderSingleton.getInstance().getUserFrom());

        mainBottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        loadFragment(LineDamageFragment.newInstance(vehicle));

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_damages:
//                    System.out.println(" ACCESSORIES");
                    fragment = LineDamageFragment.newInstance(vehicle);
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_accesssories:
                    fragment = LineAccessoriesFragment.newInstance(vehicle);
                    loadFragment(fragment);
                    return true;


            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment){
        //System.out.println(" LOAD FRAGMENT");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main_line, fragment);
        transaction.addToBackStack(null);
        transaction.commit();


    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        System.out.println(" onBackPressed --> LineMainActivity");
        /*Intent intent = new Intent(this, SearchActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }



}
