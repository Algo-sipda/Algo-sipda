import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        dfs(0, 0);
        System.out.println(answer);
    }

    static void dfs(int y, int x) {
        if (x == M) {
            x = 0;
            y++;
        }
        if (y == N) {
            answer++;
            return;
        }

        dfs(y, x + 1);

        if (y >= 1 && x >= 1) {
            if (board[y - 1][x] == 1 && board[y][x - 1] == 1 && board[y - 1][x - 1] == 1) {
                return;
            }
        }
        board[y][x] = 1;
        dfs(y, x + 1);
        board[y][x] = 0;
    }
}
