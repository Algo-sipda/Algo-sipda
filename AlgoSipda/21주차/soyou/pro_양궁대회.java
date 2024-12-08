class Solution {

    static int[] ryan, answer;
    static int max = 0;

    public int[] solution(int n, int[] info) {
        answer = new int[] {-1};
        ryan = new int[11];

        dfs(info, 1, n);

        return answer;
    }

    static void dfs(int[] info, int arrows, int n) {

        if (arrows == n + 1) {
            int apeachScore = 0;
            int ryanScore = 0;

            for (int i = 0; i < 11; i++) {
                if (info[i] == 0 && ryan[i] == 0) continue;

                if (info[i] < ryan[i]) ryanScore += 10 - i;
                else apeachScore += 10 - i;
            }

            if (apeachScore < ryanScore && (ryanScore - apeachScore >= max)) {
                max = ryanScore - apeachScore;
                answer = ryan.clone();
            }

            return;
        }

        for (int i = 0; i <= 10 && ryan[i] <= info[i]; i++) {
            ryan[i]++;
            dfs(info, arrows + 1, n);
            ryan[i]--;
        }

    }

}