import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, M, L;
	private static int[] restArea;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		 N = Integer.parseInt(st.nextToken());	// N : 현재 휴게소의 개수
		 M = Integer.parseInt(st.nextToken());	// M : 더 지으려고 하는 휴게소의 개수
		 L = Integer.parseInt(st.nextToken());	// L : 고속도로의 길이
		 
		 restArea = new int[N+2];					// restArea : 휴게소이 위치
		 if(N != 0) {
			 st = new StringTokenizer(br.readLine()," ");
			 for(int i=1;i<=N;i++) {
				 restArea[i] = Integer.parseInt(st.nextToken());
			 }
		 }
		 
		 restArea[N+1] = L;
		 Arrays.sort(restArea);
		 
		 int left = 1;
		 int right = L-1;
		 
		 while(left <= right) {
			 int mid = (left+right)/2;
			 int sum = 0;
			 
			 for(int i=1;i<restArea.length;i++) {
				 sum += (restArea[i] - restArea[i-1]-1)/mid;
			 }
			 
			 if(sum > M) left = mid + 1;
			 else right = mid-1;
		 }
		 
		 System.out.println(left);
	}

}