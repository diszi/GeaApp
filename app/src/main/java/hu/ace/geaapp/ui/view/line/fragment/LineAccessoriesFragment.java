package hu.ace.geaapp.ui.view.line.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.ace.geaapp.R;
import hu.ace.geaapp.data.model.Asset;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LineAccessoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LineAccessoriesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private Asset selectedVehicle;

/*
    @BindView(R.id.button_endTransfer)
    Button buttonEndTransfer;*/


    public LineAccessoriesFragment() {
        // Required empty public constructor
    }

    public static LineAccessoriesFragment newInstance(Asset asset) {
        // System.out.println(" FRAGMENT * new Instance - "+param1);
        LineAccessoriesFragment fragment = new LineAccessoriesFragment();
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
           selectedVehicle = (Asset) getArguments().getSerializable(Asset.SERIALIZABLE_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_line_accessories, container, false);
        ButterKnife.bind(this,view);


        return view;
    }
/*
    @OnClick(R.id.button_endTransfer)
    public void onClickonEndButton(){
        System.out.println(" END of tranfer --> launch authentication layout");
    }*/
}