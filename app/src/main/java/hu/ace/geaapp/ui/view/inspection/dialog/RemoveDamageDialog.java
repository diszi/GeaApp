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

import hu.ace.geaapp.ui.view.main.MainPresenter;

public class RemoveDamageDialog extends DialogFragment{

    @BindView(R.id.act_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.button_no)
    Button buttonNO;

    private ArrayList<AceAssetDamage> damageList = new ArrayList<>();
    private RemoveDamageAdapter adapter;
    private DamageFragment mFragment;

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
        Log.d("------------------>","RemoveDamageDialog: onCreate()");
        if (getArguments() != null){
            damageList = (ArrayList<AceAssetDamage>) getArguments().getSerializable(AceAssetDamage.SERIALIZABLE_NAME);
            mFragment = (DamageFragment) getTargetFragment();
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.dialog_remove_damage, container, false);
        ButterKnife.bind(this,view);

        this.setupRecyclerView();

        mainPresenter = new MainPresenter();

        return view;
    }



    private void setupRecyclerView() {
        Context context = getActivity().getApplicationContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        this.adapter = new RemoveDamageAdapter(damageList,this);
        this.mRecyclerView.setLayoutManager(layoutManager);
        this.mRecyclerView.setAdapter(this.adapter);

    }

    public void selectedDamage(AceAssetDamage damage){

        mainPresenter.deleteDamage(damage,mFragment, new RemoteCallBack<>() {
            @Override
            public void onSucces(Boolean success) {

            }
        });

        this.dismiss();

    }


    @OnClick(R.id.button_no)
    public void onClickOnNObutton(){
        this.dismiss();
    }


}
