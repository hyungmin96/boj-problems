import sys
sys.setrecursionlimit(10000)
input = sys.stdin.readline

N = int(input())
dp = [[-1] * N for _ in range(3)]  
arr = [int(input().strip()) for _ in range(N)]

def dfs(idx, cnt):
    
    if idx > N-1 or cnt >= 3:
        return -987654321
    
    if idx == len(arr)-1:
        return arr[idx]
    
    if dp[cnt][idx] != -1:
        return dp[cnt][idx]
    
    res = max(dfs(idx+1, cnt+1) + arr[idx], dfs(idx + 2, 1) + arr[idx])
    dp[cnt][idx] = res
    return res

if N == 1:
    print(arr[0])
else:
    print(max(dfs(0, 1), dfs(1, 1)))

