import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int MOD = 1000000;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        
        int[] dp = new int[str.length()+1];
        dp[0] = 1;
        
        for(int i=1; i<=str.length();i++) {
        	int cur = str.charAt(i-1)-'0';
        	if(cur>=1 && cur<=9) {
        		dp[i] += dp[i-1];
        		dp[i] %= MOD;
        	}
        	
        	if(i==1) continue;
        	int pre = str.charAt(i-2)-'0';
        	
        	if(pre == 0) continue;//0으로 시작 존재X
        	
        	int val = pre*10 + cur;
        	if(val >= 10 && val<=26) {
        		dp[i] += dp[i-2];
        		dp[i] %= MOD;
        	}
        }
        System.out.println(dp[str.length()]);
	}

}