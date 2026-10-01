import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

class Time {
    private short hours;
    private short minutes;
    private short seconds;

    public static int num_t = 0; 

    {
        hours = 0;
        minutes = 0;
        seconds = 0;
    }

    public Time() {
        num_t++;
    }

    public Time(int h, int m, int s) {
        setTimeFromTotalSeconds(h * 3600 + m * 60 + s);
        num_t++;
    }

    public Time(String timeStr) {
        String[] parts = timeStr.split(":");
        if (parts.length == 3) {
            int h = Integer.parseInt(parts[0].trim());
            int m = Integer.parseInt(parts[1].trim());
            int s = Integer.parseInt(parts[2].trim());
            setTimeFromTotalSeconds(h * 3600 + m * 60 + s);
        }
        num_t++;
    }

    public Time(int totalSeconds) {
        setTimeFromTotalSeconds(totalSeconds);
        num_t++;
    }

    public Time(Time other) {
        if (other != null) {
            this.hours = other.hours;
            this.minutes = other.minutes;
            this.seconds = other.seconds;
        }
        num_t++;
    }

    private void setTimeFromTotalSeconds(int totalSec) {
        while (totalSec < 0) {
            totalSec += 24 * 3600;
        }
        totalSec = totalSec % (24 * 3600);
        this.hours = (short) (totalSec / 3600);
        this.minutes = (short) ((totalSec % 3600) / 60);
        this.seconds = (short) (totalSec % 60);
    }

    public short getHours() { return hours; }
    public short getMinutes() { return minutes; }
    public short getSeconds() { return seconds; }

    public int toSeconds() {
        return hours * 3600 + minutes * 60 + seconds;
    }

    public int toMinutesRounded() {
        return Math.round((float) toSeconds() / 60);
    }

    public int differenceInSeconds(Time other) {
        return Math.abs(this.toSeconds() - other.toSeconds());
    }

    public Time addSeconds(int sec) {
        return new Time(this.toSeconds() + sec);
    }

    public Time subtractSeconds(int sec) {
        return new Time(this.toSeconds() - sec);
    }

    public int compareTo(Time other) {
        return Integer.compare(this.toSeconds(), other.toSeconds());
    }

    public static int getDifferenceBetween(Time t1, Time t2) {
        return t1.differenceInSeconds(t2);
    }

    @Override    //Перевизначення
    public String toString() {
        String s;
        s = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        return s;
    }

    @Override   //Перевизначення
    public boolean equals(Object obj) {
        boolean b = false;
        if (obj instanceof Time) {
            Time obj1 = (Time) obj;
            if (hours == obj1.getHours() && 
                minutes == obj1.getMinutes() && 
                seconds == obj1.getSeconds()) {
                b = true;
            }
        }
        return b;
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("Привіт !");
        Scanner in = new Scanner(System.in);

        System.out.print("Введіть години: ");
        int h = in.nextInt();
        System.out.print("Введіть хвилини: ");
        int m = in.nextInt();
        System.out.print("Введіть секунди: ");
        int s = in.nextInt();

        Time.num_t = 0; 

        Time obj = new Time(h, m, s);
        System.out.println("Ваш час: " + obj.toString());

        Time obj1 = new Time("12:30:00");
        System.out.println("Час obj1: " + obj1.toString());

        Time obj2 = new Time();
        System.out.println("Час obj2: " + obj2.toString());

        Time obj3 = new Time(obj); 
        System.out.println("Час obj3 (копія): " + obj3.toString());

        // Перевірка методів
        System.out.println("\nЧас у секундах: " + obj.toSeconds());
        System.out.println("Час у хвилинах (округлено): " + obj.toMinutesRounded());

        if (obj.equals(obj3)) {
            System.out.println("Об'єкти obj та obj3 однакові ... Так");
        }

        System.out.println("Кількість створених об'єктів = " + Time.num_t);
        in.close();
    }
}