import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Student {
    public void learn() {
        System.out.println("Я учусь. .zZ");
        System.out.println("Я закончил учиться");
    }
}

class StudentWithClock extends Student {
    @Override
    public void learn() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatt = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currentTime = now.format(formatt);
        super.learn();
        System.out.println("Текущее время: " + currentTime);
    }
}
public class Task1 {
    public static void main(String[] args) {
        StudentWithClock student = new StudentWithClock();
        student.learn();
    }
}