import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int mod = 1_000_000_007;

    public static class Node {
        int start;
        int end;
        int value;

        Node left;
        Node right;

        public Node(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static int[] arr;
    public static Node[] tree;
    public static int[] indexArr;

    public static int init(int start, int end, int index) {
        if (start == end) {
            tree[index] = new Node(start, end, arr[start]);
            indexArr[start] = index;
            return arr[start];
        }

        int mid = (start + end) / 2;
        int leftValue = init(start, mid, index * 2);
        int rightValue = init(mid + 1, end, index * 2 + 1);
        int value = (int)(((long) leftValue * rightValue) % mod);
        tree[index] = new Node(start, end, value);
        return value;
    }

    public static void change(int b, int c, int index) {
        if (tree[index] == null)
            return;
        if (tree[index].start == b && tree[index].end == b)
            tree[index].value = c;
        else {
            int leftValue = tree[index * 2] == null ? 1 : tree[index * 2].value;
            int rightValue = tree[index * 2 + 1] == null ? 1 : tree[index * 2 + 1].value;
            tree[index].value = (int)(((long) leftValue * rightValue) % mod);
        }
        if (index > 1)
            change(b, c, index / 2);
    }

    public static int find(int b, int c, int index) {
        if (tree[index].start > c || tree[index].end < b)
            return 1;
        if (tree[index].start >= b && tree[index].end <= c)
            return tree[index].value;

        int left = find(b, c, index * 2);
        int right = find(b, c, index * 2 + 1);
        return (int)(((long) left * right) % mod);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        indexArr = new int[N + 1];
        tree = new Node[N * 4];

        for (int n = 1; n <= N; n++) {
            arr[n] = Integer.parseInt(br.readLine());
        }

        init(1, N, 1);

        for (int order = 0; order < M + K; order++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                change(b, c, indexArr[b]);
                arr[b] = c;
            } else if (a == 2) {
                System.out.println(find(b, c, 1));
            }
        }
    }
}
