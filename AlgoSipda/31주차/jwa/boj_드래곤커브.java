import java.io.*;
import java.util.*;

public class Main {
    static final int SIZE = 100;
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[][] visited = new boolean[SIZE + 1][SIZE + 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            List<Integer> history = new ArrayList<>();
            history.add(d);
            visited[y][x] = true;
            x += dx[d];
            y += dy[d];
            visited[y][x] = true;

            for (int j = 1; j <= g; j++) {
                int len = history.size();
                for (int k = len - 1; k >= 0; k--) {
                    int nd = (history.get(k) + 1) % 4;
                    history.add(nd);
                    x += dx[nd];
                    y += dy[nd];
                    visited[y][x] = true;
                }
            }
        }

        int answer = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (visited[i][j] && visited[i][j + 1] && visited[i + 1][j]
                        && visited[i + 1][j + 1]) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}

// 현재까지 방향 저장, 다음 커브는 현재까지의 역순으로 +1한 방향으로 이어짐
