import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_acmCraft {

    static List<Integer>[] adjList;
    static int N, K;
    static int[] time;
    static int[] degree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            time = new int[N + 1];
            degree = new int[N + 1];
            adjList = new ArrayList[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                time[j] = Integer.parseInt(st.nextToken());
                adjList[j] = new ArrayList<>();
            }
            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                adjList[x].add(y);
                degree[y]++;
            }
            int W = Integer.parseInt(br.readLine());

            solve(W);
        }
    }

    public static void solve(int target){
        Queue<Integer> queue = new ArrayDeque<>();
        int[] result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            result[i] = time[i];
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int poll = queue.poll();
            for (Integer next: adjList[poll]) {
                result[next] = Math.max(result[next], result[poll] + time[next]);
                degree[next]--;
                if (degree[next]==0){
                    queue.add(next);
                }
            }
        }
        System.out.println(result[target]);
    }
    
}
