import java.util.*;
import java.io.*;

public class Main {

    static List<Integer> startList = new ArrayList<>();
    static List<Integer> endList = new ArrayList<>();
    static int bracketCnt; 
    static Set<String> answerSet = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(i);
            } else if (str.charAt(i) == ')') {
                int start = stack.pop();
                startList.add(start);
                endList.add(i);
            }
        }

        bracketCnt = startList.size();
        DFS(0, str, new boolean[bracketCnt]);

        List<String> answerList = new ArrayList<>(answerSet);
        Collections.sort(answerList);
        for (String string : answerList) {
            System.out.println(string);
        }
    }

    public static void DFS(int idx, String str, boolean[] remove) {
        if (idx == bracketCnt) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                boolean skip = false;
                for (int j = 0; j < bracketCnt; j++) {
                    if ((i == startList.get(j) || i == endList.get(j)) && remove[j]) {
                        skip = true;
                        break;
                    }
                }
                if (!skip) 
                    sb.append(str.charAt(i));
            }

            if (sb.length() < str.length()) {
                answerSet.add(sb.toString());
            }
            return;
        }

        remove[idx] = false;
        DFS(idx + 1, str, remove);

        remove[idx] = true;
        DFS(idx + 1, str, remove);
    }
}