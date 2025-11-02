class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int N = diffs.length;
        
        int max = 0;
        
        for(int diff:diffs) {
            max = Math.max(diff, max);
        }
        
        int left = 1;
        int right = max;
        
        while(left <= right) {
            int mid = (left + right)/2;
            
            long sum = 0;
            
            for(int i=0;i<N;i++) {
                if(diffs[i] <= mid) {
                    sum += times[i];
                }
                else {
                    if(i == 0){
                        sum = limit++;
                        break;
                    }
                    sum += ((times[i] + times[i-1]) * (diffs[i] - mid)) + times[i];
                }
            }
            
            if(sum <= limit) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }
}