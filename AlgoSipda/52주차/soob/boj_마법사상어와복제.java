import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] sx = {-1, 0, 1, 0};
    static int[] sy = {0, -1, 0, 1};

    static List<Integer>[][] fishMap = new ArrayList[4][4];
    static List<Integer>[][] copyMap = new ArrayList[4][4];
    static int[][] smell = new int[4][4];
    static int sxPos, syPos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                fishMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int fx = Integer.parseInt(st.nextToken()) - 1;
            int fy = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            fishMap[fx][fy].add(d);
        }

        st = new StringTokenizer(br.readLine());
        sxPos = Integer.parseInt(st.nextToken()) - 1;
        syPos = Integer.parseInt(st.nextToken()) - 1;

        for (int t = 0; t < S; t++) {
            copy();
            moveFish();
            moveShark();
            removeSmell();
            applyCopy();
        }

        System.out.println(countFish());
    }

    static void copy() {
        copyMap = new ArrayList[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copyMap[i][j] = new ArrayList<>(fishMap[i][j]);
            }
        }
    }

    static void moveFish() {
        List<Integer>[][] newMap = new ArrayList[4][4];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                newMap[i][j] = new ArrayList<>();

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                for (int dir : fishMap[x][y]) {
                    boolean moved = false;
                    for (int i = 0; i < 8; i++) {
                        int nd = (dir - i + 8) % 8;
                        int nx = x + dx[nd];
                        int ny = y + dy[nd];
                        if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
                        if (smell[nx][ny] > 0 || (nx == sxPos && ny == syPos)) continue;
                        newMap[nx][ny].add(nd);
                        moved = true;
                        break;
                    }
                    if (!moved) newMap[x][y].add(dir);
                }
            }
        }

        fishMap = newMap;
    }

    static void moveShark() {
        int maxEat = -1;
        int[] bestRoute = new int[3];
        List<int[]> candidates = new ArrayList<>();

        dfs(sxPos, syPos, 0, new boolean[4][4], 0, new int[3], candidates);

        for (int[] route : candidates) {
            int eat = countEat(route);
            if (eat > maxEat || (eat == maxEat && isBetter(route, bestRoute))) {
                maxEat = eat;
                bestRoute = route.clone();
            }
        }

        int x = sxPos, y = syPos;
        for (int d : bestRoute) {
            x += sx[d];
            y += sy[d];
            if (!fishMap[x][y].isEmpty()) {
                fishMap[x][y].clear();
                smell[x][y] = 3;
            }
        }
        sxPos = x;
        syPos = y;
    }

    static void dfs(int x, int y, int depth, boolean[][] visited, int eat, int[] route, List<int[]> candidates) {
        if (depth == 3) {
            candidates.add(route.clone());
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + sx[d];
            int ny = y + sy[d];
            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
            boolean wasVisited = visited[nx][ny];
            visited[nx][ny] = true;
            route[depth] = d;
            dfs(nx, ny, depth + 1, visited, eat, route, candidates);
            visited[nx][ny] = wasVisited;
        }
    }

    static int countEat(int[] route) {
        int x = sxPos, y = syPos;
        boolean[][] visited = new boolean[4][4];
        int count = 0;
        for (int d : route) {
            x += sx[d];
            y += sy[d];
            if (visited[x][y]) continue;
            count += fishMap[x][y].size();
            visited[x][y] = true;
        }
        return count;
    }

    static boolean isBetter(int[] a, int[] b) {
        for (int i = 0; i < 3; i++) {
            if (a[i] != b[i]) return a[i] < b[i];
        }
        return false;
    }

    static void removeSmell() {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (smell[i][j] > 0) smell[i][j]--;
    }

    static void applyCopy() {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                fishMap[i][j].addAll(copyMap[i][j]);
    }

    static int countFish() {
        int count = 0;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                count += fishMap[i][j].size();
        return count;
    }
}
