package hu.ace.geaapp.ui.view.inspection.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.ace.geaapp.R;
import hu.ace.geaapp.data.model.AccessoriesTemp;
import hu.ace.geaapp.data.model.Asset;
import hu.ace.geaapp.data.model.StructFeatures;
import hu.ace.geaapp.data.model.StructFeaturesTemp;
import hu.ace.geaapp.singleton.HolderSingleton;
import hu.ace.geaapp.ui.base.RemoteCallBack;
import hu.ace.geaapp.ui.view.Feature;
import hu.ace.geaapp.ui.view.main.MainPresenter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StructFeaturesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StructFeaturesFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //domain: ACE_GJK_SZERK
    private static final int KIVALO = 0;
    private static final int MEGFELELO = 1;
    private static final int ROSSZ = 2;



    @BindView(R.id.radiogroup_ablakmoso)
    RadioGroup radioGroup_ablakmoso;

    @BindView(R.id.radiogroup_ablaktorlo)
    RadioGroup radioGroup_ablaktorlo;
    @BindView(R.id.radiogroup_akkumulator)
    RadioGroup radioGroup_akkumulator;
    @BindView(R.id.radiogroup_biztonsagiov)
    RadioGroup radioGroup_biztonsagiov;
    @BindView(R.id.radiogroup_futomu)
    RadioGroup radioGroup_futomuallapota;
    @BindView(R.id.radiogroup_feklampa)
    RadioGroup radioGroup_feklampa;
    @BindView(R.id.radiogroup_fekrendszer)
    RadioGroup radioGroup_fekrendszer;
    @BindView(R.id.radiogroup_gumikallapota)
    RadioGroup radioGroup_gumikallapota;
    @BindView(R.id.radiogroup_helyzetjelzo)
    RadioGroup radioGroup_helyzetjelzo;
    @BindView(R.id.radiogroup_hutorendszer)
    RadioGroup radioGroup_hutorendszer;
    @BindView(R.id.radiogroup_inditomotor)
    RadioGroup radioGroup_inditomotor;
    @BindView(R.id.radiogroup_iranyjelzo)
    RadioGroup radioGroup_iranyjelzo;
    @BindView(R.id.radiogroup_kipufogo)
    RadioGroup radioGroup_kipufogo;
    @BindView(R.id.radiogroup_kontrolllampak)
    RadioGroup radioGroup_kontrolllampak;
    @BindView(R.id.radiogroup_kormanymu)
    RadioGroup radioGroup_kormanymu;
    @BindView(R.id.radiogroup_kurt)
    RadioGroup radioGroup_kurt;
    @BindView(R.id.radiogroup_olajfolyas)
    RadioGroup radioGroup_olajfolyas;
    @BindView(R.id.radiogroup_olajnyomas)
    RadioGroup radioGroup_olajnyomas;
    @BindView(R.id.radiogroup_rogzitofek)
    RadioGroup radioGroup_rogzitofek;
    @BindView(R.id.radiogroup_tengelykapcsolo)
    RadioGroup radioGroup_tengelykapcsolo;
    @BindView(R.id.radiogroup_tolatolampa)
    RadioGroup radioGroup_tolatolampa;
    @BindView(R.id.radiogroup_tompfenyszoro)
    RadioGroup radioGroup_tompitottfenyszoro;
    @BindView(R.id.radiogroup_tavfenyszoro)
    RadioGroup radioGroup_tavolsagifenyszoro;
    @BindView(R.id.radiogroup_toltes)
    RadioGroup radioGroup_toltes;
    @BindView(R.id.radiogroup_utasterallapota)
    RadioGroup radioGroup_utasterallapota;
    @BindView(R.id.radiogroup_visszapillanto)
    RadioGroup radioGroup_visszapillantotukor;
    @BindView(R.id.radiogroup_szelvedoallapota)
    RadioGroup radioGroup_szelvedoallapota;


    @BindView(R.id.text_vehicle_type)
    TextView vehicleTypeText;
    @BindView(R.id.text_vehicle_nr)
    TextView vehicleLicPlateText;

    @BindView(R.id.saveBtn)
    Button saveBtn;

    @BindView(R.id.input_note)
    EditText inputNote;

    @BindView(R.id.actVehicleFeatures_progressBar)
    ProgressBar loadingProgressBar;



    private Asset selectedVehicleAsset;
    private StructFeatures structFeatures;
    private int aFlag;
    private boolean isChanged = false;
    private MainPresenter presenter;


    private int ablakmosoValue = -1, ablaktorloValue= -1,akkumulatorValue= -1,biztonsagiovValue= -1,futomuValue= -1, feklampaValue= -1, fekrendszerValue= -1,
        gumikallapotaValue= -1, helyzetjelzoValue= -1, hutorendszerValue= -1, inditomotorValue= -1, iranyjelzoValue= -1, kipufogoValue= -1,
        kontrolllampaValue= -1, kormanymuValue= -1, kurtValue= -1, olajfolyasValue= -1, olajnyomasValue= -1, rogzitofekValue= -1,
            tengelykapcsoloValue= -1, tolatolampaValue= -1,
        tompitottfenyszoroValue= -1, tavolsagifenyszoroValue= -1, toltesValue= -1, utasterValue= -1, visszapillantotukorValue= -1, szelvedoallapota = -1;



    public StructFeaturesFragment() {
        // Required empty public constructor
    }

    public static StructFeaturesFragment newInstance() {
        StructFeaturesFragment fragment = new StructFeaturesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//           selectedVehicleAsset = (Asset) getArguments().getSerializable(Asset.SERIALIZABLE_NAME);
//           structFeatures = selectedVehicleAsset.getStructFeatures();
//           //aFlag = getArguments().getInt("ACTIVITY_FLAG");
//        }
        selectedVehicleAsset = HolderSingleton.getInstance().getVehicleAsset();
        structFeatures = selectedVehicleAsset.getStructFeatures();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_structuralfeatures, container, false);
        ButterKnife.bind(this,view);

        hideLoading();

        presenter = new MainPresenter();

        radioGroup_ablakmoso.setOnCheckedChangeListener(this);
        radioGroup_ablaktorlo.setOnCheckedChangeListener(this);
        radioGroup_akkumulator.setOnCheckedChangeListener(this);
        radioGroup_biztonsagiov.setOnCheckedChangeListener(this);
        radioGroup_futomuallapota.setOnCheckedChangeListener(this);
        radioGroup_feklampa.setOnCheckedChangeListener(this);
        radioGroup_fekrendszer.setOnCheckedChangeListener(this);
        radioGroup_gumikallapota.setOnCheckedChangeListener(this);
        radioGroup_helyzetjelzo.setOnCheckedChangeListener(this);
        radioGroup_hutorendszer.setOnCheckedChangeListener(this);
        radioGroup_inditomotor.setOnCheckedChangeListener(this);
        radioGroup_iranyjelzo.setOnCheckedChangeListener(this);
        radioGroup_kipufogo.setOnCheckedChangeListener(this);
        radioGroup_szelvedoallapota.setOnCheckedChangeListener(this);
        radioGroup_kontrolllampak.setOnCheckedChangeListener(this);
        radioGroup_kormanymu.setOnCheckedChangeListener(this);
        radioGroup_kurt.setOnCheckedChangeListener(this);
        radioGroup_olajfolyas.setOnCheckedChangeListener(this);
        radioGroup_olajnyomas.setOnCheckedChangeListener(this);

        radioGroup_rogzitofek.setOnCheckedChangeListener(this);
        radioGroup_tengelykapcsolo.setOnCheckedChangeListener(this);
        radioGroup_tolatolampa.setOnCheckedChangeListener(this);
        radioGroup_tompitottfenyszoro.setOnCheckedChangeListener(this);
        radioGroup_tavolsagifenyszoro.setOnCheckedChangeListener(this);
        radioGroup_toltes.setOnCheckedChangeListener(this);
        radioGroup_utasterallapota.setOnCheckedChangeListener(this);
        radioGroup_visszapillantotukor.setOnCheckedChangeListener(this);

        setHeaderView();
        setBodyView();
        setFooterView(false);

        inputNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setFooterView(true);
            }
        });



        return  view;
    }

    private void setHeaderView(){
       // System.out.println(" --> SELECTED vehicle = "+selectedVehicleAsset.getAssetnum());
        vehicleLicPlateText.setText(selectedVehicleAsset.getAceAssetGJK().getLicensePlate());
        vehicleTypeText.setText(selectedVehicleAsset.getAceAssetGJK().getType());
    }


    private void setBodyView(){
        //System.out.println(" STRUCT FEATURE = "+structFeatures);
        if (structFeatures != null){
            //System.out.println("set Body View -> id="+structFeatures.getId()+"; radioGroup_ablakmoso="+radioGroup_ablakmoso+"; ablakmoso Value="+structFeatures.getAblakmoso());

            setRadioButtonChecked(structFeatures.getAblakmoso(),radioGroup_ablakmoso);
            setRadioButtonChecked(structFeatures.getAblaktorlo(),radioGroup_ablaktorlo);
            setRadioButtonChecked(structFeatures.getAkkumulator(),radioGroup_akkumulator);
            setRadioButtonChecked(structFeatures.getBiztonsagiov(),radioGroup_biztonsagiov);
            setRadioButtonChecked(structFeatures.getFutomuall(),radioGroup_futomuallapota);
            setRadioButtonChecked(structFeatures.getFeklampa(),radioGroup_feklampa);
            setRadioButtonChecked(structFeatures.getFekrendszer(),radioGroup_fekrendszer);

            setRadioButtonChecked(structFeatures.getGumikall(),radioGroup_gumikallapota);
            setRadioButtonChecked(structFeatures.getHelyzetjelzo(),radioGroup_helyzetjelzo);
            setRadioButtonChecked(structFeatures.getHutorendszer(),radioGroup_hutorendszer);
            setRadioButtonChecked(structFeatures.getInditomotor(),radioGroup_inditomotor);
            setRadioButtonChecked(structFeatures.getIranyjelzo(),radioGroup_iranyjelzo);
            setRadioButtonChecked(structFeatures.getKipufogorendszer(),radioGroup_kipufogo);
            setRadioButtonChecked(structFeatures.getKontrolllampak(),radioGroup_kontrolllampak);

            setRadioButtonChecked(structFeatures.getKormanymu(),radioGroup_kormanymu);
            setRadioButtonChecked(structFeatures.getKurt(),radioGroup_kurt);
            setRadioButtonChecked(structFeatures.getOlajfolyas(),radioGroup_olajfolyas);
            setRadioButtonChecked(structFeatures.getOlajnyomas(),radioGroup_olajnyomas);
            setRadioButtonChecked(structFeatures.getRogzitofek(),radioGroup_rogzitofek);
            setRadioButtonChecked(structFeatures.getTengelykapcsolo(),radioGroup_tengelykapcsolo);

            setRadioButtonChecked(structFeatures.getTolatolampa(),radioGroup_tolatolampa);
            setRadioButtonChecked(structFeatures.getTompitottfenyszoro(),radioGroup_tompitottfenyszoro);
            setRadioButtonChecked(structFeatures.getTavolsagifenyszoro(),radioGroup_tavolsagifenyszoro);
            setRadioButtonChecked(structFeatures.getToltes(),radioGroup_toltes);
            setRadioButtonChecked(structFeatures.getUtasterall(),radioGroup_utasterallapota);
            setRadioButtonChecked(structFeatures.getVisszapillanto(),radioGroup_visszapillantotukor);
            setRadioButtonChecked(structFeatures.getSzelvedoall(),radioGroup_szelvedoallapota);

        }


    }

    private void setFooterView(boolean isChanged){
        saveBtn.setVisibility(isChanged ? View.VISIBLE : View.GONE);
    }

    private void setRadioButtonChecked(int activeBtnIndex, RadioGroup radioGroup){
       /* View radioButton = radioGroup.findViewById(activeBtnIndex);
        System.out.println(" RADIO BTN checked ID= "+radioButton.getId()+"; radiogroup ID= "+radioGroup.getId());
        radioGroup.check(radioButton.getId());
        setValue(activeBtnIndex,radioGroup);*/
        RadioButton radioButton = (RadioButton) radioGroup.getChildAt(activeBtnIndex);
       // System.out.println(" RADIO BTN = "+radioButton);
        radioButton.setChecked(true);
    }

    /*RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            View radioButton = radioGroup_ablakmoso.findViewById(checkedId);
            int index = radioGroup_ablakmoso.indexOfChild(radioButton);

            switch (index){
                case KIVALO:
                    System.out.println(" Your choose is KIVALO");
                    break;
                case MEGFELELO:
                    System.out.println(" Your choose is MEGFELELO");
                    break;
                case ROSSZ:
                    System.out.println(" Your choose is ROSSZ");
                    break;
                default:
                    break;
            }
        }
    };*/

    @OnClick(R.id.saveBtn)
    public void onClickOnSaveChangesButton(){
        //System.out.println(" SAVE SZERKEZETI jellemzok");
        StructFeaturesTemp structFeaturesTemp = new StructFeaturesTemp(structFeatures.getId(),structFeatures.getDescription(),ablakmosoValue,ablaktorloValue,
                akkumulatorValue,biztonsagiovValue,futomuValue,
                feklampaValue, fekrendszerValue, gumikallapotaValue, helyzetjelzoValue, hutorendszerValue,inditomotorValue,iranyjelzoValue,kipufogoValue,
                kontrolllampaValue,kormanymuValue,kurtValue,olajfolyasValue,olajnyomasValue,rogzitofekValue,tengelykapcsoloValue,
                tolatolampaValue,tompitottfenyszoroValue,tavolsagifenyszoroValue,toltesValue,utasterValue,visszapillantotukorValue,
                szelvedoallapota,inputNote.getText().toString());
        showLoading();
        presenter.updateVehicleStructuralFeatures(structFeaturesTemp, new RemoteCallBack<Boolean>() {
            @Override
            public void onSucces(Boolean updateValue) {
                if (updateValue){
                  //  System.out.println(" SUCCESS UPDATE on structural features");
                    Toast.makeText(getActivity(),"Success update",Toast.LENGTH_SHORT).show();
                    getChangedVehicle();
                }else{
                   // System.out.println(" UNSUCCESS UPDATE");
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
    }

    public void showLoading() {
        loadingProgressBar.setVisibility(View.VISIBLE);
    }


    public void hideLoading() {
        loadingProgressBar.setVisibility(View.GONE);
    }




    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        View radioButton = group.findViewById(checkedId);
        int index = group.indexOfChild(radioButton);

      //  System.out.println(" group = "+group+"; ID="+checkedId+"; radioBtn = "+radioButton+"; checked btn ID="+group.getCheckedRadioButtonId());
        /*System.out.println(" --> ENUM [ KIVALO = "+ Feature.KIVALO +" & "+Feature.KIVALO.getFeatureType()+"]");//ENUM [ KIVALO = KIVALO & 0]
*/
       // isChanged = true;
        setFooterView(true);

        switch (index){
            case KIVALO:
               // System.out.println("  is KIVALO ");
                setValue(KIVALO,group);
                break;
            case MEGFELELO:
               // System.out.println(" is MEGFELELO");
                setValue(MEGFELELO,group);
                break;
            case ROSSZ:
               // System.out.println(" is ROSSZ ");
                setValue(ROSSZ,group);
                break;
            default:
                break;
        }

    }

    private void setValue(int featureValue, RadioGroup radioGroup){
        /*System.out.println(" RADIOGROUP = "+radioGroup+"; "+radioGroup.getId()+"; ablakmoso ID = "+ R.id.radiogroup_ablakmoso);
        if (radioGroup.getId() == R.id.radiogroup_ablakmoso){
            ablakmosoValue = featureValue;
        }
        System.out.println(" ABLAKMOSO VALUE = "+ablakmosoValue);*/
       // System.out.println(" FEATURE VALUE = "+featureValue+"; radiogroup="+radioGroup.getId());
        switch (radioGroup.getId()){
            case R.id.radiogroup_ablakmoso:
                    ablakmosoValue = featureValue;
                    break;
            case R.id.radiogroup_ablaktorlo:
                ablaktorloValue = featureValue;
                break;
            case R.id.radiogroup_akkumulator:
                akkumulatorValue = featureValue;
                break;
            case R.id.radiogroup_biztonsagiov:
                biztonsagiovValue = featureValue;
                break;
            case R.id.radiogroup_feklampa:
                feklampaValue = featureValue;
                break;
            case R.id.radiogroup_fekrendszer:
                fekrendszerValue = featureValue;
                break;
            case R.id.radiogroup_futomu:
                futomuValue = featureValue;
                break;
            case R.id.radiogroup_gumikallapota:
                gumikallapotaValue = featureValue;
                break;
            case R.id.radiogroup_helyzetjelzo:
                helyzetjelzoValue = featureValue;
                break;
            case R.id.radiogroup_hutorendszer:
                hutorendszerValue = featureValue;
                break;
            case R.id.radiogroup_inditomotor:
                inditomotorValue = featureValue;
                break;
            case R.id.radiogroup_iranyjelzo:
                iranyjelzoValue = featureValue;
                break;
            case R.id.radiogroup_kipufogo:
                kipufogoValue = featureValue;
                break;

            case R.id.radiogroup_kontrolllampak:
                kontrolllampaValue = featureValue;
                break;
            case R.id.radiogroup_kormanymu:
                kormanymuValue = featureValue;
                break;
            case R.id.radiogroup_kurt:
                kurtValue = featureValue;
                break;
            case R.id.radiogroup_olajfolyas:
                olajfolyasValue = featureValue;
                break;
            case R.id.radiogroup_olajnyomas:
                olajnyomasValue = featureValue;
                break;
            case R.id.radiogroup_rogzitofek:
                rogzitofekValue = featureValue;
                break;
            case R.id.radiogroup_szelvedoallapota:
                szelvedoallapota =  featureValue;
                break;
            case R.id.radiogroup_tavfenyszoro:
                tavolsagifenyszoroValue = featureValue;
                break;
            case R.id.radiogroup_tengelykapcsolo:
                tengelykapcsoloValue = featureValue;
                break;
            case R.id.radiogroup_tompfenyszoro:
                tompitottfenyszoroValue = featureValue;
                break;

            case R.id.radiogroup_tolatolampa:
                tolatolampaValue = featureValue;
                break;
            case R.id.radiogroup_toltes:
                toltesValue = featureValue;
                break;
            case R.id.radiogroup_utasterallapota:
                utasterValue = featureValue;
                break;
            case R.id.radiogroup_visszapillanto:
                visszapillantotukorValue = featureValue;
                break;


        }

    }
}