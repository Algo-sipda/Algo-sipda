import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class boj_문자열잘라내기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int start = 0;
        int end = R - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (isDuplicate(map, mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start - 1);
    }

    private static boolean isDuplicate(char[][] map, int r) {
        Set<String> set = new HashSet<>();

        for (int i = 0; i < map[0].length; i++) {
            String word = "";
            for (int j = r; j < map.length; j++) {
                word += map[j][i];
            }
            set.add(word);
        }

        if (set.size() == map[0].length) {
            return false;
        }
        return true;
    }
}
