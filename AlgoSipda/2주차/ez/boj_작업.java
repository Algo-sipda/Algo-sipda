import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_작업 {

    static int N;
    static List<ArrayList<Integer>> adjList = new ArrayList<>();
    static int[] time;
    static int[] degree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        time = new int[N + 1];
        degree = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i + 1] = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                int before = Integer.parseInt(st.nextToken());
                adjList.get(before).add(i + 1);
                degree[i + 1]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        int[] res = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            res[i] = time[i];
            if (degree[i] == 0) { // 선행 조건이 없는 거
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : adjList.get(cur)) {
                res[next] = Math.max(res[next], res[cur] + time[next]);
                degree[next]--;
                if (degree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, res[i]);
        }
        System.out.println(ans);
    }

}
