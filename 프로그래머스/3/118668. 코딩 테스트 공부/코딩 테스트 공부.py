def solution(alp, cop, problems):
    dp = [[100010] * 222 for _ in range(222)]
    
    max_alp,max_cop = -1,-1
    for alp_req, cop_req, _,_,_ in problems:
        max_alp = max(max_alp, alp_req)
        max_cop = max(max_cop, cop_req)
    
    if max_alp <= alp and max_cop <= cop:
        return 0
    
    alp = min(alp, max_alp)
    cop = min(cop, max_cop)
    
    dp[alp][cop] = 0
    for i in range(alp, max_alp+1):
        for j in range(cop, max_cop+1):
            dp[i+1][j] = min(dp[i+1][j], dp[i][j] + 1)
            dp[i][j+1] = min(dp[i][j+1], dp[i][j] + 1)
            for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems:
                if i >= alp_req and j >= cop_req:
                    next_alp = min(i + alp_rwd, max_alp)
                    next_cop = min(j + cop_rwd, max_cop)
                    dp[next_alp][next_cop] = min(dp[next_alp][next_cop], dp[i][j] + cost)
                
    return dp[max_alp][max_cop]