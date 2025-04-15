class Solution {
    public long solution(int[] sequence) {
        int pulseA = 1;
        int pulseB = -1;
        int n = sequence.length;

        long sumA = sequence[0];
        long sumB = sequence[0] * -1;
        long minA = 0;
        long minB = 0;
        long maxPulseSum = Long.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            maxPulseSum = Math.max(maxPulseSum, sumA - minA);
            maxPulseSum = Math.max(maxPulseSum, sumB - minB);

            minA = Math.min(minA, sumA);
            minB = Math.min(minB, sumB);

            if (i == n)
                break;

            pulseA *= -1;
            pulseB *= -1;

            sumA += sequence[i] * pulseA;
            sumB += sequence[i] * pulseB;
        }

        return maxPulseSum;
    }
}
