import java.io.*;
import java.util.*;

public class Main {
	
	static int r, c, k;
	static int[][][] map;
	static long[][] dp;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		k = Integer.parseInt(br.readLine());
		
		map = new int[2][101][101];
		dp = new long[101][101];
		
		for(int i=0;i<k;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			if(b == d) {
				if(a > c) {
					int tmp = a;
					a = c;
					c = tmp;
				}
				map[0][c][b] = -1;
			}
			
			if(a == c) {
				if(b > d) {
					int tmp = b;
					b = d;
					d = tmp;
				}
				map[1][a][d] = -1;
			}
		}
		dp[0][0] = 1;
		
		for(int i=0;i<=r;i++) {
			for(int j=0;j<=c;j++) {
				if(map[0][i][j] != -1 && i-1 >= 0) {
					dp[i][j] += dp[i-1][j];
				}
				
				if(map[1][i][j] != -1 && j-1 >= 0) {
					dp[i][j] += dp[i][j-1];
				}
			}
		}
		System.out.println(dp[r][c]);
	}

}