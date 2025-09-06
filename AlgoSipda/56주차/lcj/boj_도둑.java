import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, M, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0;tc<T;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// N: 집의 개수
			M = Integer.parseInt(st.nextToken());	// M: 훔칠 연속된 집의 개수
			K = Integer.parseInt(st.nextToken());	// K: 최소 돈의 양
			
			int[] homes = new int[N];
			int total = 0;
			st = new StringTokenizer(br.readLine()," ");
			for(int i=0;i<N;i++) {
				homes[i] = Integer.parseInt(st.nextToken());
				total += homes[i];
			}
			
			if(N==M) {
				if(total < K) sb.append("1").append("\n");
				else sb.append("0").append("\n");
			}
			else {
				int cnt = 0;
				
				int start = 0;
				int end = M-1;
				
				int sum = 0;
				
				for(int i=0;i<=end;i++) {
					sum += homes[i];
				}
				
				while(start<N) {
					if(sum < K) cnt++;
					
					sum -= homes[start++];
					sum += homes[(++end) % N];
				}
				sb.append(cnt).append("\n");
			}
		}
		System.out.print(sb);
	}

}
