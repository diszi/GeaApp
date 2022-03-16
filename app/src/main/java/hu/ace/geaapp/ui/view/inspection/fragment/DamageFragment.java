package hu.ace.geaapp.ui.view.inspection.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import hu.ace.geaapp.R;
import hu.ace.geaapp.data.model.damages.AceAssetDamage;
import hu.ace.geaapp.data.model.Asset;
import hu.ace.geaapp.data.model.DamageCoord;
import hu.ace.geaapp.singleton.HolderSingleton;
import hu.ace.geaapp.ui.base.RemoteCallBack;
import hu.ace.geaapp.ui.view.inspection.CustomView;
import hu.ace.geaapp.ui.view.Damage;
import hu.ace.geaapp.ui.view.inspection.dialog.DamageDialog;
import hu.ace.geaapp.ui.view.inspection.dialog.RemoveDamageDialog;
import hu.ace.geaapp.ui.view.inspection.presenter.InspectionPresenter;
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
    //private List<DamageCoord> damageCoordinateList = new ArrayList<>();
    //private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");


    //private boolean isChanges = false;

    private MainPresenter mainPresenter;
    private InspectionPresenter presenter;
    private Asset selectedVehicleAsset;

    private Damage selectedDamage = null;
    //private List<AceAssetDamage> damageWithViewList = new ArrayList<>();
    private List<AceAssetDamage> selectedVehicleDamageList = new ArrayList<>();
    private List<AceAssetDamage> defaultVehicleDamageList = new ArrayList<>();


    public DamageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     */
   /* public static DamageFragment newInstance(String param1, String param2) {
        DamageFragment fragment = new DamageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

//    public static DamageFragment newInstance(Asset asset) {
//        // System.out.println(" FRAGMENT * new Instance - "+param1);
//        DamageFragment fragment = new DamageFragment();
//        Bundle args = new Bundle();
//        args.putSerializable(Asset.SERIALIZABLE_NAME, asset);
//        //args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    public static DamageFragment newInstance() {
        DamageFragment fragment = new DamageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        selectedVehicleAsset = HolderSingleton.getInstance().getVehicleAsset();
        //selectedVehicleDamageList = selectedVehicleAsset.getAssetDamageList();
        defaultVehicleDamageList = selectedVehicleAsset.getAssetDamageList();
       // System.out.println(" -------> OnCreate() -> DamageFragment(start): nr damage = "+defaultVehicleDamageList.size());
        selectedVehicleDamageList = defaultVehicleDamageList;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_damage, container, false);
//        ButterKnife.bind(this,view);
       // System.out.println("OnCreateView() ==> CUSTOM VIEW = "+customView+"; removableCustomView = "+removableCustomView);

        presenter = new InspectionPresenter();
        mainPresenter = new MainPresenter();

        final View touchView = view.findViewById(R.id.imageview_car);
        constraintLayout = view.findViewById(R.id.constraintlayout_main);

        touchView.setOnTouchListener(this);

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

        getDamages();

        return view;
    }



    private void getDamages(){
       // System.out.println(" ---> GET DAMAGES");
        /*presenter.getVehicle(selectedVehicleAsset.getAssetnum(), new RemoteCallBack<Asset>() {
            @Override
            public void onSucces(Asset asset) {
                if (asset != null){
                    selectedVehicleDamageList = asset.getAssetDamageList();
                    showDamagies(selectedVehicleDamageList);
                }else{
                    System.out.println(" HIBA");
                }
            }
        });*/

        mainPresenter.getVehicleDamages(selectedVehicleAsset.getAssetnum(), new RemoteCallBack<List<AceAssetDamage>>() {
            @Override
            public void onSucces(List<AceAssetDamage> damageList) {
                if (damageList.size() != 0){
                    //System.out.println(" SELECTED damage list = "+damageList.size()+"; elozo lista="+selectedVehicleDamageList.size());
                    /*selectedVehicleDamageList.clear();
                    selectedVehicleDamageList.addAll(damageList);
                    System.out.println(" NR damage after get (refresh) = "+selectedVehicleDamageList.size());
                    showDamages(selectedVehicleDamageList);*/
                    for (int i=0;i<damageList.size();i++){
                        //ha a selectedVehicleDamageList  tartalmazza a DAMAGEID-t és a customView != null ==> nem kell hozza adni a tablahoz
                        // ha tartalmazza, de a customView == null ==> meg kell adni a customview-t a damage-nek, de mar a meglevohoz
                        // ha nem tartalmazza a damageid-t, akkor add to list + setDamageDetails
                        AceAssetDamage damageInList = containsDamageID(selectedVehicleDamageList,damageList.get(i));
                        if (damageInList != null){
                            //damage megtalalhato a damageList-ben
                            if (damageInList.getCustomView() == null){
                                //most lett hozzaadva a felulethez ez a serules ==> set customview + coordObj
                               // System.out.println(" DAMAGE "+damageInList.getDamageID()+" szerepel + customeview == null ==> complete damage data");
                                completeDamageData(damageInList);
                            }else{
                                //nem tortenik semmi
                              //  System.out.println(" DAMAGE "+damageInList.getDamageID()+" szerepel + customeview != null");
                            }
                        }else{
                           // System.out.println(" DAMAGE "+damageList.get(i).getDamageID()+" NEM szerepel ==> set all data + add to list");
                            drawDamageOnView(damageList.get(i));
                        }
                    }
                    //System.out.println(" NR damage after get (refresh) = "+selectedVehicleDamageList.size());
                   /* for(AceAssetDamage damage: selectedVehicleDamageList){
                        System.out.println(" Damage "+damage.getStatus()+" -> [customview="+damage.getCustomView()+
                                "; enum="+damage.getDamageEnum()+"; coord="+damage.getDamageCoordinate()+"]");
                    }*/
                }else{
                    System.out.println(" nincs DAMAGE");
                }
            }
        });
    }

    private void completeDamageData(AceAssetDamage incompleteDamage){
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

        constraintLayout.removeView(customView);
        constraintLayout.addView(customView);


    }

    private AceAssetDamage containsDamageID(List<AceAssetDamage> damageList, AceAssetDamage damage){
        for (AceAssetDamage damageItem : damageList){
            if (damageItem.getDamageID().equals(damage.getDamageID())){
                return damageItem;
            }
        }
        return null;
    }

    private void showDamages(List<AceAssetDamage> damageList){
        for (AceAssetDamage damage : damageList){
            drawDamageOnView(damage);
        }
    }

    private void drawDamageOnView(AceAssetDamage damage){
       /* Log.d(" ------------>", "SHOW damage : [status="+damage.getStatus()+"; coordX="+damage.getCoordinateX()+"; coordY="+damage.getCoordinateY()+"; DAMAGE ID="+
                damage.getDamageID()+"; customView="+damage.getCustomView()+"; damageCoordObj="+damage.getDamageCoordinate()+"]");*/

        setDamageDetails(damage);


        //System.out.println(" ==> AFTER set damages on layout ==> customView="+damage.getCustomView()+"; coordObj="+damage.getDamageCoordinate()+"; damageEnum="+damage.getDamageEnum());

    }

    private void setDamageDetails(AceAssetDamage damage){
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

        //System.out.println("Set data for all damage ->  CUSTOM VIEW = "+customView+"; color="+customView.getColor()+"; d_type="+damage.getStatus());
        constraintLayout.removeView(customView);
        constraintLayout.addView(customView);

        selectedVehicleDamageList.add(damage);
    }



    @Override
    public void onClick(View v) {
       // removableCustomView = null;
        //customView = null;
        customView = new CustomView(getActivity());
        switch (v.getId()) {
            case R.id.button_break:

                customView.setColor(Color.RED);
                selectedDamage = Damage.BREAK;
                break;
            case R.id.button_bash:
               // customView = new CustomView(getActivity());
                customView.setColor(Color.GREEN);
                selectedDamage = Damage.BASH;
                break;
            case R.id.button_scratch:
                //customView = new CustomView(getActivity());
                customView.setColor(Color.YELLOW);
                selectedDamage = Damage.SCRATCH;
                break;
            case R.id.button_lack:
                //customView = new CustomView(getActivity());
                customView.setColor(Color.BLUE);
                selectedDamage = Damage.LACK;
                break;
            case R.id.button_remove:
                //removableCustomView = new CustomView(getActivity());
                selectedDamage = null;
                break;
            default:
               // customView = null;
                //removableCustomView = null;
                break;
        }

        /*System.out.println(" OnClick on Button "+v.getId()+"; customView = "+customView+"; selectedDamage = "+selectedDamage+
                "; removableCustomView = "+removableCustomView);*/
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // System.out.println(" TOUCH ACTION = "+event.getAction()+"; UP="+MotionEvent.ACTION_UP+"  DOWN="+MotionEvent.ACTION_DOWN);
       // System.out.println("onTouch : CUSTOM VIEW = "+customView+"; Action Event = "+event.getAction());
       // System.out.println(" CUSTOMVIEW = "+customView+"; removableCustomView="+removableCustomView+"; selectedDamage = "+selectedDamage);
        if (event.getAction() == MotionEvent.ACTION_DOWN){

            if (customView != null){
               /* System.out.println(" selectedDamage = "+selectedDamage);
                System.out.println(" RED="+Color.RED+"; GREEN="+Color.GREEN+"; YELLOW = "+Color.YELLOW+"; BLUE="+Color.BLUE+"; BLACK="+Color.BLACK);*/
               // System.out.println(" Selected Color = "+customView.getColor()+"; selected damage = "+selectedDamage);
                //System.out.println(" Touch coord = "+String.valueOf(event.getX())+ " x "+String.valueOf(event.getY()));
                if (selectedDamage == null){
                   // System.out.println(" Get coordinates list, verify ontouch coord there is in list?? [coord list size = "+damageCoordinateList.size()+"]");
                    ArrayList<AceAssetDamage> removableDamageList =searchSelectedDamage(event.getX(),event.getY());
                  //  System.out.println(" ARRAY removableList = "+removableDamageList.size());
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
                    //System.out.println(" CUSTOM view for new damage => "+aceAssetDamage.getCustomView());
                    //damageWithViewList.add(aceAssetDamage);
                   // damageCoordinateList.add(coord);
                    constraintLayout.removeView(customView);
                    constraintLayout.addView(customView);
                    //launchAddDamageDialog(event.getX(),event.getY(),aceAssetDamage);
                   /* System.out.println(" Add damage: [x="+event.getX()+"; y="+event.getY()+
                            "; color="+customView.getColor()+"; type="+selectedDamage+"; assetnum="+selectedVehicleAsset.getAssetnum()+"]");*/
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
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DamageDialog damageDialog = DamageDialog.newInstance(selectedVehicleAsset,  damage);
        damageDialog.setTargetFragment(this,0);
        damageDialog.show(ft, "dialog");
    }


    /*private void launchAddDamageDialog(float x, float y, AceAssetDamage damage){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DamageDialog damageDialog = DamageDialog.newInstance(x,y, selectedDamage, customView.getColor(),selectedVehicleAsset,  damage);
        damageDialog.setTargetFragment(this,0);
        damageDialog.show(ft, "dialog");

    }*/

    private void launchRemoveDamageDialog(ArrayList<AceAssetDamage> damageList){
        Log.d("------------>"," launch Remove Dialog");
        /*for(int i=0;i<damageList.size();i++){
            System.out.println(" REMOVE DAMAGE type="+damageList.get(i).getStatus()+" - x="+damageList.get(i).getCoordinateX()+" - y="
                    +damageList.get(i).getCoordinateY()+"; custom view = "+damageList.get(i).getCustomView());
        }*/
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        RemoveDamageDialog removeDamageDialog = RemoveDamageDialog.newInstance(damageList);
        removeDamageDialog.setTargetFragment(this,0);
        removeDamageDialog.show(ft, "remove_damage_dialog");

    }



    public void continueDrawing(Damage damageType,int circleColor){
        customView = new CustomView(getActivity());
        customView.setColor(circleColor);
        selectedDamage = damageType;
        //System.out.println(" CUSTOMVIEW color = "+customView.getColor()+"; selected damage="+selectedDamage);
    }

    public void continueDrawingAfterSave(Damage damageType,int circleColor){
        //System.out.println(" CONTINUE drawing with color = "+circleColor);
        getDamages();
        /*customView = new CustomView(getActivity());
        customView.setColor(circleColor);
        selectedDamage = damageType;*/
        continueDrawing(damageType,circleColor);
    }
/*
    public void removeDamageCircle(){
        constraintLayout.removeView(customView);
    }*/

    public void removeSelectedDamage(AceAssetDamage damage){
        /*System.out.println(" ---> REMOVE selected damage : x="+damage.getDamageCoordinate().getCoordinateX()+" y="+
                damage.getDamageCoordinate().getCoordinateY()+" ==> customView="+damage.getCustomView());*/
//        CustomView customView_ = new CustomView(getActivity());
//        customView_.setColor(damage.getCustomView().getColor());
        //constraintLayout.removeView(damage.getCustomView());
        //removableCustomView = null;
        AceAssetDamage damageDeleted = containsDamageID(selectedVehicleDamageList,damage);
        if (damageDeleted != null){
            selectedVehicleDamageList.remove(damageDeleted);
            constraintLayout.removeView(damage.getCustomView());
            getDamages();

        }

        //getDamages();
       // removeViews();
        //getDamages();
        /*if (customView != null){
            System.out.println(" RMEOVE");
            constraintLayout.removeView(customView);
        }*/

    }

    /*public void removeDamages(List<AceAssetDamage> damageList){
        System.out.println(" REMOVING "+damageList.size()+" damage");
        for(int i=0;i<damageList.size();i++){
//            constraintLayout.removeView();
            constraintLayout.removeView(damageList.get(i).getCustomView());
        }
    }*/


    public ArrayList<AceAssetDamage> searchSelectedDamage(float coordX, float coordY){

        removingList.clear();

        for (AceAssetDamage damage : selectedVehicleDamageList){
           //System.out.println(" DAMAGE => "+damage.getDamageID()+"; view="+damage.getCustomView());
            double resultOfDistance = EnvironmentTool.calculateDistance(Double.parseDouble(damage.getCoordinateX()),Double.parseDouble(damage.getCoordinateY()),coordX,coordY);
//            System.out.println(" RESULT of distance = "+resultOfDistance);
            if ((int)resultOfDistance >= 0 &&  (int)resultOfDistance <= 10){
                removingList.add(damage);
            }
        }
        return removingList;

    }




}
