package hu.ace.geaapp.ui.view.inspection.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


import hu.ace.geaapp.R;
import hu.ace.geaapp.data.model.damages.AceAssetDamage;
import hu.ace.geaapp.data.model.Asset;
import hu.ace.geaapp.data.model.DamageCoord;
import hu.ace.geaapp.singleton.HolderSingleton;
import hu.ace.geaapp.ui.base.RemoteCallBack;
import hu.ace.geaapp.ui.view.inspection.design.CustomView;
import hu.ace.geaapp.ui.view.Damage;
import hu.ace.geaapp.ui.view.inspection.dialog.DamageDialog;
import hu.ace.geaapp.ui.view.inspection.dialog.RemoveDamageDialog;

import hu.ace.geaapp.ui.view.main.MainPresenter;
import hu.ace.geaapp.utils.EnvironmentTool;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DamageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DamageFragment extends Fragment implements View.OnClickListener, View.OnTouchListener{


    private Button breakButton, bashButton,scratchButton,lackButton, removeDamageButton;
    private ConstraintLayout constraintLayout;
    private CustomView customView = null;
    private CustomView removableCustomView = null;

    private ArrayList<AceAssetDamage> removingList = new ArrayList<>();

    private MainPresenter mainPresenter;
    private Asset selectedVehicleAsset;
    private Damage selectedDamage = null;
    private List<AceAssetDamage> selectedVehicleDamageList = new ArrayList<>();
    private List<AceAssetDamage> defaultVehicleDamageList = new ArrayList<>();


    public DamageFragment() {
        // Required empty public constructor
    }


    public static DamageFragment newInstance() {
        DamageFragment fragment = new DamageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("------------------>","DamageFragment: onCreate()");

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_damage, container, false);
        Log.i("------------------>","DamageFragment: onCreateView()");
        final View touchView = view.findViewById(R.id.imageview_car);
        constraintLayout = view.findViewById(R.id.constraintlayout_main);

        mainPresenter = new MainPresenter();
        selectedVehicleAsset = HolderSingleton.getInstance().getVehicleAsset();

        touchView.setOnTouchListener(this);
        //layoutIsValid(constraintLayout);

        getDamages();

        breakButton = view.findViewById(R.id.button_break);
        bashButton = view.findViewById(R.id.button_bash);
        lackButton = view.findViewById(R.id.button_lack);
        scratchButton = view.findViewById(R.id.button_scratch);
        removeDamageButton = view.findViewById(R.id.button_remove);

        breakButton.setOnClickListener(this);
        bashButton.setOnClickListener(this);
        lackButton.setOnClickListener(this);
        scratchButton.setOnClickListener(this);
        removeDamageButton.setOnClickListener(this);

        return view;
    }



    private void getDamages(){
        Log.i("------------------>","getDamages()");
        removeCustomViewFromLayout(constraintLayout); //clear constraintlayout, delete all CustomView already defined

        mainPresenter.getVehicleDamages(selectedVehicleAsset.getAssetnum(), new RemoteCallBack<>() {
            @Override
            public void onSucces(List<AceAssetDamage> damageList) {
                System.out.println(" NR damage [from Maximo]: " + damageList.size());
                selectedVehicleDamageList = damageList;

                if (damageList.size() != 0) {
                    for (int i = 0; i < damageList.size(); i++) {
                        AceAssetDamage damageInList = containsDamageID(selectedVehicleDamageList, damageList.get(i)); //damage defined before loading damage page, if damageInList != null

                        if (damageInList != null) {
                            //damage megtalalhato a damageList-ben
                            if (damageInList.getCustomView() == null) {
                                completeDamageData(damageInList);
                            } else {
                                System.out.println(" [x=" + damageInList.getCustomView().getcX() + "; y=" +
                                        damageInList.getCustomView().getcY() + "; color=" + damageInList.getCustomView().getColor() + "]");
                            }
                        } else {
                            drawDamageOnView(damageList.get(i));
                        }
                    }
                } else {
                    System.out.println(" nincs DAMAGE");
                }
            }
        });

    }

    private void completeDamageData(AceAssetDamage incompleteDamage){
        Log.i("------------------>","completeDamageData()");

        CustomView customView = new CustomView(getActivity());
        String coordX = incompleteDamage.getCoordinateX();
        String coordY = incompleteDamage.getCoordinateY();

        if (incompleteDamage.getStatus().equals(Damage.LACK.getDamageType())){
            incompleteDamage.setDamageEnum(Damage.LACK);
            customView.setColor(Color.BLUE);
        }
        if (incompleteDamage.getStatus().equals(Damage.SCRATCH.getDamageType())){
            incompleteDamage.setDamageEnum(Damage.SCRATCH);
            customView.setColor(Color.YELLOW);
        }
        if (incompleteDamage.getStatus().equals(Damage.BASH.getDamageType())){
            incompleteDamage.setDamageEnum(Damage.BASH);
            customView.setColor(Color.GREEN);
        }
        if (incompleteDamage.getStatus().equals(Damage.BREAK.getDamageType())){
            incompleteDamage.setDamageEnum(Damage.BREAK);
            customView.setColor(Color.RED);
        }
        customView.setCoordinates(Float.valueOf(coordX),Float.valueOf(coordY));
        DamageCoord coordObj = new DamageCoord(Float.valueOf(coordX),Float.valueOf(coordY));

        incompleteDamage.setCustomView(customView);
        incompleteDamage.setDamageCoordinate(coordObj);

        System.out.println("Damage view = "+customView);

        constraintLayout.removeView(customView);
        constraintLayout.addView(customView);

        layoutIsValid(constraintLayout);

    }

    private AceAssetDamage containsDamageID(List<AceAssetDamage> damageList, AceAssetDamage damage){
        for (AceAssetDamage damageItem : damageList){
            if (damageItem.getDamageID().equals(damage.getDamageID())){
                return damageItem;
            }
        }
        return null;
    }



    private void drawDamageOnView(AceAssetDamage damage){
        Log.d("------------------>", "DRAW damage : [status="+damage.getStatus()+"; coordX="+damage.getCoordinateX()+"; coordY="+damage.getCoordinateY()+"; DAMAGE ID="+
                damage.getDamageID()+"; customView="+damage.getCustomView()+"; damageCoordObj="+damage.getDamageCoordinate()+"]");
        setDamageDetails(damage);

    }

    private void setDamageDetails(AceAssetDamage damage){
        Log.i("------------------>","setDamageDetails()");
        String damageType = damage.getStatus();
        String coordX = damage.getCoordinateX();
        String coordY = damage.getCoordinateY();
        CustomView customView = new CustomView(getActivity());

        if (damageType.equals(Damage.LACK.getDamageType())){
            damage.setDamageEnum(Damage.LACK);
            customView.setColor(Color.BLUE);
        }
        if (damageType.equals(Damage.SCRATCH.getDamageType())){
            damage.setDamageEnum(Damage.SCRATCH);
            customView.setColor(Color.YELLOW);
        }
        if (damageType.equals(Damage.BASH.getDamageType())){
            damage.setDamageEnum(Damage.BASH);
            customView.setColor(Color.GREEN);
        }
        if (damageType.equals(Damage.BREAK.getDamageType())){
            damage.setDamageEnum(Damage.BREAK);
            customView.setColor(Color.RED);
        }

        customView.setCoordinates(Float.valueOf(coordX),Float.valueOf(coordY));
        DamageCoord coordObj = new DamageCoord(Float.valueOf(coordX),Float.valueOf(coordY));
        damage.setCustomView(customView);
        damage.setDamageCoordinate(coordObj);

        constraintLayout.removeView(customView);
        constraintLayout.addView(customView);

        selectedVehicleDamageList.add(damage);

        layoutIsValid(constraintLayout);

    }



    @Override
    public void onClick(View v) {
        customView = new CustomView(getActivity());
        switch (v.getId()) {
            case R.id.button_break:
                customView.setColor(Color.RED);
                selectedDamage = Damage.BREAK;
                break;
            case R.id.button_bash:
                customView.setColor(Color.GREEN);
                selectedDamage = Damage.BASH;
                break;
            case R.id.button_scratch:
                customView.setColor(Color.YELLOW);
                selectedDamage = Damage.SCRATCH;
                break;
            case R.id.button_lack:
                customView.setColor(Color.BLUE);
                selectedDamage = Damage.LACK;
                break;
            case R.id.button_remove:
                selectedDamage = null;
                break;
            default:
                break;
        }


    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i("------------------>","onTouch() [customView = "+customView+"; selectedDamage = "+selectedDamage+"]");
        //layoutIsValid(constraintLayout);

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            if (customView != null){
                if (selectedDamage == null){
                    ArrayList<AceAssetDamage> removableDamageList = searchSelectedDamage(event.getX(),event.getY());
                    if (removableDamageList.size() != 0){
                        launchRemoveDamageDialog(removableDamageList);
                    }
                }else{
                    DamageCoord coord = new DamageCoord(event.getX(),event.getY());
                    customView.setCoordinates(event.getX(),event.getY());

                    AceAssetDamage aceAssetDamage = new AceAssetDamage();
                    aceAssetDamage.setStatus(selectedDamage.getDamageType());
                    aceAssetDamage.setDamageEnum(selectedDamage);
                    aceAssetDamage.setDamageCoordinate(coord);
                    aceAssetDamage.setCoordinateX(String.valueOf(event.getX()));
                    aceAssetDamage.setCoordinateY(String.valueOf(event.getY()));
                    aceAssetDamage.setCustomView(customView);

                   /* constraintLayout.removeView(customView);
                    constraintLayout.addView(customView);*/
                    //System.out.println(" ADDed new Damage: [status="+aceAssetDamage.getStatus()+"; view="+aceAssetDamage.getCustomView()+"]");
                    launchAddDamageDialog(aceAssetDamage);
                }

                return true;
            }else{
                System.out.println(" Nem történik semmi, mert a Custom View nincs meghatározva");
            }
        }

        return false;
    }

    private void launchAddDamageDialog(AceAssetDamage damage){
        Log.i("------------------>","Launch ADD damage dialog");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        DamageDialog damageDialog = DamageDialog.newInstance(selectedVehicleAsset,  damage);
        damageDialog.setTargetFragment(this,0);
        damageDialog.show(ft, "dialog");
    }




    private void launchRemoveDamageDialog(ArrayList<AceAssetDamage> damageList){
        Log.d("------------------>"," launch Remove Dialog");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        RemoveDamageDialog removeDamageDialog = RemoveDamageDialog.newInstance(damageList);
        removeDamageDialog.setTargetFragment(this,0);
        removeDamageDialog.show(ft, "remove_damage_dialog");

    }



    public void continueDrawing(Damage damageType,int circleColor){
        customView = new CustomView(getActivity());
        customView.setColor(circleColor);
        selectedDamage = damageType;

    }

    public void continueDrawingAfterSave(Damage damageType,int circleColor){
        getDamages();
        continueDrawing(damageType,circleColor);
    }


    public void removeSelectedDamage(AceAssetDamage damage){
        Log.i("------------------>","DamageFragment: removeSelectedDamage()");

        AceAssetDamage damageDeleted = containsDamageID(selectedVehicleDamageList,damage);
        System.out.println(" damageDeleted = "+damageDeleted);

        if (damageDeleted != null){
            System.out.println(" DELETED CUSTOMVIEW = "+damageDeleted.getCustomView());
            selectedVehicleDamageList.remove(damageDeleted);
            constraintLayout.removeView(damageDeleted.getCustomView());
            getDamages();
        }else{
            constraintLayout.removeView(damage.getCustomView());
            getDamages();
        }

        //layoutIsValid(constraintLayout);
    }

    public void removeSelectedDamageFromView(AceAssetDamage damage){
        Log.i("------------------>","Remove damage from selectedVehicleDamageList and from view - after DELETE ["+damage.getCustomView()+"; "+damage.getDamageID()+"; "+damage.getStatus()+"]");
        selectedVehicleDamageList.remove(damage);
        constraintLayout.removeView(damage.getCustomView());
        getDamages();

    }


    public boolean layoutIsValid(ConstraintLayout constraintLayout){
        System.out.println(" NR child on layout = "+constraintLayout.getChildCount());
        for(int i=0;i<constraintLayout.getChildCount();i++){
            View view = constraintLayout.getChildAt(i);
            System.out.println("Child view = "+view+"; instanceof image = "+(view instanceof AppCompatImageView)+"; " +
                    "instance of circle = "+(view instanceof CustomView));
        }

        return true;
    }

    public void removeCustomViewFromLayout(ConstraintLayout constraintLayout){
        for (int i=0;i<constraintLayout.getChildCount();i++){
            View view = constraintLayout.getChildAt(i);
            if (view instanceof CustomView){
                System.out.println(" REMOVE from MAIN layout ["+view+"]");
                constraintLayout.removeView(view);
            }
        }
    }


    public ArrayList<AceAssetDamage> searchSelectedDamage(float coordX, float coordY){
        removingList.clear();

        for (AceAssetDamage damage : selectedVehicleDamageList){
            double resultOfDistance = EnvironmentTool.calculateDistance(Double.parseDouble(damage.getCoordinateX()),Double.parseDouble(damage.getCoordinateY()),coordX,coordY);
            if ((int)resultOfDistance >= 0 &&  (int)resultOfDistance <= 10){
                removingList.add(damage);
                System.out.println("Removable item : "+damage.getCustomView()+"; "+damage.getStatus());
            }
        }
        return removingList;
    }





}
