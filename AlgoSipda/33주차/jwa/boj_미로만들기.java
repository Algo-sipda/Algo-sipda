import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] board = new char[N][N];

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int[][] visited = new int[N][N];
        for (int[] row : visited) {
            Arrays.fill(row, 2500);
        }

        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(0, 0));
        visited[0][0] = 0;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = curr.r + dr[d];
                int nc = curr.c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                    continue;

                int destroy = visited[curr.r][curr.c] + (board[nr][nc] == '0' ? 1 : 0);

                if (visited[nr][nc] <= destroy)
                    continue;

                q.offer(new Node(nr, nc));
                visited[nr][nc] = Math.min(visited[nr][nc], destroy);
            }
        }

        System.out.println(visited[N - 1][N - 1]);
    }
}
