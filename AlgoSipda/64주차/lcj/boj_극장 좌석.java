import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		if(N == 1) {
			System.out.println(1);
			return;
		}
		
		int[] arr = new int[N+1];
		for(int i=0;i<M;i++) {
			int idx = Integer.parseInt(br.readLine());
			arr[idx] = -1;
		}
		
		int[] dp = new int[N+1];
		dp[1] = 1;
		dp[2] = 2;
		
		for(int i=3;i<=N;i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		int answer = 1;
		int cnt = 0;
		
		for(int i=1;i<=N;i++) { 
			if(arr[i] == -1) {
				if(cnt == 0) continue;
				else {
					answer *= dp[cnt];
					cnt = 0;
				}
			}
			else {
				cnt++;
			}
		}
		
		if(cnt != 0) answer *= dp[cnt];

		System.out.println(answer);
	}

}