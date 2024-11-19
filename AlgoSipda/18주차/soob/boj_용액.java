import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            list.add(num);
        }

        int min = Integer.MAX_VALUE;
        int answer1 = 0, answer2 = 0;
        for (int i = 0; i < list.size(); i++) {
            int start = i + 1;
            int end = list.size() - 1;
            while (start <= end) {
                int mid = (start + end) / 2;
                int sum = list.get(i) + list.get(mid);
                if (Math.abs(sum) < min) {
                    min = Math.abs(sum);
                    answer1 = list.get(i);
                    answer2 = list.get(mid);
                }
                if (sum < 0) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        System.out.println(Math.min(answer1, answer2) + " " + Math.max(answer1, answer2));
    }
}