package hu.ace.geaapp.ui.view.inspection.design;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hu.ace.geaapp.R;
import hu.ace.geaapp.ui.view.inspection.BottomItemClickInterface;
import hu.ace.geaapp.ui.view.inspection.activity.InspectionMainActivity;
import hu.ace.geaapp.ui.view.inspection.adapter.BottomAdapter;
import hu.ace.geaapp.ui.view.inspection.model.BottomItem;

public class BadgeBottomNavigation {

    private final  int ITEM_LIMIT = 4;
    private Context context;
    private RecyclerView recyclerView;
    private ArrayList<BottomItem> bottomItems;
    private BottomItemClickInterface bottomItemClickInterface;


    public BadgeBottomNavigation(View view, Context context, BottomItemClickInterface bottomItemClickInterface){
        this.context  = context;
        this.bottomItemClickInterface = bottomItemClickInterface;
        setType(view);
    }

    private void setType(View view){
        recyclerView = view.findViewById(R.id.bottomnav_recyclerview);
        bottomItems = new ArrayList<>();
    }

    public void addBottomItem(BottomItem item){
        if (bottomItems.size() != ITEM_LIMIT){
            bottomItems.add(item);
        }
    }

    private int calculateItemWidth(){
        int mCount = bottomItems.size() + 1;
        int mWidth = context.getResources().getDisplayMetrics().widthPixels;
        return mWidth/mCount;
    }

    private void setAdapter(int selected, String selectedColor, String unselectedColor, InspectionMainActivity inspectionMainActivity){
        BottomAdapter bottomAdapter = new BottomAdapter(selected,bottomItems,calculateItemWidth(),selectedColor,unselectedColor,bottomItemClickInterface,inspectionMainActivity);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(bottomAdapter);
    }

    public void  apply(int selected, String selectedColor, String unselectedColor, InspectionMainActivity inspectionMainActivity){
        setAdapter(selected,selectedColor,unselectedColor,inspectionMainActivity);
    }
}
