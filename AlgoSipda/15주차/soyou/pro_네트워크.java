import java.io.*;
import java.util.*;

class Solution {

    static Integer[] parent;

    public int solution(int n, int[][] computers) {
        parent = new Integer[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) continue;
                if(computers[i][j] == 1) {
                    union(i, j);
                    System.out.println(Arrays.toString(parent));
                }
            }
        }

        for(int i = 0; i < n; i++) {
            parent[i] = find(i);
        }

        Set<Integer> set = new HashSet<>(Arrays.asList(parent));
        return set.size();
    }

    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a <= b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    public int find(int a) {
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }
}