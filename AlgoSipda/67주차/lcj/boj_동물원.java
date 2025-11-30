import java.io.*;

public class Main {
	
	static int N;
	static final int MOD = 9901;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		long[][] dp = new long[N+1][3];
		dp[1][0] = dp[1][1] = dp[1][2] = 1;
		
		for(int i=2;i<=N;i++) {
			dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2])%MOD;
			dp[i][1] = (dp[i-1][0] + dp[i-1][2])%MOD;
			dp[i][2] = (dp[i-1][0] + dp[i-1][1])%MOD;
		}
		
		System.out.println((dp[N][0]+dp[N][1]+dp[N][2])%MOD);
	}

}