import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Map<Integer, Set<Integer>> friends = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String string = br.readLine();
            for (int j = 0; j < n; j++) {
                char c = string.charAt(j);
                if (c == 'N')
                    continue;
                friends.computeIfAbsent(i, k -> new HashSet<>()).add(j);
                friends.computeIfAbsent(j, k -> new HashSet<>()).add(i);
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            Set<Integer> friends2 = friends.getOrDefault(i, new HashSet<>());
            Set<Integer> friends3 = new HashSet<>();
            for (int f : friends2) {
                friends3.addAll(friends2);
                friends3.addAll(friends.get(f));
            }
            friends3.remove(i);
            answer = Math.max(answer, friends3.size());
        }

        System.out.println(answer);
    }
}
