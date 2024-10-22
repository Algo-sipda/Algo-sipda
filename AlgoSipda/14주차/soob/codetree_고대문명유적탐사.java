import java.io.*;
import java.util.*;

public class Main {
    static int K, M, maxRoundScore;
    static StringBuilder sb = new StringBuilder();
    static List<Integer> scoreList;
    static Queue<Integer> spare;
    static boolean[][] visit;
    static int[][] arr;
    static List<int[]> list, temp;
    static final int SIZE = 5;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[SIZE][SIZE];
        spare = new ArrayDeque<>();
        for (int i = 0; i < SIZE; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; ++i)
            spare.add(Integer.parseInt(st.nextToken()));

        runSimulation();

        for (int value : scoreList)
            sb.append(value).append(" ");
        sb.append("\n");
        System.out.println(sb);
    }

    private static void runSimulation() {
        scoreList = new ArrayList<>();
        for (int t = 0; t < K; ++t) {
            maxRoundScore = 0;
            list = new ArrayList<>();
            temp = new ArrayList<>();
            int[] expect = getBestRotation();

            if (list.isEmpty()) return;

            arr = rotate(expect[0], expect[1], expect[2]);
            for (int[] pos : list) clearItem(pos[0], pos[1]);
            refillItems();

            while (true) {
                visit = new boolean[SIZE][SIZE];
                int count = 0;
                temp.clear();
                for (int i = 0; i < SIZE; ++i) {
                    for (int j = 0; j < SIZE; ++j) {
                        count += bfs(i, j, arr);
                    }
                }
                if (count == 0) break;
                visit = new boolean[SIZE][SIZE];
                for (int[] pos : temp) clearItem(pos[0], pos[1]);
                refillItems();
                maxRoundScore += count;
            }
            scoreList.add(maxRoundScore);
        }
    }

    private static void clearItem(int x, int y) {
        visit = new boolean[SIZE][SIZE];
        visit[x][y] = true;
        int base = arr[x][y];
        arr[x][y] = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            for (int d = 0; d < 4; ++d) {
                int nx = pos[0] + dx[d];
                int ny = pos[1] + dy[d];
                if (!isInRange(nx, ny)) continue;
                if (!visit[nx][ny] && arr[nx][ny] == base) {
                    visit[nx][ny] = true;
                    arr[nx][ny] = 0;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    private static void refillItems() {
        for (int j = 0; j < SIZE; ++j) {
            for (int i = SIZE - 1; i >= 0; --i) {
                if (arr[i][j] == 0) arr[i][j] = spare.poll();
            }
        }
    }

    private static int[] getBestRotation() {
        int maxScore = 0, rx = -1, ry = -1, rd = -1;

        for (int d = 1; d < 4; ++d) {
            for (int j = 1; j < 4; ++j) {
                for (int i = 1; i < 4; ++i) {
                    int[][] rotated = rotate(i, j, d);
                    visit = new boolean[SIZE][SIZE];
                    temp.clear();
                    int score = 0;
                    for (int r = 0; r < 3; ++r) {
                        for (int c = 0; c < 3; ++c) {
                            if (!visit[r + i - 1][c + j - 1]) score += bfs(r + i - 1, c + j - 1, rotated);
                        }
                    }
                    if (score > maxScore) {
                        list.clear();
                        list.addAll(temp);
                        rx = i;
                        ry = j;
                        rd = d;
                        maxScore = score;
                    }
                }
            }
        }
        maxRoundScore += maxScore;
        return new int[]{rx, ry, rd};
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static int bfs(int x, int y, int[][] grid) {
        int count = 1;
        visit[x][y] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            for (int d = 0; d < 4; ++d) {
                int nx = pos[0] + dx[d];
                int ny = pos[1] + dy[d];
                if (!isInRange(nx, ny)) continue;
                if (!visit[nx][ny] && grid[nx][ny] == grid[x][y]) {
                    count++;
                    visit[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }

        if (count > 2) {
            temp.add(new int[]{x, y});
            return count;
        }
        return 0;
    }

    public static int[][] rotate(int x, int y, int d) {
        int[][] copy = new int[3][3];
        int[][] rotated = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; ++i)
            rotated[i] = Arrays.copyOf(arr[i], SIZE);

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (d == 1) copy[i][j] = arr[3 - j + x - 2][i + y - 1];
                else if (d == 2) copy[i][j] = arr[3 - i + x - 2][3 - j + y - 2];
                else copy[i][j] = arr[j + x - 1][3 - i + y - 2];
            }
        }

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                rotated[i + x - 1][j + y - 1] = copy[i][j];
            }
        }
        return rotated;
    }

    public static boolean isInRange(int x, int y) {
        return 0 <= x && x < SIZE && 0 <= y && y < SIZE;
    }
}