import sys
input = sys.stdin.readline

C, N = map(int, input().strip().split())
arr = [list(map(int, input().strip().split())) for _ in range(N)]

dp = [-1] * 1001 # (비용, 얻은 고객 수)
def dfs(cur):
    if cur >= C:
        return 0

    if dp[cur] != -1:
        return dp[cur]
    
    res = 987654321    
    for i in range(N):
        res = min(res, dfs(cur + arr[i][1]) + arr[i][0])

    dp[cur] = res
    return res

print(dfs(0))