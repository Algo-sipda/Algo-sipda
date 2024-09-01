import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class boj_치즈 {
    static int[][] arr;
    static boolean[][] visited;
    static int n, m, count, cheese;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String []args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        n = Integer.parseInt(input.split(" ")[0]);
        m = Integer.parseInt(input.split(" ")[1]);

        arr = new int[n][m];
        visited = new boolean[n][m];
        count = 0;
        cheese = 0;

        for(int i=0;i<n;i++) {
            String line = br.readLine();
            for(int j=0;j<m;j++) {
                arr[i][j] = Integer.parseInt(line.split(" ")[j]);
                if(arr[i][j]==1) cheese++;
            }
        }
        change_out_air(0,0);

        while(cheese!=0) {
            visited = new boolean[n][m];
            for(int i=0;i<n;i++) {
                for(int j=0;j<m;j++) {
                    if(arr[i][j] == 1 && !visited[i][j])
                        bfs(i, j);
                }
            }
            visited = new boolean[n][m];
            change_out_air(0, 0);
            for(int i=0;i<n;i++) {
                for(int j=0;j<m;j++) {
                    if(arr[i][j] == 3) arr[i][j] = 2;
                }
            }
            count++;
        }
        System.out.println(count);
    }
    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});
        visited[x][y] = true;
        while(!queue.isEmpty()) {
            int[] point = queue.poll();
            if(arr[point[0]][point[1]] == 1 && check_cheese(point[0], point[1])>=2) {
                cheese--;
                arr[point[0]][point[1]] = 3;
            }
            for(int i=0;i<4;i++) {
                int nx = point[0] + dx[i];
                int ny = point[1] + dy[i];
                if(nx>-1&& ny>-1 && nx<n && ny<m) {
                    if(arr[nx][ny] == 1 && visited[nx][ny] == false) {
                        visited[nx][ny] = true;
                        queue.add(new int[] {nx, ny});
                    }
                }
            }
        }
    }
    public static void change_out_air(int x, int y) {
        visited[x][y] = true;
        arr[x][y] = 2;

        for(int i=0;i<4;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
            if(visited[nx][ny] || arr[nx][ny] == 1) continue;
            arr[nx][ny] = 2;
            change_out_air(nx, ny);
        }
    }
    public static int check_cheese(int x, int y){
        int air = 0;
        for(int i=0;i<4;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
            if(arr[nx][ny] == 2) air++;
        }
        return air;
    }
}