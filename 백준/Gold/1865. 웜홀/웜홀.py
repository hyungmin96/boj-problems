import sys, heapq
input = sys.stdin.readline

T = int(input())

def bellmanFord():
    dp = [0] * (N + 1)
    for i in range(N + 1):
        for start in range(1, N+1):
            for next_node, next_cost in nodes[start]:
                if dp[next_node] > dp[start] + next_cost:
                    dp[next_node] = dp[start] + next_cost
                    if i == N:
                        return True
    return False

ans = []
for _ in range(T):
    N, M, W = map(int, input().split())
    nodes = [[] for _ in range(N+1)]

    for _ in range(M):
        s, e, c = map(int, input().split())
        nodes[s].append((e, c))
        nodes[e].append((s, c))

    for _ in range(W):
        s, e, c = map(int, input().split())
        nodes[s].append((e, -c))

    if bellmanFord():
        ans.append("YES")
    else:
        ans.append("NO")
    
print("\n".join(map(str, ans)))

