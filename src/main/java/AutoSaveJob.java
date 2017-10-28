import org.quartz.*;

public class AutoSaveJob implements Job {

    public AutoSaveJob(){
    }
    public void execute(JobExecutionContext jobExecutionContext)
            throws JobExecutionException {
        //activeLesson.saveProgres();
        System.out.println("Dzialam");
    }
}
