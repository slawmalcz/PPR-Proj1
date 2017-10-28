import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.CronScheduleBuilder.cronSchedule;

public class Main {

    public static void main(String [] args){
        Lesson number1 = Lesson.getInstance(1);
        number1.setAnswers(0,"Ala ma kota ale kot nie slucha ali");

        System.out.println(number1.toString());
        number1.saveProgres();

        testowa();

    }

    public static void testowa(){

        try{
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobDetail autosave = newJob(AutoSaveJob.class)
                    .withIdentity("autosave","group1")
                    .build();
            Trigger autoSaveTrigger = newTrigger()
                    .withIdentity("autosavetrigger","group1")
                    .startNow()
                    .withSchedule(cronSchedule("0/30 0 0 ? * * *"))
                    .build();

            scheduler.start();
            scheduler.scheduleJob(autosave,autoSaveTrigger);

            scheduler.shutdown();

        }catch(SchedulerException se){
            System.out.println(se.getMessage());
        }
    }
}
