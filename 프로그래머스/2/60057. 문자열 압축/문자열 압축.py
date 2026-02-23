def solution(s):
    answer = len(s)
    
    def dfs(idx, l):
        if idx >= len(s):
            return ""
        
        cnt = 1
        while idx + l < len(s) and s[idx:idx+l] == s[idx+l:idx+l*2]:
            idx += l
            cnt += 1
        
        tmp = ""
        if cnt > 1:
            tmp = str(cnt)
        
        if idx + l > len(s):
            tmp += s[idx:]
        else:
            tmp += s[idx:idx+l]
        
        return tmp + dfs(idx+l, l)
            
    
    for i in range(1, len(s) // 2 + 1):
        answer = min(answer, len(dfs(0, i)))
    
    return answer