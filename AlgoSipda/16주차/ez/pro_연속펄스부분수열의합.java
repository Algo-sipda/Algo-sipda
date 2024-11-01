class pro_연속펄스부분수열의합 {
    public long solution(int[] sequence) {
        long answer = 0;

        long[] sum1 = new long[sequence.length + 1];
        long[] sum2 = new long[sequence.length + 1];

        int num = 1;
        for (int i = 0; i < sequence.length; i++) {
            sum1[i + 1] += sum1[i] + (sequence[i] * num);
            num *= -1;
            sum2[i + 1] += sum2[i] + (sequence[i] * num);
        }

        return Math.max(getMax(sum1), getMax(sum2));
    }

    private static long getMax(long[] arr) {
        long res = 0;
        int left = 0;
        int right = 1;

        while (right < arr.length) {
            long sum = arr[right] - arr[left];

            if (sum >= 0) {
                res = Math.max(res, sum);
                right++;
            } else {
                left = right;
                right++;
            }
        }
        return res;
    }
}
