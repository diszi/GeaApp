package hu.ace.geaapp.scanner;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import hu.ace.geaapp.ui.base.BaseViewPresenter;

public abstract  class BaseScannerActivity extends Activity implements IOnScannerEvent {

    protected abstract BaseViewPresenter getBasePresenter();

    protected void onDestroy() {
        super.onDestroy();
        getBasePresenter().onDestroy();
        BarcodeScanner.releaseEmdk();

    }

    protected void onResume() {
        super.onResume();
//        System.out.println(" BaseScannerActivity ----------> onResume()");
        BarcodeScanner.getInstance(this);
        BarcodeScanner.registerUIobject(this);
    }


    /*public void showErrorMessage(int messageID) {
        Toast.makeText(this, messageID, Toast.LENGTH_SHORT).show();
    }*/

    public void showErrorMessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.e("------------------>" ,msg);
    }
    public void showSuccessMessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.d("------------------>" ,msg);
    }


    protected void onPause() {
        super.onPause();
//           System.out.println(" BaseScannerActivity ----------> onPause");
        BarcodeScanner.unregisterUIobject();
    }

    protected void onStop() {
        super.onStop();
//          System.out.println(" BaseScannerActivity ----------> onStop");
        BarcodeScanner.deInitScanner();
        BarcodeScanner.releaseEmdk();


    }
}
