import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, L;
	static int[] S;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// N: 자르는 횟수
		M = Integer.parseInt(st.nextToken());	// M: 자를 수 있는 지점 개수
		L = Integer.parseInt(st.nextToken());	// L: 롤 케이크 길이
		
		S = new int[M+1];
		for(int i=0;i<M;i++) {
			S[i] = Integer.parseInt(br.readLine());
		}
		S[M] = L;
		
		for(int i=0;i<N;i++) {
			int Q = Integer.parseInt(br.readLine());
			
			// 최소 길이 : 0, 최대길이 : L(S[M])
			int lo = 0, hi = S[M];
			int answer = 0;
			
			while(lo <= hi) {
				int mid = (lo + hi)/2;
				
				int cnt = 0;
				int prev = 0;
				
				for(int j=0;j<=M;j++) {
					if(S[j]-prev >= mid) {
						cnt++;
						prev = S[j];
					}
				}
				
				if(cnt > Q) {
					lo = mid + 1;
					answer = Math.max(answer, mid);
				}
				else {
					hi = mid - 1;
				}
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.print(sb);
	}

}