import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        if (s < n) {
            return new int[] {-1};
        }

        int share = s / n;
        int remain = s % n;
        int[] answer;

        answer = new int[n];
        Arrays.fill(answer, share);
        for (int i = n - remain; i < n; i++) {
            answer[i]++;
        }

        return answer;
    }
}
