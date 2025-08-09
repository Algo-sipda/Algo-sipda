import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Integer> plusList = new ArrayList<>();
        List<Integer> minusList = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            if (x > 0)
                plusList.add(x);
            else
                minusList.add(x);
        }

        Collections.sort(plusList, Collections.reverseOrder());
        Collections.sort(minusList);

        int answer;

        if (plusList.isEmpty())
            answer = minusList.get(0);
        else if (minusList.isEmpty())
            answer = -plusList.get(0);
        else
            answer = -Math.max(plusList.get(0), Math.abs(minusList.get(0)));

        while (!plusList.isEmpty()) {
            answer += plusList.get(0) * 2;
            for (int i = 0; i < M; i++) {
                plusList.remove(0);
                if (plusList.isEmpty())
                    break;
            }
        }

        while (!minusList.isEmpty()) {
            answer -= minusList.get(0) * 2;
            for (int i = 0; i < M; i++) {
                minusList.remove(0);
                if (minusList.isEmpty())
                    break;
            }
        }

        System.out.println(answer);
    }
}