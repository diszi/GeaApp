package hu.ace.geaapp.ui.view.inspection.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import hu.ace.geaapp.R;
import hu.ace.geaapp.data.model.Asset;
import hu.ace.geaapp.data.model.DamageTemp;
import hu.ace.geaapp.data.model.damages.AceAssetDamage;
import hu.ace.geaapp.ui.view.Damage;
import hu.ace.geaapp.ui.view.inspection.fragment.DamageFragment;
import hu.ace.geaapp.ui.view.inspection.presenter.InspectionPresenter;
import hu.ace.geaapp.ui.view.main.MainPresenter;

public  class DamageDialog extends DialogFragment implements View.OnClickListener {

    //private int nNum;
    private Button buttonSave, buttonCancel;
    private EditText textDescription;

    private float coordX, coordY;
    private Damage selectedType;
    private int selectedColor;

    private DamageFragment fragment;
    //private InspectionPresenter presenter;
    private MainPresenter mainPresenter;

    private DamageTemp damageObject = null;
    private Asset vehicleAsset;

    private AceAssetDamage damage = null;

    public static DamageDialog newInstance(Asset vehicleAsset,AceAssetDamage damage){
        DamageDialog damageDialog = new DamageDialog();
        Bundle args = new Bundle();
        args.putFloat("COORD_X",damage.getDamageCoordinate().getCoordinateX());
        args.putFloat("COORD_Y",damage.getDamageCoordinate().getCoordinateY());
        args.putSerializable("DAMAGE_TYPE",damage.getDamageEnum());
        args.putInt("CIRCLE_COLOR",damage.getCustomView().getColor());
        args.putSerializable(Asset.SERIALIZABLE_NAME,vehicleAsset);
        args.putSerializable(AceAssetDamage.SERIALIZABLE_NAME,damage);
        damageDialog.setArguments(args);

        return damageDialog;
    }


    /*public static DamageDialog newInstance(float coordinateX, float coordinateY, Damage damageType, int circleColor, Asset vehicleAsset, AceAssetDamage damage){
        DamageDialog damageDialog = new DamageDialog();
        Bundle args = new Bundle();
        //args.putInt("num", num);
        args.putFloat("COORD_X",coordinateX);
        args.putFloat("COORD_Y",coordinateY);
        //args.putBundle("DAMAGE_TYPE",damageType);
        //args.putString("DAMAGE_TYPE",damageType.getDamageType());
        args.putSerializable("DAMAGE_TYPE",damageType);
        args.putInt("CIRCLE_COLOR",circleColor);
        args.putSerializable(Asset.SERIALIZABLE_NAME,vehicleAsset);
        args.putSerializable(AceAssetDamage.SERIALIZABLE_NAME,damage);
        damageDialog.setArguments(args);

        return damageDialog;

    }*/


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            coordX = getArguments().getFloat("COORD_X");
            coordY = getArguments().getFloat("COORD_Y");
            fragment = (DamageFragment) getTargetFragment();
            selectedColor = getArguments().getInt("CIRCLE_COLOR");
            selectedType = (Damage) getArguments().getSerializable("DAMAGE_TYPE");
            vehicleAsset = (Asset) getArguments().getSerializable(Asset.SERIALIZABLE_NAME);
            damage = (AceAssetDamage) getArguments().getSerializable(AceAssetDamage.SERIALIZABLE_NAME);
         //   System.out.println(" Add damage: [x="+coordX+"; y="+coordY+"; color="+selectedColor+"; type="+selectedType+"; assetnum="+vehicleAsset.getAssetnum()+"]");
        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_damage, container, false);

        //presenter = new InspectionPresenter();
        mainPresenter = new MainPresenter();

        buttonCancel = view.findViewById(R.id.button_cancel);
        buttonSave = view.findViewById(R.id.button_save);
        textDescription = view.findViewById(R.id.dialog_edittext);

        buttonSave.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);

        return view;


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_cancel:
                cancelTransaction();
                break;
            case R.id.button_save:
                saveTransaction();
                break;
        }
    }

    private void cancelTransaction(){
//        fragment.removeDamageCircle();
        fragment.removeSelectedDamage(damage);
        fragment.continueDrawing(selectedType,selectedColor);
        this.dismiss();
    }

    private void saveTransaction(){
        //System.out.println(" SAVE damage on ["+coordX+"; "+coordY+"] ==> type = "+selectedType.getDamageType());
        //damageObject = new DamageTemp(coordX,coordY,selectedType,textDescription.getText().toString(),selectedColor);

        damage.setDescription(textDescription.getText().toString());

        //System.out.println(getParentFragment()+"; "+getParentFragmentManager()+"; "+(DamageFragment)getParentFragment());
        //((DamageFragment) getParentFragment()).initDamageView();
        //fragment.initDamageView(selectedColor,selectedType);

        mainPresenter.addDamage(damage,vehicleAsset, fragment,this);


        //fragment.continueDrawing(selectedType,selectedColor);
        //this.dismiss();

    }
}
