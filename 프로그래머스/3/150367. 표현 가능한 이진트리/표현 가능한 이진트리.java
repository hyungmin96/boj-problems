class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for(int i = 0; i < numbers.length; i ++){
            String s = getBinary(numbers[i]);
            if(dfs(s)){
                answer[i] = 1;
            }
        }
        return answer;
    }
    
    public boolean dfs(String s){
        int root = s.length() / 2;
        if(s.length() == 1) return true;
        
        // 부모가 0일 경우 자식노드가 존재하는지 확인
        if(s.charAt(root) == '0'){
            if(s.contains("1")) return false;
        }
        
        if(s.charAt(root) == '0'){
            if(!s.contains("1")) return true;
        }

        String left = s.substring(0, root);
        String right = s.substring(root + 1, s.length());
        // 왼쪽 서브트리 만들기
        if(!dfs(left)) return false;
        // 오른쪽 서브트리 만들기
        if(!dfs(right)) return false;
        
        return true;
    }
    
    public String getBinary(long num){
        StringBuilder sb = new StringBuilder();
        while(num > 0){
            sb.append(num % 2 == 0 ? 0 : 1);
            num /= 2;
        }
        
        int cnt = 0;
        long total = 0;
        while(total < sb.toString().length()){
            total += (int)Math.pow(2, cnt ++);
        }

        int k = sb.toString().length();
        for(int j = 0; j < total - k; j ++){
            sb.append("0");
        }

        
        return sb.reverse().toString();
    }
}