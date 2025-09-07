import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class boj_수묶기 {

    static int N, sum;
    static PriorityQueue<Integer> plus, minus;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        plus = new PriorityQueue<>(Comparator.reverseOrder());
        minus = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0) {
                plus.add(num);
            } else {
                minus.add(num);
            }
        }

        while (!minus.isEmpty()) {
            int cur = minus.poll();
            if (minus.isEmpty()) {
                sum += cur;
                break;
            }
            if (minus.peek() == 0) {
                minus.poll();
            } else {
                sum += (cur * minus.poll());
            }
        }

        while (!plus.isEmpty()) {
            int cur = plus.poll();
            if (plus.isEmpty()) {
                sum += cur;
                break;
            }
            if (cur == 1) {
                sum += 1;
            } else if (plus.peek() == 1) {
                sum += cur + plus.poll();
            } else {
                sum += (cur * plus.poll());
            }

        }

        System.out.println(sum);
    }
}
