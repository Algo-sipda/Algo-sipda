import java.io.*;
import java.util.*;

class swea_원자소멸시뮬레이션 {
    static int n;
    static int answer = 0;
    static int[][] map = new int[4001][4001];
    static Queue<Atom> queue = new ArrayDeque<>();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Atom {
        int x, y, d, e;
        public Atom(int x, int y, int d, int e) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.e = e;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            answer = 0;
            queue.clear();
            for (int i = 0; i < 4001; i++) {
                Arrays.fill(map[i], 0);
            }

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int y = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int d = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());

                map[y][x] = e;
                queue.offer(new Atom(y, x, d, e));
            }

            while (!queue.isEmpty()) {
                Atom atom = queue.poll();
                if (map[atom.x][atom.y] != atom.e) {
                    answer += map[atom.x][atom.y];
                    map[atom.x][atom.y] = 0;
                    continue;
                }

                int nx = atom.x + dx[atom.d];
                int ny = atom.y + dy[atom.d];

                if (nx >= 0 && ny >= 0 && nx <= 4000 && ny <= 4000) {
                    if (map[nx][ny] == 0) {
                        map[nx][ny] = atom.e;
                        queue.add(new Atom(nx, ny, atom.d, atom.e));
                    } else {
                        map[nx][ny] += atom.e;
                    }
                }
                map[atom.x][atom.y] = 0;
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }
}