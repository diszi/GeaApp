package hu.ace.geaapp.ui.view.main_test.bottomnav.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hu.ace.geaapp.R;

public class MainTestViewHolder extends RecyclerView.ViewHolder {

    public TextView item_title;

    public MainTestViewHolder(@NonNull View itemView) {
        super(itemView);
        item_title = itemView.findViewById(R.id.textview_title);
    }
}
