import java.util.*;
/*
    구간합, 투포인터
*/

class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        long[] arr1 = new long[sequence.length + 1];
        long[] arr2 = new long[sequence.length + 1];

        int num = 1;
        long sum1 = 0;
        long sum2 = 0;
        for (int i = 1; i <= sequence.length; i++) {
            sum1 += (sequence[i - 1] * num);
            arr1[i] = sum1;
            sum2 += (sequence[i - 1] * num * -1);
            arr2[i] = sum2;
            num *= -1;
        }

        answer = Math.max(getMax(sequence, arr1), getMax(sequence, arr2));

        return answer;
    }

    private static long getMax(int[] sequence, long[] arr) {
        long result = 0;
        int left = 0;
        int right = 1;
        while (right <= sequence.length) {
            long sum = arr[right] - arr[left];

            if (sum >= 0) {
                result = Math.max(result, sum);
                right++;
            } else {
                left = right;
                right++;
            }
        }
        return result;
    }
}