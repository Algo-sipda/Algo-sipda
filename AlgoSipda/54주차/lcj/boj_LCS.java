import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static String[] str1;
	static String[] str2;
	static int[][] dp = new int[1001][1001];
	static String answer = "";
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str1 = br.readLine().split("");
		str2 = br.readLine().split("");
		
		lcs();
		int len = dp[str1.length][str2.length];
		System.out.println(len);
	}

	private static void lcs() {
		for(int i=0;i<str1.length;i++) {
			for(int j=0;j<str2.length;j++) {
				if(str1[i].equals(str2[j])) {
					dp[i+1][j+1] = dp[i][j]+1;
				}
				else {
					dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
				}
			}
		}
	}
}