import java.io.*;
import java.util.*;

class Pos {
    int x, y;

    Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int n, m, l;
    static int result = 0;
    static List<Pos> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr.add(new Pos(x, y));
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 1; k < l / 2; k++) {
                    calc(arr.get(i).x, arr.get(j).y, k, l / 2 - k);
                }
            }
        }

        System.out.println(result);
    }

    static void calc(int x, int y, int xx, int yy) {
        int cnt = 0;
        for (Pos p : arr) {
            if (x <= p.x && p.x <= x + xx && y <= p.y && p.y <= y + yy) {
                cnt++;
            }
        }
        result = Math.max(res, cnt);
    }
}