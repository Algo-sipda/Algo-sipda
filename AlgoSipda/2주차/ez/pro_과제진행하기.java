import java.util.*;

/*
    0. 시간 타입을 int로 변환
    1. start 기준으로 정렬
    2. 새로운 과제를 해야함
        - 진행 중이던 기존 과제가 있음
        - 기존 과제가 끝남
*/

class Solution {

    static Map<Integer, String> subject;
    static int[][] map;

    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];

        subject = new HashMap<>();
        map = new int[plans.length][plans[0].length];

        for (int i = 0; i < plans.length; i++) {
            subject.put(i, plans[i][0]);
            map[i][0] = i;
            map[i][1] = convert(plans[i][1]);
            map[i][2] = Integer.parseInt(plans[i][2]);
        }

        Arrays.sort(map, (o1, o2) -> {
            return o1[1] - o2[1];
        });

        int time = 0;
        int idx = 0;
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < map.length; i++) {

            while (!stack.isEmpty()) { // 기존 과제가 끝났으면
                if (time + stack.peek()[2] > map[i][1]) break;
                time += stack.peek()[2];
                answer[idx++] = subject.get(stack.pop()[0]);
            }

            if (stack.isEmpty()) { // stack이 비어있으면 넣기
                stack.push(map[i]);
                time = map[i][1];
                continue;
            }

            if (time + stack.peek()[2] > map[i][1]) { // 기존 과제가 끝나지 않았으면
                stack.peek()[2] -= (map[i][1] - time);
                stack.push(map[i]);
                time = map[i][1];
            }
        }

        while (!stack.isEmpty()) {
            answer[idx++] = subject.get(stack.pop()[0]);
        }

        return answer;
    }

    private int convert(String time) {
        String[] temp = time.split(":");
        int hour = Integer.parseInt(temp[0]);
        int minute = Integer.parseInt(temp[1]);
        return hour * 60 + minute;
    }

}