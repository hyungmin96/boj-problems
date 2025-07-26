def solution(info, n, m):
    answer = 0
    dp = [[float('inf')] * (len(info) + 1) for _ in range(m)]
    dp[0][0] = 0
    
    
    for i in range(m):
        for j in range(1, len(info) + 1):
            cost_a, cost_b = info[j-1]
            
            if i < cost_b:
                dp[i][j] = min(dp[i][j], dp[i][j-1]+cost_a)
            elif i - cost_b >= 0:
                dp[i][j] = min(dp[i][j], dp[i-cost_b][j-1], dp[i][j-1]+cost_a)
            
            if i-1 >= 0:
                dp[i][j] = min(dp[i][j], dp[i-1][j])
                
    if dp[m-1][len(info)] >= n:
        return -1
    
    return dp[m-1][len(info)]