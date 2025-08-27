import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] cost = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                cost[i] = Integer.parseInt(st.nextToken());
            }

            List<Integer>[] g = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                g[i] = new ArrayList<Integer>();
            }
            int[] indeg = new int[N + 1];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                g[a].add(b);
                indeg[b]++;
            }
            int W = Integer.parseInt(br.readLine());

            int[] dp = new int[N + 1];
            ArrayDeque<Integer> q = new ArrayDeque<Integer>();
            for (int i = 1; i <= N; i++) {
                if (indeg[i] == 0) q.add(i);
                dp[i] = cost[i];
            }

            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int nxt : g[cur]) {
                    if (dp[nxt] < dp[cur] + cost[nxt]) dp[nxt] = dp[cur] + cost[nxt];
                    indeg[nxt]--;
                    if (indeg[nxt] == 0) q.add(nxt);
                }
            }

            sb.append(dp[W]).append('\n');
        }
        System.out.print(sb.toString());
    }
}
