import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Point> list = new ArrayList<Point>();
        for (int i = 0; i < N; i++) {
            int s = sc.nextInt();
            int f = sc.nextInt();
            list.add(new Point(s, f));
        }
        Collections.sort(list, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.x == o2.x)
                    return o1.y - o2.y;
                return o1.x - o2.x;
            }
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(list.get(0).y);
        for (int i = 1; i < N; i++) {
            if (pq.peek() <= list.get(i).x)
                pq.poll();
            pq.offer(list.get(i).y);
        }

        System.out.println(pq.size());

    }
}