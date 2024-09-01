import java.util.*;

public class Main {

    public static int findMostFrequentVisit(int N, List<String> visits) {
        Map<String, Integer> visitCounts = new HashMap<>();

        for (String visit : visits) {
            String[] visitArray = visit.split(" ");
            Arrays.sort(visitArray);
            String sortedVisit = String.join(" ", visitArray);

            visitCounts.put(sortedVisit, visitCounts.getOrDefault(sortedVisit, 0) + 1);
        }

        int mostFrequentCount = Collections.max(visitCounts.values());

        return mostFrequentCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        scanner.nextLine();
        List<String> visits = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            visits.add(scanner.nextLine());
        }

        System.out.println(findMostFrequentVisit(N, visits));
    }
}
