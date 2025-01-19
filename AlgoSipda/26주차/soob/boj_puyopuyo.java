import java.awt.Point;
import java.util.*;

public class Main {

    static char[][] board;
    static final int ROWS = 12, COLS = 6;
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        board = new char[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            board[i] = scanner.nextLine().toCharArray();
        }

        int chainCount = 0;

        while (true) {
            boolean hasPopped = false;
            visited = new boolean[ROWS][COLS];

            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (board[i][j] != '.' && !visited[i][j]) {
                        List<Point> group = findGroup(i, j);

                        if (group.size() >= 4) {
                            hasPopped = true;
                            popGroup(group);
                        }
                    }
                }
            }

            if (!hasPopped) break;

            applyGravity();
            chainCount++;
        }

        System.out.println(chainCount);
    }

    private static List<Point> findGroup(int x, int y) {
        List<Point> group = new ArrayList<>();
        Queue<Point> queue = new LinkedList<>();
        char color = board[x][y];

        queue.add(new Point(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            group.add(current);

            for (int d = 0; d < 4; d++) {
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];

                if (isInBounds(nx, ny) && !visited[nx][ny] && board[nx][ny] == color) {
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                }
            }
        }

        return group;
    }

    private static void popGroup(List<Point> group) {
        for (Point p : group) {
            board[p.x][p.y] = '.';
        }
    }

    private static void applyGravity() {
        for (int col = 0; col < COLS; col++) {
            int emptyRow = ROWS - 1;

            for (int row = ROWS - 1; row >= 0; row--) {
                if (board[row][col] != '.') {
                    board[emptyRow][col] = board[row][col];
                    if (emptyRow != row) {
                        board[row][col] = '.';
                    }
                    emptyRow--;
                }
            }
        }
    }

    private static boolean isInBounds(int x, int y) {
        return x >= 0 && x < ROWS && y >= 0 && y < COLS;
    }
}