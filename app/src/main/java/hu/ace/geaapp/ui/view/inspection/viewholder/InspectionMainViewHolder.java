package hu.ace.geaapp.ui.view.inspection.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import hu.ace.geaapp.R;

public class InspectionMainViewHolder extends RecyclerView.ViewHolder {

    public TextView item_title;

    /*@BindView(R.id.textview_car_title)
    TextView carTitle;*/

    public InspectionMainViewHolder(@NonNull View itemView) {
        super(itemView);
        item_title = itemView.findViewById(R.id.textview_car_title);
    }
}
