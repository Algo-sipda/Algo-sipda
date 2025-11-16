import java.io.*;
import java.util.*;

public class Main {
    static int a;
    static int b;
    static int c;
    static int sum;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        sum = a + b + c;
        if (sum % 3 != 0) {
            System.out.println(0);
            return;
        }
        visited = new boolean[1501][1501];
        int[] s = sort3(a, b, c);
        Queue<int[]> q = new LinkedList<>();
        visited[s[0]][s[1]] = true;
        q.add(new int[]{s[0], s[1]});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int z = sum - x - y;
            if (x == y && y == z) {
                System.out.println(1);
                return;
            }
            move(x, y, z, q);
            move(x, z, y, q);
            move(y, z, x, q);
        }
        System.out.println(0);
    }

    static void move(int p, int q, int r, Queue<int[]> queue) {
        if (p == q) {
            return;
        }
        int small = Math.min(p, q);
        int large = Math.max(p, q);
        int np = small + small;
        int nq = large - small;
        int[] s = sort3(np, nq, r);
        if (!visited[s[0]][s[1]]) {
            visited[s[0]][s[1]] = true;
            queue.add(new int[]{s[0], s[1]});
        }
    }

    static int[] sort3(int x, int y, int z) {
        int[] arr = new int[]{x, y, z};
        Arrays.sort(arr);
        return arr;
    }
}
