import java.util.*;

class Solution {
    class Homework implements Comparable<Homework> {
        String name;
        int start;
        int playtime;

        Homework(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }

        @Override
        public int compareTo(Homework h) {
            return this.start - h.start;
        }
    }


    public String[] solution(String[][] plans) {
        String[] answer = {};

        answer = new String[plans.length];
        int idx = 0;
        PriorityQueue<Homework> q = new PriorityQueue<>((o1, o2) -> (o1.start - o2.start));
        for (String[] p : plans) {
            q.add(new Homework(p[0], convertTime(p[1]), Integer.parseInt(p[2])));
        }

        Homework s = q.poll();
        int now = s.start;

        Stack<Homework> stack = new Stack<>(); //멈춘 과제들
        while (true) {
            if (!q.isEmpty() && now + s.playtime > q.peek().start) {
                stack.push(new Homework(s.name, s.start, s.playtime - (q.peek().start - now)));
                now = q.peek().start;
                s = q.poll();
            } else {
                answer[idx++] = s.name;
                now += s.playtime;

                if (!q.isEmpty() && now == q.peek().start)
                    s = q.poll();
                else if (!stack.isEmpty())
                    s = stack.pop();
                else if (!q.isEmpty()) {
                    s = q.poll();
                    now = s.start;
                } else
                    break;
            }
        }

        return answer;
    }

    public static int convertTime(String t) {
        String[] str = t.split(":");
        int min = Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
        return min;
    }
}