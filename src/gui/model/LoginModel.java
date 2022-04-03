package gui.model;

import be.User;
import bll.exceptions.BLLException;
import bll.login.LoginFacade;
import bll.login.LoginManager;
import dal.exceptions.DALException;

public class LoginModel {

    private final LoginFacade loginF;

    public LoginModel(){
        loginF = new LoginManager();
    }

    public User checkCredentials(String email, String password) throws BLLException, DALException {
        return loginF.checkCredentials(email,password);
    }
}
