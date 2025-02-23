import java.io.*;
import java.util.*;

public class Main {

    static class Point implements Comparable<Point> {
        int r, c, dir, dist;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        public Point(int r, int c, int dir, int dist) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.dist = dist;
        }

        public boolean isSame(Point p) {
            return this.r == p.r && this.c == p.c;
        }

        @Override
        public int compareTo(Point p) {
            if(this.dist == p.dist) {
                if(this.r == p.r) {
                    return this.c - p.c;
                }
                return this.r - p.r;
            }
            return this.dist - p.dist;
        }
    }

    public static int n, m;
    public static int[][] map;
    public static int time;

    public static Point[] person, store;
    
    public static int[] dr = {-1, 0, 0, 1};
    public static int[] dc = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        person = new Point[m];
        store = new Point[m];

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;

            store[i] = new Point(r, c);
            person[i] = new Point(-1, -1);
        }

        time = 1;

        while(true) {
            moveStore();

            if(time <= m) moveBaseCamp(store[time-1]);
            if(isFinish()) break;

            time++;
        }

        System.out.println(time);
    }

    public static void moveStore() {
        for(int i=0;i<m;i++) {
            Point start = person[i];
            Point end = store[i];

            if(!isRange(start.r, start.c) || start.isSame(end)) continue;

            int dir= findDir(start, end);

            start.r += dr[dir];
            start.c += dc[dir];
        }

        for(int i=0;i<m;i++) {
            if(person[i].isSame(store[i])) {
                map[person[i].r][person[i].c] = 2;
            }
        }
    }

    public static int findDir(Point start, Point end) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[n][n];

        pq.add(new Point(start.r, start.c, -1, 0));
        visited[start.r][start.c] = true;

        while(!pq.isEmpty()) {
            Point cur = pq.poll();

            if(cur.isSame(end)) return cur.dir;

            for(int d = 0; d<4;d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if(!isRange(nr, nc) || visited[nr][nc] || map[nr][nc]== 2) continue;

                pq.add(new Point(nr, nc, cur.dir == -1?d:cur.dir, cur.dist+1));
                visited[nr][nc] = true;
            }
        }
        return 0;
    }

    public static void moveBaseCamp(Point start) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[n][n];

        pq.add(new Point(start.r, start.c, 0));
        visited[start.r][start.c] = true;

        while(!pq.isEmpty()) {
            Point cur = pq.poll();

            if(map[cur.r][cur.c] == 1) {
                map[cur.r][cur.c] = 2;
                person[time-1] = cur;
                return;
            }

            for(int d = 0; d<4;d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if(!isRange(nr, nc) || visited[nr][nc]  || map[nr][nc] == 2) continue;

                pq.add(new Point(nr, nc, cur.dist +1));
                visited[nr][nc] = true;
            }
        }
    }

    public static boolean isFinish() {
        for(int i=0;i<m;i++) {
            if(!person[i].isSame(store[i])) return false;
        }
        return true;
    }

    public static boolean isRange(int r, int c) {
        return 0<=r && r<n && 0<=c && c<n;
    }
}