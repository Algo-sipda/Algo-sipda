import java.util.*;
/*
    문제 풀이 정리
    - x를 y로 변환하려고 함
    - 사용할 수 있는 연산
        1. x에 n을 더함
        2. x에 2를 곱함
        3. x에 3을 곱함
        
        최소 연산 횟수 return (없으면 -1)
        
*/

class Solution {
    class Number {
        int num, cnt;
        
        public Number(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
    
    public int solution(int x, int y, int n) {        
        boolean[] visited = new boolean[1_000_001];
        Queue<Number> q = new LinkedList<>();
        
        q.add(new Number(x, 0));
        
        while(!q.isEmpty()) {
            Number cur = q.poll();
            
            if(cur.num == y) 
                return cur.cnt;
            
            // 1. cur에 n을 더함
            if(cur.num+n <= y && !visited[cur.num+n]){
                q.add(new Number(cur.num+n, cur.cnt+1));
                visited[cur.num+n] = true;
            }
                
            // 2. cur에 2를 곱함
            if(cur.num*2 <= y && !visited[cur.num*2]){
                q.add(new Number(cur.num*2, cur.cnt+1));
                visited[cur.num*2] = true;
            }
            // 3. cur에 3을 곱함
            if(cur.num*3 <= y && !visited[cur.num*3]){
                q.add(new Number(cur.num*3, cur.cnt+1));
                visited[cur.num*3] = true;
            }
        }
        
        return -1;
    }
}