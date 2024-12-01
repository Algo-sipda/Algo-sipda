import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, cnt = 1;
	static int[][] map;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 로봇 청소기 좌표
		st = new StringTokenizer(br.readLine());
		int robotR = Integer.parseInt(st.nextToken());
		int robotC = Integer.parseInt(st.nextToken());
		int robotDir = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cleanMap(robotR, robotC, robotDir);
		System.out.println(cnt);
	}
	
	public static void cleanMap(int r, int c, int dir) {
		map[r][c] = -1;
		
		for(int d = 0; d<4; d++) {
			// 반시계 방향
			dir = (dir + 3)%4;
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if(!isRange(nr, nc)) continue;
			if(map[nr][nc] == 0) {
				cnt++;
				cleanMap(nr, nc, dir);
				return;
			}
		}
		
		// 후진
		// 바라보는 방향은 유지해야함.
		int nDir = (dir + 2)%4;
		int nr = r + dr[nDir];
		int nc = c + dc[nDir];
		if(isRange(nr, nc) && map[nr][nc] != 1) {
			cleanMap(nr, nc, dir);
		}
	}
	
	public static boolean isRange(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}
}
