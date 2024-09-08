import java.io.*;
import java.util.*;

public class Main {

    static int R, C, answer;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        
        for(int i=0;i<R;i++) {
            String str = br.readLine();
            for(int j=0;j<C;j++) {
                map[i][j] = str.charAt(j);
            }
        }

        bfs(0, 0);

        System.out.println(answer);
    }

    public static void bfs(int r, int c) {
        if(r == (R-1) && c == (C-1)) {
            answer++;
            return;
        }

        for(int i = r+1; i<R;i++) {
            for(int j=c+1; j<C; j++) {
                if(map[r][c] != map[i][j]) {
                    bfs(i, j);
                }
            }
        }
    }
}