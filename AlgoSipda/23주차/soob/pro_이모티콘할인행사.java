import java.util.*;

class Solution {
    static int[][] users;
    static int[] emoticons;
    static int[] discounts = {10, 20, 30, 40}; // 가능한 할인율
    static int maxSubscribers = 0; // 최대 가입자 수
    static int maxRevenue = 0; // 최대 매출
    static int[] discountCombination;

    public int[] solution(int[][] users, int[] emoticons) {
        Solution.users = users;
        Solution.emoticons = emoticons;
        discountCombination = new int[emoticons.length];

        dfs(0);

        return new int[]{maxSubscribers, maxRevenue};
    }

    public static void dfs(int idx) {
        if (idx == emoticons.length) {
            calculate();
            return;
        }

        for (int discount : discounts) {
            discountCombination[idx] = discount;
            dfs(idx + 1);
        }
    }

    public static void calculate() {
        int subscribers = 0;
        int revenue = 0;

        for (int[] user : users) {
            int threshold = user[0];
            int budget = user[1];

            int totalSpent = 0;

            for (int i = 0; i < emoticons.length; i++) {
                if (discountCombination[i] >= threshold) {
                    totalSpent += emoticons[i] * (100 - discountCombination[i]) / 100;
                }
            }

            if (totalSpent >= budget) {
                subscribers++;
            } else {
                revenue += totalSpent;
            }
        }

        if (subscribers > maxSubscribers || (subscribers == maxSubscribers && revenue > maxRevenue)) {
            maxSubscribers = subscribers;
            maxRevenue = revenue;
        }
    }
}
