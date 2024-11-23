import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m, h;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine()," ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		map = new int[h+1][n+1];
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 1;
		}
		
		for(int i=0;i<4;i++) {
			comb(1, 0, i);
		}
		System.out.println(-1);
	}

	private static void comb(int nr, int cnt, int size) {
		if(cnt == size) {
			if(check()) {
				System.out.println(size);
				System.exit(0);
			}
			return;
		}
		
		for(int r = nr; r <= h; r++) {
			for(int c = 1;c<n;c++) {
				if(map[r][c] == 1 || map[r][c-1]==1 || map[r][c+1]==1) {
					continue;
				}
				map[r][c] = 1;
				comb(r, cnt+1, size);
				map[r][c] = 0;
			}
		}
	}
	
	private static boolean check() {
		for(int i=1;i<=n;i++) {
			int curP = i;
			int start = 1;
			while(start<=h) {
				if(map[start][curP] == 1) {
					curP++;
					start++;
				}
				else if(map[start][curP-1]==1) {
					curP--;
					start++;
				}else {
					start++;
				}
			}
			if(i != curP) return false;
		}
		return true;
	}
}