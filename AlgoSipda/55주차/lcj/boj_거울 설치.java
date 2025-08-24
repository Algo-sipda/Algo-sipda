import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Mirror implements Comparable<Mirror> {
    int x;
    int y;
    int dir; // 거울의 현재 방향
    int cnt; // 사용한 거울 개수
	
    public Mirror(int x, int y, int dir, int cnt) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Mirror o) {
        return this.cnt - o.cnt;
    }
}

public class Main {
	
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int N;
    static int sx, sy, ex, ey;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        N = Integer.parseInt(br.readLine());
		
        map = new char[N][N];
		
        int idx = 0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
			
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
				
                // 문 좌표 저장
                if (map[i][j] == '#') {
                    if (idx == 0) {
                        sx = i;
                        sy = j;
                    } else {
                        ex = i;
                        ey = j;
                    }
                    idx++;
                }
            }
        }
		
        bfs();
    }
	
    public static void bfs() {
        PriorityQueue<Mirror> pq = new PriorityQueue<>(); 
        boolean[][][] visited = new boolean[N][N][4];
		
        for (int i = 0; i < 4; i++) {
            pq.add(new Mirror(sx, sy, i, 0));
        }
		
        while (!pq.isEmpty()) {
            Mirror cur = pq.poll();
			
            int x = cur.x;
            int y = cur.y;
            int dir = cur.dir;
            int cnt = cur.cnt;
			
            visited[x][y][dir] = true;
			
            if (x == ex && y == ey) {
                System.out.println(cnt);
                return;
            }
			
            int nx = x + dx[dir];
            int ny = y + dy[dir];
			
            if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny][dir] && map[nx][ny] != '*') {
                if (map[nx][ny] == '!') {
					
                    int nDir = (dir + 3) % 4;
                    pq.add(new Mirror(nx, ny, nDir, cnt + 1));
					
                    nDir = (dir + 1) % 4;
                    pq.add(new Mirror(nx, ny, nDir, cnt + 1));
                }
                pq.add(new Mirror(nx, ny, dir, cnt));
            }
        }
        
    }

}