package bll.login;

import be.User;
import bll.exceptions.BLLException;
import dal.exceptions.DALException;

public interface LoginFacade {

    User checkCredentials(String email, String password) throws BLLException, DALException;

}
