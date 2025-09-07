import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] p;
    static int[] parents;

    private static void init(int n) {
        p = new int[n];
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
            parents[i] = 1;
        }
    }

    private static int find(int x) {
        if (p[x] == x) return x;
        return p[x] = find(p[x]);
    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b)
            return false;
        if (parents[a] < parents[b]) {
            int t = a;
            a = b;
            b = t;
        }
        p[b] = a;
        parents[a] += parents[b];
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        init(n);
        for (int t = 1; t <= m; t++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (!union(a, b)) {
                System.out.println(t);
                return;
            }
        }
        System.out.println(0);
    }
}
