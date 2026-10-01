import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class task2 {

    static class Circle {

        private double x;     
        private double y;      
        private double radius; 

        public static int num_r; 


        {
            x = 0.0;
            y = 0.0;
            radius = 1.0;
        }

        public Circle(double in_x, double in_y, double in_radius) {
            x = in_x;
            y = in_y;
            if (in_radius < 0) {
                System.out.println("Помилка: радіус не може бути від'ємним! Встановлено 0.");
                radius = 0;
            } else {
                radius = in_radius;
            }
            num_r++;
        }

        public Circle(double in_radius) {
            x = 0;
            y = 0;
            if (in_radius < 0) {
                radius = 0;
            } else {
                radius = in_radius;
            }
            num_r++;
        }

        public Circle() {
            num_r++;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getRadius() {
            return radius;
        }

        public double getCircumference() {
            return 2 * Math.PI * radius;
        }

        public double getArea() {
            return Math.PI * Math.pow(radius, 2);
        }

        public boolean tochkainCircle(double px, double py) {
            double distance = Math.sqrt(Math.pow(px - x, 2) + Math.pow(py - y, 2)); //за теоремою Піфагора
            return distance <= radius;
        }

        public int PeretinWithInshecolo(Circle other) {
            double d = Math.sqrt(Math.pow(other.x - x, 2) + Math.pow(other.y - y, 2));
            double r1 = radius;
            double r2 = other.radius;

            if (d == 0 && r1 == r2) {
                if (r1 == 0) return 1;
                return -1; 
            }

            if (d + Math.min(r1, r2) < Math.max(r1, r2)) return 0;
            if (d > r1 + r2) return 0;

            double epsilon = 1e-9;
            if (Math.abs(d - (r1 + r2)) < epsilon || Math.abs(d - Math.abs(r1 - r2)) < epsilon) {
                return 1;
            }

            if (d < r1 + r2 && d > Math.abs(r1 - r2)) {
                return 2;
            }

            return 0;
        }

        @Override
        public String toString() {
            String s;
            s = "Центр: (" + x + ", " + y + "), Радіус: " + radius;
            return s;
        }

        @Override
        public boolean equals(Object obj) {
            boolean b = false;
            if (obj instanceof Circle) {
                Circle obj1 = (Circle) obj;
                if (x == obj1.getX() && salaryEquals(y, obj1.getY()) && radius == obj1.getRadius()) {
                    b = true;
                }
            }
            return b;
        }


        private boolean salaryEquals(double a, double b) {
            return Double.compare(a, b) == 0;
        }
    }

    public static void main(String[] args) throws IOException {
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        } catch (Exception e) {}

        System.out.println("Привіт !");
        Scanner in = new Scanner(System.in);

        System.out.print("Введіть X центру: ");
        double x = in.nextDouble();

        System.out.print("Введіть Y центру: ");
        double y = in.nextDouble();

        System.out.print("Введіть радіус: ");
        double r = in.nextDouble();

        Circle.num_r = 0; 
        Circle obj = new Circle(x, y, r);
        System.out.println("Коло -> " + obj.toString() + " | Площа = " + obj.getArea());

        Circle obj1 = new Circle(5.0);
        System.out.println("Коло obj1 -> " + obj1.toString() + " | Довжина = " + obj1.getCircumference());

        Circle obj2 = new Circle();
        System.out.println("Коло obj2 (за замовчуванням) -> " + obj2.toString());

        System.out.println("\nПеревірка equals (чи однакові obj та obj1):");
        if (obj.equals(obj1)) {
            System.out.println(" ... Так ");
        } else {
            System.out.println(" ... Ні ");
        }

        System.out.println("\nПеревірка належності точки (1, 1) до кола:");
        System.out.println("Результат: " + obj.tochkainCircle(1, 1));

        System.out.println("\nКількість точок перетину кола з obj1: " + obj.PeretinWithInshecolo(obj1));

        in.close();
        System.out.println("\nКількість створених кіл = " + Circle.num_r);
    }
}