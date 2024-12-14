import java.util.*;

class Solution {
    static List<Set<Integer>> columnOil;
    static List<Integer> oilSize;
    static int n, m;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        columnOil = new ArrayList<>();
        oilSize = new ArrayList<>();

        for (int j = 0; j < m; j++) {
            columnOil.add(new HashSet<>());
        }

        int num = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1) {
                    int count = bfs(land, new Node(i, j), num);
                    oilSize.add(count);
                    num++;
                }
            }
        }

        int answer = 0;

        for (int j = 0; j < m; j++) {
            if (columnOil.get(j).isEmpty()) {
                continue;
            }
            int sum = 0;
            for (int n : columnOil.get(j)) {
                sum += oilSize.get(n);
            }
            answer = Math.max(answer, sum);
        }

        return answer;
    }

    static int bfs(int[][] land, Node start, int num) {
        int x = start.x;
        int y = start.y;

        Queue<Node> q = new ArrayDeque<>();
        q.offer(start);
        land[x][y] = -1;
        columnOil.get(y).add(num);

        int count = 1;
        while (!q.isEmpty()) {
            Node curr = q.poll();
            x = curr.x;
            y = curr.y;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || land[nx][ny] != 1) {
                    continue;
                }
                q.offer(new Node(nx, ny));
                land[nx][ny] = -1;
                count++;
                columnOil.get(ny).add(num);
            }
        }

        return count;
    }
}

// 석유 있는 곳에서 bfs, 번호 붙이기, 열마다 집합 만들어서 포함되는 번호 넣기
// 번호마다 석유 덩어리 크기 저장
