class Solution {
    public int[] solution(int e, int[] starts) {
        int[] count = new int[e + 1];
        int[] mostFrequent = new int[e + 1];

        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                count[j]++;
            }
        }

        int maxNum = e;
        for (int i = e; i >= 1; i--) {
            if (count[i] >= count[maxNum]) {
                maxNum = i;
            }
            mostFrequent[i] = maxNum;
        }

        int[] result = new int[starts.length];
        for (int i = 0; i < starts.length; i++) {
            result[i] = mostFrequent[starts[i]];
        }

        return result;
    }
}