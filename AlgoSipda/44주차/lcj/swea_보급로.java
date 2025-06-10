import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
 
class Point implements Comparable<Point>{
    int r;
    int c;
    int cost;
    public Point(int r, int c, int cost) {
        this.r = r;
        this.c = c;
        this.cost = cost;
    }
     
    @Override
    public int compareTo(Point o) {
        return this.cost - o.cost;
    }
}
 
public class Solution {
     
    static int N;
    static int[][] map;
    static int[][] depth;
    static int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    static int answer;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
         
        for(int test_case = 1; test_case<=T;test_case++) {
            N = Integer.parseInt(br.readLine());
             
            map = new int[N][N];
            depth = new int[N][N];
             
            for(int i=0;i<N;i++) {
                String s = br.readLine();
                for(int j=0;j<N;j++) {
                    map[i][j] = s.charAt(j)-'0';
                    depth[i][j] = Integer.MAX_VALUE;
                }
            }
             
            answer = Integer.MAX_VALUE;
             
            bfs();
             
            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }
     
    private static void bfs() {
        PriorityQueue<Point> q = new PriorityQueue<>();
        depth[0][0] = map[0][0];
        q.add(new Point(0, 0, map[0][0]));
         
        while(!q.isEmpty()) {
            Point cur = q.poll();
             
            if(cur.r == (N-1) && cur.c == (N-1)) {
                answer = Math.min(answer, cur.cost);
                break;
            }
             
            for(int i=0;i<4;i++) {
                int nr = cur.r+dir[i][0];
                int nc = cur.c + dir[i][1];
                 
                if(nr<0 || nc<0 || nr>=N || nc>=N)
                    continue;
                 
                //가중치 저장
                if(depth[nr][nc] > map[nr][nc]+cur.cost) {
                    depth[nr][nc] = map[nr][nc] + cur.cost;
                    q.add(new Point(nr, nc, depth[nr][nc]));
                }
                 
            }
        }
    }
 
}