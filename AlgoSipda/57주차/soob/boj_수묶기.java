import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> plusPq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minusPq = new PriorityQueue<>();
        int zeroCnt = 0;
        int answer = 0;
        for (int n = 0; n < N; n++) {
            int x = Integer.parseInt(br.readLine());
            if (x > 1)
                plusPq.add(x);
            if (x == 1)
                answer++;
            if (x == 0)
                zeroCnt++;
            if (x < 0)
                minusPq.add(x);
        }

        while (!plusPq.isEmpty()) {
            if (plusPq.size() == 1) {
                answer += plusPq.poll();
                break;
            }
            int n1 = plusPq.poll();
            int n2 = plusPq.poll();
            answer += n1 * n2;
        }

        while (!minusPq.isEmpty()) {
            if (minusPq.size() == 1) {
                if (zeroCnt == 0)
                    answer += minusPq.poll();
                break;
            }
            int n1 = minusPq.poll();
            int n2 = minusPq.poll();
            answer += n1 * n2;
        }

        System.out.println(answer);
    }
}
