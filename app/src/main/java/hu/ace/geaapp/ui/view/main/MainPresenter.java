package hu.ace.geaapp.ui.view.main;

import android.app.Activity;
import android.util.Log;

import androidx.fragment.app.Fragment;

import java.util.List;


import hu.ace.geaapp.app.Urls;
import hu.ace.geaapp.data.model.Accessories;
import hu.ace.geaapp.data.model.AccessoriesTemp;
import hu.ace.geaapp.data.model.Asset;
import hu.ace.geaapp.data.model.DamageTemp;
import hu.ace.geaapp.data.model.StructFeatures;
import hu.ace.geaapp.data.model.StructFeaturesTemp;
import hu.ace.geaapp.data.model.damages.AceAssetDamage;
import hu.ace.geaapp.data.network.HttpCall;
import hu.ace.geaapp.data.network.HttpRequestAsyncTask;
import hu.ace.geaapp.data.remote.AddDamageSOAP;
import hu.ace.geaapp.data.remote.UpdateAccessoriesSOAP;
import hu.ace.geaapp.data.remote.UpdateStructuralFeaturesSOAP;
import hu.ace.geaapp.singleton.HolderSingleton;
import hu.ace.geaapp.ui.base.BasePresenter;
import hu.ace.geaapp.ui.base.BaseViewPresenter;
import hu.ace.geaapp.ui.base.RemoteCallBack;
import hu.ace.geaapp.ui.view.inspection.dialog.DamageDialog;
import hu.ace.geaapp.ui.view.inspection.fragment.AccessoriesFragment;
import hu.ace.geaapp.ui.view.inspection.fragment.DamageFragment;
import hu.ace.geaapp.utils.EntityMapper;
import hu.ace.geaapp.utils.InspectionEntityMapper;
import io.reactivex.Observable;

public class MainPresenter extends BasePresenter implements BaseViewPresenter {


    @Override
    public void onDestroy() {

    }

    //get List of vehicle
    public void getVehicles(RemoteCallBack<List<Asset>> remoteCallBack){
        HttpCall httpCall = new HttpCall();
        httpCall.setMethod(HttpCall.RequestMethod.GET);
        //httpCall.setUrl(Urls.GET_VEHICLES_LIST);//http://192.168.133.51/maxrest/rest/os/ACE_GEA_ASSET?_dropnulls=0&STATUS=ACTIVE&CLASSSTRUCTUREID=1053
        httpCall.setUrl("http://192.168.133.51/maxrest/rest/os/ACE_GEA_ASSET?_dropnulls=0&STATUS=ACTIVE&CLASSSTRUCTUREID=1053&ASSETTYPE=VEHICLE");
        new HttpRequestAsyncTask(){
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                if (response != null){
                    List<Asset> vehiclesList = EntityMapper.transformVehiclesList(response);
                    remoteCallBack.onSucces(vehiclesList);
                    Log.d("---------->","Load VEHICLES list : success");
                }else{
                    Log.e("----------> ","Load VEHICLES List : ERROR ");
                }
            }
        }.execute(httpCall);

    }


    //get actual vehicle accesssories
    public void getVehicleAccessories(Asset asset, RemoteCallBack<Accessories> remoteCallBack){
        HttpCall httpCall = new HttpCall();
        httpCall.setMethod(HttpCall.RequestMethod.GET);
        httpCall.setUrl(Urls.GET_VEHICLE+"&ASSETNUM="+asset.getAssetnum());

        new HttpRequestAsyncTask(){
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                if (response != null){
                    Asset vehicleAsset = EntityMapper.transformVehicleItem(response);
                    if (vehicleAsset != null){
                        remoteCallBack.onSucces(vehicleAsset.getAccessories());
                    }else{
                        remoteCallBack.onSucces(null);
                    }

                    /*Asset vehicle = InspectionEntityMapper.transformVehicle(response);
                    remoteCallBack.onSucces(vehicle);*/
                    Log.d("---------->","Load VEHICLES list : success");
                }else{
                    Log.e("----------> ","Load VEHICLES List : ERROR ");
                }
            }
        }.execute(httpCall);
    }


    public void updateVehicleAccessories(AccessoriesTemp accessoriesTemp,RemoteCallBack<Boolean> remoteCallBack){
        System.out.println(" -------------> PRESENTER.updateAsset Accessories");
        Observable<String> addInvuseLineRemoteObservable = new UpdateAccessoriesSOAP<>(accessoriesTemp).createObserver();
        Log.d("------------------>"," Subscribe to Observable");
        addDisposableToList(addInvuseLineRemoteObservable
                .subscribe((id) -> { // onNext Consumer
                    remoteCallBack.onSucces(true);
                }, (throwable) -> { // onError Consumer
                    getErrorMessage(throwable);
                    remoteCallBack.onSucces(false);
                    Log.d("-------------->", "Don't get data", throwable);
                }, () -> { // onComplate Action
                   // getVehicleItem(HolderSingleton.getInstance().getVehicleAsset().getAssetnum());
                }));


    }

    public void updateVehicleStructuralFeatures(StructFeaturesTemp structFeatures, RemoteCallBack<Boolean> remoteCallBack){
        Observable<String> addInvuseLineRemoteObservable = new UpdateStructuralFeaturesSOAP<>(structFeatures).createObserver();
        Log.d("------------------>"," Subscribe to Observable");
        addDisposableToList(addInvuseLineRemoteObservable
                .subscribe((id) -> { // onNext Consumer
                    remoteCallBack.onSucces(true);
                }, (throwable) -> { // onError Consumer
                    getErrorMessage(throwable);
                    remoteCallBack.onSucces(false);
                    Log.d("-------------->", "Don't get data", throwable);
                }, () -> { // onComplate Action
                    // getVehicleItem(HolderSingleton.getInstance().getVehicleAsset().getAssetnum());
                }));
    }

    //get selected Vehicle details
    public void getVehicleItem(String assetnumber, RemoteCallBack<Asset> remoteCallBack){
        Log.i("------------->","MainPresenter -> getVehicle item after change tartozekok ");
            HttpCall httpCall = new HttpCall();
            httpCall.setMethod(HttpCall.RequestMethod.GET);
            httpCall.setUrl("http://192.168.133.51/maxrest/rest/os/ACE_GEA_ASSET?_dropnulls=0&STATUS=ACTIVE&CLASSSTRUCTUREID=1053&ASSETNUM="+assetnumber);

            new HttpRequestAsyncTask(){
                @Override
                public void onResponse(String response) {
                    super.onResponse(response);
                    if (response != null){
                        Asset vehicle = EntityMapper.transformVehicleItem(response);
                        System.out.println(" GET VEHICLE result = "+vehicle.getAssetnum());
                        remoteCallBack.onSucces(vehicle);

                        Log.d("---------->","Load VEHICLES list : success");
                    }else{
                        Log.e("----------> ","Load VEHICLES List : ERROR ");
                    }
                }
            }.execute(httpCall);

    }

    public void getVehicleDamages(String assetnum, RemoteCallBack<List<AceAssetDamage>> remoteCallBack){
        HttpCall httpCall = new HttpCall();
        httpCall.setMethod(HttpCall.RequestMethod.GET);
        httpCall.setUrl("http://192.168.133.51/maxrest/rest/os/ACE_GEA_ASSET?_dropnulls=0&STATUS=ACTIVE&CLASSSTRUCTUREID=1053&ASSETNUM="+assetnum);
        new HttpRequestAsyncTask(){
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                if (response != null){
                    List<AceAssetDamage> assetDamageList = EntityMapper.transformVehicleDamageList(response);
                    remoteCallBack.onSucces(assetDamageList);
                    /*Asset vehicle = InspectionEntityMapper.transformVehicle(response);
                    remoteCallBack.onSucces(vehicle);*/
                    Log.d("---------->","Load DAMAGES list : success");
                }else{
                    Log.e("----------> ","Load DAMAGES List : ERROR ");
//                    remoteCallBack.onSucces(new ArrayList<>());
                }
            }
        }.execute(httpCall);
    }


    public void addDamage(AceAssetDamage damage, Asset vehicleItem, DamageFragment fragment, DamageDialog dialog){
        Log.d("------------------>"," add DAMAGE : ["+damage.getDamageEnum().getDamageType()+"; added damage color ="+damage.getCustomView().getColor()+"]");
        Observable<String> addInvuseLineRemoteObservable = new AddDamageSOAP<>(damage,vehicleItem).createObserver();
//        Observable<String> addInvuseLineRemoteObservable = new AddDamageSOAP<>(damage,addedDamage,vehicleItem).createObserver();
        Log.d("------------------>"," Subscribe to Observable");
        addDisposableToList(addInvuseLineRemoteObservable
                .subscribe((id) -> { // onNext Consumer
                    //getDamagesAfterUpdate(vehicleItem.getAssetnum(),fragment,dialog);
                    //fragment.continueDrawing(addedDamage.getDamageType(),addedDamage.getColor());
                    fragment.continueDrawingAfterSave(damage.getDamageEnum(),damage.getCustomView().getColor());
                    dialog.dismiss();
                }, (throwable) -> { // onError Consumer
                    getErrorMessage(throwable);
                    Log.d("-------------->", "Don't get data", throwable);
                }, () -> { // onComplate Action

                }));
    }

    public void deleteDamage(AceAssetDamage deletedDamage,DamageFragment mfragment, RemoteCallBack<Boolean> remoteCallBack){
        HttpCall httpCall = new HttpCall();
        httpCall.setMethod(HttpCall.RequestMethod.DELETE);

        //http://192.168.133.51/maxrest/rest/os/ACE_GJK_KAR/23
        httpCall.setUrl(Urls.DELETE_DAMAGE+deletedDamage.getDamageID());
        new HttpRequestAsyncTask(){
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
               // System.out.println(" RESPONSE after DELETE => "+response);
                if (response != null){
                    Log.d("---------->","DELETE DAMAGE : success");
                    mfragment.removeSelectedDamage(deletedDamage);
                   // remoteCallBack.onSucces(true);
                   /* I/---------->: connection: 404; Not Found
                    I/System.out:  connection response = Error 404: BMXAA8252E - An object structure with the name ACE_GJK_KAR cannot be found. Correct the name in the path and then resubmit the request.
                            I/System.out:  RESPONSE after DELETE => Error 404: BMXAA8252E - An object structure with the name ACE_GJK_KAR cannot be found. Correct the name in the path and then resubmit the request.
                            D/---------->: DELETE DAMAGE : success*/

                }else{
                    Log.e("----------> ","DELETE DAMAGE : ERROR ");
                }
            }
        }.execute(httpCall);
    }
}
