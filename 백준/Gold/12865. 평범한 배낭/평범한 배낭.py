import sys
input = sys.stdin.readline

N, K = map(int, input().split())
bags = [list(map(int, input().split())) for _ in range(N)]
dp = [0] * 100001
for w, v in bags:
    for i in range(K, w-1, -1):
        dp[i] = max(dp[i], dp[i-w] + v)


print(dp[K])