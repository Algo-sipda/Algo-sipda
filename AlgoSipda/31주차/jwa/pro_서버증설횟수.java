class Solution {
    static final int HOURS = 24;

    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] servers = new int[HOURS];

        for (int i = 0; i < HOURS; i++) {
            if (players[i] / m <= servers[i])
                continue;

            int server = players[i] / m - servers[i];

            for (int j = 0; j < k; j++) {
                if (i + j >= HOURS)
                    break;
                servers[i + j] += server;
            }

            answer += server;
        }

        return answer;
    }
}

