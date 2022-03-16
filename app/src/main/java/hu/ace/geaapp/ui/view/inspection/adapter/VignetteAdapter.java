package hu.ace.geaapp.ui.view.inspection.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.ace.geaapp.R;
import hu.ace.geaapp.data.model.AceAssetVignette;
import hu.ace.geaapp.data.model.Asset;
import hu.ace.geaapp.ui.view.inspection.fragment.VignetteFragment;
import hu.ace.geaapp.utils.EnvironmentTool;

public class VignetteAdapter extends RecyclerView.Adapter<VignetteAdapter.VignetteViewHolder>{

    private VignetteFragment mFragment;
    private List<AceAssetVignette> vignetteList = new ArrayList<>();

    public VignetteAdapter(VignetteFragment viewFragment){
        this.mFragment = viewFragment;
    }

    public void setVignetteList(List<AceAssetVignette> list){

        this.vignetteList.clear();
        //this.vignetteList.addAll(sortVignetteListByDate(list));
        this.vignetteList.addAll(list);
        /*for(AceAssetVignette v: this.vignetteList){
            System.out.println(" VIGNETTE = "+v.getExpirationDate()+"; year="+v.getYear());
        }*/

        Collections.sort(this.vignetteList, (o1, o2) -> o2.getExpirationDate().compareTo(o1.getExpirationDate()));
       /* for(AceAssetVignette v: this.vignetteList){
            System.out.println(" VIGNETTE = "+v.getExpirationDate()+"; year="+v.getYear());
        }*/


        this.notifyDataSetChanged();
    }



    @SuppressLint("NewApi")
    private List<AceAssetVignette> sortVignetteListByDate(List<AceAssetVignette> vignetteList){
         Comparator<AceAssetVignette> compareBy = Comparator.comparing(AceAssetVignette::getYear);
//                .thenComparing(InvuseLinePicking::getFromStorelocation)
//                .thenComparing(InvuseLinePicking::getFromBin)
//                .thenComparing(InvuseLinePicking::getPartnum)
//                .thenComparing(InvuseLinePicking::getShelflife);

        return vignetteList.stream().sorted(compareBy).collect(Collectors.toList());
    }


    @NonNull
    @Override
    public VignetteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_vignette_items,parent,false);
        return new VignetteAdapter.VignetteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VignetteViewHolder holder, int position) {
        AceAssetVignette vignette = vignetteList.get(position);
        holder.bind(vignette,mFragment);
    }

    @Override
    public int getItemCount() {
        if (vignetteList != null && vignetteList.size() > 0) {
            return vignetteList.size();
        } else {
            return 0;
        }
    }

    static class VignetteViewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.value_vignette_type)
        TextView vignette_type;
        @BindView(R.id.value_vignette_price)
        TextView vignette_price;
        @BindView(R.id.value_vignette_year)
        TextView vignette_year;
        @BindView(R.id.value_expiration_date)
        TextView vignette_expiration_date;

        @BindView(R.id.vignetteCardView)
        CardView cardView;

        public VignetteViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bind(AceAssetVignette vignette,VignetteFragment mFragment){
            vignette_expiration_date.setText(EnvironmentTool.convertDateTimeStringHU(vignette.getExpirationDate()));
            vignette_price.setText(vignette.getPrice());
            vignette_year.setText(vignette.getYear());
            vignette_type.setText(vignette.getType());

            if (vignette.isStatus()){//true if expired
               // cardView.setCardBackgroundColor(Color.RED);
                int color_red = R.color.color_error_red;
                cardView.setCardBackgroundColor(mFragment.getResources().getColor(color_red));
            }else{
                //cardView.setCardBackgroundColor(Color.GREEN);
                int color_green = R.color.color_ok_green;
                cardView.setCardBackgroundColor(mFragment.getResources().getColor(color_green));
            }

        }


    }

}
