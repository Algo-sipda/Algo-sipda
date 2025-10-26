import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());
        if (S == 1)
            System.out.println(0);
        else {
            int max = 2000;
            int[][] dist = new int[max + 1][max + 1];
            for (int i = 0; i <= max; i++) {
                Arrays.fill(dist[i], -1);
            }
            ArrayDeque<int[]> q = new ArrayDeque<>();
            q.add(new int[]{1, 0});
            dist[1][0] = 0;
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int s = cur[0];
                int c = cur[1];
                int t = dist[s][c];
                if (s == S) {
                    System.out.println(t);
                    return;
                }
                if (dist[s][s] == -1) {
                    dist[s][s] = t + 1;
                    q.add(new int[]{s, s});
                }
                if (c > 0 && s + c <= max && dist[s + c][c] == -1) {
                    dist[s + c][c] = t + 1;
                    q.add(new int[]{s + c, c});
                }
                if (s > 1 && dist[s - 1][c] == -1) {
                    dist[s - 1][c] = t + 1;
                    q.add(new int[]{s - 1, c});
                }
            }
        }
    }
}
