import java.util.HashMap;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int maxGifts = 0;
        int n = friends.length;

        HashMap<String, Integer> nameToIndex = new HashMap<>();
        int[] giftScore = new int[n];
        int[][] giftCount = new int[n][n];

        for (int i = 0; i < n; i++) {
            nameToIndex.put(friends[i], i);
        }

        for (String gift : gifts) {
            String[] splitGift = gift.split(" ");
            int giver = nameToIndex.get(splitGift[0]);
            int receiver = nameToIndex.get(splitGift[1]);

            giftScore[giver]++;
            giftScore[receiver]--;
            giftCount[giver][receiver]++;
        }

        for (int i = 0; i < n; i++) {
            int received = 0;

            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;

                if (giftCount[i][j] > giftCount[j][i]
                        || (giftCount[i][j] == giftCount[j][i] && giftScore[i] > giftScore[j])) {
                    received++;
                }
            }

            maxGifts = Math.max(maxGifts, received);
        }

        return maxGifts;
    }
}
