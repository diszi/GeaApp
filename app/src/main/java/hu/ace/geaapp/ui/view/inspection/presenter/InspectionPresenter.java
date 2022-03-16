package hu.ace.geaapp.ui.view.inspection.presenter;

import android.util.Log;

import java.util.List;

import hu.ace.geaapp.app.Urls;
import hu.ace.geaapp.data.model.damages.AceAssetDamage;
import hu.ace.geaapp.data.model.Asset;
import hu.ace.geaapp.data.model.DamageTemp;
import hu.ace.geaapp.data.model.StructFeatures;
import hu.ace.geaapp.data.network.HttpCall;
import hu.ace.geaapp.data.network.HttpRequestAsyncTask;
import hu.ace.geaapp.data.remote.AddDamageSOAP;
import hu.ace.geaapp.data.remote.AddFeaturesSOAP;
import hu.ace.geaapp.ui.base.BasePresenter;
import hu.ace.geaapp.ui.base.BaseViewPresenter;
import hu.ace.geaapp.ui.base.RemoteCallBack;
import hu.ace.geaapp.ui.view.inspection.dialog.DamageDialog;
import hu.ace.geaapp.ui.view.inspection.fragment.DamageFragment;
import hu.ace.geaapp.utils.InspectionEntityMapper;
import io.reactivex.Observable;

public class InspectionPresenter extends BasePresenter implements BaseViewPresenter {

    public InspectionPresenter(){}

    /*public void getVehicles(RemoteCallBack<List<Asset>> remoteCallBack){
        HttpCall httpCall = new HttpCall();
        httpCall.setMethod(HttpCall.RequestMethod.GET);
        httpCall.setUrl(Urls.GET_VEHICLES_LIST);

        new HttpRequestAsyncTask(){
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                if (response != null){
                    List<Asset> vehiclesList = InspectionEntityMapper.transformVehiclesList(response);
                    remoteCallBack.onSucces(vehiclesList);
                    Log.d("---------->","Load VEHICLES list : success");
                }else{
                    Log.e("----------> ","Load VEHICLES List : ERROR ");
                }
            }
        }.execute(httpCall);

    }*/

    /*public void getVehicle(String assetnum, RemoteCallBack<Asset> remoteCallBack){
        Log.i("------------->","InspectionPresenter -> getVehicle "+assetnum);
        HttpCall httpCall = new HttpCall();
        httpCall.setMethod(HttpCall.RequestMethod.GET);
        httpCall.setUrl("http://192.168.133.51/maxrest/rest/os/ACE_GEA_ASSET?_dropnulls=0&STATUS=ACTIVE&CLASSSTRUCTUREID=1053&ASSETNUM="+assetnum);

        //connection response = Error 400: BMXAA3851E - Connecting to the server has been temporarily disabled.
        new HttpRequestAsyncTask(){
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                if (response != null){
                    Asset vehicle = InspectionEntityMapper.transformVehicle(response);
                    remoteCallBack.onSucces(vehicle);
                    Log.d("---------->","Load VEHICLES list : success");
                }else{
                    Log.e("----------> ","Load VEHICLES List : ERROR ");
                }
            }
        }.execute(httpCall);
    }*/

   /* public void getVehicleDamages(String assetnum, RemoteCallBack<List<AceAssetDamage>> remoteCallBack){
        HttpCall httpCall = new HttpCall();
        httpCall.setMethod(HttpCall.RequestMethod.GET);
        httpCall.setUrl("http://192.168.133.51/maxrest/rest/os/ACE_GEA_ASSET?_dropnulls=0&STATUS=ACTIVE&CLASSSTRUCTUREID=1053&ASSETNUM="+assetnum);
        new HttpRequestAsyncTask(){
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                if (response != null){
                    List<AceAssetDamage> assetDamageList = InspectionEntityMapper.transformVehicleDamageList(response);
                    remoteCallBack.onSucces(assetDamageList);
                    Log.d("---------->","Load DAMAGES list : success");
                }else{
                    Log.e("----------> ","Load DAMAGES List : ERROR ");
//                    remoteCallBack.onSucces(new ArrayList<>());
                }
            }
        }.execute(httpCall);
    }*/

/*
    public void addDamage(DamageTemp addedDamage, Asset vehicleItem, DamageFragment fragment, DamageDialog dialog){
        Log.d("------------------>"," add DAMAGE : ["+addedDamage.getDamageType().getDamageType()+"]");

        Observable<String> addInvuseLineRemoteObservable = new AddDamageSOAP<>(addedDamage,vehicleItem).createObserver();
        Log.d("------------------>"," Subscribe to Observable");
        addDisposableToList(addInvuseLineRemoteObservable
                .subscribe((id) -> { // onNext Consumer
                    //getDamagesAfterUpdate(vehicleItem.getAssetnum(),fragment,dialog);
                    //fragment.continueDrawing(addedDamage.getDamageType(),addedDamage.getColor());
                    fragment.continueDrawingAfterSave(addedDamage.getDamageType(),addedDamage.getColor());
                    dialog.dismiss();
                }, (throwable) -> { // onError Consumer
                    getErrorMessage(throwable);
                    Log.d("-------------->", "Don't get data", throwable);
                }, () -> { // onComplate Action

                }));
    }*/

   /* public void getDamagesAfterUpdate(String assetnum, DamageFragment fragment,DamageDialog dialog){
        HttpCall httpCall = new HttpCall();
        httpCall.setMethod(HttpCall.RequestMethod.GET);
        httpCall.setUrl("http://192.168.133.51/maxrest/rest/os/ACE_GEA_ASSET?_dropnulls=0&STATUS=ACTIVE&CLASSSTRUCTUREID=1053&ASSETNUM="+assetnum);
        new HttpRequestAsyncTask(){
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                if (response != null){
                    List<AceAssetDamage> assetDamageList = InspectionEntityMapper.transformVehicleDamageList(response);

                    Log.d("---------->","Load DAMAGES list : success");
                }else{
                    Log.e("----------> ","Load DAMAGES List : ERROR ");
//                    remoteCallBack.onSucces(new ArrayList<>());
                }
            }
        }.execute(httpCall);
    }*/

   /* public void deleteDamage(AceAssetDamage deletedDamage, Asset vehicleItem, RemoteCallBack<Boolean> remoteCallBack){
        HttpCall httpCall = new HttpCall();
        httpCall.setMethod(HttpCall.RequestMethod.DELETE);

        //http://192.168.133.51/maxrest/rest/os/ACE_GJK_KAR/23
        httpCall.setUrl(Urls.DELETE_DAMAGE+deletedDamage.getDamageID());
        new HttpRequestAsyncTask(){
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                System.out.println(" RESPONSE after DELETE => "+response);
                if (response != null){
                    remoteCallBack.onSucces(true);
                   // I/---------->: connection: 404; Not Found
                   // I/System.out:  connection response = Error 404: BMXAA8252E - An object structure with the name ACE_GJK_KAR cannot be found. Correct the name in the path and then resubmit the request.
                           // I/System.out:  RESPONSE after DELETE => Error 404: BMXAA8252E - An object structure with the name ACE_GJK_KAR cannot be found. Correct the name in the path and then resubmit the request.
                            //D/---------->: DELETE DAMAGE : success
                    Log.d("---------->","DELETE DAMAGE : success");
                }else{
                    Log.e("----------> ","DELETE DAMAGE : ERROR ");
                }
            }
        }.execute(httpCall);
    }*/

/*
    public void addTartozekok(Asset asset){
        Observable<String> addInvuseLineRemoteObservable = new AddAccessoriesSOAP<>(asset).createObserver();
        Log.d("------------------>"," Subscribe to Observable");
        addDisposableToList(addInvuseLineRemoteObservable
                .subscribe((id) -> { // onNext Consumer

                }, (throwable) -> { // onError Consumer
                    getErrorMessage(throwable);
                    Log.d("-------------->", "Don't get data", throwable);
                }, () -> { // onComplate Action

                }));
    }*/

    /*public void addFeatures(Asset asset){
        Observable<String> addInvuseLineRemoteObservable = new AddFeaturesSOAP<>(asset).createObserver();
        Log.d("------------------>"," Subscribe to Observable");
        addDisposableToList(addInvuseLineRemoteObservable
                .subscribe((id) -> { // onNext Consumer

                }, (throwable) -> { // onError Consumer
                    getErrorMessage(throwable);
                    Log.d("-------------->", "Don't get data", throwable);
                }, () -> { // onComplate Action

                }));
    }*/

    /*public void addFeatures(Asset asset, StructFeatures feature){
        Observable<String> addInvuseLineRemoteObservable = new AddFeaturesSOAP<>(asset,feature).createObserver();
        Log.d("------------------>"," Subscribe to Observable");
        addDisposableToList(addInvuseLineRemoteObservable
                .subscribe((id) -> { // onNext Consumer

                }, (throwable) -> { // onError Consumer
                    getErrorMessage(throwable);
                    Log.d("-------------->", "Don't get data", throwable);
                }, () -> { // onComplate Action

                }));
    }*/
    @Override
    public void onDestroy() {

    }
}
