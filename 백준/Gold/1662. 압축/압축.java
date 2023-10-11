import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = br.readLine();
        boolean[] visited = new boolean[command.length()];
        int length = dfs(command,0,visited);

        System.out.println(length);
    }
    private static int dfs( String command , int index , boolean[] visited){
        int cnt = 0;
        for(int i = index ; i < command.length() ; i++){
            char ch = command.charAt(i);
            if(ch == '(' && !visited[i]){
                visited[i] = true;
                int num = command.charAt(i-1) - '0';
                cnt--;
                cnt += num * dfs(command,i+1,visited);
                continue;
            }
            if(ch == ')' && !visited[i]){
                visited[i] = true;
                return cnt;
            }
            if(!visited[i]){
                visited[i] = true;
                cnt++;
            }
        }
        return cnt;
    }
}