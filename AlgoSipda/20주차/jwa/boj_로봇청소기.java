import java.io.*;
import java.util.*;

public class Main {
    static int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북, 동, 남, 서

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int[][] area = new int[N][M];

        // 0: 청소 안 된 빈칸, 1: 벽, -1: 청소 완료
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        
        while (true) {
            if (area[r][c] == 0) {
                area[r][c] = -1;
                answer++;
            }
            boolean flag = false;
            for (int i = 0; i < directions.length; i++) {
                int nr = r + directions[i][0];
                int nc = c + directions[i][1];
                if (area[nr][nc] == 0) {
                    flag = true;
                    break;
                }
            }
            // 청소되지 않은 칸이 없는 경우
            if (!flag) {
                int nr = r - directions[d][0];
                int nc = c - directions[d][1];
                if (area[nr][nc] == 1) {
                    break;
                }
                r = nr;
                c = nc;
                continue;
            }
            // 청소되지 않은 칸이 있는 경우
            d = (d + 3) % 4;
            int nr = r + directions[d][0];
            int nc = c + directions[d][1];
            if (area[nr][nc] == 0) {
                r = nr;
                c = nc;
            }
        }

        System.out.println(answer);
    }
}
