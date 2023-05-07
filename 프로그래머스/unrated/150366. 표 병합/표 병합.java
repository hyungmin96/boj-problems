import java.util.*;
class Solution {

    static ArrayList<String> list = new ArrayList<>();
    static String[][] arr = new String[50][50];
    static int[] parent = new int[2500];

    public String[] solution(String[] commands) {
        for(int i = 0; i < parent.length; i ++)
            parent[i] = i;
        
        for(int i = 0; i < commands.length; i ++){
            String[] words = commands[i].split(" ");
            switch(words[0]){
                case "UPDATE":
                    update(words);
                    break;
                case "MERGE":
                    merge(words);
                    break;
                case "UNMERGE":
                    unMerge(words);
                    break;
                case "PRINT":
                    print(words);
                    break;
            }
        }
        String[] answer = new String[list.size()];
        for(int i = 0; i < list.size(); i ++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    public void print(String[] words){
        int r = Integer.parseInt(words[1]) - 1;
        int c = Integer.parseInt(words[2]) - 1;
        
        int a = find(parent[r * 50 + c]);
        if(arr[a / 50][a % 50] == null || arr[a / 50][a % 50].equals("")){
            list.add("EMPTY");
        }else{
            list.add(arr[a / 50][a % 50]);
        }
    }
    
    public void unMerge(String[] words){
        int r = Integer.parseInt(words[1]) - 1;
        int c = Integer.parseInt(words[2]) - 1;

        int a = find(parent[r * 50 + c]);
        String temp = arr[a / 50][a % 50];
    
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < parent.length; i ++){
            if(find(parent[i]) == a){
                arr[i / 50][i % 50] = "";
                list.add(i);
            }
        }
        for(int item : list)
            parent[item] = item;
        
        arr[r][c] = temp;
    }
    
    public void merge(String[] words){
        int r1 = Integer.parseInt(words[1]) - 1;
        int c1 = Integer.parseInt(words[2]) - 1;
        int r2 = Integer.parseInt(words[3]) - 1;
        int c2 = Integer.parseInt(words[4]) - 1;
        
        if(r1 == r2 && c1 == c2) return;
        int pos1 = find(parent[r1 * 50 + c1]);
        int pos2 = find(parent[r2 * 50 + c2]);
        
        String temp = arr[pos1 / 50][pos1 % 50];
        if(temp == null || temp.equals("")){
            temp = arr[pos2 / 50][pos2 % 50];
        }
        
        arr[pos1 / 50][pos1 % 50] = temp;
        union(pos1, pos2);
    }
    
    public void update(String[] words){
        if(words.length == 3){
            String rep = words[1];
            String val = words[2];
            for(int r = 0; r < 50; r ++){
                for(int c = 0; c < 50; c ++){
                    if(arr[r][c] != null && arr[r][c].equals(rep)){
                        arr[r][c] = val;
                    }
                }
            }
        }else{
            int r = Integer.parseInt(words[1]) - 1;
            int c = Integer.parseInt(words[2]) - 1;
            
            int a = find(parent[r * 50 + c]);
            arr[a / 50][a % 50] = words[3];
        }
    }
    
    public void union(int a, int b){
        a = find(a);
        b = find(b);
        parent[b] = a;
    }
    
    public int find(int a){
        if(parent[a] == a) return a;
        return find(parent[a]);
    }
}