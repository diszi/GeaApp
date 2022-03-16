package hu.ace.geaapp.ui.view.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FilterReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.ace.geaapp.R;
import hu.ace.geaapp.data.model.Asset;
import hu.ace.geaapp.data.model.Car;
import hu.ace.geaapp.utils.UIConstans;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> implements Filterable {

    private SearchActivity view;
    private List<Asset> vehiclesList = new ArrayList<>();
    private ArrayList<Asset> vehiclesFullList = new ArrayList<>();

    private int mActivityFlag;

    public SearchAdapter(SearchActivity view, int activityFlag){
        this.view = view;
        this.mActivityFlag = activityFlag;
    }

    public void setVehiclesList(List<Asset> vehiclesList){
        this.vehiclesList.clear();
        this.vehiclesList.addAll(vehiclesList);

        this.vehiclesFullList.clear();
        this.vehiclesFullList.addAll(this.vehiclesList);

        this.notifyDataSetChanged();
    }



    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_search_inspection_row,parent,false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {
        /*Car carItem = this.carList.get(position);
        holder.bind(carItem);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(" ---> ONCLICK");
                view.launchCarDetailsActivity(carItem);
            }
        });*/
        Asset vehicle = this.vehiclesList.get(position);
        holder.bind(vehicle);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (mActivityFlag == UIConstans.ACTIVITY_LINE){
                //    view.launchVehicleLineDetailsActivity(vehicle);
                //}
               // if (mActivityFlag == UIConstans.ACTIVITY_SZEMLE){
                    view.launchVehicleDetailsActivity(vehicle);
               // }

            }
        });

    }

    @Override
    public int getItemCount() {
        if (vehiclesList != null && vehiclesList.size() > 0) {
            return vehiclesList.size();
        } else {
            return 0;
        }
    }

    @Override
    public Filter getFilter() {
        return vehicleFilter;
    }

    private Filter vehicleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Asset> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(vehiclesFullList);
            }else{
                String filteredPattern = constraint.toString().toUpperCase();
                for (Asset assetVehicle : vehiclesFullList){
                    if (assetVehicle.getAceAssetGJK().getLicensePlate().toUpperCase().contains(filteredPattern)){
                        filteredList.add(assetVehicle);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }



        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            vehiclesList.clear();
            vehiclesList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }

    };

    static class SearchViewHolder extends RecyclerView.ViewHolder{


        /*@BindView(R.id.textview_assetnum)
        TextView vehicle_assetnum;
        @BindView(R.id.textview_licenseplate)
        TextView vehicle_licenseplate;*/

        /*@BindView(R.id.vehicle_licenseplate_type)
        TextView licenseplate_type;
        @BindView(R.id.vehicle_assetnum)
        TextView assetnum;*/
        @BindView(R.id.vehicle_licensenr)
        TextView licenseplate;
        @BindView(R.id.vehicle_costcenter)
        TextView costcenter;
        @BindView(R.id.vehicle_type)
        TextView type;
        @BindView(R.id.vehicle_username)
        TextView username;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        /*public void bind(Car carItem) {
            carname.setText(carItem.getName());
            cartype.setText(carItem.getType());
        }*/

        public void bind(Asset asset){
            //System.out.println(" VEHICLE => "+asset.getAssetnum()+"; "+asset.getAceAssetGJK().getLicensePlate());
            /*vehicle_assetnum.setText(asset.getAssetnum());
            vehicle_licenseplate.setText(asset.getAceAssetGJK().getLicensePlate());*/

            /*licenseplate_type.setText(asset.getAceAssetGJK().getLicensePlate()+" - "+asset.getAceAssetGJK().getType());
            assetnum.setText("Asset number: "+asset.getAssetnum());*/
            licenseplate.setText(asset.getAceAssetGJK().getLicensePlate());
            type.setText(asset.getAceAssetGJK().getType());
            username.setText(asset.getAceAssetGJK().getUsername());
            costcenter.setText(asset.getAceAssetGJK().getCostCenter());
        }
    }
}
