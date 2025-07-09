import sys

N, M = map(int, input().split(" "))
mem = list(map(int, input().split()))
cost = list(map(int, input().split()))

dp = [[0 for j in range(10001)] for i in range(N + 1)]

answer = 9876543210
for i in range(1, N + 1):
    cur_mem = mem[i-1]
    cur_cost = cost[i-1]
    for j in range(0, 10001):
        if(j >= cur_cost):
            dp[i][j] = max(dp[i][j], dp[i - 1][j - cur_cost] + cur_mem)
            
        dp[i][j] = max(dp[i][j], dp[i-1][j])
        if(dp[i][j] >= M):
            answer = min(answer, j)

print(answer)