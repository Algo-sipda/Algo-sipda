import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static char[][] map, blindnessMap;
    static boolean[][] visited;
    static int answer = 0, blindnessAnswer = 0;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        blindnessMap = new char[N][N];


        for(int i=0;i<N;i++) {
            String str = br.readLine();
            for(int j=0;j<N;j++) {
                map[i][j] = str.charAt(j);
                blindnessMap[i][j] = map[i][j] == 'G'? 'R' :map[i][j];
            }
        }

        visited = new boolean[N][N];
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(!visited[i][j]) {
                    dfs(i, j, map);
                    answer++;
                }
            }
        }

        visited = new boolean[N][N];
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(!visited[i][j]) {
                    dfs(i, j, blindnessMap);
                    blindnessAnswer++;
                }
            }
        }
        System.out.println(answer+" "+blindnessAnswer);
        
    }

    public static void dfs(int r, int c, char[][] map) {
        visited[r][c] = true;

        char curColor = map[r][c];

        for(int d = 0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

            if(!visited[nr][nc] && map[nr][nc] == curColor) {
                dfs(nr, nc, map);
            }
        }
    }
}