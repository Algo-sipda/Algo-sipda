import java.util.*;

class pro_튜플 {
    public int[] solution(String s) {
        int[] answer;
        HashMap<Integer, Integer> map = new HashMap<>();

        s = s.replaceAll("\\{\\{","");
        s = s.replaceAll("\\}\\}","");
        s = s.replaceAll("\\},\\{"," ");
        s = s.replaceAll(","," ");

        StringTokenizer st = new StringTokenizer(s," ");

        while(st.hasMoreTokens()) {
            int key = Integer.parseInt(st.nextToken());
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> entryList = new LinkedList<>(map.entrySet());
        entryList.sort(Map.Entry.comparingByValue());

        answer = new int[entryList.size()];
        int index = answer.length-1;
        for(Map.Entry<Integer, Integer> entry : entryList) {
            answer[index--] = entry.getKey();
        }
        return answer;
    }
}