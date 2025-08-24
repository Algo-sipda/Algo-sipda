import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
                
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<arr.length;i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int answer = 0;
        
        int start = 0;
        int end = 0;
        int[] cnt = new int[100001];
        
        while(start < N && end < arr.length) {
        	if(cnt[arr[end]] <K) {
        		cnt[arr[end]]++;
        		end++;
        		answer = Math.max(answer, end-start);
        	}
        	else {
        		cnt[arr[start]]--;
            	start++;
        	}
        }
        
        System.out.println(answer);
    }
}