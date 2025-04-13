import java.util.Arrays;

class Solution {
    public long solution(int[] sequence) {
        int len = sequence.length;
        long[] arr1 = new long[len + 1];
        int x = 1;
        long sum1 = 0;
        
        for (int i = 0; i < len; i++) {
            sum1 += sequence[i] * x;
            x *= -1;
            arr1[i + 1] = sum1;
        }
        
        long max = Arrays.stream(arr1).max().getAsLong();
        long min = Arrays.stream(arr1).min().getAsLong();
        
        return max - min;
    }
}
