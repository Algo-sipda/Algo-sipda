import java.util.*;

class Solution {
    public List<String> solution(String[][] tickets) {
        Map<String, PriorityQueue<String>> routes = new HashMap<>();
        
        for (String[] ticket : tickets) {
            routes.putIfAbsent(ticket[0], new PriorityQueue<>());
            routes.get(ticket[0]).add(ticket[1]);
        }
        
        List<String> path = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        stack.push("ICN");
        
        while (!stack.isEmpty()) {
            String top = stack.peek();
            
            if (!routes.containsKey(top) || routes.get(top).isEmpty()) {
                path.add(stack.pop());
            } else {
                stack.push(routes.get(top).poll());
            }
        }
        
        Collections.reverse(path);
        return path;
    }
}