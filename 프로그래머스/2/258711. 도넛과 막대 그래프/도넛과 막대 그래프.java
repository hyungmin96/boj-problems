import java.util.*;
class Solution {
    int max = -1, createdNode = -1, total = 0;
    ArrayList<Integer>[] outEdges;
    int[] inEdges = new int[1000001];
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        for(int[] e : edges){
            max = Math.max(max, Math.max(e[0], e[1]));
        }
        
        outEdges = new ArrayList[max + 1];
        for(int i = 0; i <= max; i ++){
            outEdges[i] = new ArrayList<>();
        }

        for(int[] e : edges){
            int from = e[0];
            int to = e[1];
            
            outEdges[from].add(to);
            inEdges[to] ++;
        }
        
        answer[0] = findCreatedNode();
        total = outEdges[answer[0]].size();
        
        // 도넛1 : 들어오고 나가는 간선이 1개씩만 존재
        // 막대2 : 들어오는 간선 1개만 존재
        // 8자3 : 들어오는 간선 2개, 나가는 간선 2개인 노드가 존재
        
        // 전체그래프 수 - 막대 - 8자 : 도넛
        answer[2] = countBarGraph();
        answer[3] = countEightGraph();
        answer[1] = total - answer[2] - answer[3];
        return answer;
    }
    
    public int countBarGraph(){
        int cnt = 0;
        for(int i = 1; i <= max; i ++){
            if(inEdges[i] > 0 && outEdges[i].size() == 0){
                cnt ++;
            }
        }
        return cnt;
    }
    
    public int countEightGraph(){
        int cnt = 0;
        for(int i = 1; i <= max; i ++){
            if(inEdges[i] >= 2 && outEdges[i].size() >= 2){
                cnt ++;
            }
        }
        return cnt;
    }
    
    public int findCreatedNode(){
        int node = -1;
        for(int i = 1; i <= max; i ++){
            if(outEdges[i].size() >= 2 && inEdges[i] == 0){
                return i;
            }
        }
        return node;
    }
}