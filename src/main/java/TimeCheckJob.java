import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeCheckJob implements Job{
    public TimeCheckJob(){
    }
    public void execute(JobExecutionContext jobExecutionContext)
            throws JobExecutionException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        Date date = new Date();
        String output =  dateFormat.format(date);


        System.out.println("Dzialam");
    }

    private Boolean isBreak(){

    }
    private Boolean isLesson(){

    }
}
