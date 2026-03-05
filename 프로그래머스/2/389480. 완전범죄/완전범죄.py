def solution(info, n, m):
    dp = [[[-1] * 121 for _ in range(121)] for _ in range(len(info))]
    def dfs(idx, a, b):
        if idx >= len(info):
            return 0
        
        if dp[idx][a][b] != -1:
            return dp[idx][a][b]
        
        ret = 987654321
        dp[idx][a][b] = 98654321
        if a + info[idx][0] < n:
            ret = min(ret, dfs(idx+1,a+info[idx][0], b) +info[idx][0])
    
        if b + info[idx][1] < m:
            ret = min(ret, dfs(idx+1,a,b+info[idx][1]))
            
        dp[idx][a][b] = ret
        return ret
    
    ans = dfs(0,0,0)
    if ans == 987654321:
        return -1
    return ans