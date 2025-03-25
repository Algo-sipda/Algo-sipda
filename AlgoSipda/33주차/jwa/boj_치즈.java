import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static int N, M;
    static int[][] area;
    static boolean[][] visited;

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        area = new int[N][M];
        int cheese = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
                if (area[i][j] == 1) {
                    cheese++;
                }
            }
        }

        int hours = 0;
        visited = new boolean[N][M];

        while (cheese > 0) {
            List<Node> melt = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (area[i][j] == 1) {
                        int count = 0;

                        for (int d = 0; d < 4; d++) {
                            int nr = i + dr[d];
                            int nc = j + dc[d];

                            if (area[nr][nc] == 0) {
                                count++;
                            }
                        }

                        if (count < 2)
                            continue;

                        count = 0;

                        for (int d = 0; d < 4; d++) {
                            int nr = i + dr[d];
                            int nc = j + dc[d];

                            if (area[nr][nc] == 0 && bfs(nr, nc)) {
                                count++;
                            }

                            if (count > 1) {
                                melt.add(new Node(i, j));
                                cheese--;
                                break;
                            }
                        }
                    }
                }
            }

            for (Node m : melt) {
                area[m.r][m.c] = 0;
            }

            hours++;
        }

        System.out.println(hours);

    }

    public static boolean bfs(int r, int c) {
        for (boolean[] row : visited) {
            Arrays.fill(row, false);
        }

        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(r, c));
        visited[r][c] = true;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = curr.r + dr[d];
                int nc = curr.c + dc[d];

                if (nr < 1 || nr >= N - 1 || nc < 1 || nc >= M - 1) {
                    return true;
                }

                if (visited[nr][nc] || area[nr][nc] != 0)
                    continue;

                q.offer(new Node(nr, nc));
                visited[nr][nc] = true;
            }
        }

        return false;
    }
}
