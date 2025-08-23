import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_컵라면 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        PriorityQueue<int[]> deadlineQ = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int cup = Integer.parseInt(st.nextToken());
            deadlineQ.add(new int[] { deadline, cup });
        }

        PriorityQueue<int[]> cupQ = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        int max = 0;
        for (int time = N; time > 0; time--) {
           while (!deadlineQ.isEmpty() && deadlineQ.peek()[0] >= time) {
               cupQ.add(deadlineQ.poll());
           }
           if (cupQ.isEmpty()) {
               continue;
           }
           max += cupQ.poll()[1];
        }
        System.out.println(max);
    }
}