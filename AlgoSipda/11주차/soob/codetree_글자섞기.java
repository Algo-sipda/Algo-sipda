import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] originalWords = new String[N];
        String[] fastestWords = new String[N];
        String[] slowestWords = new String[N];

        for (int i = 0; i < N; i++) {
            originalWords[i] = br.readLine();

            char[] chars = originalWords[i].toCharArray();
            Arrays.sort(chars);
            fastestWords[i] = new String(chars);
            slowestWords[i] = new StringBuilder(new String(chars)).reverse().toString();
        }

        List<String> sortedFastestWords = new ArrayList<>(Arrays.asList(fastestWords));
        Collections.sort(sortedFastestWords, Collections.reverseOrder());

        List<String> sortedSlowestWords = new ArrayList<>(Arrays.asList(slowestWords));
        Collections.sort(sortedSlowestWords);

        for (int i = 0; i < N; i++) {
            String fastWord = fastestWords[i];
            String slowWord = slowestWords[i];

            int slowRank = Collections.binarySearch(sortedFastestWords, slowWord, Collections.reverseOrder());

            if (slowRank >= 0) {
                while (slowRank > 0 && sortedFastestWords.get(slowRank - 1).equals(slowWord)) {
                    slowRank--;
                }
            } else {
                slowRank = -slowRank - 1;
            }

            int fastRank = Collections.binarySearch(sortedSlowestWords, fastWord);
            if (fastRank < 0) fastRank = -fastRank - 1;

            System.out.println(fastRank + 1 + " " + (N - slowRank));
        }
    }
}