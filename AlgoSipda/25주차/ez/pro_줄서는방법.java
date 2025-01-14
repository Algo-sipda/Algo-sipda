import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        List<Integer> list = new ArrayList<>();
        long fac = 1;

        for (int i = 1; i <= n; i++) {
            list.add(i);
            fac *= i;
        }

        k -= 1;
        int cnt = 0;

        while (n > 0) {
            fac /= n; // rotate
            int idx = (int) (k / fac);
            answer[cnt++] = list.remove(idx);
            k %= fac;
            n -= 1;
        }

        return answer;
    }
}

/*
0. k - 1에서 시작
1. fac / n 번마다 숫자 바뀜(rotate)
2. k / rotate 번 인덱스 수 찾음
3. k 갱신, n 갱신

 */