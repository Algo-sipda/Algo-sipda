import java.io.*;
import java.util.*;
import java.util.regex.Pattern;


class Solution {

    public static HashSet<HashSet<Integer>> result;
    List<List<Integer>> banList;

    public int solution(String[] user_id, String[] banned_id) {
        result = new HashSet<HashSet<Integer>>();
        banList = new ArrayList<>();

        for(String id: banned_id) {
            List<Integer> list = new ArrayList<>();

            String pattern = id.replace('*', '.');
            for(int i = 0; i < user_id.length; i++) {
                if(Pattern.matches(pattern, user_id[i])) {
                    list.add(i);
                }
            }

            banList.add(list);
        }

        dfs(new HashSet<>(), 0, banned_id.length);

        return result.size();
    }

    public void dfs(HashSet<Integer> list, int depth, int size) {
        if(depth == size) {
            result.add(new HashSet<>(list));
            return;
        }

        for(int idx: banList.get(depth)) {
            if(!list.contains(idx)) {
                list.add(idx);
                dfs(list, depth + 1, size);
                list.remove(idx);
            }
        }
    }
}