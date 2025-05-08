// [PRO] 두 원 사이의 정수 쌍 https://school.programmers.co.kr/learn/courses/30/lessons/181187
// x ^ 2 + y ^ 2 = r ^ 2
class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        long zero = 0;
        for (int x = -r2; x <= r2; x++) {
            long x2 = x * x;
            if (x2 >= r1 * r1 && x2 <= r2 * r2) {
                zero++;
            }
            
            long r2_x2 = r2 * r2 - x2;
            long r1_x2 = r1 * r1 - x2;
            if (r2_x2 < 0) continue;
            
            long highY = (long)Math.floor(Math.sqrt(r2_x2));
            long lowY = 0;
            if (r1_x2 > 0) {
                lowY = (long)Math.ceil(Math.sqrt(r1_x2));
            }
            
            if (highY >= lowY) {
                answer += (highY - lowY + 1);
            }
        }
        
        return answer * 2 - zero;
    }
}
