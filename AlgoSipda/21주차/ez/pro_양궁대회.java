class pro_양궁대회 {
    static int score = 0;
    static int[] result;
    public int[] solution(int n, int[] info) {
        int[] answer;
        int[] lion = new int[11];

        dfs(lion, info, 0, 1, n);
        if(score == 0) {
            answer = new int[1];
            answer[0] = -1;
        } else
            answer = result.clone();
        return answer;
    }
    public void dfs(int[] lion, int[] info, int start, int cnt, int n) {
        if (cnt == n + 1) {
            int lionSum = 0, apeachSum = 0, sum = 0;
            for (int i = 0;i <= 10; i++) {
                if (lion[i] == 0 && info[i] == 0) continue; // 여기 중요..
                if (lion[i] > info[i])
                    lionSum += 10 - i;
                else
                    apeachSum += 10 - i;
            }
            sum = lionSum - apeachSum;
            if (sum >= score && sum > 0) {
                result = new int[11];
                result = lion.clone();
                score = sum;
            }
            return;
        }

        for (int i = start; i <= 10; i++) {
            if (lion[i] <= info[i]) {
                lion[i] += 1;
                dfs(lion, info, i, cnt+1, n);
                lion[i] -= 1;
            }
        }
    }
}