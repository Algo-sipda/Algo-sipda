import java.util.*;
import java.io.*;

public class Main {
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int currentTime = 0;
    static int gridSize, peopleCount;
    static int[][] area;
    static ArrayList<Point> targetStores, baseCamps, peoplePositions;

    static class Point {
        int row, col;
        boolean hasArrived;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        Point(int row, int col, boolean hasArrived) {
            this.row = row;
            this.col = col;
            this.hasArrived = hasArrived;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        gridSize = Integer.parseInt(st.nextToken());
        peopleCount = Integer.parseInt(st.nextToken());
        area = new int[gridSize][gridSize];
        targetStores = new ArrayList<>();
        baseCamps = new ArrayList<>();
        peoplePositions = new ArrayList<>();

        for (int i = 0; i < gridSize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < gridSize; j++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 1)
                    baseCamps.add(new Point(i, j));
                area[i][j] = 0;
            }
        }

        for (int i = 1; i <= peopleCount; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            targetStores.add(new Point(row, col));
        }

        while (true) {
            currentTime++;

            movePeople();

            if (currentTime <= peopleCount)
                assignBaseCamp();

            if (allPeopleArrived()) {
                break;
            }
        }

        System.out.println(currentTime);
    }

    static void movePeople() {
        for (int i = 0; i < peoplePositions.size(); i++) {
            Point person = peoplePositions.get(i);
            if (person.hasArrived)
                continue;
            Point store = targetStores.get(i);

            Queue<Point> queue = new LinkedList<>();
            boolean[][] visited = new boolean[gridSize][gridSize];
            visited[person.row][person.col] = true;
            Point[][] previousStep = new Point[gridSize][gridSize];
            queue.add(person);

            while (!queue.isEmpty()) {
                Point node = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = node.row + dx[j];
                    int ny = node.col + dy[j];

                    if (nx < 0 || ny < 0 || nx >= gridSize || ny >= gridSize || visited[nx][ny]
                            || (area[nx][ny] > 0 && area[nx][ny] != i + 1))
                        continue;

                    queue.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                    previousStep[nx][ny] = new Point(node.row, node.col);
                }
            }

            if (previousStep[store.row][store.col] == null) {
                continue;
            }

            int cx = previousStep[store.row][store.col].row;
            int cy = previousStep[store.row][store.col].col;

            if (cx == person.row && cy == person.col) {
                person.row = cx;
                person.col = cy;
                store.hasArrived = true;
                person.hasArrived = true;
                area[store.row][store.col] = i + 1;
                continue;
            }

            while (true) {
                int nextRow = previousStep[cx][cy].row;
                int nextCol = previousStep[cx][cy].col;

                if (nextRow == person.row && nextCol == person.col) {
                    person.row = cx;
                    person.col = cy;
                    break;
                }

                cx = nextRow;
                cy = nextCol;
            }
        }
    }

    static void assignBaseCamp() {
        Point store = targetStores.get(currentTime - 1);
        int minDist = Integer.MAX_VALUE;
        int minRow = 0;
        int minCol = 0;
        int selectedBaseCamp = 0;

        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[gridSize][gridSize];
        int[][] distance = new int[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        queue.add(store);
        visited[store.row][store.col] = true;
        distance[store.row][store.col] = 0;

        while (!queue.isEmpty()) {
            Point node = queue.poll();
            for (int j = 0; j < 4; j++) {
                int nx = node.row + dx[j];
                int ny = node.col + dy[j];

                if (nx < 0 || ny < 0 || nx >= gridSize || ny >= gridSize || visited[nx][ny]
                        || (area[nx][ny] > 0 && area[nx][ny] != currentTime))
                    continue;

                queue.add(new Point(nx, ny));
                visited[nx][ny] = true;
                distance[nx][ny] = distance[node.row][node.col] + 1;
            }
        }

        for (Point base : baseCamps) {
            if (base.hasArrived)
                continue;

            int dist = distance[base.row][base.col];

            if (minDist > dist) {
                minDist = dist;
                minRow = base.row;
                minCol = base.col;
                selectedBaseCamp = baseCamps.indexOf(base);
            } else if (minDist == dist) {
                if (minRow > base.row || (minRow == base.row && minCol > base.col)) {
                    minRow = base.row;
                    minCol = base.col;
                    selectedBaseCamp = baseCamps.indexOf(base);
                }
            }
        }

        baseCamps.get(selectedBaseCamp).hasArrived = true;
        peoplePositions.add(new Point(minRow, minCol, false));
        area[minRow][minCol] = currentTime;
    }

    static boolean allPeopleArrived() {
        for (int i = 0; i < peopleCount; i++) {
            if (peoplePositions.get(i) == null || !peoplePositions.get(i).hasArrived)
                return false;
        }

        return true;
    }
}
