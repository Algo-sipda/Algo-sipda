import java.util.*;

/*
 다시 풀어보기
*/

class pro_추석트래픽 {
    public int solution(String[] lines) {
        int answer = 0;
        List<int[]> list = new ArrayList<>();

        for (String line : lines) {
            String[] temp = line.split(" ");
            String time = temp[1];
            int con = convert(time);
            int term = convertTerm(temp[2]);
            list.add(new int[] { con - term, 0});
            list.add(new int[] { con + 1000, 1});
        }

        Collections.sort(list, (a, b) -> (a[0] - b[0]));

        int cnt = 0;

        for (int[] time : list) {
            if (time[1] == 0) {
                cnt++;
            } else {
                cnt--;
            }
            answer = Math.max(answer, cnt);
        }

        return answer;
    }

    private int convert(String time) {
        int sum = 0;
        String[] temp = time.split(":");
        int hour = Integer.parseInt(temp[0]);
        int min = Integer.parseInt(temp[1]);
        int sec = (int) (Double.parseDouble(temp[2]) * 1000);
        sum = 3600 * 1000 * hour + 60 * 1000 * min + sec;
        return sum;
    }

    private int convertTerm(String term) {
        term = term.substring(0, term.length() - 1);
        int time = (int) (Double.parseDouble(term) * 1000) - 1;
        return time;
    }
}
