import sys
input = sys.stdin.readline

N = int(input())
grid = [list(map(int, input().split())) for _ in range(N)]

dp = [[-1] * 2501 for _ in range(N)]
dp[0][0] = grid[0][0]

ans = dp[0][0]
for i in range(N-1):
    for j in range(len(grid[i])):
        dp[i+1][j] = max(dp[i+1][j], dp[i][j] + grid[i+1][j])
        dp[i+1][j+1] = max(dp[i+1][j+1], dp[i][j] + grid[i+1][j+1])
        ans = max(ans, dp[i+1][j], dp[i+1][j+1])

print(ans)