package logicTest;

import java.util.*;

public class LogicPracticeTest {

    public static void main(String[] args) {
        reverseListManual();
        reverseListWithCollections();

        sortListManual();
        sortListWithCollections();

        removeDuplicatesManual();
        removeDuplicatesWithCollections();
    }

    // ---------- Reversing ----------
    public static void reverseListManual() {
        List<Integer> original = Arrays.asList(2, 5, 3, 7, 4, 7, 9, 6, 4, 8);
        List<Integer> reversed = new ArrayList<>();

        for (int i = original.size() - 1; i >= 0; i--) {
            reversed.add(original.get(i));
        }

        System.out.println("Manual List Reversal:");
        System.out.println("Original: " + original);
        System.out.println("Reversed: " + reversed + "\n");
    }

    public static void reverseListWithCollections() {
        List<Integer> original = Arrays.asList(2, 5, 3, 7, 4, 7, 9, 6, 4, 8);
        List<Integer> reversed = new ArrayList<>(original);
        Collections.reverse(reversed);

        System.out.println("List Reversal with Collections.reverse:");
        System.out.println("Original: " + original);
        System.out.println("Reversed: " + reversed + "\n");
    }

    // ---------- Sorting ----------
    public static void sortListManual() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 4, 6, 3, 7));

        System.out.println("Manual List Sorting (Bubble Sort):");
        System.out.println("Original: " + numbers);

        for (int i = 0; i < numbers.size() - 1; i++) {
            for (int j = 0; j < numbers.size() - 1 - i; j++) {
                if (numbers.get(j) > numbers.get(j + 1)) {

                    int temp = numbers.get(j);
                    numbers.set(j, numbers.get(j + 1));
                    numbers.set(j + 1, temp);
                }
            }
        }

        System.out.println("Sorted: " + numbers + "\n");
    }

    public static void sortListWithCollections() {
        List<Integer> original = Arrays.asList(5, 2, 8, 4, 6, 3, 7);
        List<Integer> numbers = new ArrayList<>(original);

        System.out.println("List Sorting with Collections.sort:");
        System.out.println("Original: " + original);

        Collections.sort(numbers);

        System.out.println("Sorted:   " + numbers + "\n");
    }

    // ---------- Duplicate Removal ----------
    public static void removeDuplicatesManual() {
        List<Integer> input = Arrays.asList(2, 5, 3, 7, 4, 3, 7, 9, 3, 6, 4, 8, 7);
        List<Integer> result = new ArrayList<>();

        for (Integer num : input) {
            if (!result.contains(num)) {
                result.add(num);
            }
        }

        System.out.println("Manual Duplicate Removal:");
        System.out.println("Original: " + input);
        System.out.println("Without duplicates: " + result + "\n");
    }

    public static void removeDuplicatesWithCollections() {
        List<Integer> input = Arrays.asList(2, 5, 3, 7, 4, 3, 7, 9, 3, 6, 4, 8, 7);
        Set<Integer> set = new LinkedHashSet<>(input);
        List<Integer> result = new ArrayList<>(set);

        System.out.println("Duplicate Removal with Set:");
        System.out.println("Original: " + input);
        System.out.println("Without duplicates: " + result + "\n");
    }
}
