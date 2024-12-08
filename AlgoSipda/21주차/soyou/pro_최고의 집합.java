import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int n, int s) {
        int q = (int)(s / n);
        int r = s % n;

        if(q == 0) return new int[] {-1};

        int[] answer = new int[n];
        Arrays.fill(answer, q);

        for(int i = 0; i < r; i++) {
            answer[answer.length - i - 1]++;
        }

        return answer;
    }
}