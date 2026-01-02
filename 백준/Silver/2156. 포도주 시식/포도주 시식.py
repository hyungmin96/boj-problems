import sys
input = sys.stdin.readline
sys.setrecursionlimit(100001)
N = int(input())
wine_list = []
for _ in range(N):
    wine_list.append(int(input()))

dp = [[-1] * 3 for _ in range(N+1)]
def dfs(depth, cnt):
    if depth == len(wine_list):
        return 0

    if dp[depth][cnt] != -1:
        return dp[depth][cnt]
    
    res = 0
    if cnt + 1 < 3:    
        # 해당 와인 마시기
        res = dfs(depth+1, cnt + 1) + wine_list[depth]
    # 넘어가기  
    res = max(res, dfs(depth+1, 0))

    dp[depth][cnt] = max(res, dp[depth][cnt])
    return dp[depth][cnt]

print(dfs(0,0))