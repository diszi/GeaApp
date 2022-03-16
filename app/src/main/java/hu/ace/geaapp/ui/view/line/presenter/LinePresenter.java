package hu.ace.geaapp.ui.view.line.presenter;

import android.util.Log;

import hu.ace.geaapp.app.Urls;
import hu.ace.geaapp.data.model.Asset;
import hu.ace.geaapp.data.network.HttpCall;
import hu.ace.geaapp.data.network.HttpRequestAsyncTask;
import hu.ace.geaapp.ui.base.BasePresenter;
import hu.ace.geaapp.ui.base.BaseViewPresenter;
import hu.ace.geaapp.ui.base.RemoteCallBack;
import hu.ace.geaapp.utils.InspectionEntityMapper;

public class LinePresenter extends BasePresenter implements BaseViewPresenter {
    @Override
    public void onDestroy() {

    }


    /*public void getVehicleDamages(String assetnum, RemoteCallBack<Asset> remoteCallBack){
        Log.i("------------->","InspectionPresenter -> getVehicle "+assetnum);
        HttpCall httpCall = new HttpCall();
        httpCall.setMethod(HttpCall.RequestMethod.GET);
        httpCall.setUrl(Urls.GET_VEHICLE+"&ASSETNUM="+assetnum);

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

}
