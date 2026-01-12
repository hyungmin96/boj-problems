def solution(info, n, m):
    dp = [[-1] * m for _ in range(len(info))]
    def dfs(depth, m_cnt):
        if depth == len(info):
            return 0
        
        if dp[depth][m_cnt] != -1:
            return dp[depth][m_cnt]
        
        res = dfs(depth + 1, m_cnt) + info[depth][0]
        if m_cnt + info[depth][1] < m:
            res = min(res, dfs(depth + 1, m_cnt + info[depth][1]))
        
        dp[depth][m_cnt] = res
        return res
    
    ans = dfs(0, 0)
    return -1 if ans >= n else ans