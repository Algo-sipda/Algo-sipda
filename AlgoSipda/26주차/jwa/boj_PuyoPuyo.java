import java.io.*;
import java.util.*;

public class Main {
    static final int N = 12, M = 6;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] field;
    static List<Point> puyos;

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        field = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                field[i][j] = line.charAt(j);
            }
        }

        int answer = 0;

        while (true) {
            boolean flag = false;

            // 터뜨리기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (field[i][j] == '.')
                        continue;

                    boolean[][] visited = new boolean[N][M];
                    puyos = new ArrayList<>();
                    puyos.add(new Point(i, j));
                    visited[i][j] = true;

                    dfs(i, j, visited);

                    if (puyos.size() >= 4) {
                        flag = true;
                        for (Point puyo : puyos) {
                            field[puyo.x][puyo.y] = '.';
                        }
                    }
                }
            }

            if (!flag) {
                break;
            }

            answer++;

            // 아래로 내리기
            for (int j = 0; j < M; j++) {
                int count = 0;
                List<Character> puyo = new ArrayList<>();

                for (int i = N - 1; i >= 0; i--) {
                    if (field[i][j] == '.')
                        continue;
                    count = N - i;
                    puyo.add(field[i][j]);
                }

                if (puyo.size() < count) {
                    for (int p = 0; p < puyo.size(); p++) {
                        field[N - 1 - p][j] = puyo.get(p);
                    }
                    for (int p = puyo.size(); p < count; p++) {
                        field[N - 1 - p][j] = '.';
                    }
                }
            }
        }

        System.out.println(answer);
    }

    public static void dfs(int x, int y, boolean[][] visited) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny])
                continue;

            visited[nx][ny] = true;

            if (field[x][y] == field[nx][ny]) {
                puyos.add(new Point(nx, ny));
                dfs(nx, ny, visited);
            }
        }
    }
}
