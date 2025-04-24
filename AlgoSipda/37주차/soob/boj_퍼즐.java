import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static final String TARGET = "123456780";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder start = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                start.append(st.nextToken());
            }
        }

        System.out.println(bfs(start.toString()));
    }

    static int bfs(String start) {
        Queue<String> q = new LinkedList<>();
        Map<String, Integer> visited = new HashMap<>();

        q.add(start);
        visited.put(start, 0);

        while (!q.isEmpty()) {
            String cur = q.poll();
            int move = visited.get(cur);

            if (cur.equals(TARGET)) return move;

            int zero = cur.indexOf('0');
            int x = zero / 3;
            int y = zero % 3;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= 3 || ny >= 3) continue;

                int swapIdx = nx * 3 + ny;
                String next = swap(cur, zero, swapIdx);

                if (!visited.containsKey(next)) {
                    visited.put(next, move + 1);
                    q.add(next);
                }
            }
        }

        return -1;
    }

    static String swap(String s, int i, int j) {
        char[] arr = s.toCharArray();
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return new String(arr);
    }
}
