import sys
input = sys.stdin.readline
sys.setrecursionlimit(100001)

N, M = map(int, input().split())
mem = list(map(int, input().split()))
cost = list(map(int, input().split()))

dp = [[-1] * (10001) for _ in range(N+1)]

## 앱, 비용 : 얻을 수 있는 최대 메모리 값
def dfs(depth, cur):
    if depth == N:
        return 0
    
    if dp[depth][cur] != -1:
        return dp[depth][cur]
    
    dp[depth][cur] = 0
    if cur >= cost[depth]:
        dp[depth][cur] = max(dp[depth][cur], dfs(depth+1, cur - cost[depth]) + mem[depth])
    dp[depth][cur] = max(dp[depth][cur], dfs(depth+1, cur))

    return dp[depth][cur]

for i in range(10001):
    if dfs(0, i) >= M:
        print(i)
        break