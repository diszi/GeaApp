package hu.ace.geaapp.ui.view.inspection.adapter;

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
import hu.ace.geaapp.ui.view.inspection.BottomItemClickInterface;
import hu.ace.geaapp.ui.view.inspection.activity.InspectionMainActivity;
import hu.ace.geaapp.ui.view.inspection.model.BottomItem;
import hu.ace.geaapp.ui.view.inspection.viewholder.BottomNavigationViewHolder;
import hu.ace.geaapp.ui.view.login.LoginActivity;


public class BottomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<BottomItem> bottomItems;
    private int itemWidth;
    private int selected;
    private String selectedColor;
    private String unselectedColor;
    private BottomItemClickInterface bottomItemClickInterface;
    private InspectionMainActivity inspectionView;

    public BottomAdapter(int selected, ArrayList<BottomItem> bottomItemList, int itemWidth, String selectedColor, String unselectedColor,
                         BottomItemClickInterface bottomItemClickInterface, InspectionMainActivity view){
        this.selected = selected;
        this.bottomItems = bottomItemList;
        this.itemWidth = itemWidth;
        this.selectedColor = selectedColor;
        this.unselectedColor = unselectedColor;
        this.bottomItemClickInterface = bottomItemClickInterface;
        this.inspectionView = view;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_navigation_test_item,parent,false);
        return new BottomNavigationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        resizeItemWidth(((BottomNavigationViewHolder) holder).bottom_parent);
        setTitle(((BottomNavigationViewHolder) holder).item_title,bottomItems.get(position).getItemName());
        selectedStyle(((BottomNavigationViewHolder) holder).bottom_icon, ((BottomNavigationViewHolder) holder).item_title, bottomItems.get(position).getItemId());
        setIcon(((BottomNavigationViewHolder) holder).bottom_icon, bottomItems.get(position).getItemIconId());

        setOnClickItem(((BottomNavigationViewHolder) holder).bottom_item_parent, ((BottomNavigationViewHolder) holder).bottom_icon, ((BottomNavigationViewHolder) holder).item_title,
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

    private void selectedStyle(ImageView imageView, TextView textView, int itemId){
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
                //openActivity(itemId);
            }
        });
    }
    private void openActivity(int itemId){
        if (itemId == 1){
            Intent intent = new Intent(inspectionView, LoginActivity.class);
            inspectionView.startActivity(intent);

        }
    }

    private void setTitle(TextView textView,String text){
        textView.setText(text);
    }


}
