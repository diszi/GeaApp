package hu.ace.geaapp.ui.view.line.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
import hu.ace.geaapp.ui.base.RemoteCallBack;
import hu.ace.geaapp.ui.view.inspection.CustomView;
import hu.ace.geaapp.ui.view.Damage;
import hu.ace.geaapp.ui.view.inspection.dialog.DamageDialog;
import hu.ace.geaapp.ui.view.line.presenter.LinePresenter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LineDamageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LineDamageFragment extends Fragment implements View.OnClickListener, View.OnTouchListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private ImageView carImage;
    private Button breakButton, bashButton,scratchButton,lackButton, removeDamageButton;
    private ConstraintLayout constraintLayout;

    private CustomView customView = null;
    private Damage selectedDamage = null;


    private Asset selectedVehicleAsset;
    private List<AceAssetDamage> selectedVehicleDamageList;


    private List<DamageCoord> damageCoordinateList = new ArrayList<>();
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");



    private LinePresenter presenter;

    public LineDamageFragment() {
        // Required empty public constructor
    }

    public static LineDamageFragment newInstance(Asset asset) {
        // System.out.println(" FRAGMENT * new Instance - "+param1);
        LineDamageFragment fragment = new LineDamageFragment();
        Bundle args = new Bundle();
        args.putSerializable(Asset.SERIALIZABLE_NAME, asset);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedVehicleAsset = (Asset) getArguments().getSerializable(Asset.SERIALIZABLE_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_line_damage, container, false);


        presenter = new LinePresenter();


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

        getDamageList();

        return view;
    }

    private void getDamageList(){
       /* presenter.getVehicleDamages(selectedVehicleAsset.getAssetnum(), new RemoteCallBack<Asset>() {
            @Override
            public void onSucces(Asset asset) {
                if (asset != null){
                    System.out.println(" VAN ILYEN VEHICLE");
                    selectedVehicleDamageList = asset.getAssetDamageList();
                    showDamagies(selectedVehicleDamageList);
                }else{
                    System.out.println(" HIBA");
                }
            }
        });*/


    }




    @Override
    public void onClick(View v) {
        customView = new CustomView(getActivity());
        //damageSelected = true;
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
        }
        System.out.println(" OnClick on Button "+v.getId()+"; customView = "+customView+"; selectedDamage = "+selectedDamage);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        System.out.println("onTouch : CUSTOM VIEW = "+customView+"; Action Event = "+event.getAction());


        if (event.getAction() == MotionEvent.ACTION_DOWN){
            if (customView != null){
                System.out.println(" RED="+Color.RED+"; GREEN="+Color.GREEN+"; YELLOW = "+Color.YELLOW+"; BLUE="+Color.BLUE+"; BLACK="+Color.BLACK);
                System.out.println(" Selected Color = "+customView.getColor()+"; selected damage = "+selectedDamage);
                System.out.println(" Touch coord = "+String.valueOf(event.getX())+ " x "+String.valueOf(event.getY()));

                if (selectedDamage == null){
                    System.out.println(" Get coordinates list, verify ontouch coord there is in list?? [coord list size = "+damageCoordinateList.size()+"]");
                    removeDamage(event.getX(),event.getY());
                }else{
                    DamageCoord coord = new DamageCoord(event.getX(),event.getY());
                    //DamageCoord coord = new DamageCoord(roundCoord(event.getX(),2), roundCoord(event.getY(),2));
                    damageCoordinateList.add(coord);
                    customView.setCoordinates(event.getX(),event.getY());
                    constraintLayout.removeView(customView);
                    constraintLayout.addView(customView);
                    showDialog(event.getX(),event.getY());
                }

                return true;
            }else{
                System.out.println(" Nem történik semmi, mert a Custom View nincs meghatározva");

            }
        }

        return false;
    }


    public void removeDamage(float coordX, float coordY){
        //REMOVE DAMAGE  in coord [x=1167.9492; y=450.9712];
        System.out.println(" REMOVE DAMAGE  in coord [x="+coordX+"; y="+coordY+"]; nr damages = "+damageCoordinateList.size());
        if (damageCoordinateList.size() > 0){
           // loadRemoveDamageDialog();
        }


    }


  /*  private void loadRemoveDamageDialog(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        RemoveDamageDialog removeDamageDialog = RemoveDamageDialog.newInstance();
        removeDamageDialog.setTargetFragment(this,0);
        removeDamageDialog.show(ft, "remove_damage_dialog");


    }*/

    private void showDialog(float x, float y){
        /*FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DamageDialog damageDialog = DamageDialog.newInstance(1,x,y, selectedDamage, customView.getColor(),selectedVehicleAsset);
        damageDialog.setTargetFragment(this,0);
        damageDialog.show(ft, "dialog");*/

    }

    private void showDamagies(List<AceAssetDamage> damageList){
        damageCoordinateList.clear();
        for (AceAssetDamage damage : damageList){
            drawDamageOnView(damage);
        }
    }

    private void drawDamageOnView(AceAssetDamage damage){
        System.out.println(" --> damage.status = "+damage.getStatus()+"; BREAK type ="+Damage.BREAK.getDamageType()+"; value="+Damage.BREAK);
        String damageType = damage.getStatus();
        if (damageType.equals(Damage.BREAK.getDamageType())){
            String coordX = damage.getCoordinateX();
            String coordY = damage.getCoordinateY();
            customView = new CustomView(getActivity());
            customView.setColor(Color.RED);
            customView.setCoordinates(Float.valueOf(coordX),Float.valueOf(coordY));
            DamageCoord coordObj = new DamageCoord(Float.valueOf(coordX),Float.valueOf(coordY));
            damageCoordinateList.add(coordObj);
            constraintLayout.removeView(customView);
            constraintLayout.addView(customView);
            //showDialog(event.getX(),event.getY());

        }

        if (damageType.equals(Damage.BASH.getDamageType())){
            String coordX = damage.getCoordinateX();
            String coordY = damage.getCoordinateY();
            customView = new CustomView(getActivity());
            customView.setColor(Color.GREEN);
            customView.setCoordinates(Float.valueOf(coordX),Float.valueOf(coordY));
            DamageCoord coordObj = new DamageCoord(Float.valueOf(coordX),Float.valueOf(coordY));
            damageCoordinateList.add(coordObj);
            constraintLayout.removeView(customView);
            constraintLayout.addView(customView);
            //showDialog(event.getX(),event.getY());

        }

        if (damageType.equals(Damage.SCRATCH.getDamageType())){
            String coordX = damage.getCoordinateX();
            String coordY = damage.getCoordinateY();
            customView = new CustomView(getActivity());
            customView.setColor(Color.YELLOW);
            customView.setCoordinates(Float.valueOf(coordX),Float.valueOf(coordY));
            DamageCoord coordObj = new DamageCoord(Float.valueOf(coordX),Float.valueOf(coordY));
            damageCoordinateList.add(coordObj);
            constraintLayout.removeView(customView);
            constraintLayout.addView(customView);
            //showDialog(event.getX(),event.getY());

        }

        if (damageType.equals(Damage.LACK.getDamageType())){
            String coordX = damage.getCoordinateX();
            String coordY = damage.getCoordinateY();
            customView = new CustomView(getActivity());
            customView.setColor(Color.BLUE);
            customView.setCoordinates(Float.valueOf(coordX),Float.valueOf(coordY));
            DamageCoord coordObj = new DamageCoord(Float.valueOf(coordX),Float.valueOf(coordY));
            damageCoordinateList.add(coordObj);
            constraintLayout.removeView(customView);
            constraintLayout.addView(customView);
            //showDialog(event.getX(),event.getY());

        }
    }

}