import java.io.*;
import java.util.*;
 
public class Main {
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
 
        int n, m;
        long result=0;
        long [][] dp = new long [11][4001];
 
        for(int j=1; j<=4000; j++){
            dp[1][j] = 1;
        }
 
        for(int j=2; j<=10; j++){
            for(int k=1; k<=4000;k++){
                if(dp[j-1][k]!=0){
                    for(int q =k*2; q<=4000; q++){
                        dp[j][q] += dp[j-1][k];
                    }
                }
            }
        }
        for(int i=0; i<t; i++){
            String[] s = br.readLine().split(" ");
            n = Integer.parseInt(s[0]);
            m = Integer.parseInt(s[1]);
            result=0;
            for(int k=1; k<=m; k++){
                result+=dp[n][k];
            }
            System.out.println(result);
        }
    }
}
