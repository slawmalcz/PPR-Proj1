import org.quartz.*;

public class AutoSaveJob implements Job {

    public AutoSaveJob(){
    }
    public void execute(JobExecutionContext jobExecutionContext)
            throws JobExecutionException {
        Lesson.getInstance().saveProgres();
        System.out.println("Dzialam");
    }
}
