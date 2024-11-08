import java.util.*;

class pro_불량사용자 {

    static int N, M;
    static Set<List<String>> set;
    static String[] selected;
    static boolean[] visited;

    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        N = user_id.length;
        M = banned_id.length;
        selected = new String[M];
        visited = new boolean[N];
        set = new HashSet<>();

        perm(0, user_id, banned_id);

        return set.size();
    }

    private static void perm(int cnt, String[] user_id, String[] banned_id) {
        if (cnt == M) {
            List<String> list = new ArrayList<>(Arrays.asList(selected));
            Collections.sort(list);
            set.add(list);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            if (isFound(user_id[i], banned_id[cnt])) {
                selected[cnt] = user_id[i];
                visited[i] = true;
                perm(cnt + 1, user_id, banned_id);
                visited[i] = false;
            }
        }
    }

    private static boolean isFound(String user, String ban) {
        if (user.length() != ban.length()) {
            return false;
        }

        for (int i = 0; i < user.length(); i++) {
            if (user.charAt(i) != ban.charAt(i) && ban.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }
}
