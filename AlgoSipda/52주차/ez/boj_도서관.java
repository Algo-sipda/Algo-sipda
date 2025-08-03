import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_도서관 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> upQ = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        PriorityQueue<Integer> downQ = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int max = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num > 0) {
                upQ.add(num);
            } else {
                downQ.add(Math.abs(num));
            }
            max = Math.max(Math.abs(num), max);
        }

        int answer = 0;
        answer += calc(upQ, M);
        answer += calc(downQ, M);

        System.out.println(answer - max);
    }

    private static int calc(PriorityQueue<Integer> pq, int M) {
        int sum = 0;

        while (!pq.isEmpty()) {
            int cur = pq.poll();
            for (int i = 0; i < M - 1; i++) {
                pq.poll();
                if (pq.isEmpty()) {
                    break;
                }
            }
            sum += (cur * 2);
        }
        return sum;
    }
}
