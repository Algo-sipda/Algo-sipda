import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static char[][] map = new char[5][5];
    static int answer = 0;
    static List<Integer> selected = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int y = 0; y < 5; y++) {
            String str = br.readLine();
            for (int x = 0; x < 5; x++) {
                map[y][x] = str.charAt(x);
            }
        }

        combination(0, 0);
        System.out.println(answer);
    }

    public static void combination(int start, int depth) {
        if (depth == 7) {
            if (check()) answer++;
            return;
        }

        for (int i = start; i < 25; i++) {
            selected.add(i);
            combination(i + 1, depth + 1);
            selected.remove(selected.size() - 1);
        }
    }

    public static boolean check() {
        int sCount = 0;
        boolean[][] isSelected = new boolean[5][5];
        for (int pos : selected) {
            int y = pos / 5;
            int x = pos % 5;
            isSelected[y][x] = true;
            if (map[y][x] == 'S') sCount++;
        }
        if (sCount < 4) return false;

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];

        int startY = selected.get(0) / 5;
        int startX = selected.get(0) % 5;
        q.add(new int[]{startY, startX});
        visited[startY][startX] = true;

        int count = 1;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int ny = cur[0] + dy[d];
                int nx = cur[1] + dx[d];
                if (ny < 0 || nx < 0 || ny >= 5 || nx >= 5) continue;
                if (visited[ny][nx]) continue;
                if (!isSelected[ny][nx]) continue;
                visited[ny][nx] = true;
                q.add(new int[]{ny, nx});
                count++;
            }
        }

        return count == 7;
    }
}
