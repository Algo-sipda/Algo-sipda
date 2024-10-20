import java.io.*;
import java.util.*;

public class Main {

    static int[][] map;
    static int answer = 0;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int K = Integer.parseInt(br.readLine());
        map = new int[5][5];
        for(int i = 0; i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            map[r][c] = 2;
        }

        map[0][0] = 1;
        dfs(0, 0, K+1);

        System.out.println(answer);
    }

    public static void dfs(int r, int c, int cnt) {
        if(r == 4 && c == 4 && cnt == 25) {
            answer++;
            return;
        }

        for(int d = 0; d< 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(!isRange(nr, nc) || map[nr][nc] != 0) continue;
            map[nr][nc] = 1;
            dfs(nr, nc, cnt+1);
            map[nr][nc] = 0;
        }

    }

    public static boolean isRange(int r, int c) {
        return 0<= r && r<5 && 0<=c && c<5;
    }
}