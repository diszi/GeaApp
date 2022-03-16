package hu.ace.geaapp.ui.view.main_test.bottomnav.design;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hu.ace.geaapp.R;
import hu.ace.geaapp.ui.view.main_test.MainTestActivityBottomNav;
import hu.ace.geaapp.ui.view.main_test.bottomnav.BottomItemTestClickInterface;
import hu.ace.geaapp.ui.view.main_test.bottomnav.adapter.BottomTestAdapter;
import hu.ace.geaapp.ui.view.main_test.bottomnav.model.BottomItemTest;

public class BadgeBottomNavigationTest {


    private final  int ITEM_LIMIT = 4;
    private Context context;
    private RecyclerView recyclerView;
    private ArrayList<BottomItemTest> bottomItems;
    private BottomItemTestClickInterface bottomItemClickInterface;


    public BadgeBottomNavigationTest(View view, Context context, BottomItemTestClickInterface bottomItemClickInterface){
        this.context  = context;
        this.bottomItemClickInterface = bottomItemClickInterface;
        setType(view);
    }

    private void setType(View view){
        recyclerView = view.findViewById(R.id.bottomnav_recyclerview);
        bottomItems = new ArrayList<>();
    }

    public void addBottomItem(BottomItemTest item){
        if (bottomItems.size() != ITEM_LIMIT){
            bottomItems.add(item);
        }
    }

    private int calculateItemWidth(){
        int mCount = bottomItems.size() + 1;
        int mWidth = context.getResources().getDisplayMetrics().widthPixels;
        return mWidth/mCount;
    }

    private void setAdapter(int selected, String selectedColor, String unselectedColor,MainTestActivityBottomNav bottomactivity){
        BottomTestAdapter bottomAdapter = new BottomTestAdapter(selected,bottomItems,calculateItemWidth(),selectedColor,unselectedColor,bottomItemClickInterface,bottomactivity);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(bottomAdapter);
    }

    public void  apply(int selected, String selectedColor, String unselectedColor, MainTestActivityBottomNav bottomactivity){
        setAdapter(selected,selectedColor,unselectedColor,bottomactivity);
    }
}
