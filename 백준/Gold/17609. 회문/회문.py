import sys
sys.setrecursionlimit(100000)
input = sys.stdin.readline

def dfs(diff_cnt, depth, l, r, val):
    if diff_cnt >= 2:
        return 2
    
    if l == r or depth > len(val) // 2:
        return diff_cnt
    
    res = 987654321
    if val[l] != val[r]:
        res = min(dfs(diff_cnt+1, depth+1, l+1, r, val), dfs(diff_cnt+1,depth+1, l, r-1, val))
    else:
        res = min(res, dfs(diff_cnt, depth+1, l+1,r-1,val))

    return res

ans = []
N = int(input().strip())
for _ in range(N):
    val = input().strip()
    ans.append(dfs(0, 0, 0, len(val)-1, val))

print("\n".join(map(str, ans)))