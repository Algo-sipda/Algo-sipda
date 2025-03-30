import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;;
		
		int N = Integer.parseInt(br.readLine());
		int[] maxDP = new int[3];
		int[] minDP = new int[3];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int x3 = Integer.parseInt(st.nextToken());
			if(i==0) {
				maxDP[0] = minDP[0] = x1;
				maxDP[1] = minDP[1] = x2;
				maxDP[2] = minDP[2] = x3;
			}else {
				int bfMAXDP0 = maxDP[0];
				int bfMAXDP2 = maxDP[2];
				
				maxDP[0] = Math.max(maxDP[0], maxDP[1])+x1;
				maxDP[2] = Math.max(maxDP[1], maxDP[2])+x3;
				maxDP[1] = Math.max(Math.max(bfMAXDP0, maxDP[1]), bfMAXDP2)+x2;
				

				int bfMINDP0 = minDP[0];
				int bfMINDP2 = minDP[2];
				
				minDP[0] = Math.min(minDP[0], minDP[1])+x1;
				minDP[2] = Math.min(minDP[1], minDP[2])+x3;
				minDP[1] = Math.min(Math.min(bfMINDP0, minDP[1]), bfMINDP2)+x2;
			}
		}
		
		int maxScore = Math.max(maxDP[0], Math.max(maxDP[1], maxDP[2]));
		int minScore = Math.min(minDP[0], Math.min(minDP[1], minDP[2]));
		System.out.println(maxScore+" "+minScore);
	}

}