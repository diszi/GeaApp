package hu.ace.geaapp.ui.view.inspection.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ProgressBar;

import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.ace.geaapp.R;
import hu.ace.geaapp.data.model.Accessories;
import hu.ace.geaapp.data.model.AccessoriesTemp;
import hu.ace.geaapp.data.model.Asset;
import hu.ace.geaapp.singleton.HolderSingleton;
import hu.ace.geaapp.ui.base.RemoteCallBack;

import hu.ace.geaapp.ui.view.main.MainPresenter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccessoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccessoriesFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private Asset selectedVehicleAsset;
    private Accessories selectedVehicleAccessories;
    private MainPresenter presenter;


    private boolean isChanged = false;

    @BindView(R.id.actVehicleAccess_progressBar)
    ProgressBar loadingProgressBar;

    @BindView(R.id.button_save_accessories)
    Button saveBtn;
    @BindView(R.id.input_notes)
    EditText notesInputText;
    @BindView(R.id.text_vehicle_type)
    TextView vehicleTypeText;
    @BindView(R.id.text_vehicle_nr)
    TextView vehicleLicPlateText;


    @BindView(R.id.switch_antenna)
    Switch antennaSwitch;
    @BindView(R.id.switch_assistanceinfo)
    Switch assistanceInfoSwitch;
    @BindView(R.id.switch_elakadasjelzo)
    Switch elakadasjelzoSwitch;
    @BindView(R.id.switch_emelo)
    Switch emeloSwitch;
    @BindView(R.id.switch_flottakezeloifel)
    Switch flottakezeloiFelhSwitch;
    @BindView(R.id.switch_forgengedely)
    Switch forgalmiEngedelySwitch;
    @BindView(R.id.switch_izzokeszlet)
    Switch izzokeszletSwitch;
    @BindView(R.id.switch_kgfbigazolas)
    Switch kgfbIgazolasSwitch;
    @BindView(R.id.switch_keresztlecek)
    Switch keresztlecekSwitch;
    @BindView(R.id.switch_kerekkulcs)
    Switch kerekkulcsSwitch;
    @BindView(R.id.switch_kerekorkulccsal)
    Switch kerekorKulccsalSwitch;
    @BindView(R.id.switch_kezelesiutmutato)
    Switch kezelesiUtmutatoSwitch;
    @BindView(R.id.switch_mentodoboz)
    Switch mentodobozSwitch;
    @BindView(R.id.switch_mobilpark)
    Switch mobilParkSwitch;
    @BindView(R.id.switch_potkerek)
    Switch potkerekSwitch;
    @BindView(R.id.switch_radiokod)
    Switch radioKodSwitch;
    @BindView(R.id.switch_szervkonyv)
    Switch szervizKonyvSwitch;
    @BindView(R.id.switch_vonohorog)
    Switch vonoHorogSwitch;
    @BindView(R.id.switch_vonoszem)
    Switch vonoszemSwitch;
    @BindView(R.id.switch_uzemanyagkartya)
    Switch uzemanyagkartyaSwitch;



    @BindView(R.id.text_nr_jarmukulcsai)
    TextView text_jarmukulcsai;
    @BindView(R.id.text_nr_nyari_felnin)
    TextView text_nyarigumiFelninelkul;
    @BindView(R.id.text_nr_nyari_afelni)
    TextView text_nyarigumiAcelFelnin;
    @BindView(R.id.text_nr_nyari_kfelni)
    TextView text_nyarigumiKonnyuFelnin;


    @BindView(R.id.text_nr_teli_felnin)
    TextView text_teligumiFelninelkul;
    @BindView(R.id.text_nr_teli_afelni)
    TextView text_teligumiAcelFelnin;
    @BindView(R.id.text_nr_teli_kfelni)
    TextView text_teligumiKonnyuFelnin;

    @BindView(R.id.text_nr_gumiszonyeg)
    TextView text_gumiszonyeg;
    @BindView(R.id.text_nr_riasztotav)
    TextView text_riasztotaviranyito;
    @BindView(R.id.text_nr_lathatosagim)
    TextView text_lathatosagimelleny;


    @BindView(R.id.button_minus_jarmukulcsai)
    Button minus_jarmuk_btn;
    @BindView(R.id.button_plus_jarmukulcsai)
    Button plus_jarmuk_btn;
    @BindView(R.id.button_minus_gumiszonyeg)
    Button minus_gumiszonyeg_btn;
    @BindView(R.id.button_plus_gumiszonyeg)
    Button plus_gumiszonyeg_btn;
    @BindView(R.id.button_minus_riasztotav)
    Button minus_riasztotav_btn;
    @BindView(R.id.button_plus_riasztotav)
    Button plus_riasztotav_btn;
    @BindView(R.id.button_minus_lathatosagim)
    Button minus_lathatosagim_btn;
    @BindView(R.id.button_plus_lathatosagim)
    Button plus_lathatosagim_btn;

    //nyari gumi
    @BindView(R.id.button_minus_nyari_afelni)
    Button minus_nyari_afelnin_btn;
    @BindView(R.id.button_plus_nyari_afelni)
    Button plus_nyari_afelnin_btn;
    @BindView(R.id.button_minus_nyari_felnin)
    Button minus_nyari_felninelkul_btn;
    @BindView(R.id.button_plus_nyari_felnin)
    Button plus_nyari_felninelkul_btn;
    @BindView(R.id.button_minus_nyari_kfelni)
    Button minus_nyari_kfelnin_btn;
    @BindView(R.id.button_plus_nyari_kfelni)
    Button plus_nyari_kfelnin_btn;

    //teli gumi
    @BindView(R.id.button_minus_teli_afelni)
    Button minus_teli_afelnin_btn;
    @BindView(R.id.button_plus_teli_afelni)
    Button plus_teli_afelnin_btn;
    @BindView(R.id.button_minus_teli_felnin)
    Button minus_teli_felninelkul_btn;
    @BindView(R.id.button_plus_teli_felnin)
    Button plus_teli_felninelkul_btn;
    @BindView(R.id.button_minus_teli_kfelni)
    Button minus_teli_kfelnin_btn;
    @BindView(R.id.button_plus_teli_kfelni)
    Button plus_teli_kfelnin_btn;


    private int nrJarmukulcsai = 0;
    private int  nrTeliGumiFelninelkul = 0, nrTeliGumiAFelnin = 0, nrTeliGumiKFelnin = 0;
    private int  nrNyariGumiFelninelkul = 0, nrNyariGumiAFelnin = 0, nrNyariGumiKFelnin = 0;
    private int  nrGumiszonyeg = 0, nrRiasztoTaviranyito = 0, nrLathatosagiMelleny = 0;


    public AccessoriesFragment() {
        // Required empty public constructor
    }



//    public static AccessoriesFragment newInstance(Asset param1) {
//        // System.out.println(" FRAGMENT * new Instance - "+param1);
//        AccessoriesFragment fragment = new AccessoriesFragment();
//        Bundle args = new Bundle();
//        args.putSerializable(Asset.SERIALIZABLE_NAME, param1);
//        //args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    public static AccessoriesFragment newInstance() {
        AccessoriesFragment fragment = new AccessoriesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        selectedVehicleAsset = HolderSingleton.getInstance().getVehicleAsset();
        selectedVehicleAccessories = selectedVehicleAsset.getAccessories();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_accessories, container, false);
        ButterKnife.bind(this,view);
        hideLoading();

        presenter = new MainPresenter();

       // getAccessories();
        //inspectionPresenter.addFeatures(selectedVehicleAsset);

        setHeaderView(selectedVehicleAsset);
        setView(selectedVehicleAccessories);

       /* antennaSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                System.out.println(" CompoundButton = "+buttonView+"; isChecked = "+isChecked);
            }
        });*/


        minus_jarmuk_btn.setOnClickListener(this);
        plus_jarmuk_btn.setOnClickListener(this);
        minus_gumiszonyeg_btn.setOnClickListener(this);
        plus_gumiszonyeg_btn.setOnClickListener(this);
        minus_lathatosagim_btn.setOnClickListener(this);
        plus_lathatosagim_btn.setOnClickListener(this);
        minus_riasztotav_btn.setOnClickListener(this);
        plus_riasztotav_btn.setOnClickListener(this);

        minus_nyari_afelnin_btn.setOnClickListener(this);
        minus_nyari_felninelkul_btn.setOnClickListener(this);
        minus_nyari_kfelnin_btn.setOnClickListener(this);
        plus_nyari_felninelkul_btn.setOnClickListener(this);
        plus_nyari_afelnin_btn.setOnClickListener(this);
        plus_nyari_kfelnin_btn.setOnClickListener(this);

        minus_teli_afelnin_btn.setOnClickListener(this);
        minus_teli_felninelkul_btn.setOnClickListener(this);
        minus_teli_kfelnin_btn.setOnClickListener(this);
        plus_teli_felninelkul_btn.setOnClickListener(this);
        plus_teli_afelnin_btn.setOnClickListener(this);
        plus_teli_kfelnin_btn.setOnClickListener(this);

        return view;
    }

    private void setHeaderView(Asset asset){
        vehicleLicPlateText.setText(asset.getAceAssetGJK().getLicensePlate());
        vehicleTypeText.setText(asset.getAceAssetGJK().getType());
    }

    private void setView(Accessories accessories){
        //System.out.println(" setView() [nr jarmukulcsai = " +accessories.getAltalanos()+"]");
        antennaSwitch.setChecked(accessories.isAntenna());
        assistanceInfoSwitch.setChecked(accessories.isAssistanceInfo());
        elakadasjelzoSwitch.setChecked(accessories.isElakadasjelzo());
        emeloSwitch.setChecked(accessories.isEmelo());
        flottakezeloiFelhSwitch.setChecked(accessories.isFlotta());

        forgalmiEngedelySwitch.setChecked(accessories.isForgalmiEngedely());
        izzokeszletSwitch.setChecked(accessories.isIzzokeszlet());
        kgfbIgazolasSwitch.setChecked(accessories.isIgazolasKGFB());
        keresztlecekSwitch.setChecked(accessories.isKeresztlecek());
        kerekkulcsSwitch.setChecked(accessories.isKerekkulcs());

        kerekorKulccsalSwitch.setChecked(accessories.isKerekorkulccsal());
        kezelesiUtmutatoSwitch.setChecked(accessories.isKezelesiUtmutato());
        mentodobozSwitch.setChecked(accessories.isMentodoboz());
        mobilParkSwitch.setChecked(accessories.isMobilparkolas());
        potkerekSwitch.setChecked(accessories.isPotkerek());

        radioKodSwitch.setChecked(accessories.isRadiokod());
        szervizKonyvSwitch.setChecked(accessories.isSzervizkonyv());
        vonoHorogSwitch.setChecked(accessories.isVonohorog());
        vonoszemSwitch.setChecked(accessories.isVonoszem());
        uzemanyagkartyaSwitch.setChecked(accessories.isUzemeanyagkartya());


        text_jarmukulcsai.setText(accessories.getAltalanos());
        text_nyarigumiAcelFelnin.setText(accessories.getGumiNyariAcelFelninNR());
        text_nyarigumiFelninelkul.setText(accessories.getGumiNyariFelniNelkulNR());
        text_nyarigumiKonnyuFelnin.setText(accessories.getGumiNyariKonnyufelFelninNR());
        text_teligumiAcelFelnin.setText(accessories.getGumiTeliAcelFelninNR());
        text_teligumiFelninelkul.setText(accessories.getGumiTeliFelniNelkulNR());
        text_teligumiKonnyuFelnin.setText(accessories.getGumiTeliKonnyufelFelninNR());
        text_gumiszonyeg.setText(accessories.getGumiszonyegNR());
        text_riasztotaviranyito.setText(accessories.getRiasztoTaviranyitoNR());
        text_lathatosagimelleny.setText(accessories.getLathatosagiMellenyNR());


    }

    /*private void getAccessories(){
        //showLoading();
        presenter.getVehicleAccessories(selectedVehicleAsset, new RemoteCallBack<Accessories>() {
            @Override
            public void onSucces(Accessories accessories) {
                if (accessories != null){
                  //  hideLoading();
                    setView(accessories);
                }
            }
        });
    }*/

    @OnClick(R.id.button_save_accessories)
    public void onClickOnSaveBtn(){
       // System.out.println(" UPDATE accessories");
        //isChanged = true;

        //save changes + get vehicle with updated data

        AccessoriesTemp accessoriesTemp = new AccessoriesTemp(selectedVehicleAccessories.getAccessoriesID(),selectedVehicleAccessories.getDescription(),
                notesInputText.getText().toString(),selectedVehicleAccessories.getPersonID(),selectedVehicleAsset.getAssetnum(),
                antennaSwitch.isChecked(),assistanceInfoSwitch.isChecked(),elakadasjelzoSwitch.isChecked(),emeloSwitch.isChecked(),flottakezeloiFelhSwitch.isChecked(),
                forgalmiEngedelySwitch.isChecked(),izzokeszletSwitch.isChecked(),kgfbIgazolasSwitch.isChecked(),keresztlecekSwitch.isChecked(),
                kerekkulcsSwitch.isChecked(),kerekorKulccsalSwitch.isChecked(), kezelesiUtmutatoSwitch.isChecked(),mentodobozSwitch.isChecked(),mobilParkSwitch.isChecked(),
                potkerekSwitch.isChecked(),radioKodSwitch.isChecked(),szervizKonyvSwitch.isChecked(),vonoHorogSwitch.isChecked(),
                vonoszemSwitch.isChecked(),uzemanyagkartyaSwitch.isChecked(),text_jarmukulcsai.getText().toString(),text_gumiszonyeg.getText().toString(),
                text_lathatosagimelleny.getText().toString(),text_riasztotaviranyito.getText().toString(),text_nyarigumiAcelFelnin.getText().toString(),
                text_nyarigumiKonnyuFelnin.getText().toString(),text_nyarigumiFelninelkul.getText().toString(),
                text_teligumiAcelFelnin.getText().toString(),text_teligumiKonnyuFelnin.getText().toString(),text_teligumiFelninelkul.getText().toString());
        showLoading();
        presenter.updateVehicleAccessories(accessoriesTemp, new RemoteCallBack<Boolean>() {
            @Override
            public void onSucces(Boolean successUpdate) {
                if (successUpdate){
                  //  System.out.println(" SUCCESS update");
                    Toast.makeText(getActivity(),"Success update",Toast.LENGTH_SHORT).show();
                    getChangedVehicle();
                }else{
                    Toast.makeText(getActivity(),"Unsuccess update",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getChangedVehicle(){
        presenter.getVehicleItem(selectedVehicleAsset.getAssetnum(), new RemoteCallBack<Asset>() {
            @Override
            public void onSucces(Asset changedAsset) {
                HolderSingleton.getInstance().setVehicleAsset(changedAsset);
                hideLoading();
            }
        });
        notesInputText.clearFocus();
    }


    public void showLoading() {
        loadingProgressBar.setVisibility(View.VISIBLE);
    }


    public void hideLoading() {
        loadingProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
//        System.out.println(" Onclick on +/- button");
        switch (v.getId()){
            case R.id.button_minus_jarmukulcsai:
                doSub(text_jarmukulcsai,text_jarmukulcsai.getText().toString());
                break;
            case R.id.button_plus_jarmukulcsai:
                doSum(text_jarmukulcsai,text_jarmukulcsai.getText().toString());
                break;
            case R.id.button_minus_gumiszonyeg:
                doSub(text_gumiszonyeg,text_gumiszonyeg.getText().toString());
                break;
            case R.id.button_plus_gumiszonyeg:
                doSum(text_gumiszonyeg,text_gumiszonyeg.getText().toString());
                break;
            case R.id.button_minus_lathatosagim:
                doSub(text_lathatosagimelleny,text_lathatosagimelleny.getText().toString());
                break;
            case R.id.button_plus_lathatosagim:
                doSum(text_lathatosagimelleny,text_lathatosagimelleny.getText().toString());
                break;
            case R.id.button_minus_riasztotav:
                doSub(text_riasztotaviranyito,text_riasztotaviranyito.getText().toString());
                break;
            case R.id.button_plus_riasztotav:
                doSum(text_riasztotaviranyito,text_riasztotaviranyito.getText().toString());
                break;

            case R.id.button_minus_nyari_afelni:
                doSub(text_nyarigumiAcelFelnin,text_nyarigumiAcelFelnin.getText().toString());
                break;
            case R.id.button_plus_nyari_afelni:
                doSum(text_nyarigumiAcelFelnin,text_nyarigumiAcelFelnin.getText().toString());
                break;
            case R.id.button_minus_nyari_felnin:
                doSub(text_nyarigumiFelninelkul,text_nyarigumiFelninelkul.getText().toString());
                break;
            case R.id.button_plus_nyari_felnin:
                doSum(text_nyarigumiFelninelkul,text_nyarigumiFelninelkul.getText().toString());
                break;
            case R.id.button_minus_nyari_kfelni:
                doSub(text_nyarigumiKonnyuFelnin,text_nyarigumiKonnyuFelnin.getText().toString());
                break;
            case R.id.button_plus_nyari_kfelni:
                doSum(text_nyarigumiKonnyuFelnin,text_nyarigumiKonnyuFelnin.getText().toString());
                break;


            case R.id.button_minus_teli_afelni:
                doSub(text_teligumiAcelFelnin,text_teligumiAcelFelnin.getText().toString());
                break;
            case R.id.button_plus_teli_afelni:
                doSum(text_teligumiAcelFelnin,text_teligumiAcelFelnin.getText().toString());
                break;
            case R.id.button_minus_teli_felnin:
                doSub(text_teligumiFelninelkul,text_teligumiFelninelkul.getText().toString());
                break;
            case R.id.button_plus_teli_felnin:
                doSum(text_teligumiFelninelkul,text_teligumiFelninelkul.getText().toString());
                break;
            case R.id.button_minus_teli_kfelni:
                doSub(text_teligumiKonnyuFelnin,text_teligumiKonnyuFelnin.getText().toString());
                break;
            case R.id.button_plus_teli_kfelni:
                doSum(text_teligumiKonnyuFelnin,text_teligumiKonnyuFelnin.getText().toString());
                break;


        }
    }




    private void doSum(TextView label, String initialNR){
        //+
        int initNR = Integer.parseInt(initialNR);
        int sum = initNR + 1;
        //System.out.println(" + operation [initialNR = "+initialNR+" ==> sum = "+sum+"]");
        label.setText(String.valueOf(sum));

    }

    private void doSub(TextView label, String initialNR){
        //-
        int initNR = Integer.parseInt(initialNR);
        if (initNR > 0){
            int sub = initNR - 1;
            //System.out.println(" - operation [initialNR = "+initialNR+" ==> sub = "+sub+"]");
            label.setText(String.valueOf(sub));
        }
    }


}