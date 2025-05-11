class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        StringBuilder sb = new StringBuilder();
        Map<Character, List<String>> dictMap = new HashMap<>();

        for(String d: dictionary) {
            char c = d.charAt(0);

            List<String> list = dictMap.getOrDefault(c, new ArrayList<>());

            list.add(d);
            Collections.sort(list);
            dictMap.put(c, list);
        }

        String[] words = sentence.split(" ");
        for(String w:words) {
            char c = w.charAt(0);

            List<String> list = dictMap.getOrDefault(c, new ArrayList<>());
            boolean isFind = false;

            for(String l:list) {
                if(w.startsWith(l)) {
                    sb.append(l);
                    isFind = true;
                    break;
                }
            }

            if(!isFind) sb.append(w);
            sb.append(" ");
        }

        return sb.toString().trim();
    }
}