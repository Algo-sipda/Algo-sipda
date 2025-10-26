import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_mootube {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        List<int[]>[] adj = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++)
            adj[i] = new ArrayList<>();

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            adj[p].add(new int[]{q, r});
            adj[q].add(new int[]{p, r});
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            boolean[] visit = new boolean[N + 1];
            visit[v] = true;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(v);

            int ans = 0;
            while (!queue.isEmpty()) {
                int cur = queue.poll();

                for (int[] a : adj[cur]) {
                    if (!visit[a[0]] && a[1] >= k) {
                        queue.add(a[0]);
                        visit[a[0]] = true;
                        ans++;
                    }
                }
            }
            sb.append(ans).append('\n');
        }
        System.out.println(sb.toString());
    }
}