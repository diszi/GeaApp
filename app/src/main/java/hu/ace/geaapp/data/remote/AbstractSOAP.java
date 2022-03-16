package hu.ace.geaapp.data.remote;

import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import javax.xml.parsers.ParserConfigurationException;

import hu.ace.geaapp.R;
import hu.ace.geaapp.data.network.NetworkTool;
import hu.ace.geaapp.utils.UIThrowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class AbstractSOAP<T> {

    public static String SOAP_ACTION = "urn:processDocument";

    public Observable<T> createObserver() {
        Observable<T> result = Observable.create( emitter-> {
            try {
                Log.d("------------------>", " Start Remote SOAP Call");
                HttpURLConnection connection = null;
                InputStream inputStream = null;
                try {

                    connection = NetworkTool.createSOAPConnection(getSOAPURL(), getSOAPAction(), getSOAPPayload());
                    connection.connect();

                    Log.d("------------------>","connection = "+connection);
//                    Log.d("------------------>"," connection = "+connection+"; responseMSG = "+connection.getResponseMessage()+"; code = "+connection.getResponseCode());

                    // Log.d("------------------>"," RESPONSE MSG = "+connection.getResponseMessage());

                    int responseCode = connection.getResponseCode();
                    Log.d("------------------>"," RESPONSE code = "+responseCode);

                    if (responseCode == 200) {
                        inputStream = connection.getInputStream();
                        onSucces(inputStream, emitter);
                    } else {
                        //   System.out.println(" ERROR - "+emitter.isDisposed());
                        Log.e("------------------>","Error in connecting to network!");
                        emitter.onError(new UIThrowable(R.string.error_network));
                    }

                } finally {
                    if (connection != null) {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        connection.disconnect();
                    }
                }
            } catch (Exception ex) {
                Log.e("", "---------->", ex);
                emitter.onError(new UIThrowable(R.string.error_unknown));
            }

        });

        return result.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }

    abstract protected void onSucces(InputStream inputStream, ObservableEmitter<T> emitter) throws IOException, SAXException, ParserConfigurationException;

    abstract protected String getSOAPURL();

    abstract protected String getSOAPPayload();

    private String getSOAPAction() {
        return SOAP_ACTION;
    }
}
