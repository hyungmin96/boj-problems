import sys
sys.setrecursionlimit(1000000)
def solution(alp, cop, problems):
    
    ta, tc = 0, 0 # 목표 알고력, 코딩력
    for i in range(len(problems)):
        alp_req, cop_req, alp_rwd, cop_rwd, cost = problems[i]
        ta = max(ta, alp_req)
        tc = max(tc, cop_req)
        
    alp, cop = min(alp, ta), min(cop, tc)
    dp = [[float('inf')] * 181 for _ in range(181)]

    def dfs(alp, cop):
        alp, cop = min(alp, ta), min(cop, tc)
        if alp >= ta and cop >= tc:
            return 0
        if dp[alp][cop] != float('inf'):
            return dp[alp][cop]

        # 1 기다리고 alp/cop 증가
        res = 1000000000
        dp[alp][cop] = res
        res = min(dfs(alp + 1, cop) + 1, dfs(alp, cop + 1) + 1)
        for i in range(len(problems)):
            alp_req, cop_req, alp_rwd, cop_rwd, cost = problems[i]
            if alp >= alp_req and cop >= cop_req:
                res = min(res, dfs(alp + alp_rwd, cop + cop_rwd) + cost)
        
        dp[alp][cop] = res
        return res
    
    return dfs(alp, cop)
