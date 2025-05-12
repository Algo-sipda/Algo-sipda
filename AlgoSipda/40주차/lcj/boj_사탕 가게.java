import java.io.*;
import java.util.*;

public class Main_4781_사탕가게 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int money = (int)(Double.parseDouble(st.nextToken()) * 100 + 0.5);
        
        while(n != 0) {
        	int[] cal = new int[n];
        	int[] cost = new int[money];
        	
        	for(int i=0;i<n;i++) {
        		st = new StringTokenizer(br.readLine());
        		cal[i] = Integer.parseInt(st.nextToken());
        		cost[i] = (int)(Double.parseDouble(st.nextToken())* 100 +0.05);
        	}
        	
        	int[] dp = new int[money+1];
        	
        	for(int i=0;i<n;i++) {
        		for(int j=0;j<=money;j++) {
        			int remainMoney = j-cost[i];
        			
        			if(0 <= remainMoney) dp[j] = Math.max(dp[j], dp[remainMoney] + cal[i]);
        		}
        	}
        	
        	sb.append(dp[money]).append("\n");
        	
        	st = new StringTokenizer(br.readLine());
        	n = Integer.parseInt(st.nextToken());
        	money = (int)(Double.parseDouble(st.nextToken())* 100 +0.05);
        }
        System.out.print(sb);
	}

}
