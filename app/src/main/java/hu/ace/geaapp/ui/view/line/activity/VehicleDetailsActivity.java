package hu.ace.geaapp.ui.view.line.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.ace.geaapp.R;
import hu.ace.geaapp.data.model.AceAssetGJK;
import hu.ace.geaapp.data.model.Asset;
import hu.ace.geaapp.utils.EnvironmentTool;

public class VehicleDetailsActivity extends AppCompatActivity {

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
    @BindView(R.id.button_startTransfer)
    Button startButton;


    private Asset selectedVehicleAsset;
    private AceAssetGJK selectedVehicleGJK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);//kereso felulet
        ButterKnife.bind(this);

        selectedVehicleAsset = (Asset) getIntent().getExtras().getSerializable(Asset.SERIALIZABLE_NAME);
        selectedVehicleGJK = selectedVehicleAsset.getAceAssetGJK();


        setDetails();
    }

    private void setDetails(){
        assetnum.setText(selectedVehicleAsset.getAssetnum());
        status.setText(selectedVehicleAsset.getStatus());


        license_plate.setText(selectedVehicleGJK.getLicensePlate());
        ownership.setText(selectedVehicleGJK.getStatus());
        type.setText(selectedVehicleGJK.getType());
        fuel.setText(selectedVehicleGJK.getFuel());
        cost_center.setText(selectedVehicleGJK.getCostCenter());
        traffic_license_number.setText(selectedVehicleGJK.getTrafficLicenseNumber());
        traffic_license_validity_date.setText(EnvironmentTool.convertDateTimeStringHU(selectedVehicleGJK.getTrafficLicenseValidity()));
        entry_validity_date.setText(EnvironmentTool.convertDateTimeStringHU(selectedVehicleGJK.getEntryValidity()));

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
    }


    @OnClick(R.id.button_startTransfer)
    public void onClickOnStartBtn(){
        System.out.println(" START ÁTADÁS - ÁTVÉTEL ");
        Intent intent = new Intent(this, AuthenticationFromActivity.class);
        intent.putExtra(Asset.SERIALIZABLE_NAME,selectedVehicleAsset);
        startActivity(intent);
    }
}
