package hu.ace.geaapp.ui.view.login;

import android.util.Base64;
import android.util.Log;

import java.net.HttpURLConnection;

import hu.ace.geaapp.R;
import hu.ace.geaapp.app.Urls;
import hu.ace.geaapp.data.network.NetworkTool;
import hu.ace.geaapp.singleton.HolderSingleton;
import hu.ace.geaapp.ui.base.BasePresenter;
import hu.ace.geaapp.utils.UIConstans;
import hu.ace.geaapp.utils.UIThrowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter implements Login.Presenter{

    private Login.View loginView;
    private Disposable disposable;

    public LoginPresenter(Login.View view){
        this.loginView = view;
    }



    @Override
    public void login(String userName, String password) {
        Log.d("---------->","Login presenter");
        loginView.showLoading();

        disposable = createObservable(userName, password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).doOnNext((param) -> {

                }).subscribe((object) -> { // onNext Consumer
                }, (throwable) -> { // onError Consumer
                    UIThrowable uiThrowable = (UIThrowable) throwable;
                    loginView.showErrorMessage(uiThrowable.getMessageId());
                    loginView.hideLoading();
                }, () -> { // onComplate Action
                    loginView.setUserToContext(userName);
                    loginView.hideLoading();
                    loginView.launchMainView();
//                    loginView.setStorerooms();
//                    loginView.setDomains();


                });
    }


    public Observable createObservable(String userName, String password) {
        Observable result = Observable.create(emitter -> {
            try {

                //HolderSingleton.getInstance().setAuthBase64("bWF4YWRtaW46SGUxMW9vNzAu");
                //emitter.onComplete();
                HttpURLConnection connection = null;
                try {
                    Urls.setURLs(HolderSingleton.getInstance().getServerIPaddress());
                    connection = NetworkTool.createConnection(Urls.LOGIN_URL);
                  //  connection = NetworkTool.createConnection("https://www.google.hu/");
                    String credentials = userName + ":" + password;
                    String base64Credentials = Base64.encodeToString(credentials.getBytes(), Base64.DEFAULT);
//                    connection.setRequestProperty("MAXAUTH", base64Credentials); //bWF4YWRtaW46SGUxMW9vNzAu
                    System.out.println(" ---> login base64 = "+base64Credentials);
                    HolderSingleton.getInstance().setAuthBase64(base64Credentials);
                    connection.setRequestProperty(UIConstans.HTTP_REQUEST_PROP_AUTH_KEY, base64Credentials);
                    connection.connect();
                    int responseCode = connection.getResponseCode();
                    Log.i("---------->","Connection response="+responseCode);

                    if (responseCode == 200) {
                        emitter.onComplete();
                    } else if (responseCode == 400) {
                        emitter.onError(new UIThrowable(R.string.error_wrongUser));
                    } else {
                        emitter.onError(new UIThrowable(R.string.error_authFailed));
                    }

                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            } catch (Exception ex) {
                Log.e("", "---------->", ex);
                emitter.onError(new UIThrowable(R.string.error_network_2));


            }

        });

        return result;
    }

    @Override
    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
