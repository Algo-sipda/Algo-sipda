import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }

        boolean[] vis = new boolean[n + 1];
        boolean[] in = new boolean[n + 1];
        boolean[] sel = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (vis[i])
                continue;
            ArrayList<Integer> path = new ArrayList<>();
            int x = i;
            while (!vis[x]) {
                vis[x] = true;
                in[x] = true;
                path.add(x);
                x = a[x];
            }
            if (in[x]) {
                int y = x;
                do {
                    sel[y] = true;
                    y = a[y];
                } while (y != x);
            }
            for (int v : path) {
                in[v] = false;
            }
        }

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (sel[i])
                cnt++;
        }
        sb.append(cnt).append('\n');
        for (int i = 1; i <= n; i++) {
            if (sel[i]) {
                sb.append(i).append('\n');
            }
        }
        System.out.print(sb.toString());
    }
}
