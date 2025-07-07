// [PRO] 경주로 건설 https://school.programmers.co.kr/learn/courses/30/lessons/67259
import java.util.*;

class Solution {
    static int N, M;
    static int[][] way = { {0,1}, {1,0}, {-1,0}, {0,-1} }; // 우, 하, 상, 좌

    public int solution(int[][] board) {
        N = board.length;
        M = board[0].length;

        int[][][] cost = new int[N][M][4]; // [x][y][direction]
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(cost[i][j], Integer.MAX_VALUE);
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        pq.add(new Node(0, 0, 0, -1)); // 시작점 (x, y, 비용, 방향)

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.x == N - 1 && cur.y == M - 1) {
                return cur.cost;
            }

            for (int w = 0; w < 4; w++) {
                int nx = cur.x + way[w][0];
                int ny = cur.y + way[w][1];

                if (!inRange(nx, ny) || board[nx][ny] == 1) continue;

                int nextCost = cur.cost + (cur.dir == -1 || cur.dir == w ? 100 : 600);

                if (cost[nx][ny][w] > nextCost) {
                    cost[nx][ny][w] = nextCost;
                    pq.add(new Node(nx, ny, nextCost, w));
                }
            }
        }

        return 0;
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static class Node {
        int x, y, cost, dir;
        Node(int x, int y, int cost, int dir) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.dir = dir;
        }
    }
}
