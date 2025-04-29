// [PRO] 가장 많이 받은 선물 https://school.programmers.co.kr/learn/courses/30/lessons/258712

import java.util.*;
class Solution {
    static int N;
    static Map<String, Integer> toIds;
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        N = friends.length;
        toIds = new HashMap<>();
        for(String friend : friends){
            toIds.put(friend, toIds.size());
        }
        
        int[][] indegree = new int[N][N];
        for(String gift : gifts){
            int from = toIds.get(gift.split(" ")[0]);
            int to = toIds.get(gift.split(" ")[1]);
            indegree[from][to] += 1;
        }
        
        // 선물지수 : 이번달까지 자신이 친구들에게 준 선물의 수에서 받은 선물의 수 뺀값
        Map<Integer, Integer> giftJisu = new HashMap<>();
        for(int i = 0; i < N; i++){
            int rowSum = 0;
            for(int j = 0; j < N; j++){
                rowSum+= indegree[i][j];
            }
            int colSum = 0;
            for(int j = 0; j < N; j++){
                colSum += indegree[j][i];
            }
            giftJisu.put(i, rowSum - colSum);
        }
        
        Map<Integer, Integer> nextGet = new HashMap<>(); // 누가 : 몇개 받는지 기록 
        for(int i = 0; i <  N; i++){
            for(int j = 0; j < N; j++){
                int iGive = indegree[i][j];
                int jGive = indegree[j][i];
                 if ( i == j) {
                    continue;
                }   
                if(iGive > jGive) { // 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받음 
                    nextGet.put(i, nextGet.getOrDefault(i, 0) + 1);
                } else if(iGive == jGive) { // 주고 받은 수가 같다면 + 선물을 주고 받은 적이 없다면 
                    if(giftJisu.get(i) > giftJisu.get(j)) {
                        nextGet.put(i, nextGet.getOrDefault(i, 0) + 1);
                    }
                }
            }
        }
        
        for(int i = 0; i < N; i++){
            answer = Math.max(answer, nextGet.getOrDefault(i, 0));
        }
        return answer;
    }
}