import java.io.*;
import java.util.*;

public class Main {
    static int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int M, N;
    static int[][] area;
    static boolean[][][] visited;

    static class Node implements Comparable<Node> {
        int x, y, d, dist;

        Node(int x, int y, int d, int dist) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        area = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Node[] nodes = new Node[2];
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            nodes[i] = new Node(Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1,
                    convertDirection(Integer.parseInt(st.nextToken()) - 1), 0);
        }

        Node start = nodes[0], end = nodes[1];

        Queue<Node> queue = new ArrayDeque<>();
        visited = new boolean[M][N][4];

        queue.add(start);
        visited[start.x][start.y][start.d] = true;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            int x = curr.x;
            int y = curr.y;
            int d = curr.d;
            int dist = curr.dist;

            if (x == end.x && y == end.y && d == end.d) {
                System.out.println(dist);
                return;
            }

            for (int i = 1; i <= 3; i++) {
                int nx = x + directions[d][0] * i;
                int ny = y + directions[d][1] * i;

                if (nx < 0 || nx >= M || ny < 0 || ny >= N || area[nx][ny] == 1) {
                    break;
                }

                if (!visited[nx][ny][d]) {
                    queue.add(new Node(nx, ny, d, dist + 1));
                    visited[nx][ny][d] = true;
                }
            }

            int[] nds = {(d + 3) % 4, (d + 1) % 4};

            for (int nd : nds) {
                if (!visited[x][y][nd]) {
                    queue.add(new Node(x, y, nd, dist + 1));
                    visited[x][y][nd] = true;
                }
            }
        }
    }

    static int convertDirection(int d) {
        int[] newD = {1, 3, 2, 0};

        return newD[d];
    }
}
