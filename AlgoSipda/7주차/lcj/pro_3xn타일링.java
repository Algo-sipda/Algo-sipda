class Solution {
    final int MOD = 1_000_000_007;
    
    public long solution(int n) {
        long[] arr = new long[5001];
        arr[1] = 2;
        arr[2] = 3;
        for(int i=3;i<=n;i++) {
            if(i%2 == 0) {
                arr[i] = (arr[i-1] + arr[i-2])%MOD;
            }else {
                arr[i] = (arr[i-1]*2 + arr[i-2])%MOD;
            }
        }
        return arr[n];
    }
}