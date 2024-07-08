import java.util.*;

/*
    1. hashmap > (귤 크기, 개수) 저장
    2. 배열로 변환해서 개수 큰 순서대로 정렬
*/

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < tangerine.length; i++) {
            map.put(tangerine[i], map.getOrDefault(tangerine[i], 0) + 1);
        }

        int[][] arr = new int[map.size()][2];
        int idx = 0;
        for (Integer key : map.keySet()) {
            arr[idx][0] = key;
            arr[idx][1] = map.get(key);
            idx++;
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        for (int i = 0; i < arr.length; i++) {
            if (k - arr[i][1] < 0 && k > 0) {
                answer++;
                break;
            }
            if (k - arr[i][1] >= 0) {
                answer++;
                k -= arr[i][1];
                continue;
            }
        }

        return answer;
    }
}