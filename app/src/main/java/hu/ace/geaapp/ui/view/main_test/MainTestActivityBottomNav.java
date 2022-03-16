package hu.ace.geaapp.ui.view.main_test;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import hu.ace.geaapp.R;
import hu.ace.geaapp.ui.view.main_test.bottomnav.BottomItemTestClickInterface;
import hu.ace.geaapp.ui.view.main_test.bottomnav.adapter.MainTestAdapter;
import hu.ace.geaapp.ui.view.main_test.bottomnav.design.BadgeBottomNavigationTest;
import hu.ace.geaapp.ui.view.main_test.bottomnav.model.BottomItemTest;

public class MainTestActivityBottomNav extends AppCompatActivity implements BottomItemTestClickInterface {

    private BadgeBottomNavigationTest badgeBottomNavigation;
    private RecyclerView mainRecyclerView;

    private final int HOME = 0;
    private final  int NOTIFICATIONS = 1;
    private final  int FRIENDS = 2;
    private final  int SETTINGS = 3;

    private int selectedId = 0;

    private MainTestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test_bottmnav);
        badgeBottomNavigation = new BadgeBottomNavigationTest(findViewById(R.id.bottomNavigation), MainTestActivityBottomNav.this, MainTestActivityBottomNav.this);
        mainRecyclerView = findViewById(R.id.main_recyclerview);
        this.setupRecyclerView();
        initBottomItems();
    }

    private void setupRecyclerView(){
        Context context = getApplicationContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        this.adapter = new MainTestAdapter(this);
        this.mainRecyclerView.setLayoutManager(layoutManager);
        this.mainRecyclerView.setAdapter(this.adapter);
    }

    private void initBottomItems(){
//        BottomItem homeItem = new BottomItem(HOME,R.drawable.ic_baseline_home_24,"Home",false);
//
//        BottomItem notificationItem = new BottomItem(NOTIFICATIONS,R.drawable.ic_baseline_notifications_24,"Notification",true);
//        BottomItem friendsItem = new BottomItem(FRIENDS,R.drawable.ic_baseline_people_24,"Friends",false);
//        BottomItem settingsItem = new BottomItem(SETTINGS,R.drawable.ic_baseline_settings_24,"Settings",false);
//
//        badgeBottomNavigation.addBottomItem(homeItem);
//        badgeBottomNavigation.addBottomItem(notificationItem);
//        badgeBottomNavigation.addBottomItem(friendsItem);
//        badgeBottomNavigation.addBottomItem(settingsItem);

        BottomItemTest homeItem = new BottomItemTest(HOME,R.drawable.ic_baseline_home_24,"Home",false);

        BottomItemTest secondItem = new BottomItemTest(NOTIFICATIONS,R.drawable.ic_baseline_compare_arrows_24,"Átadás/Átvétel",true);
//        BottomItem newRequestItem = new BottomItem(FRIENDS,R.drawable.ic_baseline_note_add_24,"New Request",false);
//        BottomItem ocupationsItem = new BottomItem(SETTINGS,R.drawable.ic_baseline_car_24,"Foglaltságok",false);

        badgeBottomNavigation.addBottomItem(homeItem);
        badgeBottomNavigation.addBottomItem(secondItem);
//        badgeBottomNavigation.addBottomItem(newRequestItem);
//        badgeBottomNavigation.addBottomItem(ocupationsItem);

        badgeBottomNavigation.apply(selectedId,getString(R.color.colorPrimary),getString(R.color.maximoLightBlue),this);
        itemSelect(selectedId);
    }

    @Override
    public void itemSelect(int itemId) {
        Toast.makeText(MainTestActivityBottomNav.this, "Selected id: " + itemId, Toast.LENGTH_LONG).show();
    }
}
