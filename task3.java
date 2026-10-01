import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class ArrayProcessor {
    private int[] array;   
    private int size;       
    public static int num_r;    

    {
        size = 5;
        array = new int[size];
        num_r++;
    }

    public ArrayProcessor() {
        fillRandom(1, 50);
    }

    public ArrayProcessor(int size, int min, int max) {
        this.size = size;
        this.array = new int[size];
        fillRandom(min, max);
    }

    public ArrayProcessor(int[] in_array) {
        if (in_array != null) {
            this.size = in_array.length;
            this.array = in_array.clone();
        } else {
            this.size = 0;
            this.array = new int[0];
        }
    }

    private void fillRandom(int min, int max) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt((max - min) + 1) + min;
        }
    }

    public int[] getArray() {
        return array.clone();
    }

    public int getSize() {
        return size;
    }

    public void bubbleSort() {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static ArrayProcessor mergeSorted(ArrayProcessor obj1, ArrayProcessor obj2) {
        int[] a1 = obj1.getArray();
        int[] a2 = obj2.getArray();
        int[] merged = new int[a1.length + a2.length];

        int i = 0, j = 0, k = 0;
        while (i < a1.length && j < a2.length) {
            if (a1[i] <= a2[j]) {
                merged[k++] = a1[i++];
            } else {
                merged[k++] = a2[j++];
            }
        }
        while (i < a1.length) merged[k++] = a1[i++];
        while (j < a2.length) merged[k++] = a2[j++];

        return new ArrayProcessor(merged);
    }

    @Override
    public String toString() {
        String s;
        s = "Масив (розмір " + size + "): " + Arrays.toString(array);
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        boolean b = false;
        if (obj instanceof ArrayProcessor) {
            ArrayProcessor obj1 = (ArrayProcessor) obj;
            if (Arrays.equals(this.array, obj1.getArray())) {
                b = true;
            }
        }
        return b;
    }
}

public class task3 {
    public static void main(String[] args) throws IOException {
        // Безпечна настройка кодування UTF-8
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);

        System.out.println("Привіт!");

        System.out.print("Введіть розмір першого масиву: ");
        int size1 = in.nextInt();
        System.out.print("Введіть мінімальне значення для випадкових чисел: ");
        int min1 = in.nextInt();
        System.out.print("Введіть максимальне значення для випадкових чисел: ");
        int max1 = in.nextInt();

        ArrayProcessor.num_r = 0; 

        ArrayProcessor obj = new ArrayProcessor(size1, min1, max1);
        System.out.println("Перший масив (obj): " + obj.toString());

        System.out.print("\nВведіть розмір другого масиву: ");
        int size2 = in.nextInt();
        ArrayProcessor obj1 = new ArrayProcessor(size2, 1, 30);
        System.out.println("Другий масив (obj1): " + obj1.toString());

        ArrayProcessor obj2 = new ArrayProcessor();
        System.out.println("Третій масив (дефолтний obj2): " + obj2.toString());

        System.out.println("\n--- Сортування масивів методом бульбашки ---");
        obj.bubbleSort();
        obj1.bubbleSort();
        System.out.println("Відсортований obj:  " + obj.toString());
        System.out.println("Відсортований obj1: " + obj1.toString());

        // Злиття у третій масив
        System.out.println("\nЗлиття obj та obj1 у третій впорядкований масив");
        ArrayProcessor mergedObj = ArrayProcessor.mergeSorted(obj, obj1);
        System.out.println("Результат злиття: " + mergedObj.toString());

        // Перевірка equals
        System.out.println("\nПеревірка методів equals та toString");
        ArrayProcessor objCopy = new ArrayProcessor(obj.getArray());
        System.out.println("obj:     " + obj.toString());
        System.out.println("objCopy: " + objCopy.toString());

        if (obj.equals(objCopy)) {
            System.out.println("obj та objCopy однакові? ... Так");
        }
        if (!obj.equals(obj1)) {
            System.out.println("obj та obj1 однакові? ... Ні");
        }

        in.close();
        System.out.println("\nКількість створених об'єктів = " + ArrayProcessor.num_r);
    }
}