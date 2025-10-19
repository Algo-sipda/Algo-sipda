import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int N, M;
    static List<List<Integer>> g, rg;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 구슬의 개수
        M = Integer.parseInt(st.nextToken()); // 비교 결과의 개수

        g = new ArrayList<List<Integer>>();
        rg = new ArrayList<List<Integer>>();
        for (int i = 0; i <= N; i++) {
            g.add(new ArrayList<Integer>());
            rg.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 더 무거운 구슬
            int b = Integer.parseInt(st.nextToken()); // 더 가벼운 구슬
            g.get(a).add(b);
            rg.get(b).add(a);
        }

        int half = N / 2;
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (bfsCount(g, i) > half)
                ans++;
            else if (bfsCount(rg, i) > half)
                ans++;
        }
        System.out.println(ans);
    }

    static int bfsCount(List<List<Integer>> graph, int start) {
        boolean[] v = new boolean[N + 1];
        Queue<Integer> q = new ArrayDeque<Integer>();
        v[start] = true;
        q.add(start);
        int cnt = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nx : graph.get(cur)) {
                if (v[nx])
                    continue;
                v[nx] = true;
                q.add(nx);
                cnt++;
            }
        }
        return cnt;
    }
}
