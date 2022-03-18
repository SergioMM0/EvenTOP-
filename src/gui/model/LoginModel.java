package gui.model;

import bll.login.LoginFacade;
import bll.login.LoginManager;

public class LoginModel {

    private LoginFacade loginF;

    public LoginModel(){
        loginF = new LoginManager();
    }


}
