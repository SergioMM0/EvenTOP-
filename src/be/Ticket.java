package be;

public abstract class Ticket {

    private int id;
    private String barCode;

    public Ticket(int id,String barCode){
        this.id = id;
        this.barCode = barCode;
    }

    public Ticket(String barCode){
        this.barCode = barCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
}
