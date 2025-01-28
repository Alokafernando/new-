package lk.ijse.pos.leyard.view.tdm;

public class CheckTM implements Comparable<CheckTM> {
    private String vID;
    private String vModel;
    private int vYear;
    private String color;
    private String vType;

    public CheckTM() {}

    public CheckTM(String vID, String vModel, int vYear, String color, String vType) {
        this.vID = vID;
        this.vModel = vModel;
        this.vYear = vYear;
        this.color = color;
        this.vType = vType;
    }

    public String getVID() {return vID;}

    public void setVID(String vID) {this.vID = vID;}

    public String getVModel() {return vModel;}

    public void setVModel(String vModel) {this.vModel = vModel;}

    public int getVYear() {return vYear;}

    public void setVYear(int vYear) {this.vYear = vYear;}

    public String getColor() {return color;}

    public void setColor(String color) {this.color = color;}

    public String getVType() {return vType;}

    public void setVType(String vType) {this.vType = vType;}

    @Override
    public String toString() {
        return "CheckTM{" +
                "vID='" + vID + '\'' +
                ", vModel='" + vModel + '\'' +
                ", vYear=" + vYear +
                ", color='" + color + '\'' +
                ", vType='" + vType + '\'' +
                '}';
    }

    @Override
    public int compareTo(CheckTM o) {
        return vID.compareTo(o.getVID());
    }
}
