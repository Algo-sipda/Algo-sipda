import java.util.*;
import java.awt.Point;

class Solution {
    static class Robot {
        int r, c, t;

        Robot(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }

    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int x = routes.length;
        int m = routes[0].length;
        Robot[] robots = new Robot[x];

        for (int i = 0; i < x; i++) {
            int[] start = points[routes[i][0] - 1];
            robots[i] = new Robot(start[0], start[1], 0);
        }

        int count = x;

        while (count > 0) {
            HashMap<Point, Integer> counter = new HashMap<>();

            for (int i = 0; i < x; i++) {
                Robot robot = robots[i];

                if (robot.t == m)
                    continue;

                int[] target = points[routes[i][robot.t] - 1];
                int nx = robot.r;
                int ny = robot.c;
                int dx = target[0] - robot.r;
                int dy = target[1] - robot.c;

                if (dx != 0) {
                    nx = robot.r + dx / Math.abs(dx);
                } else if (dy != 0) {
                    ny = robot.c + dy / Math.abs(dy);
                }

                int nextT = robot.t;

                if (nx == target[0] && ny == target[1]) {
                    nextT++;
                }

                robot.r = nx;
                robot.c = ny;
                robot.t = nextT;

                Point p = new Point(nx, ny);
                counter.put(p, counter.getOrDefault(p, 0) + 1);

                if (counter.get(p) == 2) {
                    answer++;
                }

                if (robot.t == m)
                    count--;
            }
        }


        return answer;
    }
}
