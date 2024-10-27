class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int k = 1000 * 100;
        t1 += 10;
        t2 += 10;
        temperature += 10;

        int[][] DP = new int[onboard.length][51];
        for (int i = 0; i < onboard.length; i++) {
            for (int j = 0; j < 51; j++) {
                DP[i][j] = k;
            }
        }

        DP[0][temperature] = 0;

        int flag = 1;
        if (temperature > t2)
            flag = -1;

        for (int i = 1; i < onboard.length; i++) {
            for (int j = 0; j < 51; j++) {
                int minEnergy = k;
                if ((onboard[i] == 1 && t1 <= j && j <= t2) || onboard[i] == 0) {
                    if (0 <= j + flag && j + flag <= 50)
                        minEnergy = Math.min(minEnergy, DP[i - 1][j + flag]);
                    if (j == temperature)
                        minEnergy = Math.min(minEnergy, DP[i - 1][j]);
                    if (0 <= j - flag && j - flag <= 50)
                        minEnergy = Math.min(minEnergy, DP[i - 1][j - flag] + a);
                    if (t1 <= j && j <= t2)
                        minEnergy = Math.min(minEnergy, DP[i - 1][j] + b);
                    DP[i][j] = minEnergy;
                }
            }
        }

        int i = onboard.length - 1;
        int answer = DP[i][0];
        for (int j = 1; j < 51; j++) {
            answer = Math.min(answer, DP[i][j]);
        }
        return answer;
    }
}
