import sys
input = sys.stdin.readline
sys.setrecursionlimit(10000)
mod = 1000000

value = input().strip()
dp = [-1] * len(value)

def dfs(idx):
    if idx == len(value):
        return 1
    
    if dp[idx] != -1:
        return dp[idx]
    
    res = 0
    if idx < len(value) and 1 <= int(value[idx]) <= 26:
        res += dfs(idx+1) % 1000000
    if idx + 1 < len(value) and value[idx] != '0' and 1 <= int(value[idx:idx+2]) <= 26:
        res += dfs(idx+2) % 1000000

    dp[idx] = res % 1000000
    return res % 1000000

print(dfs(0))