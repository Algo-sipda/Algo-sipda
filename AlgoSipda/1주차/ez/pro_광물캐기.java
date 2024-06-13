import java.util.*;

/*
    풀이 방식: 중복 순열
 */

class Solution {

    static int pickLen, mineLen, answer;
    static List<String> mine = List.of("diamond", "iron", "stone");
    static int[][] power = { { 1, 1, 1 }, {5, 1, 1}, {25, 5, 1}};
    static int[] selected;

    public int solution(int[] picks, String[] minerals) {
        answer = Integer.MAX_VALUE;

        mineLen = minerals.length;
        for (int pick : picks) {
            pickLen += pick;
        }
        selected = new int[Math.min((mineLen % 5 == 0) ? mineLen / 5 : mineLen / 5 + 1, pickLen)];
        perm(0, picks, minerals);

        return answer;
    }

    private static void perm(int cnt, int[] picks, String[] minerals) {
        if (cnt == selected.length) {
            answer = Math.min(answer, calc(picks, minerals));
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (picks[i] == 0) continue;
            selected[cnt] = i;
            picks[i]--;
            perm(cnt + 1, picks, minerals);
            picks[i]++;
        }
    }

    private static int calc(int[] picks, String[] minerals) {
        int sum = 0;
        for (int i = 0; i < selected.length; i++) {
            for (int j = 0; j < 5; j++) {
                int idx = (i * 5) + j;
                if (idx >= minerals.length) break;
                int mineIdx = mine.indexOf(minerals[idx]);
                sum += power[selected[i]][mineIdx];
            }
        }
        return sum;
    }

}