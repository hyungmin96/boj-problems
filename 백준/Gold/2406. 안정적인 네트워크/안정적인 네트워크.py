import sys
input = sys.stdin.readline

N, M = map(int, input().strip().split())
p, cost = [i for i in range(N+1)], []
def find(a):
    if p[a] == a:
        return a
    
    p[a] = find(p[a])
    return p[a]

def union(a, b):
    a = find(a)
    b = find(b)

    if a < b:
        p[b] = a
    else:
        p[a] = b

for _ in range(M):
    n1, n2 = map(int, input().strip().split())
    if n1 == 1 or n2 == 1:
        continue

    union(n1, n2)

for i in range(1, N+1):
    cost.append(list(map(int, input().strip().split())))

edges = []
for i in range(2, N+1):
    for j in range(i+1, N+1):
        if i == j: continue
        edges.append((cost[i-1][j-1], i, j))

edges.sort()
cnt, sum_cost, connection_node_list = 0, 0, []

for e in edges:
    cur_cost, n1, n2 = e

    if find(n1) != find(n2):
        cnt += 1
        sum_cost += cur_cost
        connection_node_list.append((n1, n2))
        union(n1, n2)


if cnt > 0:
    print(sum_cost, cnt)
    for i in range(len(connection_node_list)):
        a, b = connection_node_list[i]
        print(a, b)
else:
    print(0, 0)
