package hu.ace.geaapp.ui.view.inspection.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.ace.geaapp.R;
import hu.ace.geaapp.data.model.Asset;
import hu.ace.geaapp.singleton.HolderSingleton;
import hu.ace.geaapp.ui.view.inspection.adapter.VignetteAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VignetteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VignetteFragment extends Fragment {



    private Asset selectedVehicleAsset;
    private VignetteAdapter adapter;

    @BindView(R.id.recyclerview_vignette)
    RecyclerView mRecyclerView;
//    @BindView(R.id.actVehicleVignette_progressBar)
//    ProgressBar compLoadingProgressBar;
    @BindView(R.id.text_vehicle_nr)
    TextView vehicleNR;
    @BindView(R.id.text_vehicle_type)
    TextView vehicleType;
    @BindView(R.id.text_empty)
    TextView emptyText;


    public VignetteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment VignetteFragment.
     */
    // TODO: Rename and change types and number of parameters
    /*public static VignetteFragment newInstance(String param1, String param2) {
        VignetteFragment fragment = new VignetteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/


    public static VignetteFragment newInstance() {
        VignetteFragment fragment = new VignetteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            selectedVehicleAsset = (Asset) getArguments().getSerializable(Asset.SERIALIZABLE_NAME);
//        }
        selectedVehicleAsset = HolderSingleton.getInstance().getVehicleAsset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vignette, container, false);
        ButterKnife.bind(this,view);

        this.setupRecyclerView();
        setHeaderView();

        return view;
    }

    private void setHeaderView(){
        vehicleNR.setText(selectedVehicleAsset.getAceAssetGJK().getLicensePlate());
        vehicleType.setText(selectedVehicleAsset.getAceAssetGJK().getType());
    }

    private void setupRecyclerView(){
        Context context = this.getContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        this.adapter = new VignetteAdapter(this);
        if (selectedVehicleAsset.getAssetVignetteList().isEmpty()){
            emptyText.setVisibility(View.VISIBLE);
        }else{
            emptyText.setVisibility(View.GONE);
            this.adapter.setVignetteList(selectedVehicleAsset.getAssetVignetteList());
        }


        this.mRecyclerView.setLayoutManager(layoutManager);
        this.mRecyclerView.setAdapter(this.adapter);
    }
}