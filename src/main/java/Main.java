import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.CronScheduleBuilder.cronSchedule;

public class Main {

    public static void main(String[] args) {
        Lesson number1 = Lesson.getInstance(10);


        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            autoSave(scheduler);
            timeOutDispenser(scheduler);


            scheduler.start();

            startUserInterface(number1);

            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    public static void timeOutDispenser(Scheduler scheduler) {
        JobDetail timeOut = newJob(TimeCheckJob.class)
                .withIdentity("timeCheck", "group2")
                .build();
        Trigger timeOutTrigger = newTrigger()
                .withIdentity("timeOutTrigger", "group2")
                .startNow()
                .withSchedule(cronSchedule("0 * 8-19 ? * MON,TUE,WED,THU,FRI *"))
                .build();

        try {
            scheduler.scheduleJob(timeOut, timeOutTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static void autoSave(Scheduler scheduler) {
        JobDetail autosave = newJob(AutoSaveJob.class)
                .withIdentity("autosave", "group1")
                .build();
        Trigger autoSaveTrigger = newTrigger()
                .withIdentity("autosavetrigger", "group1")
                .startNow()
                .withSchedule(cronSchedule("0/30 * * ? * * *"))
                .build();
        try {
            scheduler.scheduleJob(autosave, autoSaveTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static void startUserInterface(Lesson activeLesson) {
        System.out.println("Witaj na kolejnych zajęciach. Program będzie automatycznie zapisywa twoje postępy," +
                " oraz da ci znac za ile koncza się zajecia.");
        String command = "";
        do{
            System.out.println("Podaj numer zadania, ktore chesz zmienic (od 1 do 10), lub napisz EXIT jezeli" +
                    " chesz zakonczyc zajecia");
            Scanner readerOuter = new Scanner(System.in);
            command = readerOuter.nextLine();
            if(!command.equals("EXIT")){
                try{
                    int numOfExe = Integer.parseInt(command);
                    String respod = "";
                    String lastLine = "";
                    Scanner reader = new Scanner(System.in);
                    System.out.println("Rozpoczynasz edycje zadania nr."+numOfExe+" ,aby zakonczyc wprowadzanie wpisz :wq .");
                    do{
                        lastLine = reader.nextLine();
                        if(!lastLine.equals(":wq")){
                            respod = respod + lastLine+"\n";
                        }
                    }while(!lastLine.equals(":wq"));
                    activeLesson.setAnswers(numOfExe-1,respod);
                }catch(Exception e){
                    System.out.println("Nie udalo się odczytac numeru zadania poniewaz:");
                    System.out.println(e.getMessage());
                }
            }
        }while (!command.equals("EXIT"));
        activeLesson.saveProgres();
    }
}
