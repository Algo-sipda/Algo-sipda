import java.io.*;
import java.util.*;

public class Main {
    static class E {
        int to;
        int w;
        E(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        ArrayList<E>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            g[a].add(new E(b, w));
            g[b].add(new E(a, w));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            boolean[] vis = new boolean[n + 1];
            ArrayDeque<Integer> dq = new ArrayDeque<>();
            dq.add(v);
            vis[v] = true;
            int cnt = 0;
            while (!dq.isEmpty()) {
                int cur = dq.poll();
                for (E e : g[cur]) {
                    if (!vis[e.to] && e.w >= k) {
                        vis[e.to] = true;
                        dq.add(e.to);
                        cnt++;
                    }
                }
            }
            sb.append(cnt).append('\n');
        }
        System.out.print(sb.toString());
    }
}
