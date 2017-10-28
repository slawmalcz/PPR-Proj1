import java.lang.String;

public class Excersise {
    private int ID;
    private String respond;

    Excersise(int newID){
        this.setID(newID);
    }
    Excersise(int newID,String newRespond){
        this.setID(newID);
        this.setRespond(newRespond);
    }

    @Override
    public String toString(){
        String output = "";
        output = "Zad nr."+getID()+"\n\n"+getRespond();
        return output;
    }

    private void setID(int newID){
        ID = newID;
    }
    public int getID(){
        return ID;
    }
    private void setRespond(String newRespond) {
        respond = newRespond;
    }
    public String getRespond(){
        return respond;
    }
}
