import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 동전 가지 수
        int S = Integer.parseInt(st.nextToken());   // 거스름돈의 액수
        List<Point> coinList = new ArrayList<>();
        int useCnt = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            coinList.add(new Point(v, a));
            useCnt += a;
            S -= v*a;
        }

        int[] dp = new int[S+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(Point p : coinList){
            int x = p.x;
            for(int i = 1; i <= S; i++){
                if(i-x < 0)
                    continue;
                if(dp[i - x] == Integer.MAX_VALUE)
                    continue;
                dp[i] = Math.min(dp[i], dp[i-x] + 1);
            }
        }
        if(dp[S] == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(useCnt + dp[S]);
    }
}