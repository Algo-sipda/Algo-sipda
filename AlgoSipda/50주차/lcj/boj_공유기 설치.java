import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());	// N: 집의 개수
		int C = Integer.parseInt(st.nextToken());	// C: 공유기의 개수
		
		int[] arr = new int[N];

		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int low = 1;
		int high = arr[N-1];
		Arrays.sort(arr);
		
		while(low <= high) {
			int mid = (low + high)/2;
			
			int position = 0;
			int cnt = 1;
			
			for(int i=1;i<N;i++) {
				if(arr[i] - arr[position] >= mid) {
					position = i;
					cnt++;
				}
			}
			
			if(cnt < C) {
				high = mid-1;
				continue;
			}
			low = mid+1;
		}
		System.out.println(low-1);
	}

}
