import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		int odd = 0;
		int answer = 0;
		
		while(end < N) {
			if(odd < K) {
				// 홀수 개수 K보다 작을 경우
				if(arr[end]%2 == 1) odd++;
				
				end++;
				answer = Math.max(answer, end-start-odd);
			}
			else if(arr[end]%2 == 0) {
				// 홀수 개수 K개 이상 && end가 짝수
				end++;
				answer = Math.max(answer, end-start-odd);
			}
			else {
				// K개 이상 && end 홀수
				if(arr[start]%2 == 1) odd--;
				start++;
			}
		}
		System.out.println(answer);
	}
}