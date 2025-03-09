import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        StringBuilder sb = new StringBuilder();
        long[] prefixSum = new long[11];
        prefixSum[1] = 26;
        
        for(int i=2; i<11;i++) {
            prefixSum[i] = prefixSum[i-1] + (long) Math.pow(26, i);
        }
        
        Set<Long> hs = new HashSet<>();
        long realN = n;
        
        for(int i=0;i<bans.length;i++) {
            char[] wordArr = bans[i].toCharArray();
            int len = wordArr.length;
            
            long idx = 0;
            for(int j=0;j<len;j++) {
                idx += (wordArr[j] - 96) * Math.pow(26, len-j-1);
            }
            
            if(realN >= idx) {
                while(hs.contains(++realN)) hs.remove(realN);
            }
            else {
                hs.add(idx);
            }
        }
        
        realN--;
        
        int len = 0;
        for(int i=11;i>0;i--) {
            if(prefixSum[i-1] < realN) {
                realN -= prefixSum[i-1];
                len = i;
                break;
            }
        }
        
        for(int i=len; i>0;i--) {
            long div = (long) Math.pow(26, i-1);
            sb.append((char)(realN/div + 96 + 1));
            realN %= div;
        }
        
        return sb.toString();
    }
}