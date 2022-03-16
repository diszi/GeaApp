package hu.ace.geaapp.ui.view.inspection.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.ace.geaapp.R;
import hu.ace.geaapp.data.model.damages.AceAssetDamage;
import hu.ace.geaapp.ui.view.Damage;
import hu.ace.geaapp.ui.view.inspection.dialog.RemoveDamageDialog;

public class RemoveDamageAdapter  extends RecyclerView.Adapter<RemoveDamageAdapter.RemovingDamageViewHolder>{

    private ArrayList<AceAssetDamage> assetDamageRemovingList = new ArrayList<>();
    private RemoveDamageDialog dialog;

    public RemoveDamageAdapter(ArrayList<AceAssetDamage> aceAssetDamageArrayList, RemoveDamageDialog mDialog){
        this.dialog = mDialog;
        this.assetDamageRemovingList.clear();
        this.assetDamageRemovingList.addAll(aceAssetDamageArrayList);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RemovingDamageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_remove_damage_row,parent,false);
        return new RemoveDamageAdapter.RemovingDamageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RemovingDamageViewHolder holder, int position) {
        AceAssetDamage aceAssetDamage = assetDamageRemovingList.get(position);
        holder.bind(aceAssetDamage,dialog);

    }

    @Override
    public int getItemCount() {
        if (assetDamageRemovingList != null && assetDamageRemovingList.size() > 0) {
            return assetDamageRemovingList.size();
        } else {
            return 0;
        }
    }

    static class RemovingDamageViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.value_damage_type)
        TextView damagetype;
        @BindView(R.id.value_damage_coordX)
        TextView coordX;
        @BindView(R.id.value_damage_coordY)
        TextView coordY;

        @BindView(R.id.removeDamageButton)
        Button removeBtn;

        @BindView(R.id.damageCardView)
        CardView mCardView;

        public RemovingDamageViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(AceAssetDamage aceAssetDamage,RemoveDamageDialog mDialog){
            //System.out.println("ADAPTER: ASSET damage => type="+aceAssetDamage.getStatus()+"; coord ["+aceAssetDamage.getCoordinateX()+"; "+aceAssetDamage.getCoordinateY()+"]");

            damagetype.setText(aceAssetDamage.getStatus());
            coordX.setText(aceAssetDamage.getCoordinateX());
            coordY.setText(aceAssetDamage.getCoordinateY());

            mCardView.setCardBackgroundColor(aceAssetDamage.getCustomView().getColor());

            removeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // System.out.println(" OnClick on REMOVE btn");
                    mDialog.selectedDamage(aceAssetDamage);

                }
            });


        }
    }
}
