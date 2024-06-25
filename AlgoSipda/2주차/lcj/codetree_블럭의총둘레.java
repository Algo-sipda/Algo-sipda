import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] map;
    private static int answer = 0;

    private static final int MAX_SIZE = 101;
    
    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        map = new int[MAX_SIZE+1][MAX_SIZE+1];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine()," ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 1;
        }
        bfs(0, 0);
        System.out.println(answer);
    }

    private static void bfs(int r, int c) {
        if(map[r][c] == 1) {
            answer++;
            return;
        }
        map[r][c] = 2;

        for(int d=0;d<4;d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(isRange(nr, nc) && map[nr][nc] != 2) {
                bfs(nr, nc);
            }
        }
    }

    private static boolean isRange(int r, int c) {
        return (0<=r && r<=MAX_SIZE && 0<=c && c<=MAX_SIZE);
    }
}