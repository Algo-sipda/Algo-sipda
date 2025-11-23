import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            set.add(Integer.parseInt(br.readLine()));
        }
        Object[] array = set.toArray();
        int len = array.length;
        int[] intArr = new int[len];

        for (int i = 0; i < len; i++) {
            intArr[i] = (int) array[i];
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int gap = intArr[j] - intArr[i];
                if (gap >= M) {
                    answer = Math.min(answer, gap);
                    break;
                }
            }
        }

        System.out.println(answer);

    }

}