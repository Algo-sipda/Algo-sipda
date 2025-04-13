import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] trains = new int[N+1];
		int[] trainSum = new int[N+1];
		st = new StringTokenizer(br.readLine()," ");
		for(int i=1;i<=N;i++) {
			trains[i] = Integer.parseInt(st.nextToken());
			trainSum[i] = trains[i] + trainSum[i-1];
		}
		
		int max = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[4][N+1];
		for(int i=1;i<=3;i++) {
			for(int j=max*i;j<=N;j++) {
				dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-max] + trainSum[j]-trainSum[j-max]);
			}
		}
		System.out.println(dp[3][N]);
	}

}
