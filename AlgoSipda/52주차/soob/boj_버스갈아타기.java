import java.io.*;
import java.util.*;

public class Main {
    static class Bus {
        boolean horiz;
        int x1, x2, y1, y2;

        Bus(int a, int b, int c, int d) {
            if (b == d) {
                horiz = true;
                y1 = y2 = b;
                x1 = Math.min(a, c);
                x2 = Math.max(a, c);
            } else {
                horiz = false;
                x1 = x2 = a;
                y1 = Math.min(b, d);
                y2 = Math.max(b, d);
            }
        }

        boolean containsPoint(int x, int y) {
            if (horiz) return y == y1 && x1 <= x && x <= x2;
            else       return x == x1 && y1 <= y && y <= y2;
        }

        boolean intersects(Bus o) {
            if (this.horiz && o.horiz) {
                if (this.y1 != o.y1) return false;
                return !(this.x2 < o.x1 || o.x2 < this.x1);
            } else if (!this.horiz && !o.horiz) {
                if (this.x1 != o.x1) return false;
                return !(this.y2 < o.y1 || o.y2 < this.y1);
            } else {
                Bus h = this.horiz ? this : o;
                Bus v = this.horiz ? o : this;
                return (v.x1 >= h.x1 && v.x1 <= h.x2) && (h.y1 >= v.y1 && h.y1 <= v.y2);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(br.readLine().trim());
        Bus[] buses = new Bus[k + 1];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            buses[id] = new Bus(x1, y1, x2, y2);
        }

        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());
        int ex = Integer.parseInt(st.nextToken());
        int ey = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[k + 1];
        ArrayDeque<int[]> q = new ArrayDeque<int[]>();

        for (int id = 1; id <= k; id++) {
            if (buses[id].containsPoint(sx, sy)) {
                visited[id] = true;
                q.add(new int[]{id, 1});
            }
        }

        int ans = -1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int id = cur[0];
            int cnt = cur[1];

            if (buses[id].containsPoint(ex, ey)) {
                ans = cnt;
                break;
            }

            for (int nxt = 1; nxt <= k; nxt++) {
                if (!visited[nxt] && buses[id].intersects(buses[nxt])) {
                    visited[nxt] = true;
                    q.add(new int[]{nxt, cnt + 1});
                }
            }
        }

        System.out.println(ans);
    }
}
