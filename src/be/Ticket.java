package be;

public abstract class Ticket {

    private String barCode;
    private String typeName;
    private String extras;
    private String startTime;
    private String endTime;

    public Ticket(String barCode,String typeName,String extras, String startTime, String endTime){
        this.barCode = barCode;
        this.typeName = typeName;
        this.extras = extras;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Ticket(String barCode){
        this.barCode = barCode;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String hour, String min) {
        this.startTime = hour + ":" + min;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String hour, String min) {
        this.endTime = hour + ":" + min;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "barCode='" + barCode + '\'' +
                ", typeName='" + typeName + '\'' +
                ", extras='" + extras + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
