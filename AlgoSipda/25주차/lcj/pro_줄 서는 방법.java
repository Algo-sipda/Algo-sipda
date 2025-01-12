import java.util.*;

/*
    순열로 구하면 20!일 경우 시간초과가 나기 때문에 불가
    => 풀이 참조 했습니다!
    
    n= 4인 경우 
    -> (총 경우의 수) / 총 자릿 수 = 요소의 개수
    24 / 4 = 6
    인덱스 : 찾아야할 번호 / 요소의 개수
    14 / 6 = 2.xxx
    [1, 2, 3, 4] -> 3
    다음 번호 14 % 6 = 2
*/

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        List<Integer> list = new ArrayList<>();
        
        long total = 1;
        for(int i=1;i<=n;i++) {
            list.add(i);
            total *= i;
        }
        
        k--;
        
        int idx= 0;
        while(idx < n) {
            total /= n - idx; // 전체 경우의 수 / 숫자의 개수
            answer[idx++] = list.remove((int) (k/total));
            k %= total; // 다음 찾아야 할 번호
        }
        
        return answer;
    }
}