class Solution {
    public int solution(int n) {
        int idx = -1;

        for(int i = 0; n >> i > 0; i++) {
            if((n >> i & 1) == 1 && (((n >> (i + 1)) & 1) == 0)) {
                idx = i + 1;
                break;
            }
        }

        n = n | (1 << idx);
        n = n & ~(1 << (idx - 1));

        int lowerIdx = (1 << idx - 1) - 1;
        int bitCount = Integer.bitCount(n & lowerIdx);
        n = n & ~lowerIdx;
        n = n | ((1 << bitCount) - 1);

        return n;
    }
}