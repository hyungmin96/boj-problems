import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<String> set = new HashSet<>();

        int n = Integer.parseInt(br.readLine());
        String[] menus = new String[n];

        for (int i = 0; i < n; i++) {
            menus[i] = br.readLine().trim();
        }

        for (String menu : solution(menus, set))
            System.out.println(menu);

    }

    public static String[] solution(String[] menus, Set<String> set) {

        for(int i = 0; i < menus.length; i ++){
            boolean select = false;

            if(menus[i].contains(" ")){
                String[] data = menus[i].split(" ");
                for(int j = 0; j < data.length; j ++){
                    if(!set.contains(data[j].substring(0, 1).toUpperCase())){
                        set.add(data[j].substring(0, 1).toUpperCase());
                        menus[i] = "";
                        for(int k = 0; k < data.length; k ++){
                            if(j == k){
                                menus[i] += "[" + data[j].substring(0, 1) + "]" + data[j].substring(1) + " ";
                            }else{
                                menus[i] += data[k] + " ";
                            }
                        }
                        select = true;
                        break;
                    }
                }
            }
            
            if(!select){
                for(int j = 0; j < menus[i].length(); j ++){
                    if(!set.contains(menus[i].substring(j, j + 1).toUpperCase())){
                        if(!menus[i].substring(j, j + 1).equals(" ")){
                            set.add(menus[i].substring(j, j + 1).toUpperCase());
                            menus[i] = menus[i].substring(0, j) + "[" + menus[i].substring(j, j + 1) + "]" + menus[i].substring(j + 1);
                            break;
                        }
                    }
                }
            }
        }

        return menus;

    }
}
