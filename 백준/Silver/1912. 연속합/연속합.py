import sys
input = sys.stdin.readline

N = int(input())
arr = list(map(int, input().strip().split()))
dp = [-987654321] * (N+1)

dp[0] = arr[0]
for i in range(1, len(arr)):
    dp[i] = max(arr[i], dp[i-1] + arr[i])

print(max(dp))