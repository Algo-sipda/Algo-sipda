// [PRO] 비밀 코드 해독 https://school.programmers.co.kr/learn/courses/30/lessons/388352

import java.util.*;
class Solution {
    static int[][] query;
    static int[] answer;
    static int result = 0;
    static int MAX;
    static int QUERY_CNT;
    public int solution(int n, int[][] q, int[] ans) {
        MAX = n;
        QUERY_CNT = q.length;
        query = q;
        // 1 - n 정수 5개 오름차순 선택 -> 조건에 적합한지 확인
        
        // m번의 시도를 할 수 있음
        // 5개의 정수 입력하면 몇 개가 비밀 코드에 포함되어있는지 알려줌
        // n : 10이상 30이하 
        // q개수 : 10
        answer = ans;
        back(1, new HashSet<>(), 0);
        return result;
    }
    static void back(int n, Set<Integer> selected, int sId) {
        if(n == MAX + 1){
            if(sId == 5){ // 몇 개가 비밀 코드에 포함되어 있는가 
                for(int i = 0; i < answer.length; i++) {
                    int cnt = 0;
                    for(int j = 0; j < 5; j++) {
                        if(selected.contains(query[i][j])){
                            cnt += 1; 
                        }

                    }
                    
                    if(answer[i] != cnt){
                       return;
                    }
                }
                result+=1;
            }
            return;
        }
        if (sId < 5) {
            selected.add(n);
            back(n + 1, selected, sId + 1); // 선택하는 경우 
        }
        selected.remove(n);
        back(n + 1, selected, sId);
        
    }
}