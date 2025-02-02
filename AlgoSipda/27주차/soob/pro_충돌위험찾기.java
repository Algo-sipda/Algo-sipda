import java.awt.Point;
import java.util.*;

class Solution {
    public class Status {
        int x, y, moveX, moveY;
        Queue<Point> queue = new LinkedList<>();

        public Status(int x, int y, int moveX, int moveY, Queue<Point> queue) {
            this.x = x;
            this.y = y;
            this.moveX = moveX;
            this.moveY = moveY;
            this.queue = queue;
        }
    }

    public int solution(int[][] points, int[][] routes) {
        List<Status> statusList = new ArrayList<>();
        for (int[] route : routes) {
            int startX = points[route[0] - 1][0];
            int startY = points[route[0] - 1][1];
            int endX = points[route[1] - 1][0];
            int endY = points[route[1] - 1][1];
            Queue<Point> queue = new LinkedList<>();
            if (route.length > 2) {
                for (int i = 2; i < route.length; i++) {
                    queue.add(new Point(points[route[i] - 1][0], points[route[i] - 1][1]));
                }
            }
            statusList.add(new Status(startX, startY, endX - startX, endY - startY, queue));
        }

        int answer = 0;
        while (!statusList.isEmpty()) {
            Map<Point, Integer> map = new HashMap<>();
            List<Status> newStatusList = new ArrayList<>();
            for (Status status : statusList) {
                if (map.containsKey(new Point(status.x, status.y)))
                    map.put(new Point(status.x, status.y), map.get(new Point(status.x, status.y)) + 1);
                else map.put(new Point(status.x, status.y), 1);
                if (status.moveX == 0 && status.moveY == 0) {
                    if (!status.queue.isEmpty()) {
                        Point point = status.queue.poll();
                        status.moveX = point.x - status.x;
                        status.moveY = point.y - status.y;
                    }
                }
                if (status.moveX != 0) {
                    if (status.moveX > 0) {
                        status.moveX--;
                        status.x++;
                    } else {
                        status.moveX++;
                        status.x--;
                    }
                    newStatusList.add(new Status(status.x, status.y, status.moveX, status.moveY, new LinkedList<>(status.queue)));
                    continue;
                }
                if (status.moveY != 0) {
                    if (status.moveY > 0) {
                        status.moveY--;
                        status.y++;
                    } else {
                        status.moveY++;
                        status.y--;
                    }
                    newStatusList.add(new Status(status.x, status.y, status.moveX, status.moveY, new LinkedList<>(status.queue)));
                }
            }
            statusList = new ArrayList<>(newStatusList);
            for (Map.Entry<Point, Integer> entry : map.entrySet()) {
                if (entry.getValue() != 1)
                    answer++;
            }
        }

        return answer;
    }
}