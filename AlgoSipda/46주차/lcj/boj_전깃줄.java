import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] wire;
	static Integer[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		wire = new int[N][2];
		dp = new Integer[N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			wire[i][0] = Integer.parseInt(st.nextToken());
			wire[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(wire, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		int answer = 0;
		for(int i=0;i<N;i++) {
			answer = Math.max(recur(i), answer);
		}
		
		System.out.println(N-answer);
	}

	private static int recur(int num) {
		if(dp[num] == null) {
			dp[num] = 1;
			for(int i=num+1;i<dp.length;i++) {
				if(wire[num][1]<wire[i][1]) {
					dp[num] = Math.max(dp[num], recur(i)+1);
				}
			}
		}
		return dp[num];
	}
}