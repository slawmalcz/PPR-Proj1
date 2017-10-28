public class Main {

    public static void main(String [] args){
        Lesson number1 = new Lesson(1);
        number1.setAnswers(0,"Ala ma kota ale kot nie slucha ali");

        System.out.println(number1.toString());
        number1.saveProgres();
    }
}
