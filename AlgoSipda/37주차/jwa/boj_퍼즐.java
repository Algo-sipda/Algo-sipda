import java.io.*;
import java.util.*;

public class Main {
    static final String GOAL = "123456780";
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

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

        q.offer(start);
        visited.put(start, 0);

        while (!q.isEmpty()) {
            String curr = q.poll();
            int moves = visited.get(curr);

            if (curr.equals(GOAL))
                return moves;

            int zeroIdx = curr.indexOf('0');
            int row = zeroIdx / 3;
            int col = zeroIdx % 3;

            for (int d = 0; d < 4; d++) {
                int nr = row + dr[d];
                int nc = col + dc[d];

                if (nr >= 0 && nr < 3 && nc >= 0 && nc < 3) {
                    int newIdx = nr * 3 + nc;
                    String next = swap(curr, zeroIdx, newIdx);

                    if (!visited.containsKey(next)) {
                        visited.put(next, moves + 1);
                        q.offer(next);
                    }
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
