import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] map;
    static int count = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) count++;
            }
        }

        for(int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            attack(l, r);
        }

        System.out.println(count);
    }

    public static void attack(int l, int r) {

        for(int i = l - 1; i < r; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 1) {
                    map[i][j] = 0;
                    count--;
                    break;
                }
            }
        }
    }
}
