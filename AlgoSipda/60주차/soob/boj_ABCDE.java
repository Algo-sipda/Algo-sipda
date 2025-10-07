import java.io.*;
import java.util.*;

public class Main {
    static int n; // 사람 수
    static int m; // 친구 관계 수
    static List<Integer>[] g;
    static boolean[] v;
    static boolean found;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 사람 수
        m = Integer.parseInt(st.nextToken()); // 친구 관계 수
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            g[a].add(b);
            g[b].add(a);
        }
        v = new boolean[n];
        for (int i = 0; i < n; i++) {
            dfs(i, 0);
            if (found)
                break;
        }
        System.out.println(found ? 1 : 0);
    }

    static void dfs(int u, int depth) {
        if (found)
            return;
        if (depth == 4) {
            found = true;
            return;
        }
        v[u] = true;
        for (int nx : g[u]) {
            if (!v[nx])
                dfs(nx, depth + 1);
        }
        v[u] = false;
    }
}
