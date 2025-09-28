import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_ABCDE {

    static int N, M, res;
    static List<Integer>[] adjList;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }

        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (res == 1) continue;
            dfs(i, 1);
        }
        System.out.println(res);
    }

    private static void dfs(int cur, int cnt) {
        if (cnt == 5) {
            res = 1;
            return;
        }

        visited[cur] = true;
        for (Integer next : adjList[cur]) {
            if (visited[next]) continue;
            dfs(next, cnt + 1);
        }
        visited[cur] = false;
    }
}
