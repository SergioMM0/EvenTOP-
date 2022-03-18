package bll.login;

import be.User;
import dal.DALFacade;
import dal.DALManager;


public class LoginManager implements LoginFacade{

    DALFacade dalFacade;
    public LoginManager(){
        dalFacade = new DALManager();
    }

    @Override
    public User checkCredentials(String email, String password) {
        return dalFacade.checkCredentials(email, password);
    }

}
