## 1. 1시간 기다려서 알고력 올리기
## 2. 1시간 기다려서 코딩력 올리기
## 3. 문제풀기
import sys
sys.setrecursionlimit(1000)
def solution(alp, cop, problems):
    answer = 0
    dp = [[100001] * 231 for _ in range(231)]
    max_alp, max_cop = -1,-1
    for p in problems:
        max_alp = max(max_alp, p[0])
        max_cop = max(max_cop, p[1])
    
    dp[max_alp][max_cop] = 0
    
    alp = min(alp, max_alp)
    cop = min(cop, max_cop)
    def dfs(cur_alp, cur_cop):
        cur_alp = min(cur_alp, max_alp)
        cur_cop = min(cur_cop, max_cop)
        
        if dp[cur_alp][cur_cop] != 100001:
            return dp[cur_alp][cur_cop]

        dp[cur_alp][cur_cop] = 100002
        dp[cur_alp][cur_cop] = min(dp[cur_alp][cur_cop], dfs(cur_alp+1, cur_cop) + 1)
        dp[cur_alp][cur_cop] = min(dp[cur_alp][cur_cop], dfs(cur_alp, cur_cop+1) + 1)
        
        for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems:
            if alp_req <= cur_alp and cop_req <= cur_cop:
                dp[cur_alp][cur_cop] = min(dp[cur_alp][cur_cop], dfs(cur_alp+alp_rwd, cur_cop+cop_rwd) + cost)
        
        return dp[cur_alp][cur_cop]
    
    return dfs(alp, cop)
