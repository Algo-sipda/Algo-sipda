import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_트리 {
    static ArrayList<Integer>[] graph;
    static boolean visited[];
    static int delete;
    static int parent[];
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        int n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        parent = new int[n + 1];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        int root = -1;
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int p = Integer.parseInt(stk.nextToken());
            if (p == -1) {
                root = i;
            } else {
                graph[i].add(p);
                graph[p].add(i);
            }
        }
        delete = Integer.parseInt(br.readLine());
        if (delete == root) {
            System.out.println(0);
            return;
        } else dfs(root);
        System.out.println(ans);
    }

    static void dfs(int v) {
        visited[v] = true;
        int nodes = 0;
        for (int cur : graph[v]) {
            if (cur != delete && !visited[cur]) {
                nodes++;
                dfs(cur);
            }
        }
        if (nodes == 0) {
            ans++;
        }
    }
}