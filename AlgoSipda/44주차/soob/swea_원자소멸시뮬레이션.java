import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;
 
public class Solution {
 
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N * 2][N * 2];
            Queue<Atom> queue = new LinkedList<>();
            int sum = 0;
            for (int n = 0; n < N; n++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());   // x 좌표
                int y = Integer.parseInt(st.nextToken());   // y 좌표
                int d = Integer.parseInt(st.nextToken());   // 방향
                int k = Integer.parseInt(st.nextToken());   // 보유 에너지
                sum += k;
                queue.add(new Atom((x + 1000) * 2, (y + 1000) * 2, d, k));
            }
 
            while (!queue.isEmpty()) {
                int size = queue.size();
                Set<Point> coordinates = new HashSet<>();
                Set<Point> collision = new HashSet<>();
                List<Atom> list = new ArrayList<>();
                for (int s = 0; s < size; s++) {
                    Atom atom = queue.poll();
                    int nx = atom.x + dx[atom.d];
                    int ny = atom.y + dy[atom.d];
                    if (nx < 0 || ny < 0 || nx >= 4000 || ny >= 4000) {
                        sum -= atom.k;
                        continue;
                    }
                    if (coordinates.contains(new Point(nx, ny)))
                        collision.add(new Point(nx, ny));
 
                    list.add(new Atom(nx, ny, atom.d, atom.k));
                    coordinates.add(new Point(nx, ny));
                }
 
                for (Atom next : list) {
                    boolean flag = false;
                    for (Point point : collision) {
                        if (next.x == point.x && next.y == point.y) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag)
                        queue.add(next);
                }
            }
 
            System.out.println("#" + t + " " + sum);
        }
    }
 
    public static class Atom {
        int x;
        int y;
        int d;
        int k;
 
        public Atom(int x, int y, int d, int k) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.k = k;
        }
    }
}