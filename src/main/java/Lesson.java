import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Lesson {
    private List<Excersise> answers;
    private String name;

    private void setAswers(List<Excersise> newAnser){
        this.answers = newAnser;
    }
    private void setAnswers(int id,String newExercise){
        this.answers.get(id).setRespond(newExercise);
    }
    public List<Excersise> getAnswers() {
        return answers;
    }
    public Excersise getAnswers(int ID){
        return this.answers.get(ID-1);
    }
    private void setName(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Date date = new Date();
        this.name = dateFormat.format(date);
    }
    public String getName() {
        return name;
    }
}
