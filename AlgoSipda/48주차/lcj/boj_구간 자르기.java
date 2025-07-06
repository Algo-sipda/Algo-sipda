import java.util.*;
import java.io.*;

public class Main_2283_구간 자르기 {
    
	static int N, K;
	static int[] cnt;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
                
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        cnt = new int[1000001];
        
        int max = -1;
        int min = cnt.length;
        
        for(int i=0;i<N;i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int left = Integer.parseInt(st.nextToken());
        	int right = Integer.parseInt(st.nextToken());
        	
        	cnt[left]++;
        	cnt[right]--;
        	
        	min = Math.min(min, left);
        	max = Math.max(max, right);
        }
        
        for(int i=min+1;i<=max;i++) {
        	cnt[i] += cnt[i-1];
        }
        
        int startId = min;
        int endId = min;
        
        int A = 0;
        int B = 0;
        int cur = 0;
        
        while(endId <= max) {
        	if(cur < K) {
        		cur += cnt[endId++];
        	}
        	else if(cur == K) {
        		A = startId;
        		B = endId;
        		break;
        	}
        	else {
        		cur -= cnt[startId++];
        	}
        }
        
        if(A == min) A = 0;
        System.out.println(A+" "+B);
    }
}