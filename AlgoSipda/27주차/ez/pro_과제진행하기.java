import java.util.*;

class pro_과제진행하기 {

    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        Map<Integer, String> map = new HashMap<>();
        Stack<int[]> stack = new Stack<>();
        int[][] arr = new int[plans.length][3];

        for (int i = 0; i < plans.length; i++) {
            map.put(i, plans[i][0]);
            arr[i][0] = i;
            arr[i][1] = convertTime(plans[i][1]);
            arr[i][2] = Integer.parseInt(plans[i][2]);
        }

        Arrays.sort(arr, new Comparator<>() {
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int time = 0;
        int idx = 0;
        for (int i = 0; i < arr.length; i++) {

            while (!stack.isEmpty()) {
                if (time + stack.peek()[2] > arr[i][1]) break;
                time += stack.peek()[2];
                answer[idx++] = map.get(stack.pop()[0]);
            }

            if (stack.isEmpty()) {
                stack.push(arr[i]);
                time = arr[i][1];
                continue;
            }

            if (time + stack.peek()[2] > arr[i][1]) {
                stack.peek()[2] -= (arr[i][1] - time);
                stack.push(arr[i]);
                time = arr[i][1];
            }

        }

        while (!stack.isEmpty()) {
            answer[idx++] = map.get(stack.pop()[0]);
        }

        return answer;
    }

    private int convertTime(String time) {
        int hour = Integer.parseInt(time.split(":")[0]);
        int minute = Integer.parseInt(time.split(":")[1]);
        return hour * 60 + minute;
    }
}
