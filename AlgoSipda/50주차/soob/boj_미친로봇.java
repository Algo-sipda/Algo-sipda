import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static double[] ratioArr;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N;
    static double answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        ratioArr = new double[4];
        for (int i = 0; i < 4; i++) {
            ratioArr[i] = Double.parseDouble(st.nextToken()) / 100;
        }
        
        boolean[][] visited = new boolean[30][30];
        visited[15][15] = true;
        DFS(15, 15, 0, 1, visited);

        System.out.printf("%.20f", answer);
    }

    public static void DFS(int x, int y, int move, double ratio, boolean[][] visited) {
        if (move == N) {
            answer += ratio;
            return;
        }
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || ny < 0 || nx >= 30 || ny >= 30)
                continue;
            if (!visited[ny][nx]) {
                visited[ny][nx] = true;
                DFS(nx, ny, move + 1, ratio * ratioArr[d], visited);
                visited[ny][nx] = false;
            }
        }
    }
}
