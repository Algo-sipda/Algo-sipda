import java.util.*;

class pro_지형이동 {

    int N;
    final int[] dr = {1, 0, -1, 0};
    final int[] dc = {0, 1, 0, -1};

    class Point implements Comparable<Point> {
        int r, c, cost;
        public Point (int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
        public int compareTo(Point o) {
            return this.cost - o.cost;
        }
    }

    public int solution(int[][] land, int height) {
        int answer = 0;
        N = land.length;
        boolean[][] visited = new boolean[N][N];
        Queue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(0, 0, 0));

        while (!pq.isEmpty()) {
            Point cur = pq.poll();

            if (visited[cur.r][cur.c]) continue;

            visited[cur.r][cur.c] = true;
            answer += cur.cost;

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (isOut(nr, nc) || visited[nr][nc]) continue;
                int newCost = Math.abs(land[nr][nc] - land[cur.r][cur.c]);
                if (newCost > height) {
                    pq.add(new Point(nr, nc, newCost));
                    continue;
                }
                pq.add(new Point(nr, nc, 0));
            }
        }

        return answer;
    }

    private boolean isOut(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= N || nc >= N;
    }
}