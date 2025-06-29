import java.util.*;
import java.io.*;

public class Main_16946_벽부수고이동하기4 {
	static class Point {
		int r, c;
		
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[][] map, group;
	static int n, m;
	static Map<Integer, Integer> size;
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		group = new int[n][m];
		
		for(int i=0;i<n;i++) {
			String str = br.readLine();
			for(int j=0;j<m;j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		int cnt = 1;
		size = new HashMap<>();
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]==0 && group[i][j] == 0) {
					int zeroCnt = bfs(i, j, cnt);
					size.put(cnt, zeroCnt);
					cnt++;
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(group[i][j] == 0) {
					sb.append(groupSumWall(i, j));
					continue;
				}
				sb.append(0);
			}
			sb.append("\n");
		}
		
		System.out.print(sb);

	}

	static int bfs(int r, int c, int cnt) {
		int curCnt = 1;
		Queue<Point> q = new LinkedList<>();
		
		q.add(new Point(r, c));
		group[r][c] = cnt;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int d = 0; d<4;d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(!isRange(nr, nc)) continue;
				
				if(group[nr][nc]== 0 && map[nr][nc] == 0) {
					group[nr][nc] = cnt;
					curCnt++;
					q.add(new Point(nr, nc));
				}
			}
		}
		return curCnt;
	}
	
	static boolean isRange(int r, int c) {
		return 0<= r && r < n && 0<=c && c<m;
	}
	
	static int groupSumWall(int r, int c) {
		int cnt = 1;
		
		if(map[r][c] == 0) return 0;
		
		Set<Integer> set = new HashSet<>();
		
		for(int d = 0;d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(!isRange(nr, nc) || group[nr][nc] == 0) continue;
			
			set.add(group[nr][nc]);
		}
		
		for(int s:set) {
			cnt += size.get(s);
		}
		
		return cnt%10;
	}
}
