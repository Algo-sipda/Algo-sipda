class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;

        for (int i = 0; i < schedules.length; i++) {
            int standard = toMinutes(schedules[i]) + 10;
            int day = startday;
            boolean ok = true;

            for (int j = 0; j < 7; j++) {
                if (day != 6 && day != 7) {
                    int time = toMinutes(timelogs[i][j]);
                    if (time > standard) {
                        ok = false;
                        break;
                    }
                }
                day = day == 7 ? 1 : day + 1;
            }

            if (ok) answer++;
        }

        return answer;
    }

    private int toMinutes(int time) {
        return (time / 100) * 60 + (time % 100);
    }
}
