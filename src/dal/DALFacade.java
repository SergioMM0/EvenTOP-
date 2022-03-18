package dal;

import be.User;

public interface DALFacade {

    User checkCredentials(String email,String password);

}
