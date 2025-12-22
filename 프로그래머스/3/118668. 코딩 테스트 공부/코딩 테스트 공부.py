# 알고/코딩력이 주어짐.
# 주어진 모든 문제를 풀 수 있는 알고/코딩력을 얻게되는데까지 걸리는 최소시간.
import sys
sys.setrecursionlimit(1000000)
def solution(alp, cop, problems):

    max_alp, max_cop = -1, -1
    dp = [[100001] * 181 for _ in range(181)]
    
    for alp_req, cop_req, _, _, _ in problems:
        max_alp = max(alp_req, max_alp)
        max_cop = max(cop_req, max_cop)
    
    alp = min(alp, max_alp)
    cop = min(cop, max_cop)
    
    def dfs(alp_cur, cop_cur):
        alp_cur = min(alp_cur, max_alp)
        cop_cur = min(cop_cur, max_cop)
        
        if alp_cur >= max_alp and cop_cur >= max_cop:
            return 0

        if dp[alp_cur][cop_cur] != 100001:
            return dp[alp_cur][cop_cur]
        
        dp[alp_cur][cop_cur] = 100002
        res = 100002
        # 코딩력 + 1
        res = min(res, dfs(alp_cur, cop_cur+1) + 1)
        # 알고력 + 1
        res = min(res, dfs(alp_cur+1, cop_cur) + 1)
        for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems:
            if alp_cur >= alp_req and cop_cur >= cop_req:
                res = min(res, dfs(alp_cur + alp_rwd, cop_cur + cop_rwd) + cost)
        
        dp[alp_cur][cop_cur] = min(dp[alp_cur][cop_cur], res)
        return dp[alp_cur][cop_cur]
    
    return dfs(alp, cop)