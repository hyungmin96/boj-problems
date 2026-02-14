import sys
input = sys.stdin.readline

N = int(input().strip())
price = list(map(int, input().strip().split()))
M = int(input().strip())
dp = [-1] * 51

def dfs(m):
    if dp[m] != -1:
        return dp[m]
    
    res = 0
    for i in range(N-1, -1, -1):
        if m - price[i] >= 0:
            res = max(res, dfs(m-price[i]) * 10 + i)

    dp[m] = res
    return dp[m]

print(dfs(M))