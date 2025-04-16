class Solution {
    public long solution(int[] sequence) {
        long answer = Long.MIN_VALUE;
        
        long sum = 0;
        long max = 0;
        long min = 0;
        
        for(int i = 0; i < sequence.length; i++){
            sum += sequence[i] * (i % 2 == 0 ? 1 : -1);
            max = Math.max(sum, max);
            min = Math.min(sum, min);
        }
        answer = max - min;
        return answer;
        
    }
}