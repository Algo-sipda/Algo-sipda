import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int answer = 0;
    static int R = 0;
    static int C = 0;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        map = new char[R][C];
        for (int r = 0; r < R; r++) {
            String str = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = str.charAt(c);
            }
        }

        DFS(0, 0, map[0][0]);
        System.out.println(answer);
    }

    public static void DFS(int x, int y, char color) {
        if (x == R - 1 && y == C - 1) {
            answer++;
            return;
        }
        for (int r = y + 1; r < R; r++) {
            for (int c = x + 1; c < C; c++) {
                if (map[r][c] != color)
                    DFS(c, r, map[r][c]);
            }
        }
    }
}