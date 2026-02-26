def solution(s):
    ans, s_len = len(s), len(s)
    for l in range(1, s_len // 2 + 1):
        idx, tmp = 0, []
        while idx + l <= s_len:
            cnt, cur = 1, s[idx:idx+l]
            while idx+l*2 <= s_len and cur == s[idx+l:idx+l*2]:
                idx += l
                cnt += 1
            idx += l
            
            if cnt > 1:
                tmp.append(cnt)
            
            tmp.append(cur)
            
        if idx < s_len:
            tmp.append(s[idx:])
            
        ans = min(ans, len("".join(map(str, tmp))))
            
    return ans