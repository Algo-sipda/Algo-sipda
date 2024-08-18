import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {

    static int N;
    static Point[] location;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        location = new Point[N+1];

        location[0] = new Point(0, 0);
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            location[i] = new Point(x, y);
        }

        boolean[] visited = new boolean[N+1];
        
        backtracking(0, 0, visited, -1, 0);
        System.out.println(result);
    }

    public static void backtracking(int x, int y, boolean[] visited, int vCnt, int dir) {
        if(vCnt == N && x == 0 && y == 0) {
            result++;
            return;
        }

        for(int i=0;i<=N;i++) {
            if(!visited[i]) {
                Point cur = location[i];
                if((x == cur.x || y == cur.y) ) {
                    int nextdir = getDirection(x, y, cur.x, cur.y);
                    if(nextdir == dir || nextdir == -1) continue;
                    
                    visited[i] = true;
                    backtracking(cur.x, cur.y, visited, vCnt+1, nextdir);
                    visited[i] = false;
                }
            }
        }
    }

    public static int getDirection(int x1, int y1, int x2, int y2) {
        // dir => 1: 위, 2: 오, 3: 아래, 4: 왼 0: 제자리
        if(x1 == x2 && y1 == y2) {
            return 0;
        }
        else if(x1 == x2) {
            return y1>y2? 3: 1;
        }
        else if(y1 == y2) {
            return x1> x2? 4: 2;
        }
        else {
            return -1;
        }
    }
}