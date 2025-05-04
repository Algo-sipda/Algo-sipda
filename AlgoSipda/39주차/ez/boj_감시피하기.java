import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_감시피하기 {
    static int N;
    static char[][] map;
    static List<int[]> teacher;

    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        teacher = new ArrayList<int[]>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if (map[i][j] == 'T') {
                    teacher.add(new int[] {i, j});
                }
            }
        }

        if (makeWall(0, 0, 0)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean makeWall(int cnt, int row, int col) {
        if (cnt == 3) {
            if (see()) {
                return true;
            }
            return false;
        }

        for (int i = row; i < N; i++) {
            for (int j = col; j < N; j++) {
                if (map[i][j] == 'X') {
                    map[i][j] = 'O';
                    if (makeWall(cnt + 1, i, j+1)) {
                        return true;
                    }
                    map[i][j] = 'X';
                }
            }
            col = 0;
        }
        return false;
    }

    private static boolean see() {
        for (int i = 0; i < teacher.size(); i++) {
            int[] cur = teacher.get(i);
            for (int j = 0; j < 4; j++) {
                int nr = cur[0];
                int nc = cur[1];
                while (true) {
                    nr += dr[j];
                    nc += dc[j];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) break;
                    if (map[nr][nc] == 'O') break;
                    if (map[nr][nc] == 'S') {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}