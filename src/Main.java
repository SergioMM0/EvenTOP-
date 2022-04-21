import be.User;
import be.UserType;
import dal.DAO.DAOTickets;
import dal.exceptions.DALException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gui/views/MainLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 450);
        stage.setResizable(false);
        stage.setTitle("EvenTOP");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        DAOTickets daoTickets = new DAOTickets();

        try {
            daoTickets.getTicketByShorBarCode(new User(0, UserType.CUSTOMER,"0","fca8ecd19","2","0"));
        } catch (DALException e) {
            e.printStackTrace();
        }
    }
}