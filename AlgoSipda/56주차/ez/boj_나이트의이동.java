import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj_나이트의이동 {
    static int[][] arr;
    static int[][]  visited;
    static int n, cx, cy, ex, ey;
    static int[] dx = {1, 1, 2, 2, -1, -1, -2, -2};
    static int[] dy = {2, -2, 1, -1, 2, -2, 1, -1};

    public static void main(String []args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int t=0;t<T;t++) {
            n = sc.nextInt();
            cx = sc.nextInt();
            cy = sc.nextInt();
            ex = sc.nextInt();
            ey = sc.nextInt();

            arr = new int[n][n];
            visited = new int[n][n];
            visited[cx][cy] = 1;
            bfs(cx, cy);
            System.out.println(visited[ex][ey]-1);
        }
    }
    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});
        while(!queue.isEmpty()) {
            int[] point = queue.poll();
            if(point[0]==ex && point[1]==ey)
                return;
            for(int i=0;i<8;i++) {
                int nx = point[0] + dx[i];
                int ny = point[1] + dy[i];
                if(nx>=0 && ny>=0 && nx<n && ny<n) {
                    if(visited[nx][ny]==0) {
                        queue.add(new int[] {nx, ny});
                        visited[nx][ny] = visited[point[0]][point[1]] + 1;
                    }
                }
            }
        }
    }
}