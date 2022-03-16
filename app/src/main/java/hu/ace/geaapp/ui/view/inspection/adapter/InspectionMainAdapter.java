package hu.ace.geaapp.ui.view.inspection.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.ace.geaapp.R;
import hu.ace.geaapp.ui.view.inspection.activity.InspectionMainActivity;
import hu.ace.geaapp.ui.view.inspection.viewholder.InspectionMainViewHolder;

public class InspectionMainAdapter  extends RecyclerView.Adapter<InspectionMainViewHolder>{


    private InspectionMainActivity view;
    private List<String> carList = new ArrayList<>();

    public InspectionMainAdapter(InspectionMainActivity view){
        this.view = view;
        populateList();
    }

    private void populateList(){
        carList.add("CAR1");
        carList.add("CAR2");
        carList.add("CAR3");
    }

    @NonNull
    @Override
    public InspectionMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_inspection_items,parent,false);
        return new InspectionMainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InspectionMainViewHolder holder, int position) {
        String carItem = this.carList.get(position);
        setCarName(((InspectionMainViewHolder) holder).item_title,carItem);
        setOnClickItem(((InspectionMainViewHolder) holder).item_title,carItem,position);
       /* holder.bind(invBalanceItem);
        holder.itemView.setOnClickListener(v -> view.setSelectedInvBalanceItem(invBalanceItem));*/
    }


    private void setOnClickItem(TextView text,String carItem,  final  int position){
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(" Click on car "+carItem+"; pos="+position+" ===> launch details view");
                openDetailsView(carItem);
            }
        });
    }

    private void openDetailsView(String carItem){
        /*Intent intent = new Intent(view, MainDetailsActivity.class);
        intent.putExtra("CAR",carItem);
        view.startActivity(intent);*/
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    private void setCarName(TextView textView,String carName){
        textView.setText(carName);
    }
}
