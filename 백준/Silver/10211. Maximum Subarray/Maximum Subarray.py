import sys
sys.setrecursionlimit(100001)

input = sys.stdin.readline

ans = []
T = int(input())
for _ in range(T):
    N = int(input())
    arr = list(map(int, input().split()))

    def dfs(idx):
        if idx == len(arr):
            return -float('inf')
        
        cur, res = 0, -float('inf')
        for i in range(idx, len(arr)):
            cur += arr[i]
            res = max(res, cur)

        res = max(res, dfs(idx + 1))
        return res

    ans.append(dfs(0))

print("\n".join(map(str, ans)))