import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static boolean[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N+1];
		dp = new boolean[N+1][N+1];
		st = new StringTokenizer(br.readLine()," ");
		for(int i=1;i<=N;i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		palindrome(N, nums);
		
		
		int M = Integer.parseInt(br.readLine());	// 질문의 개수
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			if(dp[start][end]) sb.append("1\n");
			else sb.append("0\n");
		}
		System.out.print(sb);
	}

	private static void palindrome(int n, int[] nums) {
		//길이가 1개일 경우 무조건 팰린드롬
		for(int i=1;i<=n;i++) {
			dp[i][i] = true;
		}
		
		//길이가 2인 경우 두 수가 모두 동일할 경우
		 for(int i=1;i<n;i++) {
			 if(nums[i] == nums[i+1]) {
				 dp[i][i+1] = true;
			 }
		 }
		 
		 //길이가 3 이상인 경우 맨 첫글자와 마지막 숫자가 같고 그 사이 값의 dp값(시작점 +1 ~ 끝점 -1)을 확인하여 그 사이 값이 true일 경우 true
		 for(int i=2;i<n;i++) {
			 for(int j=1;j<=n-i;j++) {
				 if(nums[j] == nums[j+i] && dp[j+1][j+i-1])
					 dp[j][j+i] = true;
			 }
		 }
	}
}