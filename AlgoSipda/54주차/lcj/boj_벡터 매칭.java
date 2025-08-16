import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1007_벡터매칭 {
	
	static int[][] P;
	static boolean[] positive;
	static double answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc<T;tc++) {
			int N = Integer.parseInt(br.readLine());
			
			P = new int[N][2];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				P[i][0] = Integer.parseInt(st.nextToken());
				P[i][1] = Integer.parseInt(st.nextToken());
			}
			
			positive = new boolean[N];
			
			answer = Double.MAX_VALUE;
			
			comb(0, N/2);
			sb.append(answer).append("\n");
		}
		System.out.print(sb);
	}
	
	private static void comb(int idx, int cnt) {
		if(idx == P.length) return;
		
		if(cnt == 0) answer = Math.min(answer, getVector());
		
		for(int i=idx;i<P.length;i++) {
			positive[i] = true;
			comb(i+1, cnt-1);
			positive[i] = false;
		}
	}
	
	private static double getVector() {		
		int sumX = 0;
		int sumY = 0;
		
		for(int i=0;i<P.length;i++) {
			if(positive[i]) {
				sumX += P[i][0];
				sumY += P[i][1];
			}
			else {
				sumX -= P[i][0];
				sumY -= P[i][1];
			}
		}
		
		return Math.sqrt(Math.pow(sumX, 2) + Math.pow(sumY, 2));
		
	}

}