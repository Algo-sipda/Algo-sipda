import java.io.*;
import java.util.*;

public class Main {
    static int N, K, S, X, Y;
    static int[][] map;
    static Queue<Node> q;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Node implements Comparable<Node> {
        int r, c, num;

        Node(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }

        @Override
        public int compareTo(Node o) {
            return this.num - o.num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        List<Node> virusList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int value = Integer.parseInt(st.nextToken());
                map[i][j] = value;
                if (value > 0) {
                    virusList.add(new Node(i, j, value));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;
        Y = Integer.parseInt(st.nextToken()) - 1;

        Collections.sort(virusList);

        q = new ArrayDeque<>();
        for (Node node : virusList) {
            q.offer(node);
        }

        bfs();

        System.out.println(map[X][Y]);
    }

    static void bfs() {
        int time = 0;

        while (!q.isEmpty()) {
            if (time == S) {
                return;
            }

            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node curr = q.poll();
                int r = curr.r;
                int c = curr.c;
                int num = curr.num;

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] != 0) {
                        continue;
                    }

                    map[nr][nc] = num;
                    q.offer(new Node(nr, nc, num));
                }
            }
            time++;
        }
    }
}
