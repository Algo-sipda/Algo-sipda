// 1525 퍼즐

import java.util.*;

public class Main {
    static String target = "123456780";

    static int[] dx = { -1, 1, 0, 0 }; // 상, 하, 좌, 우
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder start = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                start.append(sc.nextInt());
            }
        }

        System.out.println(bfs(start.toString()));
    }

    static int bfs(String start) {
        Queue<String> q = new LinkedList<>();
        Map<String, Integer> visited = new HashMap<>();
        q.offer(start);
        visited.put(start, 0);

        while (!q.isEmpty()) {
            String cur = q.poll();
            int moves = visited.get(cur);

            if (cur.equals(target)) return moves;

            int zeroIdx = cur.indexOf('0');
            int x = zeroIdx / 3;
            int y = zeroIdx % 3;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= 3 || ny >= 3) continue;

                int swapIdx = nx * 3 + ny;
                char[] next = cur.toCharArray();
                char temp = next[zeroIdx];
                next[zeroIdx] = next[swapIdx];
                next[swapIdx] = temp;

                String nextStr = new String(next);
                if (!visited.containsKey(nextStr)) {
                    visited.put(nextStr, moves + 1);
                    q.offer(nextStr);
                }
            }
        }

        return -1;
    }
}
