import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N+1];
		int[] dp1 = new int[N+1];
		int[] dp2 = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 왼 -> 오
		for(int i=1;i<=N;i++) {
			dp1[i] = 1;
			for(int j=1;j<i;j++) {
				if(arr[i]>arr[j])
					dp1[i] = Math.max(dp1[i], dp1[j]+1);
			}
		}
		
		// 오 -> 왼
		for(int i=N;i>0;i--) {
			dp2[i] = 1;
			for(int j=N;j>1;j--) {
				if(arr[i] > arr[j])
					dp2[i] = Math.max(dp2[i], dp2[j]+1);
			}
		}
		
		int answer = 0;
		for(int i=1;i<=N;i++) {
			answer = Math.max(dp1[i]+dp2[i], answer);
		}
		System.out.println(answer-1);
	}

}