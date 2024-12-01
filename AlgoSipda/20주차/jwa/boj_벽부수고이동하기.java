import java.io.*;
import java.util.*;

public class Main {
    static int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static class Node {
        int x, y, destroy, cost;

        Node(int x, int y, int destroy, int cost) {
            this.x = x;
            this.y = y;
            this.destroy = destroy;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] area = new int[N][M];
        boolean[][][] visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                area[i][j] = line.charAt(j) - '0';
            }
        }

        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0, 0, 1));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (curr.x == N - 1 && curr.y == M - 1) {
                System.out.println(curr.cost);
                return;
            }

            for (int[] d : directions) {
                int nx = curr.x + d[0];
                int ny = curr.y + d[1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                // 다음이 벽이면 부술지 결정
                if (area[nx][ny] == 1) {
                    // 아직 안 부쉈으면 부수기
                    if (curr.destroy == 0) {
                        visited[nx][ny][1] = true;
                        queue.add(new Node(nx, ny, 1, curr.cost + 1));
                    }
                } else {
                    if (visited[nx][ny][curr.destroy]) {
                        continue;
                    }
                    visited[nx][ny][curr.destroy] = true;
                    queue.add(new Node(nx, ny, curr.destroy, curr.cost + 1));
                }
            }
        }

        System.out.println(-1);
    }
}
