package bll.login;

import be.User;
import bll.exceptions.LoginEX;

public interface LoginFacade {

    User checkCredentials(String email, String password) throws LoginEX;

}
