import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

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
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(2)
                            .repeatForever()
                    ).build();

            scheduler.start();
            scheduler.scheduleJob(autosave,autoSaveTrigger);

            Thread.sleep(1000);

            scheduler.shutdown();

        }catch(SchedulerException se){
            System.out.println(se.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
