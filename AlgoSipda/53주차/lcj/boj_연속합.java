import java.io.*;
import java.util.*;

/**
 * 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합 구하기
 * 풀이 방법 : dp
 * 입력 크기가 200_000이기 때문에 O(n^2)하면 40_000_000_000(4백억)인데
 * 제한시간 최대 1억개 안에 해결해야 하기 때문에 브루트포스 불가능
 * @author lcj52
 *
 */

public class Main {
	
	static int n, answer;
	static int[] arr, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n];
		dp = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		answer = dp[0] = arr[0];
		
		for(int i=1;i<n;i++) {
			dp[i] = Math.max(arr[i], dp[i-1]+arr[i]);
			answer = Math.max(dp[i], answer);
		}
		
		System.out.println(answer);
	}

}
