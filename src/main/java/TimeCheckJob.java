import org.quartz.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@PersistJobDataAfterExecution
public class TimeCheckJob implements Job {
    private int toCountdown = 0;

    public TimeCheckJob() {
    }

    public void execute(JobExecutionContext jobExecutionContext)
            throws JobExecutionException {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();

        toCountdown = dataMap.getInt("toCountDown");

        if (toCountdown == 0) {
            DateFormat dateFormat = new SimpleDateFormat("HHmm");
            Date date = new Date();
            int output = Integer.parseInt(dateFormat.format(date));
            if((output==815)||(output==1000)||(output==1145)
                    ||(output==1530)||(output==1715)){
                toCountdown = 150;


            }else if((output==945)||(output==1130)||(output==1515)||(output==1700)){
                toCountdown = 15;
            }else if(output==1315){
                toCountdown = 30;
            }
        } else {
            toCountdown--;
        }
            System.out.println(toCountdown + " pozostao do końca "+
                    (isLesson()?"zajęc":"przerwy"));
        dataMap.put("toCountDown",toCountdown);
    }

    private Boolean isBreak() {
        return (isLesson()?false:true);
    }

    private Boolean isLesson() {
        DateFormat dateFormat = new SimpleDateFormat("HHmm");
        Date date = new Date();
        int output = Integer.parseInt(dateFormat.format(date));
        if ((output > 815 && output < 945) || (output > 1000 && output < 1130) || (output > 1145 && output < 1315)
                || (output > 1345 && output < 1515) || (output > 1530 && output < 1700) || (output > 1715 && output < 1845)) {
            return true;
        } else {
            return false;
        }
    }
}
