import java.io.*;
import java.util.*;

public class Main_17837_새로운게임2 {
	
	static class Horse {
		int r, c, dir, idx;
		
		Horse(int r, int c, int dir, int idx) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.idx = idx;
		}
	}
	
	static int N, K, answer = 1;
	static int[][] map;
	static int[][][] move;
	static boolean isCheck;
	static Horse[] horses;
	
	static int[] dr = {0, 0, 0, -1, 1};
	static int[] dc = {0, 1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		move = new int[N][N][K+1];
		horses = new Horse[K+1];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1;i<=K;i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken());
			
			horses[i] = new Horse(r, c, dir, 0);
			move[r][c][0] = i;
		}
		
		game();
		System.out.println(answer > 1000? -1:answer);
	}
	
	public static void game() {
		while(true) {
			for(int i=1;i<=K;i++) {
				Horse h = horses[i];
				
				int nr = h.r + dr[h.dir];
				int nc = h.c + dc[h.dir];
				
				if(isRange(nr, nc) && (map[nr][nc] == 0 || map[nr][nc] == 1)) {
					// 흰색 or 빨간색
					moveTo(nr, nc, h, map[nr][nc]);
				}
				else if(!isRange(nr, nc) || map[nr][nc] == 2) {
					// 파란색 or 범위를 벗어남
					if(h.dir == 1 || h.dir == 3) h.dir += 1;
					else h.dir -= 1;
					
					nr = h.r + dr[h.dir];
					nc = h.c + dc[h.dir];
					
					if(isRange(nr, nc) && map[nr][nc] != 2) {
						moveTo(nr, nc, h, map[nr][nc]);
					}
				}
				
				if(isCheck) break;
			}
			
			if(answer > 1000 || isCheck) break;
			answer++;
		}
	}
	
	public static void moveTo(int r, int c, Horse h, int color) {
		List<Integer> list = new ArrayList<>();
		
		for(int i=h.idx;i<=K;i++) {
			if(move[h.r][h.c][i] == 0) break;
			
			list.add(move[h.r][h.c][i]);
			move[h.r][h.c][i] = 0;
		}
		
		for(int i=0;i<=K;i++) {
			if(move[r][c][i] == 0) {
				h.idx = i;
				
				if(color == 0) {
					for(int j=0;j<list.size();j++ ) {
						move[r][c][i+j] = list.get(j);
						horses[list.get(j)] = new Horse(r, c, horses[list.get(j)].dir, i+j);
					}
				}
				else {
					for(int j=list.size()-1, k=0; j>=0;j--, k++) {
						move[r][c][i+k] = list.get(j);
						horses[list.get(j)] = new Horse(r, c, horses[list.get(j)].dir, i+k);
					}
				}
				
				if(i+list.size()>=4) isCheck = true;
				break;
			}
		}
		
		h.r = r;
		h.c = c;
	}
	
	public static boolean isRange(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}

}