import java.util.*;

public class Main {
    static String[] cows = {"Bessie", "Buttercup", "Belinda", "Beatrice", "Bella", "Blue", "Betsy", "Sue"};

    static List<String[]> constraints = new ArrayList<>();

    public static boolean validOrder(List<String> perm) {
        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < perm.size(); i++) {
            indexMap.put(perm.get(i), i);
        }

        for (String[] constraint : constraints) {
            String a = constraint[0];
            String b = constraint[1];
            if (Math.abs(indexMap.get(a) - indexMap.get(b)) != 1)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        List<String> sortedCows = Arrays.asList(cows);
        Collections.sort(sortedCows);

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < N; i++) {
            String[] parts = scanner.nextLine().split(" ");
            String A = parts[0];
            String B = parts[parts.length - 1];
            constraints.add(new String[]{A, B});
        }

        List<List<String>> permutations = new ArrayList<>();
        generatePermutations(sortedCows, new ArrayList<>(), permutations);

        for (List<String> perm : permutations) {
            if (validOrder(perm)) {
                for (String cow : perm) {
                    System.out.println(cow);
                }
                break;
            }
        }
    }

    public static void generatePermutations(List<String> items, List<String> current, List<List<String>> permutations) {
        if (items.isEmpty()) {
            permutations.add(new ArrayList<>(current));
        } else {
            for (int i = 0; i < items.size(); i++) {
                List<String> newItems = new ArrayList<>(items);
                newItems.remove(i);

                List<String> newCurrent = new ArrayList<>(current);
                newCurrent.add(items.get(i));

                generatePermutations(newItems, newCurrent, permutations);
            }
        }
    }
}