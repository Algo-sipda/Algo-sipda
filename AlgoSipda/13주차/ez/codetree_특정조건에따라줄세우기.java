import java.util.*;
import java.io.*;

public class Main {
    static String[] name = {"Beatrice", "Belinda", "Bella", "Bessie", "Betsy", "Blue", "Buttercup", "Sue"};
    static List<String[]> info, selected;
    static String[] per;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        info = new ArrayList<>();
        selected = new ArrayList<>();
        per = new String[8];
        visited = new boolean[8];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            String[] arr = line.split(" ");
            info.add(new String[] {arr[0], arr[arr.length - 1]});
        }

        perm(0);

        for (int i = 0; i < selected.size(); i++) {
            List<String> temp = new ArrayList<>(Arrays.asList(selected.get(i)));
            if (isValid(temp)) {
                for (String na : selected.get(i)) {
                    System.out.println(na);
                }
                break;
            }
        }
    }

    private static boolean isValid(List<String> name) {
        for (String[] arr : info) {
            int idx1 = name.indexOf(arr[0]);
            int idx2 = name.indexOf(arr[1]);
            if (Math.abs(idx1 - idx2) != 1) {
                return false;
            }
        }
        return true;
    }

    private static void perm(int cnt) {
        if (cnt == 8) {
            selected.add(per.clone());
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            per[cnt] = name[i];
            perm(cnt + 1);
            visited[i] = false;
        }
    }
}