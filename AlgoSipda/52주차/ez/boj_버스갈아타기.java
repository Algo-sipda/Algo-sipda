import java.io.*;
import java.util.*;

public class boj_버스갈아타기 {
    static int M, N, K;
    static int[] departure, destination;
    static Bus[] buses;

    static class Bus {
        int[] start, end;
        boolean isVertical;

        public Bus(int[] start, int[] end, boolean isVertical) {
            this.start = start;
            this.end = end;
            this.isVertical = isVertical;
        }
    }

    static class State implements Comparable<State> {
        int num, usedNum;

        public State(int num, int usedNum) {
            this.num = num;
            this.usedNum = usedNum;
        }

        @Override
        public int compareTo(State o) {
            return usedNum - o.usedNum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        buses = new Bus[K + 1];

        for (int bus = 1; bus <= K; bus++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int startR = Integer.parseInt(st.nextToken());
            int startC = Integer.parseInt(st.nextToken());
            int endR = Integer.parseInt(st.nextToken());
            int endC = Integer.parseInt(st.nextToken());

            if (startR == endR)
                buses[num] = new Bus(new int[] {startR, Math.min(startC, endC)}, new int[] {endR, Math.max(startC, endC)}, true);
            else if (startC == endC)
                buses[num] = new Bus(new int[] {Math.min(startR, endR), startC}, new int[] {Math.max(startR, endR), endC}, false);
        }

        st = new StringTokenizer(br.readLine());
        int startR = Integer.parseInt(st.nextToken());
        int startC = Integer.parseInt(st.nextToken());
        departure = new int[] {startR, startC};

        int endR = Integer.parseInt(st.nextToken());
        int endC = Integer.parseInt(st.nextToken());
        destination = new int[] {endR, endC};

        System.out.println(bfs());
    }

    static int bfs() {
        List<State> startBusList = findStartBus(departure);
        PriorityQueue<State> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[K + 1];
        for (State startBus : startBusList) {
            queue.add(startBus);
            visited[startBus.num] = true;
        }

        while (!queue.isEmpty()) {
            State cur = queue.poll();
            Bus bus = buses[cur.num];
            if (bus.isVertical && destination[0] == bus.start[0] &&
                    (bus.start[1] <= destination[1] && destination[1] <= bus.end[1])) {
                return cur.usedNum;
            }
            if (!bus.isVertical && destination[1] == bus.start[1] &&
                    (bus.start[0] <= destination[0] && destination[0] <= bus.end[0])) {
                return cur.usedNum;
            }

            for (int i = 1; i <= K; i++) {
                if(!visited[i] && isCross(buses[cur.num], buses[i])) {
                    visited[i] = true;
                    queue.offer(new State(i, cur.usedNum + 1));
                }
            }
        }

        return -1;
    }

    static List<State> findStartBus(int[] departure) {
        List<State> startBuses = new ArrayList<>();
        for(int i = 1; i <= K; i++) {
            Bus bus = buses[i];

            if (bus.isVertical && departure[0] == bus.start[0] &&
                    (bus.start[1] <= departure[1] && departure[1] <= bus.end[1])) {
                startBuses.add(new State(i, 1));
            } else if (!bus.isVertical && departure[1] == bus.start[1] &&
                    (bus.start[0] <= departure[0] && departure[0] <= bus.end[0])) {
                startBuses.add(new State(i, 1));
            }
        }
        return startBuses;
    }

    static boolean isCross(Bus bus1, Bus bus2) {
        if (bus1.isVertical && bus2.isVertical) {
            if(bus1.start[0] != bus2.start[0] || bus1.start[1] > bus2.end[1] || bus1.end[1] < bus2.start[1]) {
                return false;
            }
            return true;
        }
        if(bus1.isVertical && !bus2.isVertical) {
            if(bus1.start[0] >= bus2.start[0] && bus1.start[0] <= bus2.end[0] && bus2.start[1] >= bus1.start[1] && bus2.start[1] <= bus1.end[1]) {
                return true;
            }
            return false;
        }
        if (!bus1.isVertical && bus2.isVertical) {
            if(bus2.start[0] >= bus1.start[0] && bus2.start[0] <= bus1.end[0] && bus1.start[1] >= bus2.start[1] && bus1.start[1] <= bus2.end[1]) {
                return true;
            }
            return false;
        }
        if (bus1.start[1] != bus2.start[1] || bus1.start[0] > bus2.end[0] || bus1.end[0] < bus2.start[0]) {
            return false;
        }
        return true;
    }
}