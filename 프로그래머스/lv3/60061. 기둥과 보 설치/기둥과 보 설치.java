import java.util.*;
class Solution {
    
    static boolean[][] pillar, ceiling;
    static ArrayList<int[]> list = new ArrayList<>();
    static ArrayList<int[]> work = new ArrayList<>();
    
    public int[][] solution(int n, int[][] build_frame) {
        
        pillar = new boolean[n + 1][n + 1];
        ceiling = new boolean[n + 1][n + 1];
        
        // x, y, 기둥(0)/보(1), 삭제(0)/설치(1)
        for(int[] i : build_frame){
            if(i[3] == 1){ // 설치
                if(i[2] == 0){
                    if(checkPillar(n, i[0], i[1])){
                        work.add(new int[] { i[0], i[1], 0 });
                        pillar[i[0]][i[1]] = true;
                    }
                }else{
                    if(checkCeiling(n, i[0], i[1])){
                        work.add(new int[] { i[0], i[1], 1 });
                        ceiling[i[0]][i[1]] = true;
                    }
                }
            }else{ // 삭제
                if(!delete(n, i[0], i[1], i[2])){
                    if(i[2] == 0) 
                        pillar[i[0]][i[1]] = true;
                    else
                        ceiling[i[0]][i[1]] = true;
                }
            }
        }
        
        for(int i = 0; i <= n; i ++){
            for(int j = 0; j <= n; j ++){
                if(pillar[i][j]){
                    list.add(new int[] { i, j, 0 });
                }
                if(ceiling[i][j]){
                    list.add(new int[] { i, j, 1 });
                }
            }
        }
        
        Collections.sort(list, new Comparator<>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[0] != o2[0]) return o1[0] - o2[0];
                else if(o1[1] != o2[1]) return o1[1] - o2[1];
                return o1[2] - o2[2];
            }
        });
        
        int[][] answer = new int[list.size()][3];
        for(int i = 0; i < list.size(); i ++){
            answer[i] = list.get(i);
        }
        return answer;
    }
    
    public boolean delete(int n, int x, int y, int a){
        if(a == 0) 
            pillar[x][y] = false;
        else
            ceiling[x][y] = false;
        
        for(int[] item : work){
            if(item[2] == 0 && pillar[item[0]][item[1]] && !checkPillar(n, item[0], item[1])){
                return false;
            }else if(item[2] == 1 && ceiling[item[0]][item[1]] && !checkCeiling(n, item[0], item[1])){
                return false;
            }
        }
        return true;
    }
    
    public boolean checkCeiling(int n, int x, int y){
        if(y - 1 >= 0 && pillar[x][y - 1]) return true;
        if(x + 1 <= n && y - 1 >= 0 && pillar[x + 1][y - 1]) return true;
        if(x - 1 >= 0 && ceiling[x - 1][y] && x + 1 <= n && ceiling[x + 1][y]) return true;
        return false;
    }
    
    public boolean checkPillar(int n, int x, int y){
        if(y == 0) return true;
        if(y - 1 >= 0 && pillar[x][y - 1]) return true;
        if(ceiling[x][y]) return true;
        if(x - 1 >= 0 && ceiling[x - 1][y]) return true;
        return false;
    }
}