package hu.ace.geaapp.ui.component;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import hu.ace.geaapp.R;
import hu.ace.geaapp.singleton.HolderSingleton;
import hu.ace.geaapp.ui.view.login.LoginActivity;

public class OnBackPressedDialog extends DialogFragment {

    /**
     *  Create custom Dialog object
     * @param savedInstanceState - the last saved instance state of the Fragment, or null if this is a freshly created Fragment
     * @return - a new Dialog instance to be displayed by the Fragment.
     */
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Log out
        return new AlertDialog.Builder(getActivity()).setMessage(R.string.logout_question_hu).setPositiveButton(R.string.text_YES_hu, (dialogInterface, i) -> {

            HolderSingleton.getInstance().setServerIPaddress(null);
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.putExtra("backPressed","true");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }).setNegativeButton(R.string.text_NO_hu, (dialogInterface, i) -> dismiss()).create();
    }
}
