import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		
		int[] boxes = new int[n];
		int[] dp = new int[n];
		
		int answer = Integer.MIN_VALUE;
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			boxes[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
			
			for(int j=0;j<i;j++) {
				if(boxes[i] > boxes[j]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			answer = Math.max(answer, dp[i]);
		}
		System.out.println(answer);

	}

}