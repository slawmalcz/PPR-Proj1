import java.lang.String;

public class Excersise {
    private int ID;
    private String respond;

    Excersise(int newID){
        this.setID(newID);
        this.respond="";
    }
    Excersise(int newID,String newRespond){
        this.setID(newID);
        this.setRespond(newRespond);
    }

    @Override
    public String toString(){
        String output;
        output = "Zad nr."+getID()+1+"\n\n"+getRespond();
        return output;
    }

    private void setID(int newID){
        ID = newID;
    }
    public int getID(){
        return ID;
    }
    public void setRespond(String newRespond) {
        try{
            if(newRespond.matches("(.*)SELECT(.*)FROM(.*)")||
                    (newRespond.matches("(.*)WHERE(.*)")||
                    newRespond.matches("(.*)ORDER BY(.*)"))){
                respond = newRespond;
            }else{
                throw new Exception(){
                    @Override
                    public String getMessage() {
                        return "Respond don't matches formula: * SELECT * FROM * WHERE * ORDER BY *";
                    }
                };
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public String getRespond(){
        return respond;
    }
}
