import java.io.*;
import java.util.*;

public class Main {
    static final char[] COLORS = {'B', 'W'};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        char[][] board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        int answer = Integer.MAX_VALUE;

        for (int c = 0; c < COLORS.length; c++) {
            int[][] changed = new int[N + 1][M + 1];
            int prevColor = c;

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (board[i - 1][j - 1] == COLORS[prevColor]) {
                        changed[i][j]++;
                    }

                    changed[i][j] += changed[i - 1][j] + changed[i][j - 1] - changed[i - 1][j - 1];
                    prevColor = (prevColor + 1) % 2;
                }

                if (M % 2 == 0) {
                    prevColor = (prevColor + 1) % 2;
                }
            }

            for (int i = 1; i <= N - K + 1; i++) {
                for (int j = 1; j <= M - K + 1; j++) {
                    answer = Math.min(answer,
                            changed[i + K - 1][j + K - 1] - changed[i - 1][j + K - 1]
                                    - changed[i + K - 1][j - 1] + changed[i - 1][j - 1]);
                }
            }
        }

        System.out.println(answer);
    }
}

// 전체를 두 가지 버전으로, 바뀐 위치 체크
// 누적합으로 특정 영역에서 바뀐 거 개수 세기
