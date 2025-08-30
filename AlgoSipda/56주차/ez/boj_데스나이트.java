import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_데스나이트 {
    static int[][] arr;
    static int[][] visited;
    static int n, r1, c1, r2, c2;
    static int[] dx = {-2, -2, 0, 0, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -1, 1};
    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[n][n];
        visited = new int[n][n];
        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        visited[r1][c1] = 1;
        bfs(r1, c1);
        if(visited[r2][c2]>1)
            System.out.println(visited[r2][c2] - 1);
        else
            System.out.println(-1);
    }
    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});
        while(!queue.isEmpty()) {
            int[] point = queue.poll();
            for(int i=0;i<6;i++) {
                int nx = point[0] + dx[i];
                int ny = point[1] + dy[i];
                if(nx<0 || ny<0 || nx>=n || ny>=n) continue;
                if(visited[nx][ny] == 0) {
                    queue.add(new int[] {nx, ny});
                    visited[nx][ny] = visited[point[0]][point[1]] + 1;
                }
            }
        }
    }
}