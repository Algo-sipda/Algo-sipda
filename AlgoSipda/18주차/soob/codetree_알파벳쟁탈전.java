import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Relation {
        int a;
        int b;
        char status;

        public Relation(int a, int b, char status) {
            this.a = a;
            this.b = b;
            this.status = status;
        }
    }

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        List<Relation> list = new ArrayList<>();

        // 관계 입력
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new Relation(Math.min(a, b), Math.max(a, b), c));
        }

        // 관계 정렬
        list.sort(Comparator.comparingInt(o -> o.a));

        // DFS 탐색
        DFS(list, arr, 0);
        System.out.println(answer);
    }

    public static void DFS(List<Relation> list, int[] arr, int index) {
        if (index == list.size()) {
            int x = 1;

            // 남은 학생들(A, B, C 모두 가능) 처리
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] == 0) {
                    x *= 3;
                }
            }

            answer += x;
            return;
        }

        Relation rel = list.get(index);
        int a = rel.a;
        int b = rel.b;

        // 상태 복구를 위한 원래 값 저장
        int originalA = arr[a];
        int originalB = arr[b];

        // 두 학생 모두 이미 값이 지정된 경우
        if (arr[a] != 0 && arr[b] != 0) {
            if (isValid(arr[a], arr[b], rel.status)) {
                DFS(list, arr, index + 1);
            }
        }
        // 한쪽 값이 정해진 경우
        else if (arr[a] != 0 || arr[b] != 0) {
            if (arr[a] != 0) { // a가 정해진 경우
                assignAndDFS(list, arr, index, rel, a, b);
            } else { // b가 정해진 경우
                assignAndDFS(list, arr, index, rel, b, a);
            }
        }
        // 둘 다 값이 없는 경우
        else {
            if (rel.status == 'S') { // 같은 값
                for (int i = 1; i <= 3; i++) {
                    arr[a] = i;
                    arr[b] = i;
                    DFS(list, arr, index + 1);
                }
            } else { // 다른 값
                for (int i = 1; i <= 3; i++) {
                    for (int j = 1; j <= 3; j++) {
                        if (i != j) {
                            arr[a] = i;
                            arr[b] = j;
                            DFS(list, arr, index + 1);
                        }
                    }
                }
            }
        }

        // 상태 복구
        arr[a] = originalA;
        arr[b] = originalB;
    }

    public static void assignAndDFS(List<Relation> list, int[] arr, int index, Relation rel, int fixed, int toAssign) {
        if (rel.status == 'S') { // 같은 값
            arr[toAssign] = arr[fixed];
            DFS(list, arr, index + 1);
        } else { // 다른 값
            for (int i = 1; i <= 2; i++) {
                arr[toAssign] = (arr[fixed] + i - 1) % 3 + 1;
                DFS(list, arr, index + 1);
            }
        }
    }

    public static boolean isValid(int a, int b, char status) {
        if (status == 'S') return a == b;
        return a != b;
    }
}
