class Solution {
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        int[][] giftCount = new int[n][n];
        int[] giftScore = new int[n];
        int[] received = new int[n];
        
        for (String gift : gifts) {
            String[] parts = gift.split(" ");
            int from = findIndex(friends, parts[0]);
            int to = findIndex(friends, parts[1]);
            giftCount[from][to]++;
            giftScore[from]++;
            giftScore[to]--;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (giftCount[i][j] > giftCount[j][i]) {
                    received[i]++;
                } else if (giftCount[i][j] == giftCount[j][i]) {
                    if (giftScore[i] > giftScore[j]) {
                        received[i]++;
                    }
                }
            }
        }
        
        int answer = 0;
        for (int r : received) {
            answer = Math.max(answer, r);
        }
        return answer;
    }
    
    private int findIndex(String[] friends, String name) {
        for (int i = 0; i < friends.length; i++) {
            if (friends[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }
}