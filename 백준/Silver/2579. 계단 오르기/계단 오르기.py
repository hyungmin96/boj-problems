import sys
sys.setrecursionlimit(10000)
input = sys.stdin.readline

N = int(input().strip())
arr = [int(input().strip()) for _ in range(N)]
dp = [[-1] * len(arr) for _ in range(3)]
def dfs(idx, cnt):
    if idx >= len(arr):
        return -987654321
    
    if idx == len(arr)-1:
        return arr[len(arr)-1]
    
    if dp[cnt][idx] != -1:
        return dp[cnt][idx]
    
    res = 0
    if cnt + 1 < 3:
        res = max(res, dfs(idx+1,cnt+1) + arr[idx])
        
    res = max(res, dfs(idx+2, 1) + arr[idx])

    dp[cnt][idx] = res
    return res

if N == 1:
    print(arr[0])
else:
    print(max(dfs(0,1), dfs(1,1)))