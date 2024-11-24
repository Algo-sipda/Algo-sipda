import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static int N, M, H, answer;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 세로선의 개수
        M = Integer.parseInt(st.nextToken());   // 가로선의 개수
        H = Integer.parseInt(st.nextToken());   // 가로선을 놓을 수 있는 위치의 개수

        map = new int[H][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            map[a][b] = 1;  // 사다리 표시
        }

        answer = Integer.MAX_VALUE;
        dfs(0, 0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    public static void dfs(int x, int y, int count) {
        if (count > 3)
            return;

        if (isValid()) {
            answer = Math.min(answer, count);
            return;
        }

        for (int i = x; i < H; i++) {
            for (int j = (i == x) ? y : 0; j < N - 1; j++) {
                if (map[i][j] == 0 && map[i][j + 1] == 0) {
                    map[i][j] = 1;
                    dfs(i, j + 2, count + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    public static boolean isValid() {
        for (int start = 0; start < N; start++) {
            int current = start;
            for (int row = 0; row < H; row++) {
                if (current > 0 && map[row][current - 1] == 1)
                    current--;
                else if (current < N - 1 && map[row][current] == 1)
                    current++;
            }
            if (current != start)
                return false;
        }
        return true;
    }
}