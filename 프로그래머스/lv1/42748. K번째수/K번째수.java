import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for(int i = 0; i < commands.length; i ++){
            int[] cmd = commands[i];
            int[] temp = new int[(cmd[1] - cmd[0]) + 1];
            int idx = 0;
            for(int k = cmd[0] - 1; k < cmd[1]; k ++){
                temp[idx++] = array[k];
            }
            Arrays.sort(temp);
            answer[i] = temp[cmd[2] - 1];
        }
        return answer;
    }
}