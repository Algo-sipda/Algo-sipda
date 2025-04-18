import java.io.*;
import java.util.*;

public class Main_2624_동전바꿔주기 {
	
	static int T, k;
	static int[][] coins, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		
		coins = new int[k+1][2];
		dp = new int[k+1][T+1];
		
		for(int i=1;i<=k;i++) {
			st = new StringTokenizer(br.readLine());
			coins[i][0] = Integer.parseInt(st.nextToken());
			coins[i][1] = Integer.parseInt(st.nextToken());
		}
		
		dp[0][0] = 1;
		for(int i=1;i<=k;i++) {
			int cost = coins[i][0];
			for(int j=0;j<=coins[i][1];j++) {
				for(int k=0;k<=T;k++) {
					int pos = k + cost*j;
					
					if(pos > T) break;
					
					dp[i][pos] += dp[i-1][k];
				}
			}
		}
		System.out.println(dp[k][T]);
	}

}
