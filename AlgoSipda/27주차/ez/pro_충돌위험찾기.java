public class pro_충돌위험찾기 {

    static class Robot {
        int r, c, road;
        boolean isOut;

        public Robot(int r, int c, int road, boolean isOut) {
            this.r = r;
            this.c = c;
            this.road = road;
            this.isOut = isOut;
        }
    }

    static Robot[] robots;
    static int answer;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public int solution(int[][] points, int[][] routes) {
        robots = new Robot[routes.length];

        // 로봇 위치 세팅
        init(points, routes);

        do {
            countDanger();

            for (int i = 0; i < robots.length; i++) {
                if (robots[i].road >= routes[i].length) {
                    robots[i].isOut = true;
                }
            }

            move(points, routes);
        } while (!isEnd());

        countDanger();

        return answer;
    }

    private void move(int[][] points, int[][] routes) {
        for (int i = 0; i < robots.length; i++) {
            if (robots[i].isOut) continue;
            int des = routes[i][robots[i].road] - 1;
            int dis = Math.abs(robots[i].r - points[des][0]) + Math.abs(robots[i].c - points[des][1]);
            int dir = 0;
            for (int d = 0; d < 4; d++) {
                int nr = robots[i].r + dr[d];
                int nc = robots[i].c + dc[d];
                int nextDis = Math.abs(nr - points[des][0]) + Math.abs(nc - points[des][1]);
                if (dis > nextDis) {
                    dis = nextDis;
                    dir = d;
                }
            }
            robots[i].r += dr[dir];
            robots[i].c += dc[dir];
            if (robots[i].r == points[des][0] && robots[i].c == points[des][1]) {
                robots[i].road += 1;
            }
        }
    }

    private void init(int[][] points, int[][] routes) {
        for (int i = 0; i < routes.length; i++) {
            int center = routes[i][0] - 1;
            robots[i] = new Robot(points[center][0], points[center][1], 1, false);
        }
    }

    private boolean isEnd() {
        for (Robot robot : robots) {
            if (!robot.isOut) {
                return false;
            }
        }
        return true;
    }

    private void countDanger() {
        int cnt = 0;
        boolean[] visited = new boolean[robots.length];
        for (int i = 0; i < robots.length; i++) {
            if (visited[i] || robots[i].isOut) continue;
            boolean danger = false;
            for (int j = i + 1; j < robots.length; j++) {
                if (visited[j] || robots[j].isOut) continue;
                if (robots[i].r == robots[j].r && robots[i].c == robots[j].c) {
                    visited[j] = true;
                    danger = true;
                }
            }
            if (danger) {
                cnt++;
            }
            visited[i] = true;
        }
        System.out.println(cnt);
        answer += cnt;
    }

}
