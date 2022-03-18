package hu.ace.geaapp.ui.view.main;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.ace.geaapp.R;
import hu.ace.geaapp.data.model.Asset;
import hu.ace.geaapp.data.model.StructFeatures;
import hu.ace.geaapp.ui.base.RemoteCallBack;
import hu.ace.geaapp.ui.component.OnBackPressedDialog;
import hu.ace.geaapp.ui.view.inspection.activity.InspectionMainActivity;



public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview_search)
    RecyclerView mRecyclerview;
    @BindView(R.id.actSearchVehicle_progressBar)
    ProgressBar loadingProgressBar;

    @BindView(R.id.textview_numberofvehicle)
    TextView text_nrofvehicle;
    @BindView(R.id.searchview_car)
    SearchView vehicleSearchView;


    private SearchAdapter adapter;

    //private InspectionPresenter presenter;
    private MainPresenter  mainPresenter;


    private int activityFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_inspection);
        ButterKnife.bind(this);
        hideLoading();

        //presenter = new InspectionPresenter();
        mainPresenter = new MainPresenter();

        this.setupRecyclerView();

        getVehicleList();

        vehicleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }


    private void setupRecyclerView(){
        Context context = getApplicationContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        this.adapter = new SearchAdapter(this,activityFlag);
        this.mRecyclerview.setLayoutManager(layoutManager);
        this.mRecyclerview.setAdapter(this.adapter);
    }

    private void getVehicleList(){
        showLoading();
        /*presenter.getVehicles(new RemoteCallBack<List<Asset>>() {
            @Override
            public void onSucces(List<Asset> assetList) {
                //if (assetList.size() > 0){
                text_nrofvehicle.setText("Gépjárművek száma: "+assetList.size());
                adapter.setVehiclesList(assetList);
                hideLoading();
               // setTartozekok(assetList);
                // }
            }
        });*/

        mainPresenter.getVehicles(new RemoteCallBack<List<Asset>>() {
            @Override
            public void onSucces(List<Asset> assetList) {
                //if (assetList.size() > 0){
                text_nrofvehicle.setText("Gépjárművek száma: "+assetList.size());
                adapter.setVehiclesList(assetList);
               //setSzerkezetiJellemzok(assetList);
                hideLoading();
                // setTartozekok(assetList);
                // }
            }
        });
    }

   /* private void setSzerkezetiJellemzok(List<Asset> assetList){
        StructFeatures structFeatures = new StructFeatures(1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,
                1,"test");
        for (int i=5;i<assetList.size();i++){
            presenter.addFeatures(assetList.get(i),structFeatures);
        }
    }*/


    /*private void setTartozekok(List<Asset> assetList){
        for(Asset asset : assetList){
            presenter.addTartozekok(asset);
        }
    }*/

    public void showLoading() {
        loadingProgressBar.setVisibility(View.VISIBLE);
    }


    public void hideLoading() {
        loadingProgressBar.setVisibility(View.GONE);
    }




   /* public void launchCarDetailsActivity(Car selectedCar){
        Intent intent = new Intent(this, InspectionMainActivity.class);
        intent.putExtra(Car.SERIALIZABLE_NAME, selectedCar);
        startActivity(intent);
    }*/


    public void launchVehicleDetailsActivity(Asset vehicle){
        Intent intent = new Intent(this,InspectionMainActivity.class);
        intent.putExtra(Asset.SERIALIZABLE_NAME, vehicle);
        //intent.putExtra("ACTIVITY_FLAG",activityFlag);
        startActivity(intent);

    }
/*
    public void launchVehicleLineDetailsActivity(Asset vehicle){
        Intent intent = new Intent(this, VehicleDetailsActivity.class);
        intent.putExtra(Asset.SERIALIZABLE_NAME,vehicle);
        startActivity(intent);
    }*/

    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();
        OnBackPressedDialog alertDialogFragment = new OnBackPressedDialog();
        alertDialogFragment.show(fm,"AlertDialogFragment");
        //super.onBackPressed();
        //System.out.println(" onBackPressed --> SearchActivity");
    }
}
