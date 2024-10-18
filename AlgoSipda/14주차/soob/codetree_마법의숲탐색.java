import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Golem {
        int id, x, y, dir;

        Golem(int id, int x, int y, int dir) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static Golem[] golems;
    static int R, C, K, answer;
    static int[][] arr, exitMap;
    static int[] maxRowValue, parent;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] tx = {2, 1, 1};
    static int[] ty = {0, -1, 1};
    static int[] lx = {0, 1, -1, 1, 2};
    static int[] ly = {-2, -1, -1, -2, -1};
    static int[] rx = {0, 1, -1, 1, 2};
    static int[] ry = {2, 1, 1, 2, 1};
    static int[] ex = {-2, -1, 0, 1, 2, 1, 0, -1};
    static int[] ey = {0, 1, 2, 1, 0, -1, -2, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        initMap();
        maxRowValue = new int[K + 1];
        parent = new int[K + 1];
        for (int i = 0; i <= K; ++i) {
            parent[i] = i;
        }
        golems = new Golem[K + 1];

        answer = 0;
        for (int i = 1; i <= K; ++i) {
            st = new StringTokenizer(br.readLine());
            golems[i] = new Golem(i, 1, stoi(st.nextToken()) - 1, stoi(st.nextToken()));
            simulate(golems[i]);
        }

        System.out.println(answer);
    }

    private static void initMap() {
        arr = new int[R + 3][C];
        exitMap = new int[R + 3][C];
    }

    private static void simulate(Golem golem) {
        while (true) {
            if (canMove(golem, tx, ty)) {
                golem.x++;
                continue;
            }
            if (canMove(golem, lx, ly)) {
                golem.dir = (golem.dir + 3) % 4;
                golem.x++;
                golem.y--;
                continue;
            }
            if (canMove(golem, rx, ry)) {
                golem.dir = (golem.dir + 1) % 4;
                golem.x++;
                golem.y++;
                continue;
            }
            break;
        }

        if (isOut(golem)) {
            initMap();
            return;
        }

        exitMap[golem.x + dx[golem.dir]][golem.y + dy[golem.dir]] = golem.id;
        arr[golem.x][golem.y] = golem.id;
        for (int d = 0; d < 4; ++d) arr[golem.x + dx[d]][golem.y + dy[d]] = golem.id;

        maxRowValue[golem.id] = golem.x - 1;

        int exitX = golem.x + dx[golem.dir];
        int exitY = golem.y + dy[golem.dir];
        for (int d = 0; d < 4; ++d) {
            int nx = exitX + dx[d];
            int ny = exitY + dy[d];
            if (isInRange(nx, ny) && arr[nx][ny] != 0) {
                int n = arr[nx][ny];
                if (arr[nx][ny] != golem.id && maxRowValue[n] > maxRowValue[golem.id]) {
                    maxRowValue[golem.id] = maxRowValue[n];
                    parent[golem.id] = find(parent[n]);
                }
            }
        }

        answer += maxRowValue[golem.id];

        boolean[] checked = new boolean[K + 1];
        checked[golem.id] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(golem.id);
        while (!q.isEmpty()) {
            int id = q.poll();
            for (int d = 0; d < 8; ++d) {
                int nx = golems[id].x + ex[d];
                int ny = golems[id].y + ey[d];
                if (isInRange(nx, ny) && exitMap[nx][ny] != 0 && !checked[exitMap[nx][ny]]) {
                    int near = exitMap[nx][ny];
                    checked[near] = true;
                    q.add(near);
                    if (maxRowValue[golem.id] > maxRowValue[near]) {
                        parent[near] = find(parent[golem.id]);
                        maxRowValue[near] = maxRowValue[golem.id];
                    }
                }
            }
        }
    }

    private static int find(int n) {
        if (parent[n] == n) return n;
        return parent[n] = find(parent[n]);
    }

    private static boolean isInRange(int x, int y) {
        return 0 <= x && x < R + 3 && 0 <= y && y < C;
    }

    private static boolean isInForest(int x, int y) {
        return 3 <= x && x < R + 3 && 0 <= y && y < C;
    }

    private static boolean canMove(Golem golem, int[] xArr, int[] yArr) {
        int len = xArr.length;
        for (int d = 0; d < len; ++d) {
            int nx = golem.x + xArr[d];
            int ny = golem.y + yArr[d];
            if (!isInRange(nx, ny) || arr[nx][ny] != 0) return false;
        }
        return true;
    }

    private static boolean isOut(Golem golem) {
        for (int d = 0; d < 4; ++d) {
            if (!isInForest(golem.x + dx[d], golem.y + dy[d])) return true;
        }
        return false;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}