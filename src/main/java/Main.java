import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.CronScheduleBuilder.cronSchedule;

public class Main {

    public static void main(String [] args){
        Lesson number1 = Lesson.getInstance(1);
        number1.setAnswers(0,"Ala ma kota ale kot nie slucha ali");

        System.out.println(number1.toString());
        number1.saveProgres();



        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            autoSave(scheduler);
            timeOutDispenser(scheduler);


            scheduler.start();

            System.out.println("zapuszczona schedulery czekamy na rozwoj wydarze: \n");
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    public static void timeOutDispenser(Scheduler scheduler){
        JobDetail timeOut = newJob(TimeCheckJob.class)
                .withIdentity("timeCheck","group2")
                .build();
        Trigger timeOutTrigger = newTrigger()
                .withIdentity("timeOutTrigger","group2")
                .startNow()
                //.withSchedule(cronSchedule("0 * 8-19 ? * MON,TUE,WED,THU,FRI *"))
                .withSchedule(cronSchedule("0/5 * * ? * * *"))
                .build();

        try {
            scheduler.scheduleJob(timeOut,timeOutTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static void autoSave(Scheduler scheduler){
        JobDetail autosave = newJob(AutoSaveJob.class)
                    .withIdentity("autosave","group1")
                    .build();
        Trigger autoSaveTrigger = newTrigger()
                    .withIdentity("autosavetrigger","group1")
                    .startNow()
                    .withSchedule(cronSchedule("0/30 * * ? * * *"))
                    .build();
        try {
            scheduler.scheduleJob(autosave,autoSaveTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
