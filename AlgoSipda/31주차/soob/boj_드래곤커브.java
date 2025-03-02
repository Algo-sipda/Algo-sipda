import java.util.*;

public class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] map = new boolean[101][101];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int d = sc.nextInt();
            int g = sc.nextInt();

            drawDragonCurve(x, y, d, g);
        }

        System.out.println(countSquares());
    }

    static void drawDragonCurve(int x, int y, int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);

        for (int i = 0; i < g; i++) {
            for (int j = directions.size() - 1; j >= 0; j--) {
                directions.add((directions.get(j) + 1) % 4);
            }
        }

        map[x][y] = true;
        for (int dir : directions) {
            x += dx[dir];
            y += dy[dir];
            map[x][y] = true;
        }
    }

    static int countSquares() {
        int count = 0;

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
                    count++;
                }
            }
        }

        return count;
    }
}