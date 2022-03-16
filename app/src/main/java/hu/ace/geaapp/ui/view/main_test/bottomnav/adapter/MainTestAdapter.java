package hu.ace.geaapp.ui.view.main_test.bottomnav.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.ace.geaapp.R;
import hu.ace.geaapp.data.model.Asset;
import hu.ace.geaapp.ui.view.main_test.MainTestActivityBottomNav;
import hu.ace.geaapp.ui.view.main_test.bottomnav.viewholder.MainTestViewHolder;

public class MainTestAdapter extends RecyclerView.Adapter<MainTestViewHolder>{

    private MainTestActivityBottomNav view;
    private List<Asset> assetList = new ArrayList<>();

    public MainTestAdapter(MainTestActivityBottomNav view){
        this.view = view;
    }

    public void setVehicleList(List<Asset> assetList){
        this.assetList.clear();
        this.assetList = assetList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainTestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_test_recyclerview_row,parent,false);
        return new MainTestViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainTestViewHolder holder, int position) {
        setTitle(((MainTestViewHolder) holder).item_title,assetList.get(position).getAssetnum());
    }

    private void setTitle(TextView textView, String text){
        textView.setText(text);
    }

    @Override
    public int getItemCount() {
        if (assetList != null && assetList.size() > 0) {
            return assetList.size();
        } else {
            return 0;
        }
    }


}
