import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_사이클게임 {

    static int[] parents;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            makeSet(i);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (find(from) == find(to)) {
                System.out.println(i + 1);
                return;
            } else {
                union(from, to);
            }
        }
        System.out.println(0);
    }

    private static void makeSet(int v) {
        parents[v] = v;
    }

    private static void union(int u, int v) {
        if (parents[u] > parents[v]) {
            parents[find(u)] = find(v);
        } else {
            parents[find(v)] = find(u);
        }
    }

    private static int find(int v) {
        if (parents[v] == v) {
            return v;
        }
        return parents[v] = find(parents[v]);
    }
}
