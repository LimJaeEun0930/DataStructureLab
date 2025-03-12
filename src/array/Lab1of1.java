package array;

import java.util.Arrays;

public class Lab1of1 {

    public static void main(String args[]) {
        float[] x = new float[10];
        args = new String[10];
        boolean[] isPrime = new boolean[5];
        int[] fib1 = new int[] {0, 1, 1, 2, 3, 5, 8, 13, 21, 34};
        int[] fib2 = new int[] {0, 1, 1, 2, 3, 5, 8, 13, 21, 34};
        double[][] a = new double[][]{{1.1, 2.2}, {3.3, 4.4}, null, {5.5, 6.6}, null};
        short[][] b = new short[4][10];
        Integer[] integers = new Integer[0];
        Object[] objects = new Object[]{x, args, isPrime, fib1, fib2, b, a};

        for (Object obj: objects) {
                if (obj instanceof Object[]) {
                // 다차원 배열이나 Object 배열의 경우 deepToString 사용
                System.out.println(Arrays.deepToString((Object[]) obj));
            } else if (obj instanceof boolean[]) {
                System.out.println(Arrays.toString((boolean[]) obj));
            } else if (obj instanceof int[]) {
                System.out.println(Arrays.toString((int[]) obj));
            } else if (obj instanceof float[]) {
                System.out.println(Arrays.toString((float[]) obj));
            } else if (obj instanceof double[]) {
                System.out.println(Arrays.toString((double[]) obj));
            } else if (obj instanceof short[]) {
                System.out.println(Arrays.toString((short[]) obj));
            } else if (obj instanceof double[][]) {
                System.out.println(Arrays.deepToString((double[][]) obj));
            } else if (obj instanceof short[][]) {
                System.out.println(Arrays.deepToString((short[][]) obj));
            } else if (obj instanceof Integer[]) {
                    System.out.println(Arrays.deepToString((Integer[]) obj));
            } else {
                System.out.println(obj);
            }
        }

        System.out.println(objects instanceof Object[]);
        System.out.println(objects[0] instanceof Object);
        System.out.println(objects[0] instanceof float[]);
    }
}
