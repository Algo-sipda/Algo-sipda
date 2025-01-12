import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        ArrayDeque<Integer> buildings = new ArrayDeque<>();

        for (int i = 1; i < a; i++) {
            buildings.addLast(i);
        }

        buildings.addLast(Math.max(a, b));

        for (int i = b - 1; i > 0; i--) {
            buildings.addLast(i);
        }

        if (buildings.size() > N) {
            System.out.println(-1);
            return;
        }

        int first = buildings.pollFirst();
        int size = buildings.size();

        for (int i = 0; i < N - size - 1; i++) {
            buildings.addFirst(1);
        }

        buildings.addFirst(first);

        for (int building : buildings) {
            System.out.print(building + " ");
        }
    }
}
