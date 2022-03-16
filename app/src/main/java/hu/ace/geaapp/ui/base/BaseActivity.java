package hu.ace.geaapp.ui.base;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }


    protected void onPause() {
        super.onPause();

    }

    protected void onResume() {
        super.onResume();
    }

    protected abstract BaseViewPresenter getBasePresenter();


    protected void onDestroy() {
        super.onDestroy();
        getBasePresenter().onDestroy();

    }

    public void showErrorMessage(int messageID) {
        Toast.makeText(this, messageID, Toast.LENGTH_SHORT).show();
    }

    public void showSuccessMessage() {
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }
}
