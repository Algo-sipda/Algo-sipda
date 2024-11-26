import java.io.*;
import java.util.*;

public class Main {

    static class Developer implements Comparable<Developer> {
        int position;
        int infection;

        public Developer(int position, int infection) {
            this.position = position;
            this.infection = infection;
        }

        @Override
        public int compareTo(Developer o) {
            return this.position - o.position;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Developer> developers = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int position = Integer.parseInt(st.nextToken());
            int infection = Integer.parseInt(st.nextToken());
            developers.add(new Developer(position, infection));
        }

        Collections.sort(developers);

        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < developers.size(); i++) {
            Developer curr = developers.get(i);

            if (curr.infection == 0) {
                if (i != 0) {
                    if (developers.get(i - 1).infection == 1) {
                        minDistance = Math.min(minDistance,
                                curr.position - developers.get(i - 1).position - 1);
                    }
                }
                if (i != developers.size() - 1) {
                    if (developers.get(i + 1).infection == 1) {
                        minDistance = Math.min(minDistance,
                                developers.get(i + 1).position - curr.position - 1);
                    }
                }
            }
        }

        int lastPos = Integer.MIN_VALUE;
        int answer = 0;

        for (Developer developer : developers) {
            if (developer.infection == 0)
                continue;
            if (lastPos + minDistance < developer.position) {
                answer++;
            }
            lastPos = developer.position;
        }

        System.out.println(answer);
    }
}
