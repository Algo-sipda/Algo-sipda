import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] timeArr = new int[N + 1];
        int[] completeTime = new int[N + 1];
        List<Integer>[] nextList = new List[N + 1];
        List<Integer>[] preList = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            nextList[i] = new ArrayList<>();
            preList[i] = new ArrayList<>();
        }

        Queue<Point> startList = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            timeArr[i] = time;
            int count = Integer.parseInt(st.nextToken());
            if (count == 0)
                startList.add(new Point(i, time));
            for (int j = 0; j < count; j++) {
                int next = Integer.parseInt(st.nextToken());
                preList[i].add(next);
                nextList[next].add(i);
            }
        }

        boolean[] finished = new boolean[N + 1];
        while (!startList.isEmpty()) {
            int size = startList.size();
            for (int i = 0; i < size; i++) {
                Point p = startList.poll();
                finished[p.x] = true;
                completeTime[p.x] = p.y;
                for (int next : nextList[p.x]) {
                    if (!finished[next]) {
                        boolean flag = true;
                        int max = 0;
                        for (int pre : preList[next]) {
                            if (!finished[pre]) {
                                flag = false;
                                break;
                            }
                            max = Math.max(max, completeTime[pre]);
                        }
                        if (flag) {
                            int t = max + timeArr[next];
                            startList.add(new Point(next, t));
                            completeTime[next] = t;
                        }
                    }
                }
            }
        }

        int answer = 0;
        for (int i : completeTime) {
            answer = Math.max(answer, i);
        }

        System.out.println(answer);

    }
}