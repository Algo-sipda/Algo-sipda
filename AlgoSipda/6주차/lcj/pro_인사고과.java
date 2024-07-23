import java.util.*;

class Solution {
    
    public int solution(int[][] scores) {
        int rank = 1;
        int[] wanho = scores[0];
        
        Arrays.sort(scores, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] < o2[0]) {
                    return 1;
                }
                else {
                    if(o1[0] == o2[0] && o1[1] > o2[1]) {
                        return 1;
                    }
                    return -1;
                }
            }
        });
        
        int wanhoScore = wanho[0] + wanho[1];
        int maxPeerScore = 0;
        for(int i=0;i<scores.length;i++) {
            if(scores[i][1] < maxPeerScore) {
                if(wanho[0] == scores[i][0] && wanho[1] == scores[i][1]) return -1;
            }
            else {
                maxPeerScore = Math.max(maxPeerScore, scores[i][1]);
                
                if(wanhoScore < scores[i][0] + scores[i][1]) rank++;
            }
        }
        
        
        return rank;
    }
}