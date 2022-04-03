package bll.login;

import be.User;
import bll.exceptions.BLLException;
import dal.DALFacade;
import dal.DALManager;
import dal.exceptions.DALException;

import java.security.InvalidParameterException;


public class LoginManager implements LoginFacade{

    DALFacade dalFacade;
    public LoginManager(){
        dalFacade = new DALManager();
    }

    @Override
    public User checkCredentials(String email, String password) throws BLLException, DALException {
        User user = dalFacade.checkCredentials(email, password);
        if(user == null){
            throw new BLLException("Wrong email or password, try again", new InvalidParameterException());
        }
        return user;
    }

}
