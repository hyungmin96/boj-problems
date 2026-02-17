import sys
sys.setrecursionlimit(100000)
input = sys.stdin.readline

N = int(input().strip())
nodes, edges, v, dist = [[] for _ in range(N)], [], [False] * N, [0] * N

def dfs(node, e1, e2):
    cur, dist[node] = node, 0
    for nxt in nodes[node]:
        nxt_node, nxt_cost = nxt
        if nxt_node == node or v[nxt_node]:
            continue

        if (node == e1 and nxt_node == e2) or (node == e2 and nxt_node == e1):
            continue
        
        v[nxt_node] = True
        tmp = dfs(nxt_node, e1, e2)
        if dist[nxt_node] + nxt_cost > dist[node]:
            dist[node] = dist[nxt_node] + nxt_cost
            cur = tmp

    return cur

for _ in range(N-1):
    s, e, c = map(int, input().strip().split())
    nodes[s].append((e, c))
    nodes[e].append((s, c))

    edges.append((s, e, c))

ans = 0
for edge in edges:
    e1, e2, c = edge

    # 분리된 1번 트리의 지름 구하기
    v = [False] * N
    v[e1] = True
    n1 = dfs(e1, e1, e2)

    v = [False] * N
    v[n1] = True
    n2 = dfs(n1, e1, e2)

    # 분리된 2번 트리의 지름 구하기
    v = [False] * N
    v[e2] = True
    n3 = dfs(e2, e1, e2)

    v = [False] * N
    v[n3] = True
    n4 = dfs(n3, e1, e2)

    ans = max(ans, dist[n1] + dist[n3] + c)

print(ans)