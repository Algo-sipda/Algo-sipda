import java.util.*;

// 풀다 몰라서 답지 참고했어요..ㅎㅎ 다시 풀어보겠습니다..!

class pro_표병합 {

    final int SIZE = 2501;
    int[] parent = new int[SIZE];
    String[] value = new String[SIZE];

    public int find(int a) {
        if (parent[a] == a)
            return a;

        return parent[a] = find(parent[a]);
    }

    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    public int convertNum(int x, int y) {
        int result = 50 * (x - 1);
        return result + y;
    }

    public String[] solution(String[] commands) {

        for (int i = 1; i < SIZE; i++) {
            parent[i] = i;
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < commands.length; i++) {
            String line = commands[i];
            StringTokenizer st = new StringTokenizer(line);
            String command = st.nextToken();

            if ("UPDATE".equals(command)) {
                if (st.countTokens() == 2) {
                    String before = st.nextToken();
                    String after = st.nextToken();
                    for (int j = 1; j < SIZE; j++) {
                        if (before.equals(value[j]))
                            value[j] = after;
                    }
                }
                else {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    String after = st.nextToken();
                    int num = convertNum(x, y);
                    value[find(num)] = after;
                }
            } else if ("MERGE".equals(command)) {
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                int n1 = convertNum(x1, y1);
                int n2 = convertNum(x2, y2);
                int root1 = find(n1);
                int root2 = find(n2);
                if (root1 == root2) continue;
                String rootString = value[root1].isBlank() ? value[root2] : value[root1];
                value[root1] = "";
                value[root2] = "";
                union(root1, root2);
                value[root1] = rootString;
            } else if ("UNMERGE".equals(command)) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int num = convertNum(x, y);
                int root = find(num);
                String rootString = value[root];
                value[root] = "";
                value[num] = rootString;
                List<Integer> deleteList = new ArrayList<>();
                for (int j = 1; j < SIZE; j++) {
                    if (find(j) == root)
                        deleteList.add(j);
                }
                for (Integer t : deleteList)
                    parent[t] = t;
            } else if ("PRINT".equals(command)) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int num = convertNum(x, y);
                int root = find(num);
                if (value[root].isBlank())
                    result.add("EMPTY");
                else
                    result.add(value[root]);
            }
        }
        return result.toArray(new String[0]);
    }

}
