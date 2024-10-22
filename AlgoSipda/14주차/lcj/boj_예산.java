import java.io.*;
import java.util.*;

public class Main_2512_예산_S2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		long left = 0;
		long right = arr[N-1];
		
		long M = Long.parseLong(br.readLine());
		long answer = 0;
		
		while(left <= right) {
			long mid = (left+right)/2;
			
			long sum = 0;
			for(int i=0;i<N;i++) {
				if(arr[i] > mid) {
					sum += mid;
				}
				else {
					sum += arr[i];
				}
			}
			
			if(sum > M) {
				right = mid-1;
			}
			else {
				left = mid+1;
				answer = Math.max(answer, mid);
			}
		}
		System.out.println(answer);
	}
}