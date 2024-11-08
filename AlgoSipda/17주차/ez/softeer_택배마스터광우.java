import java.io.*;
import java.util.*;

public class softeer_택배마스터광우 {

    static int N, M, K, res;
    static int[] rail, selected;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        res = Integer.MAX_VALUE;
        rail = new int[N];
        selected = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            rail[i] = Integer.parseInt(st.nextToken());
        }

        perm(0);

        System.out.println(res);
    }

    private static void perm(int cnt) {
        if (cnt == N) {
            res = Math.min(res, simul());
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            selected[cnt] = i;
            visited[i] = true;
            perm(cnt + 1);
            visited[i] = false;
        }
    }

    private static int simul() {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            queue.add(rail[selected[i]]);
        }

        int cnt = 0;
        int sum = 0;
        int total = 0;
        while (cnt < K) {
            int cur = queue.poll();

            if (sum + cur <= M) {
                sum += cur;
            } else {
                total += sum;
                sum = cur;
                cnt++;
            }
            queue.add(cur);
        }

        return total;
    }
}
