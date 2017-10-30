import org.quartz.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@PersistJobDataAfterExecution
public class TimeCheckJob implements Job {
    public TimeCheckJob() {
    }

    public void execute(JobExecutionContext jobExecutionContext)
            throws JobExecutionException {

            System.out.println((isLesson()?askAboutTimeLesson():askAboutTimeBreak()) + " pozostao do końca "+
                    (isLesson()?"zajęc":"przerwy"));
    }

    private Boolean isBreak() {
        return (isLesson()?false:true);
    }

    private Boolean isLesson() {
        DateFormat dateFormat = new SimpleDateFormat("HHmm");
        Date date = new Date();
        int output = Integer.parseInt(dateFormat.format(date));
        if ((output >= 815 && output < 945) || (output >= 1000 && output < 1130) || (output >= 1145 && output < 1315)
                || (output >= 1345 && output < 1515) || (output >= 1530 && output < 1700) || (output >= 1715 && output < 1845)) {
            return true;
        } else {
            return false;
        }
    }

    private int askAboutTimeLesson(){
        DateFormat dateFormat = new SimpleDateFormat("HHmm");
        Date date = new Date();
        int output = Integer.parseInt(dateFormat.format(date));
        if(output>=815&&output<945){
            return (90-(output>859?output-900:output-815));
        } else if(output>=1000 && output < 1130){
            return (90-(output>1059?output-1100:output-1000));
        }else if(output >= 1145 && output < 1315){
            return (90-(output>1159?(output>1259?output-1300:output-1200):output-1145));
        }else if(output >= 1345 && output < 1515){
            return (90-(output>1359?(output>1459?output-1500:output-1400):output-1345));
        }else if(output >= 1530 && output < 1700){
            return (90-(output>1559?output-1600:output-1530));
        }else if(output >= 1715 && output < 1845){
            return (90-(output>1759?output-1800:output-1715));
        }else return 0;
    }

    private int askAboutTimeBreak(){
        DateFormat dateFormat = new SimpleDateFormat("HHmm");
        Date date = new Date();
        int output = Integer.parseInt(dateFormat.format(date));
        if(output>=800&&output<815){
            return 15-(output-800);
        }else if(output>=945&&output<1000){
            return 15-(output-945);
        }else if(output>=1130&&output<1145){
            return 15-(output-1130);
        }else if(output>=1315&&output<1345){
            return 30-(output-1315);
        }else if(output>=1515&&output<1530){
            return 15-(output-1515);
        }else if(output>=1700&&output<1715){
            return 15-(output-1700);
        }else if(output>=1845&&output<1900){
            return 15-(output-1845);
        }else return 0;
    }
}
