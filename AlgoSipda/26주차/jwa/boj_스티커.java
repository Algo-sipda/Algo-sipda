import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] stickers = new int[2][N];

            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if (N > 1) {
                stickers[0][1] += stickers[1][0];
                stickers[1][1] += stickers[0][0];
            }

            for (int i = 2; i < N; i++) {
                stickers[0][i] = Math.max(stickers[1][i - 1], stickers[1][i - 2]) + stickers[0][i];
                stickers[1][i] = Math.max(stickers[0][i - 1], stickers[0][i - 2]) + stickers[1][i];
            }

            sb.append(Math.max(stickers[0][N - 1], stickers[1][N - 1]))
                    .append(t != T - 1 ? "\n" : "");
        }

        System.out.println(sb);
    }
}

// 첫번째 줄은 왼쪽 아래, 왼쪽 아래의 왼쪽
// 두번째 줄은 왼쪽 위, 왼쪽 위의 왼쪽
