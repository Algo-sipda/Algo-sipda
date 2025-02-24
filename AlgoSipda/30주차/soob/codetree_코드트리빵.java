import java.io.*;
import java.util.*;

class Position {
    int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isSame(Position p) {
        return this.x == p.x && this.y == p.y;
    }
}

public class Main {
    public static final int INF = Integer.MAX_VALUE;
    public static final Position EMPTY = new Position(-1, -1);
    public static final int DIRECTION_COUNT = 4;
    public static final int MAX_M = 30;
    public static final int MAX_N = 15;

    public static int gridSize, personCount, currentTime;
    public static int[][] grid = new int[MAX_N][MAX_N];
    public static Position[] convenienceStores = new Position[MAX_M];
    public static Position[] people = new Position[MAX_M];
    public static int[][] distance = new int[MAX_N][MAX_N];
    public static boolean[][] visited = new boolean[MAX_N][MAX_N];
    public static int[] dx = {-1, 0, 0, 1};
    public static int[] dy = {0, -1, 1, 0};

    public static boolean isValidPosition(int x, int y) {
        return x >= 0 && x < gridSize && y >= 0 && y < gridSize;
    }

    public static boolean canMove(int x, int y) {
        return isValidPosition(x, y) && !visited[x][y] && grid[x][y] != 2;
    }

    public static void bfs(Position start) {
        for (int i = 0; i < gridSize; i++)
            Arrays.fill(visited[i], false);

        Queue<Position> queue = new LinkedList<>();
        queue.add(start);
        visited[start.x][start.y] = true;
        distance[start.x][start.y] = 0;

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            int x = current.x, y = current.y;
            for (int i = 0; i < DIRECTION_COUNT; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (canMove(nx, ny)) {
                    visited[nx][ny] = true;
                    distance[nx][ny] = distance[x][y] + 1;
                    queue.add(new Position(nx, ny));
                }
            }
        }
    }

    public static void simulate() {
        for (int i = 0; i < personCount; i++) {
            if (people[i] == EMPTY || people[i].isSame(convenienceStores[i]))
                continue;

            bfs(convenienceStores[i]);
            int px = people[i].x, py = people[i].y, minDist = INF, minX = -1, minY = -1;
            for (int j = 0; j < DIRECTION_COUNT; j++) {
                int nx = px + dx[j], ny = py + dy[j];
                if (isValidPosition(nx, ny) && visited[nx][ny] && minDist > distance[nx][ny]) {
                    minDist = distance[nx][ny];
                    minX = nx;
                    minY = ny;
                }
            }
            people[i] = new Position(minX, minY);
        }

        for (int i = 0; i < personCount; i++) {
            if (people[i].isSame(convenienceStores[i]))
                grid[people[i].x][people[i].y] = 2;
        }

        if (currentTime > personCount) return;

        bfs(convenienceStores[currentTime - 1]);
        int minDist = INF, minX = -1, minY = -1;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (visited[i][j] && grid[i][j] == 1 && minDist > distance[i][j]) {
                    minDist = distance[i][j];
                    minX = i;
                    minY = j;
                }
            }
        }
        people[currentTime - 1] = new Position(minX, minY);
        grid[minX][minY] = 2;
    }

    public static boolean allArrived() {
        for (int i = 0; i < personCount; i++) {
            if (!people[i].isSame(convenienceStores[i]))
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        gridSize = Integer.parseInt(st.nextToken());
        personCount = Integer.parseInt(st.nextToken());

        for (int i = 0; i < gridSize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < gridSize; j++)
                grid[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < personCount; i++) {
            st = new StringTokenizer(br.readLine());
            convenienceStores[i] = new Position(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        }

        Arrays.fill(people, EMPTY);

        while (true) {
            currentTime++;
            simulate();
            if (allArrived()) break;
        }

        System.out.print(currentTime);
    }
}

