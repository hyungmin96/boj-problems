import sys
input = sys.stdin.readline
sys.setrecursionlimit(100001)

N, T = map(int, input().split())
study_list = []
for _ in range(N):
    study_list.append(list(map(int, input().split())))

dp = [[-1] * 10001 for _ in range(N+1)]
def dfs(depth, time):
    if depth == N:
        return 0
    
    if dp[depth][time] != -1:
        return dp[depth][time]
    
    t, s, res = study_list[depth][0], study_list[depth][1], 0
    if time >= t:
        res = max(res, dfs(depth+1, time - t) + s)

    res = max(res, dfs(depth + 1, time))
    dp[depth][time] = max(dp[depth][time], res)
    return dp[depth][time]

print(dfs(0,T))