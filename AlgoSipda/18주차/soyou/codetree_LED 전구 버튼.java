import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        int lights = 0;
        int result = 0;

        for(int i = n - 1; i >= 0; i--) {
            lights |= (Integer.parseInt(br.readLine()) << i);
        }

        Map<Integer, Long> map = new HashMap<>();
        List<Integer> memo = new ArrayList<>();

        long idx = 0;
        int current = lights;
        while(idx < b) {
            if(map.containsKey(current)) {
                long start = map.get(current);
                long cycle = idx - start;
                result = memo.get((int)(start + ((b - idx) % cycle)));
                break;
            }

            map.put(current, idx);
            memo.add(current);

            int nextLights = current;
            for(int i = 0; i < n; i++) {
                int leftIdx = (i + 1) % n;
                if((current & (1 << leftIdx)) != 0) {
                    nextLights ^= (1 << i);
                }
            }

            current = nextLights;
            result = current;
            idx++;
        }


        String str = String.format("%" + n + "s", Integer.toBinaryString(result)).replace(' ', '0');
        for(char c: str.toCharArray()) {
            out.append(c).append("\n");
        }

        System.out.println(out);
    }
}
