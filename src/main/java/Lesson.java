import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Lesson {
    private List<Excersise> answers = new ArrayList<Excersise>();
    private String name;

    Lesson(int numberOfExercise){
        for(int i=0;i<numberOfExercise;i++){
            answers.add(new Excersise(i,""));
        }
        this.setName();
    }

    @Override
    public String toString(){
        String output = "";
        for (Excersise a:getAnswers()) {
            output += a.toString()+"\n";
        }
        return output;
    }

    public Boolean saveProgres(){
        try{
            PrintWriter file = new PrintWriter(getName()+".txt","UTF-8");
            file.write(toString());
            file.close();
            return true;
        }catch(Exception e){
            System.out.println("Error during creating response file");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void setAnswers(int id,String newExercise){
        this.getAnswers().get(id).setRespond(newExercise);
    }
    public List<Excersise> getAnswers() {
        return answers;
    }
    public Excersise getAnswers(int ID){
        return this.answers.get(ID);
    }
    private void setName(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        Date date = new Date();
        this.name = dateFormat.format(date);
    }
    public String getName() {
        return name;
    }
}
