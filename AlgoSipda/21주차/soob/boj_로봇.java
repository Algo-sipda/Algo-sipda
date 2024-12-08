import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 0, 1, -1}; // 동, 서, 남, 북
    static int[] dy = {1, -1, 0, 0};

    static class Status {
        int x, y, d;

        public Status(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[M][N];
        for (int x = 0; x < M; x++) { // 행(row)
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) { // 열(column)
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        // 시작 좌표 및 방향 입력
        st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken()) - 1; // 행(row)
        int startY = Integer.parseInt(st.nextToken()) - 1; // 열(column)
        int startD = Integer.parseInt(st.nextToken()) - 1;

        // 목표 좌표 및 방향 입력
        st = new StringTokenizer(br.readLine());
        int targetX = Integer.parseInt(st.nextToken()) - 1; // 행(row)
        int targetY = Integer.parseInt(st.nextToken()) - 1; // 열(column)
        int targetD = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(bfs(M, N, map, startX, startY, startD, targetX, targetY, targetD));
    }

    private static int bfs(int M, int N, int[][] map, int startX, int startY, int startD, int targetX, int targetY, int targetD) {
        if (startX == targetX && startY == targetY && startD == targetD) {
            return 0; // 이동할 필요가 없음
        }

        boolean[][][] visited = new boolean[M][N][4];
        Queue<Status> queue = new LinkedList<>();
        queue.add(new Status(startX, startY, startD));
        visited[startX][startY][startD] = true;

        int cnt = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Status current = queue.poll();

                // 목표 지점 도달 확인
                if (current.x == targetX && current.y == targetY && current.d == targetD)
                    return cnt;

                // 1~3칸 전진
                for (int move = 1; move <= 3; move++) {
                    int newX = current.x + dx[current.d] * move;
                    int newY = current.y + dy[current.d] * move;

                    // 범위와 장애물 체크
                    if (newX < 0 || newY < 0 || newX >= M || newY >= N || map[newX][newY] == 1)
                        break;

                    if (!visited[newX][newY][current.d]) {
                        visited[newX][newY][current.d] = true;
                        queue.add(new Status(newX, newY, current.d));
                    }
                }

                if (current.d < 2) {
                    for (int j = 2; j < 4; j++) {
                        if (!visited[current.x][current.y][j]) {
                            queue.add(new Status(current.x, current.y, j));
                            visited[current.x][current.y][j] = true;
                        }
                    }
                } else {
                    for (int j = 0; j < 2; j++) {
                        if (!visited[current.x][current.y][j]) {
                            queue.add(new Status(current.x, current.y, j));
                            visited[current.x][current.y][j] = true;
                        }
                    }
                }

            }
            cnt++;
        }

        return -1;
    }
}