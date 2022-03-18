package hu.ace.geaapp.ui.view.inspection.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.ace.geaapp.R;
import hu.ace.geaapp.data.model.Accessories;
import hu.ace.geaapp.data.model.damages.AceAssetDamage;
import hu.ace.geaapp.data.model.AceAssetGJK;
import hu.ace.geaapp.data.model.AceAssetVignette;
import hu.ace.geaapp.data.model.Asset;
import hu.ace.geaapp.data.model.StructFeatures;
import hu.ace.geaapp.singleton.HolderSingleton;
import hu.ace.geaapp.ui.view.inspection.activity.InspectionMainActivity;

import hu.ace.geaapp.ui.view.main.SearchActivity;
import hu.ace.geaapp.utils.EnvironmentTool;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GeneralInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GeneralInfoFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    private String mParam1;
//    private String mParam2;
//
//
//    private int aFlag;

    @BindView(R.id.text_assetnum)
    TextView assetnum;
    @BindView(R.id.text_license_plate_number)
    TextView license_plate;
    @BindView(R.id.text_username)
    TextView username;
    @BindView(R.id.text_type)
    TextView type;
    @BindView(R.id.text_owner_or_fin)
    TextView owner_financier;
    @BindView(R.id.text_fuel)
    TextView fuel; //uzemanyag
    @BindView(R.id.text_licenser)
    TextView licenser;
    @BindView(R.id.text_vehicleID)
    TextView vehicle_id; //alvazszam
    @BindView(R.id.text_financial)
    TextView financial;
    @BindView(R.id.text_ownership)
    TextView ownership;
    @BindView(R.id.text_costcenter)
    TextView cost_center;
    @BindView(R.id.text_traffic_lic_nr)
    TextView traffic_license_number;
    @BindView(R.id.text_inventory_nr)
    TextView inventory_number;
    @BindView(R.id.text_traffic_lic_validity)
    TextView traffic_license_validity_date;
    @BindView(R.id.text_cm3)
    TextView cm3;
    @BindView(R.id.text_entry_validity)
    TextView entry_validity_date;
    @BindView(R.id.text_motor_nr)
    TextView motor_number;
    @BindView(R.id.text_next_service_km)
    TextView next_service_km;
    @BindView(R.id.text_tire_size)
    TextView tire_size;
    @BindView(R.id.text_next_service_date)
    TextView next_service_date;
    @BindView(R.id.text_vignette)
    TextView vignette;
   /* @BindView(R.id.text_current_km)
    TextView current_km;
    @BindView(R.id.text_current_km_date)
    TextView current_km_date;*/
    @BindView(R.id.text_status)
    TextView status;


    @BindView(R.id.toolbar_general_info)
    Toolbar mToolbar;


//    private InspectionPresenter inspectionPresenter;


    private Asset selectedVehicleAsset;
    private AceAssetGJK selectedVehicleGJK;
    /*private List<AceAssetDamage> selectedVehicleDamageList;
    private List<AceAssetVignette> selectedVehicleVignetteList;
    private Accessories selectedVehicleAccessories;
    private StructFeatures selectedVehicleStructuralFeature;*/

    public GeneralInfoFragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
   /* public static GeneralInfoFragment newInstance(String param1, String param2) {
        GeneralInfoFragment fragment = new GeneralInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

//    public static GeneralInfoFragment newInstance(Asset param1) {
//        GeneralInfoFragment fragment = new GeneralInfoFragment();
//        Bundle args = new Bundle();
//        args.putSerializable(Asset.SERIALIZABLE_NAME, param1);
//        fragment.setArguments(args);
//        return fragment;
//    }

    public static GeneralInfoFragment newInstance() {
        GeneralInfoFragment fragment = new GeneralInfoFragment();
        Bundle args = new Bundle();
       // args.putSerializable(Asset.SERIALIZABLE_NAME, param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        selectedVehicleAsset = HolderSingleton.getInstance().getVehicleAsset();
        selectedVehicleGJK = selectedVehicleAsset.getAceAssetGJK();
       /* selectedVehicleAccessories = selectedVehicleAsset.getAccessories();
        selectedVehicleDamageList = selectedVehicleAsset.getAssetDamageList();
        selectedVehicleStructuralFeature = selectedVehicleAsset.getStructFeatures();
        selectedVehicleVignetteList = selectedVehicleAsset.getAssetVignetteList();*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_general_info, container, false);
        ButterKnife.bind(this, view);

        //inspectionPresenter = new InspectionPresenter();

        ((InspectionMainActivity) getActivity()).setSupportActionBar(mToolbar);

        ((InspectionMainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((InspectionMainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
//                System.out.println(" LOAD main - search activity");//ok
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });


        String licDate = "", entryValidityDate="";


        assetnum.setText(selectedVehicleAsset.getAssetnum());
        status.setText(selectedVehicleAsset.getStatus());


        license_plate.setText(selectedVehicleGJK.getLicensePlate());
        ownership.setText(selectedVehicleGJK.getStatus());
        type.setText(selectedVehicleGJK.getType());
        fuel.setText(selectedVehicleGJK.getFuel());
        cost_center.setText(selectedVehicleGJK.getCostCenter());
        traffic_license_number.setText(selectedVehicleGJK.getTrafficLicenseNumber());
        if (selectedVehicleGJK.getTrafficLicenseValidity() != null){
            licDate = EnvironmentTool.convertDateTimeStringHU(selectedVehicleGJK.getTrafficLicenseValidity());
        }
        if (selectedVehicleGJK.getEntryValidity() != null){
            entryValidityDate = EnvironmentTool.convertDateTimeStringHU(selectedVehicleGJK.getEntryValidity());
        }
        traffic_license_validity_date.setText(licDate);
        entry_validity_date.setText(entryValidityDate);

        licenser.setText(selectedVehicleGJK.getLicenser());
        username.setText(selectedVehicleGJK.getUsername());
        financial.setText(selectedVehicleGJK.getFinancing());
        owner_financier.setText(selectedVehicleGJK.getOwner());
        inventory_number.setText(selectedVehicleGJK.getInventoryNumber());
        motor_number.setText(selectedVehicleGJK.getMotorNumber());
        vehicle_id.setText(selectedVehicleGJK.getVehicleIDnumber());
        cm3.setText(selectedVehicleGJK.getCm3());
        //current_km.setText(selectedVehicleGJK.getKmHour());
      //  current_km_date.setText(EnvironmentTool.convertDateTimeStringHU(selectedVehicleGJK.getCurrentKMdate()));
        next_service_km.setText(selectedVehicleGJK.getNextOilChange());
        //next_service_date.setText();
        tire_size.setText(selectedVehicleGJK.getTireSize());
        vignette.setText(selectedVehicleGJK.getVignette());


//        System.out.println(" SELECTED VEHICLE = " + selectedVehicleGJK.getLicensePlate());

        return view;
    }





}