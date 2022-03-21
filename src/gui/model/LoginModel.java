package gui.model;

import be.User;
import bll.exceptions.LoginEX;
import bll.login.LoginFacade;
import bll.login.LoginManager;

public class LoginModel {

    private LoginFacade loginF;

    public LoginModel(){
        loginF = new LoginManager();
    }

    public User checkCredentials(String email, String password) throws LoginEX {
        return loginF.checkCredentials(email,password);
    }
}
