import java.util.*;

class Solution {
    public int solution(int n, int[][] q, int[] ans) {
        List<int[]> candidates = new ArrayList<>();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i + 1;
        comb(arr, 0, 5, new int[5], candidates);

        int count = 0;
        for (int[] code : candidates) {
            boolean valid = true;
            for (int i = 0; i < q.length; i++) {
                int match = 0;
                for (int x : code) {
                    for (int y : q[i]) {
                        if (x == y) match++;
                    }
                }
                if (match != ans[i]) {
                    valid = false;
                    break;
                }
            }
            if (valid) count++;
        }
        return count;
    }

    void comb(int[] arr, int start, int r, int[] out, List<int[]> res) {
        if (r == 0) {
            res.add(out.clone());
            return;
        }
        for (int i = start; i <= arr.length - r; i++) {
            out[out.length - r] = arr[i];
            comb(arr, i + 1, r - 1, out, res);
        }
    }
}
