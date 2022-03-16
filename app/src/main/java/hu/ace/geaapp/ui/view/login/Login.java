package hu.ace.geaapp.ui.view.login;

import hu.ace.geaapp.ui.base.BaseViewPresenter;

public interface Login {

    interface View{
        //void setStorerooms();

        void showLoading();

        void hideLoading();

        void showErrorMessage(int messageID);

        void launchMainView();

        //void launchFunctionView();

        void setUserToContext(String username);

        //void setDomains();

    }


    interface Presenter extends BaseViewPresenter {

        void login(String userName, String password);
    }
}
