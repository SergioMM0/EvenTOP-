package bll.exceptions;

public class ExceptionsManager {

    private static ExceptionsManager exceptionsManager;

    public ExceptionsManager(){}

    public ExceptionsManager getInstance(){
        if(exceptionsManager == null){
            exceptionsManager = new ExceptionsManager();
        }
        return exceptionsManager;
    }


}
