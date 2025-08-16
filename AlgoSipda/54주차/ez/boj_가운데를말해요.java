import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;

public class boj_가운데를말해요 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        Queue<Integer> maxQueue = new PriorityQueue<>((o1,o2) -> o2 - o1);
        Queue<Integer> minQueue = new PriorityQueue<>((o1,o2) -> o1 - o2);

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (maxQueue.size() == minQueue.size()) {
                maxQueue.add(num);
            } else {
                minQueue.add(num);
            }

            if (!maxQueue.isEmpty() && !minQueue.isEmpty()) {
                if (maxQueue.peek() > minQueue.peek()) {
                    int tmp = maxQueue.poll();
                    maxQueue.add(minQueue.poll());
                    minQueue.add(tmp);
                }
            }
            sb.append(maxQueue.peek()+"\n");
        }
        System.out.println(sb.toString());
    }
}
