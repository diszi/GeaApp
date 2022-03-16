package hu.ace.geaapp.ui.view.main_test.bottomnav.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hu.ace.geaapp.R;
import hu.ace.geaapp.ui.view.login.LoginActivity;
import hu.ace.geaapp.ui.view.main_test.MainTestActivityBottomNav;
import hu.ace.geaapp.ui.view.main_test.bottomnav.model.BottomItemTest;
import hu.ace.geaapp.ui.view.main_test.bottomnav.BottomItemTestClickInterface;
import hu.ace.geaapp.ui.view.main_test.bottomnav.viewholder.BottomNavigationTestViewHolder;

public class BottomTestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<BottomItemTest> bottomItems;
    private int itemWidth;
    private int selected;
    private String selectedColor;
    private String unselectedColor;
    private BottomItemTestClickInterface bottomItemClickInterface;
    private MainTestActivityBottomNav view;

    public BottomTestAdapter(int selected, ArrayList<BottomItemTest> bottomItemList, int itemWidth, String selectedColor, String unselectedColor,
                             BottomItemTestClickInterface bottomItemClickInterface, MainTestActivityBottomNav view){
        this.selected = selected;
        this.bottomItems = bottomItemList;
        this.itemWidth = itemWidth;
        this.selectedColor = selectedColor;
        this.unselectedColor = unselectedColor;
        this.bottomItemClickInterface = bottomItemClickInterface;
        this.view = view;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_navigation_test_item,parent,false);
        return new BottomNavigationTestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        resizeItemWidth(((BottomNavigationTestViewHolder) holder).bottom_parent);
        setTitle(((BottomNavigationTestViewHolder) holder).item_title,bottomItems.get(position).getItemName());
        selectedStyle(((BottomNavigationTestViewHolder) holder).bottom_icon, ((BottomNavigationTestViewHolder) holder).item_title, bottomItems.get(position).getItemId());
        setIcon(((BottomNavigationTestViewHolder) holder).bottom_icon, bottomItems.get(position).getItemIconId());

        setOnClickItem(((BottomNavigationTestViewHolder) holder).bottom_item_parent, ((BottomNavigationTestViewHolder) holder).bottom_icon, ((BottomNavigationTestViewHolder) holder).item_title,
                bottomItems.get(position).getItemId(),position);
    }

    @Override
    public int getItemCount() {
        return bottomItems.size();
    }

    private void resizeItemWidth(RelativeLayout parent){
        parent.getLayoutParams().width = itemWidth;
    }

    private void setIcon(ImageView imageView, int iconId){
        imageView.setImageResource(iconId);
    }

    private void selectedStyle(ImageView imageView, TextView textView,int itemId){
        System.out.println(" Selected Style : itemId="+itemId+"; selectedItem="+selected);
        if (itemId == selected){
            imageView.setColorFilter(Color.parseColor(selectedColor), PorterDuff.Mode.SRC_IN);
            textView.setTextColor(Color.parseColor(selectedColor));
        }else{
            imageView.setColorFilter(Color.parseColor(unselectedColor), PorterDuff.Mode.SRC_IN);
            textView.setTextColor(Color.parseColor(unselectedColor));
        }
    }

    private void setOnClickItem(RelativeLayout parent, final ImageView imageView, final TextView textView,final  int itemId,final  int position){
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomItemClickInterface.itemSelect(itemId);
                selected = itemId;
//                bottomItems.get(position).setHasNotification(false);
                selectedStyle(imageView,textView,itemId);
                notifyDataSetChanged();
                openActivity(itemId);
            }
        });
    }
    private void openActivity(int itemId){
        if (itemId == 1){
            Intent intent = new Intent(view, LoginActivity.class);
            view.startActivity(intent);

        }
    }

    private void setTitle(TextView textView,String text){
        textView.setText(text);
    }




}
