package hu.ace.geaapp.ui.view.inspection.dialog;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.ace.geaapp.R;
import hu.ace.geaapp.data.model.damages.AceAssetDamage;
import hu.ace.geaapp.singleton.HolderSingleton;
import hu.ace.geaapp.ui.base.RemoteCallBack;
import hu.ace.geaapp.ui.view.inspection.adapter.RemoveDamageAdapter;
import hu.ace.geaapp.ui.view.inspection.fragment.DamageFragment;
import hu.ace.geaapp.ui.view.inspection.presenter.InspectionPresenter;
import hu.ace.geaapp.ui.view.main.MainPresenter;

public class RemoveDamageDialog extends DialogFragment{

    /*@BindView(R.id.nr_damage)
    TextView damageNR;*/

    @BindView(R.id.act_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.button_no)
    Button buttonNO;
    /*@BindView(R.id.button_yes)
    Button buttonYES;*/

    private ArrayList<AceAssetDamage> damageList = new ArrayList<>();
    private RemoveDamageAdapter adapter;
    private DamageFragment mFragment;

    //private InspectionPresenter presenter;
    private MainPresenter mainPresenter;

    public static RemoveDamageDialog newInstance(ArrayList<AceAssetDamage> damageList){
        RemoveDamageDialog damageDialog = new RemoveDamageDialog();
        Bundle args = new Bundle();
        args.putSerializable(AceAssetDamage.SERIALIZABLE_NAME, damageList);
        damageDialog.setArguments(args);

        return damageDialog;

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("------------->","Remove Dialog");
        if (getArguments() != null){
            damageList = (ArrayList<AceAssetDamage>) getArguments().getSerializable(AceAssetDamage.SERIALIZABLE_NAME);
            mFragment = (DamageFragment) getTargetFragment();
        }
        //System.out.println(" selected DAMAGE CUSTOMVIEW = "+damageList.get(0).getCustomView());
     /*   System.out.println(" REMOVE DAMAGE type="+damageList.get(0).getStatus()+" - x="+damageList.get(0).getCoordinateX()+" - y="
                +damageList.get(0).getCoordinateY()+"; custom view = "+damageList.get(0).getCustomView());*/
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.dialog_remove_damage, container, false);
        ButterKnife.bind(this,view);

        this.setupRecyclerView();

       // presenter = new InspectionPresenter();
        mainPresenter = new MainPresenter();

       // System.out.println("DAMAGE list size in distance 5 unit ==> "+damageList.size());

        return view;
    }

   /* @Override
    public void onResume() {
        System.out.println(" DIALOG -> onResume()");
        // Sets the height and the width of the DialogFragment
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setLayout(width, height);
        System.out.println(" W="+width+"; H="+height);
        super.onResume();
    }*/


    private void setupRecyclerView() {
        Context context = getActivity().getApplicationContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        this.adapter = new RemoveDamageAdapter(damageList,this);
        this.mRecyclerView.setLayoutManager(layoutManager);
        this.mRecyclerView.setAdapter(this.adapter);

    }

    public void selectedDamage(AceAssetDamage damage){
        ///System.out.println(" REMOVE damage "+damage.getStatus().toUpperCase()+" from page;");

        mainPresenter.deleteDamage(damage,mFragment, new RemoteCallBack<Boolean>() {
            @Override
            public void onSucces(Boolean success) {
             //   System.out.println(" RESULT of delete damage = "+success);
                /*if (success) {
                    mFragment.removeSelectedDamage(damage);
                }*/
            }
        });

        this.dismiss();
        //mFragment.removeCircle();
        //this.dismiss();
    }


    @OnClick(R.id.button_no)
    public void onClickOnNObutton(){
        this.dismiss();
    }



}
