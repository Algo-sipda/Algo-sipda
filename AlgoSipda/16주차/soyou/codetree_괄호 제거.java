import java.io.*;
import java.util.*;

public class Main {

    static char[] expression;
    static List<String> results = new ArrayList<>();
    static Deque<Integer> stack = new ArrayDeque<>();
    static Map<Integer, Integer> map = new HashMap<>();
    static List<Integer> brackets = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        expression = br.readLine().toCharArray();

        for(int i = 0; i < expression.length; i++) {
            if(expression[i] == '(') {
                stack.offer(i);
            } else if(expression[i] == ')') {
                int idx = stack.pollLast();
                map.put(idx, i);
                brackets.add(idx);
            }
        }

        combination(0, new boolean[brackets.size()]);
        Collections.sort(results);

        for(int i = 0; i < results.size(); i++) {
            System.out.println(results.get(i));
        }
    }

    public static void combination(int nth, boolean[] choose) {

        if(nth == choose.length) {
            boolean[] isBracket = new boolean[expression.length];
            boolean isRemoved = false;

            for(int i = 0; i < choose.length; i++) {
                if(choose[i]) {
                    int idx = brackets.get(i);
                    int matchIdx = map.get(idx);
                    isBracket[idx] = true;
                    isBracket[matchIdx] = true;
                    isRemoved = true;
                }
            }

            if(!isRemoved) return;

            String result = "";
            for(int i = 0; i < expression.length; i++) {
                if(isBracket[i]) continue;
                result += expression[i];
            }

            results.add(result);
            return;
        }

        choose[nth] = true;
        combination(nth + 1, choose);
        choose[nth] = false;
        combination(nth + 1, choose);
    }
}