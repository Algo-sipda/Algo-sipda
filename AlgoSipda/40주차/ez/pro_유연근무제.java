class pro_유연근무제 {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int n = schedules.length;
        int[] deadline = new int[n];
        int[] days = new int[n];

        for (int i = 0; i < n; i++) {
            deadline[i] = convertTime(schedules[i]);
        }

        int total = 7;
        for (int i = 0; i < 7; i++) {
            int today = (startday + i - 1) % 7 + 1;
            if (today > 5) {
                total--;
                continue;
            }
            for (int j = 0; j < n; j++) {
                if (timelogs[j][i] <= deadline[j]) {
                    days[j]++;
                }
            }
        }

        for (int day : days) {
            if (day == total) {
                answer++;
            }
        }
        return answer;
    }

    private int convertTime(int time) {
        time += 10;
        if (time % 100 >= 60) {
            time -= 60;
            time += 100;
        }
        return time;
    }
}