class Solution {
    
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args){
        System.out.println(solution("hit", "lot", new String[] {"hot", "dot", "dog", "lot", "log"}));
    }

    public static int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        dfs(begin, target, words.length, words, visited, 0);
        return answer;
    }
    
    public static void dfs(String begin, String target, int n, String[] words, boolean[] visited, int depth){
        for(int i = 0; i < n; i ++){
            if(!visited[i] && isPossible(begin, words[i])){
                visited[i] = true;
                if(begin == target){
                    answer = (answer > depth) ? depth : answer;
                    return;
                }
                dfs(words[i], target, n, words, visited, depth + 1);
                visited[i] = false;
            }
        }
    }
    
    public static boolean isPossible(String s1, String s2){
        int temp = 0;
        for(int i = 0; i < s1.length(); i ++)
            if(s2.charAt(i) != s1.charAt(i))
                temp ++;
        
        if(temp == 1)
            return true;
        else
            return false;
    }
}