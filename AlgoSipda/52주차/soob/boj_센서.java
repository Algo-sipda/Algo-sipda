import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 센서의 개수
        int K = Integer.parseInt(br.readLine());    // 집중국의 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> sensorList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            sensorList.add(Integer.parseInt(st.nextToken()));
        }
        if (N <= K) {
            System.out.println(0);
            return;
        }
        Collections.sort(sensorList);
        List<Integer> gapList = new ArrayList<>();
        for (int i = 0; i < sensorList.size() - 1; i++) {
            gapList.add(sensorList.get(i + 1) - sensorList.get(i));
        }
        Collections.sort(gapList, Collections.reverseOrder());
        int answer = sensorList.get(sensorList.size() - 1) - sensorList.get(0);
        for (int i = 0; i < K - 1; i++) {
            answer -= gapList.get(i);
        }
        System.out.println(answer);
    }
}
