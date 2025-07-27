import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] map = new int[101];
        for (int i = 0; i < 101; i++) {
            map[i] = i;
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            map[A] = B;
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            map[A] = B;
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[101];
        queue.add(1);
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[1] = 0;
        int cnt = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int dice = 1; dice <= 6; dice++) {
                    if (cur + dice <= 100) {
                        if (visited[cur + dice] > cnt) {
                            visited[cur + dice] = cnt;
                            queue.add(map[cur + dice]);
                        }
                    }
                }
            }
            cnt++;
        }

        System.out.println(visited[100]);

    }

}