import java.util.*;
import java.awt.*;
import java.io.*;

public class Main {

    static int[][] map = new int[102][102];
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static boolean[][] visited = new boolean[102][102];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[y][x] = 1;
        }

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        visited[0][0] = true;
        int answer = 0;

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                if (nx >= 0 && nx < 102 && ny >= 0 && ny < 102) {
                    if (!visited[ny][nx] && map[ny][nx] != 1) {
                        q.add(new Point(nx, ny));
                        visited[ny][nx] = true;
                    } else if (!visited[ny][nx] && map[ny][nx] == 1) {
                        answer++;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
