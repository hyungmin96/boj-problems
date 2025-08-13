def solution(alp, cop, problems):
    answer = float('INF')
    
    max_alp, max_cop = alp, cop
    for i in range(len(problems)):
        alp_req, cop_req, alp_rwd, cop_rwd, cost = problems[i]
        
        max_alp = max(max_alp, alp_req)
        max_cop = max(max_cop, cop_req)
        
    dp = [[float('INF')] * 301 for _ in range(301)]
    dp[alp][cop] = 0
    
    for i in range(alp, 151):
        for j in range(cop, 151):
            for k in range(len(problems)):
                dp[i+1][j] = min(dp[i+1][j], dp[i][j] + 1) #cop
                dp[i][j+1] = min(dp[i][j+1], dp[i][j] + 1) #alp
                alp_req, cop_req, alp_rwd, cop_rwd, cost = problems[k]
                
                if alp_req <= i and cop_req <= j:
                    next_alp = min(i+alp_rwd, max_alp)
                    next_cop = min(j+cop_rwd, max_cop)
                    dp[next_alp][next_cop] = min(dp[next_alp][next_cop], dp[i][j] + cost)
    
    return dp[max_alp][max_cop]