import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        TreeMap<Integer, Integer> village = new TreeMap<>();
        long totalPeople = 0;

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            village.put(v, p);
            totalPeople += p;
        }

        long now = 0;
        for (Map.Entry<Integer, Integer> entry : village.entrySet()) {
            now += entry.getValue();
            if (now >= (totalPeople + 1) / 2) {
                System.out.println(entry.getKey());
                return;
            }
        }
    }
}