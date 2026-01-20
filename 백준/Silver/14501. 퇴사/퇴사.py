import sys
input = sys.stdin.readline

N = int(input())
arr = [[]]
for i in range(N):
    time, cost = map(int, input().strip().split())
    arr.append((time, cost))

dp = [-1] * (N+1)
def dfs(depth):
    if depth == N+1:
        return 0

    if depth > N:
        return -987654321
    
    if dp[depth] != -1:
        return dp[depth]
    
    time = arr[depth][0]
    cost = arr[depth][1]

    res = dfs(depth+time) + cost
    res = max(res, dfs(depth+1))

    dp[depth] = res
    return res

print(dfs(1))