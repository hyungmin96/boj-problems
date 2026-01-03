import sys
sys.setrecursionlimit(100001)

input = sys.stdin.readline

T = int(input())
for _ in range(T):
    N = int(input())
    dp = [0] * N
    arr = list(map(int, input().split()))
    dp[0] = arr[0]

    for i in range(1, N):
        dp[i] = max(dp[i-1] + arr[i], arr[i])

    print(max(dp))