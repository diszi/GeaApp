package hu.ace.geaapp.ui.view.inspection.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hu.ace.geaapp.R;

public class BottomNavigationViewHolder extends RecyclerView.ViewHolder {


    public RelativeLayout bottom_item_parent;
    public RelativeLayout bottom_parent;
    public ImageView bottom_icon;
    public TextView item_title;

    public BottomNavigationViewHolder(@NonNull View itemView) {
        super(itemView);

        bottom_item_parent = itemView.findViewById(R.id.bottom_item_parent);
        bottom_parent = itemView.findViewById(R.id.bottom_parent);
        bottom_icon = itemView.findViewById(R.id.bottom_icon);
        item_title = itemView.findViewById(R.id.item_title);
    }
}
