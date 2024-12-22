import java.util.*;

class Solution {

    static int N, M, res, money;
    static int[] percent = {10, 20, 30, 40};
    static int[] isSelected;

    public int[] solution(int[][] users, int[] emoticons) {
        N = users.length;
        M = emoticons.length;
        isSelected = new int[M];

        perm(0, users, emoticons);

        int[] answer = { res, money };
        return answer;
    }

    private static void perm(int cnt, int[][] users, int[] emoticons) {
        if (cnt == M) {
            calc(users, emoticons);
            return;
        }

        for (int i = 0; i < 4; i++) {
            isSelected[cnt] = i;
            perm(cnt + 1, users, emoticons);
        }
    }

    private static void calc(int[][] users, int[] emoticons) {
        int cnt = 0;
        int total = 0;

        for (int i = 0; i < N; i++) {
            int buy = 0;
            for (int j = 0; j < M; j++) {
                if (percent[isSelected[j]] >= users[i][0]) {
                    int price = emoticons[j] * (100 - percent[isSelected[j]]) / 100;
                    buy += price;
                }
            }
            if (buy >= users[i][1]) {
                cnt++;
            } else {
                total += buy;
            }
        }

        if (cnt > res) {
            res = cnt;
            money = total;
        } else if (cnt == res && money < total) {
            money = total;
        }
    }
}