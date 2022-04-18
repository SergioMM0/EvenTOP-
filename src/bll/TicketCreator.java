package bll;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TicketCreator {

    public TicketCreator(){

    }

    public void createTicket() throws IOException {

        BufferedImage image = ImageIO.read(new File("ticket_assets/ticketSample.png"));
        Font font = new Font("Arial", Font.BOLD, 18);
        Graphics g = image.getGraphics();
        g.setFont(font);
        g.setColor(Color.GREEN);
        g.drawString("name", 0, 20);
    }

}
