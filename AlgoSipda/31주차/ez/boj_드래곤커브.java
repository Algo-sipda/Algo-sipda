import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_드래곤커브 {

    static int N;
    static boolean[][] map;

    static final int SIZE = 101;
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new boolean[SIZE][SIZE];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            dragon_curve(x, y, d, g);
        }

        System.out.println(calcSquare());
    }

    private static void dragon_curve(int x, int y, int d, int g) {
        List<Integer> dirList = new ArrayList<Integer>();
        dirList.add(d);
        map[x][y] = true;

        // 방향 지정
        for (int i = 0; i < g; i++) { // 세대만큼 반복
            for (int j = dirList.size() - 1; j >= 0; j--) { // 끝점을 기준으로 시계 방향 90도 이동
                int nd = (dirList.get(j) + 1) % 4;
                dirList.add(nd);
            }
        }

        for (int nd : dirList) {
            x += dx[nd];
            y += dy[nd];
            map[x][y] = true;
        }

    }

    private static int calcSquare() {
        int cnt = 0;
        for (int i = 0; i < SIZE - 1; i++) {
            for (int j = 0; j < SIZE - 1; j++) {
                if (map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}