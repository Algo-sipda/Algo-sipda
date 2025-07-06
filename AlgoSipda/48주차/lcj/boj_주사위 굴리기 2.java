import java.util.*;
import java.io.*;

public class Main_23288_주사위굴리기2 {

	static int N, M, K;
	static int[][] map;
	static int answer;
	
	// 동 서 남 북
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N+1][M+1];
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int r = 1;
		int c = 1;
		int dir = 0;
		
		// 주사위 전개도
		int[] rDice = {4, 1, 3, 6};
		int[] cDice = {2, 1, 5, 6};
		
		for(int i=0;i<K;i++) {
			if(r + dr[dir]>=1 && r + dr[dir] <= N && c + dc[dir] >= 1 && c + dc[dir] <= M) {
				rollDice(dir, rDice, cDice);
				r += dr[dir];
				c += dc[dir];
			}
			else {
				dir = (dir+2)%4;
				rollDice(dir, rDice, cDice);
				r += dr[dir];
				c += dc[dir];
			}
			
			int a = rDice[3];
			int b = map[r][c];
			
			int cnt = bfs(r, c)*b;
			answer += cnt;
			
			if(a>b) {
				dir = (dir+1)%4;
			}
			else if(a<b) {
				if(dir == 0) dir = 3;
				else dir = (dir-1)%4;
			}
		}
		System.out.println(answer);
	}

	public static void rollDice(int dir, int[] rDice, int[] cDice) {
		if(dir == 0) {
			// 오른쪽
			int tmp = rDice[3];
			
			for(int i=2;i>=0;i--) {
				rDice[i+1] = rDice[i];
			}
			
			rDice[0] = tmp;
			
			cDice[1] = rDice[1];
			cDice[3] = rDice[3];
		}
		else if(dir == 2) {
			// 왼쪽
			int tmp = rDice[0];
			
			for(int i=1;i<=3; i++) {
				rDice[i-1] = rDice[i];
			}
			
			rDice[3] = tmp;
			
			cDice[1] = rDice[1];
			cDice[3] = rDice[3];
		}
		else if(dir == 1) {
			// 남쪽 - 오른쪽 회전
			int tmp = cDice[3];
			
			for(int i=2;i>=0;i--) {
				cDice[i+1] = cDice[i];
			}
			
			cDice[0] = tmp;
			
			rDice[1] = cDice[1];
			rDice[3] = cDice[3];
		}
		else {
			// 북쪽 - 왼쪽
			int tmp = cDice[0];
			
			for(int i=1;i<=3; i++) {
				cDice[i-1] = cDice[i];
			}
			
			cDice[3] = tmp;
			
			rDice[1] = cDice[1];
			rDice[3] = cDice[3];
		}
	}
	
	public static int bfs(int r, int c) {
		boolean[][] visited = new boolean[N+1][M+1];
		Queue<int[]> q = new LinkedList<>();
		
		q.add(new int[] {r, c});
		visited[r][c] = true;
		
		int cnt = 1;
		int num = map[r][c];
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int d = 0; d<4;d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				
				if(!isRange(nr, nc) || map[nr][nc] != num || visited[nr][nc])
					continue;
				
				cnt++;
				visited[nr][nc] = true;
				q.add(new int[] {nr, nc});
			}
		}
		
		return cnt;
	}
	
	public static boolean isRange(int r, int c) {
		return 0< r&& r<=N && 0<c && c<=M;
	}
}
