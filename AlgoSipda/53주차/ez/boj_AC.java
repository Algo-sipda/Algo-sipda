import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_AC {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String command = br.readLine();
            int N = Integer.parseInt(br.readLine());
            char[] commands = command.toCharArray();
            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
            List<Integer> list = new ArrayList<>();
            while (st.hasMoreTokens()) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            StringBuilder sb = new StringBuilder("[");
            String[] com = command.split("");
            boolean dir = true;
            for (int i = 0; i < com.length; i++) {
                if (com[i].equals("R")) {
                    dir = !dir;
                } else {
                    if (list.size() == 0) {
                        sb = new StringBuilder("error");
                        break;
                    }
                    if (dir) {
                        list.remove(0);
                    } else {
                        list.remove(list.size() - 1);
                    }
                }
            }
            if (sb.toString().equals("error")) {
                System.out.println(sb);
                continue;
            }
            if (dir) {
                for (Integer n : list) {
                    sb.append(n).append(",");
                }
            } else {
                for (int i = list.size() - 1; i >= 0; i--) {
                    sb.append(list.get(i)).append(",");
                }
            }
            if (sb.toString().length() > 1) {
                sb = sb.delete(sb.length() - 1, sb.length());
            }
            sb.append("]");
            System.out.println(sb);
        }
    }
}
