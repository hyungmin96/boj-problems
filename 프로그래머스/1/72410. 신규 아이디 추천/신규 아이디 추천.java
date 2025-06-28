class Solution {
    public String solution(String new_id) {
   
        String step1 = changeLower(new_id);
        String step2 = replaceEtcChar(step1);
        String step3 = deleteConsecutiveDot(step2);
        String step4 = deleteSideDot(step3);
        String step5 = emptyStringAdd(step4);
        String step6 = cutString(step5);
        String step7 = AddCharIfLengthLess(step6);
        System.out.println(step7);
        
        return step7;
    }
    
    public String AddCharIfLengthLess(String s){
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        if(sb.toString().length() <= 2){
            while(sb.toString().length() < 3){
                sb.append(s.charAt(s.length() - 1));
            }
        }
        return sb.toString();
    }
    
    public String cutString(String s){
        if(s.length() > 15){
            return deleteSideDot(s.substring(0, 15));
        }
        return s;
    }
    
    public String emptyStringAdd(String s){
        if(s.length() == 0){
            return "a";
        }
        return s;
    }
    
    public String deleteSideDot(String s){
        if(s.length() > 0){
            if(s.charAt(0) == '.'){
                s = s.substring(1);    
            }
            if(s.length() > 0 && s.charAt(s.length() - 1) == '.'){
                s = s.substring(0, s.length() - 1);
            }   
        }
        return s;
    }
    
    public String deleteConsecutiveDot(String s){
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        while(s.indexOf("..") != -1){
            char[] tmp = s.toCharArray();
            sb = new StringBuilder();
            int start = s.indexOf("..");
            for(int i = start; i < s.length(); i ++){
                if(s.charAt(i) != '.'){
                    break;
                }
                tmp[i] = '\0';
            }
            tmp[start] = '.';
            for(char c : tmp){
                if(c == '\0') continue;
                sb.append(c);
            }
            s = sb.toString();
        }
        return sb.toString();
    }
    
    public String replaceEtcChar(String s){
        StringBuilder sb = new StringBuilder();
        char[] chs = new char[] { '-', '_', '.' };
        for(int i = 0; i < s.length(); i ++){
            boolean flag = false;
            for(int j = 0; j < chs.length; j ++){
                if(s.charAt(i) == chs[j]){
                    flag = true;
                }
                if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z'){
                    flag = true;
                }
                if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                    flag = true;
                }
            }
            if(flag){
               sb.append(s.charAt(i));
            }
        }
        
        return sb.toString();
    }
    
    public String changeLower(String s){
        return s.toLowerCase();
    }
}