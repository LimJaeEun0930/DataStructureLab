package list;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        {
            ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        //arrayList.add(3, 3);
        }


        XArrayList<Integer> nums = new XArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);

        printArrayList(nums);
        System.out.printf("size: %d, capacity: %d\n", nums.size(), nums.getCapacity());

        nums.add(5);
        System.out.printf("size: %d, capacity: %d\n", nums.size(), nums.getCapacity());
        //nums.add(-2, 6);
        printArrayList(nums);

        nums.remove(0);
        printArrayList(nums);
    }

    private static void printArrayList(XArrayList<Integer> nums) {
        for (int a : nums) {
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
