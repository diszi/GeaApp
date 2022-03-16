package hu.ace.geaapp.ui.view.line.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.ace.geaapp.R;
import hu.ace.geaapp.data.model.Asset;
import hu.ace.geaapp.singleton.HolderSingleton;
import hu.ace.geaapp.ui.view.line.presenter.LinePresenter;

public class AuthenticationToActivity extends Activity {


    private LinePresenter presenter;
    private Asset vehicleAsset;


    @BindView(R.id.act_input)
    EditText input;
    @BindView(R.id.act_button)
    Button sendBtn;
    @BindView(R.id.act_title)
    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_authentication);
        ButterKnife.bind(this);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Log.d("------------------>","Start Authentication Activity");

        presenter = new LinePresenter();

        title.setText("Add meg az ÁTVEVŐ azonosítóját"); //vehicle receiver
        sendBtn.setText("Mentés");

        vehicleAsset = (Asset) getIntent().getSerializableExtra(Asset.SERIALIZABLE_NAME);

    }


    @OnClick(R.id.act_button)
    public void onClickOnAuthenticationBtn() {
        System.out.println(" SEND AUTH --> data = " + input.getText().toString());
        HolderSingleton.getInstance().setUserTo("ACE0001");
        Toast.makeText(this,"Sikeres ÁTADÁS - ÁTVÉTEL ",Toast.LENGTH_SHORT).show();
    }



}
