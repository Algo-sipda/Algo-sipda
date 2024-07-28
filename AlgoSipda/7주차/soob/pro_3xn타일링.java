import java.util.*;

class Solution {
    public int solution(int n) {
        if(n % 2 == 1)
            return 0;
        
        long[] arr = new long[n+1];
        arr[2] = 3;
        if(n < 4)
            return Long.valueOf(arr[n]).intValue();
        
        int mod = 1000000007;
        for(int i = 4; i <= n; i += 2){
            long sum = (arr[i-2] * 3 + 2) % mod;
            for(int j = i-4; j > 0; j -= 2){
                sum += arr[j] * 2 % mod;
            }
            arr[i] = sum % mod;
        }
                
        return Long.valueOf(arr[n]).intValue();
    }
}