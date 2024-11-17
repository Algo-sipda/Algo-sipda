import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		long[] arr = new long[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		int left = 0;
		int right = N-1;
		int pl = 0;
		int pr = 0;
		long min = Long.MAX_VALUE;
		while(left<right) {
			long sum = arr[left]+arr[right];
			if(min>=Math.abs(sum)) {
				min = Math.abs(sum);
				pl = left;
				pr = right;
			}
			if(sum>=0)
				right--;
			else
				left++;
		}
		System.out.println(arr[pl]+" "+arr[pr]);
	}

}
