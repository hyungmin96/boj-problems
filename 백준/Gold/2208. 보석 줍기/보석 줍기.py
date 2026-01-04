import sys
sys.setrecursionlimit(100001)

input = sys.stdin.readline

N, M = map(int, input().split())

arr = []
sum_list = [0] + ([0] * N)
dp = [-float('inf')] * (N+1)
cur, tmp = 0, 0
for i in range(N):
    arr.append(int(input()))
    cur += arr[i]
    sum_list[i+1] += cur
    if i < M:
        tmp = cur

dp[M-1] = tmp
ans = max(0, dp[M-1])
for i in range(M, N):
    dp[i] = max(dp[i-1] + arr[i], sum_list[i+1]-sum_list[i-M+1])
    ans = max(ans, dp[i])

print(ans)