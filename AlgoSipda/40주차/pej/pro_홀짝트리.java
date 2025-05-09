// [PRO]  홀짝트리 https://school.programmers.co.kr/learn/courses/30/lessons/388354
import java.util.*;
import java.io.*;

class Solution {
    static Map<Integer, List<Integer>> connections;
    static boolean[] visited;
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];
        
        connections = new HashMap<>();
        for(int node : nodes) {
            connections.put(node, new ArrayList<>());
        }
        
        for(int[] edge : edges) {
            connections.get(edge[0]).add(edge[1]);
            connections.get(edge[1]).add(edge[0]);
        }
        
        visited = new boolean[1000001];
        for(int node : nodes) {
            if(!visited[node] && checkIsHolZak(node, -1)) {
                answer[0] += 1;
            }
        }
        
        // 역홀짝 트리 : 역홀수노드, 역짝수 노드 
        // -> [홀짝 트리 개수, 역홀짝 트리 개수]   
        visited = new boolean[1000001];
        for(int node : nodes) {
            if(!visited[node] && checkIsReverseHolZak(node, -1)) {
                answer[1] += 1;
            }
        }

    
        return answer;
    }
            
    // 홀수노드 : 번호(홀수), 자식노드개수(홀수)
    // 짝수노드 : 번호(짝수), 자식노드개수(짝수)
    static boolean checkIsHolZak(int current, int parent) {
        int childrenCnt = connections.get(current).size() - 1;
        if(parent == -1){
            childrenCnt+=1;
        }
        if(current % 2 == childrenCnt % 2) { // 홀짝여부 번호 == 자식노드개수 
            visited[current] = true;
            for(int child : connections.get(current)) {
                if(child == parent)continue;
                if(!checkIsHolZak(child, current)) {
                    visited[child] = false;
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // 역홀수 노드 : 번호(홀수), 자식노드개수(짝수)
    // 역짝수 노드 : 번호(짝수), 자식노드개수(홀수)
    static boolean checkIsReverseHolZak(int current, int parent) {
        int childrenCnt = connections.get(current).size() - 1;
        if(parent == -1){
            childrenCnt+=1;
        }
        if(current % 2 != childrenCnt % 2) { // 홀짝여부: 번호 != 자식노드 개수
            visited[current] = true;
            for(int child : connections.get(current)) {
                if(child == parent)continue;
                if(!checkIsReverseHolZak(child, current)) {
                    visited[child] = false;
                    return false;
                }
            }
            return true;
        }
        return false;
    }
 
}