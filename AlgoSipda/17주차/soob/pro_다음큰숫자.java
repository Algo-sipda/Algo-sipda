class Solution {
    public int solution(int n) {
        int nowCount = Integer.bitCount(n);

        while(true){
            n++;
            int nextCount = Integer.bitCount(n);

            if(nowCount == nextCount)
                break;
        }

        return n;
    }
}