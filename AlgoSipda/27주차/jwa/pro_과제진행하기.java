import java.util.*;

class Solution {
    static class Task {
        String name;
        int start, playtime;

        Task(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
    }

    public String[] solution(String[][] plans) {
        PriorityQueue<Task> pq = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);

        for (String[] plan : plans) {
            String[] time = plan[1].split(":");
            int hour = Integer.parseInt(time[0]) * 60;
            int minute = Integer.parseInt(time[1]);

            pq.add(new Task(plan[0], hour + minute, Integer.parseInt(plan[2])));
        }

        String[] answer = new String[plans.length];
        Stack<Task> stack = new Stack<>();
        ArrayDeque<Task> q = new ArrayDeque<>();
        int now = 0;
        int index = 0;

        while (!pq.isEmpty() || !stack.isEmpty()) {
            if (q.isEmpty()) {
                if (pq.isEmpty()) {
                    q.add(stack.pop());
                } else if (stack.isEmpty()) {
                    Task t = pq.poll();
                    now = t.start;
                    q.add(t);
                } else {
                    if (now >= pq.peek().start) {
                        q.add(pq.poll());
                    } else {
                        q.add(stack.pop());
                    }
                }
            } else {
                if (pq.isEmpty() || now + q.peek().playtime <= pq.peek().start) {
                    Task t = q.poll();
                    answer[index++] = t.name;
                    now += t.playtime;
                } else {
                    Task t = q.poll();
                    t.playtime -= (pq.peek().start - now);
                    stack.push(t);
                    now = pq.peek().start;
                }
            }
        }
        answer[index] = q.poll().name;

        return answer;
    }

}
