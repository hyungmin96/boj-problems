import sys
input = sys.stdin.readline
N, M, X = map(int, input().strip().split())

up_list = [[] for _ in range(N+1)]
down_list = [[] for _ in range(N+1)]

for _ in range(M):
    p, c = map(int, input().split())
    up_list[c].append(p)
    down_list[p].append(c)

dp = [[0] * 2 for _ in range(N+1)]

def dfs(node, cur_list, v):
    if len(cur_list[node]) == 0:
        return 0
    
    cnt = 0
    for next_node in cur_list[node]:
        if next_node in v: continue
        v.add(next_node)
        cnt += dfs(next_node, cur_list, v) + 1

    return cnt

print((dfs(X, up_list, set()) + 1), (N - dfs(X, down_list, set())))