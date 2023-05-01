class Solution {
    
    int answer = 99999999;
    public int solution(int N, int number) {
        dfs(0, 0, N, number);
        return answer == 99999999 ? -1 : answer;
    }
    
    public void dfs(int depth, int cur, int N, int number){
        if(depth > 8) return;
        if(cur == number){
            if(answer > depth || answer == 99999999)
                answer = Math.min(answer, depth);
            return;
        }
        int temp = 0;
        for(int i = 1; i <= 8; i ++){
            temp = temp * 10 + N;
            dfs(depth + i, cur + temp, N, number);
            dfs(depth + i, cur - temp, N, number);
            dfs(depth + i, cur * temp, N, number);
            dfs(depth + i, cur / temp, N, number);
            
        }
    }
}