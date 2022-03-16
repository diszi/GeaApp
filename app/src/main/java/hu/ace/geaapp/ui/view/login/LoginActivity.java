package hu.ace.geaapp.ui.view.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.ace.geaapp.R;
import hu.ace.geaapp.singleton.HolderSingleton;
import hu.ace.geaapp.singleton.SettingsSingleton;
import hu.ace.geaapp.ui.base.BaseActivity;
import hu.ace.geaapp.ui.base.BaseViewPresenter;
import hu.ace.geaapp.ui.view.main.MainActivity;
import hu.ace.geaapp.ui.view.main.SearchActivity;
import hu.ace.geaapp.ui.view.main_test.MainTestActivityBottomNav;
import hu.ace.geaapp.utils.EnvironmentTool;
import hu.ace.geaapp.utils.UIConstans;

public class LoginActivity extends BaseActivity implements Login.View{


    public static String TAG = "---------->";
    public Context mContext;
    private LoginPresenter presenter;

    @BindView(R.id.inputtext_username)
    EditText compUsernameInput;
    @BindView(R.id.inputtext_password)
    EditText compPasswordInput;
    @BindView(R.id.button_login)
    Button compLoginButton;
    @BindView(R.id.progressbar_login)
    ProgressBar compProgressBar;
    @BindView(R.id.text_appversion)
    TextView compVersion;
    @BindView(R.id.switch_selectSim)
    Switch compSimCardSwitch;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setUpFocus();
        this.mContext = getApplicationContext();
        compVersion.setText(EnvironmentTool.getVersionApp());

        presenter = new LoginPresenter(this);
    }


    /* After adding the username, jump to the password field */
    private void setUpFocus(){
        compUsernameInput.setNextFocusDownId(R.id.inputtext_password);
    }



    @OnClick(R.id.button_login)
    protected void onClickLogin(){
        Log.i(TAG,"OnClick on LOGIN btton ");
        compLoginButton.setEnabled(false);
        compUsernameInput.setError(null);
        compPasswordInput.setError(null);

        String loginName = compUsernameInput.getText().toString();
        String password = compPasswordInput.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(password)){
            cancel = true;
            compPasswordInput.setError(getResources().getString(R.string.error_required));
            focusView = compPasswordInput;
        }

        if (TextUtils.isEmpty(loginName)){
            cancel = true;
            compUsernameInput.setError(getResources().getString(R.string.error_required));
            focusView = compUsernameInput;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {

            if (compSimCardSwitch.isChecked()){
//                String address = "172.31.147.41:9080";
//                String address = "172.31.147.51";
//                String address = UIConstans.IP_PROD_MAXIMO_SIM;
                String address = UIConstans.IP_TEST_MAXIMO_SIM;
                HolderSingleton.getInstance().setServerIPaddress(address);

            }else{
//                String address = "192.168.133.41:9080";
//                String address = "192.168.133.51";
//                String address = UIConstans.IP_PROD_MAXIMO_WIFI;
                String address = UIConstans.IP_TEST_MAXIMO_WIFI;
                HolderSingleton.getInstance().setServerIPaddress(address);

            }
             System.out.println(" ADDRESS = "+HolderSingleton.getInstance().getServerIPaddress());

            if (HolderSingleton.getInstance().getServerIPaddress()!=null){
                presenter.login(loginName, password);

            }else{
                Toast.makeText(this,getString(R.string.alert_select_ip),Toast.LENGTH_SHORT).show();
            }
        }


        compLoginButton.setEnabled(true);
    }

    @Override
    public void launchMainView() {
        Log.i(TAG,"Launch MAIN page of application");

        /*Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);*/
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected BaseViewPresenter getBasePresenter() {
        return presenter;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void showLoading() {
        compProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        compProgressBar.setVisibility(View.GONE);
    }


    @Override
    public void setUserToContext(String username) {
        SettingsSingleton.getInstance().init(this,username);
    }

    private void initApplication(){
        HolderSingleton.getInstance().setContext(this);
    }

}
