import java.util.*;

// Logic
// 1. 요격할 라인을 찾기 위해 두번째 원소 기준 오름차순 정렬
// 2. 요격할 총알의 위치를 -1로 우선 지정(초기화)
// 3. for문 돌면서 요격할 대상 순회
//    curStart: 해당 격추시킬 시작점
//    curEnd: 해당 격추시킬 끝나는 지점
//    a. left가 bulletLocation보다 작을 경우
//       - 그 이전 총알에 격추되는 위치이기 때문에 통과
//    b. 그 외의 경우
//       - 총알을 쏴야하는 위치이기 때문에 총알 쏘기
//       - 현재 총알 위치 갱신하고, answer 값 증가

class Solution {
    public int solution(int[][] targets) {
        
        Arrays.sort(targets, (o1, o2) -> {
            if(o1[1] == o2[1]){
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        
        int answer = 0;
        int bulletLocation = -1;
        
        for(int i=0;i<targets.length;i++){
            int curStart = targets[i][0];
            int curEnd = targets[i][1];
            
            if(curStart>=bulletLocation) {
                answer++;
                bulletLocation = curEnd;
            }
        }
        
        return answer;
    }
}