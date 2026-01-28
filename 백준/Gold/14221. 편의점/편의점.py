import sys, heapq
input = sys.stdin.readline

N, M = map(int, input().strip().split())

nodes = [[] for _ in range(N+1)]
for _ in range(M):
    s, e, c = map(int, input().strip().split())
    nodes[s].append((e, c))
    nodes[e].append((s, c))

input()
home = set(map(int, input().strip().split()))
store = set(map(int, input().strip().split()))

dp = [float('inf')] * (N+1)
def bfs():
    pq = []

    for s in store:
        dp[s] = 0
        heapq.heappush(pq, (0, s))

    while pq:
        cost, cur_node = heapq.heappop(pq)
        if cur_node in home:
            return cur_node
        
        for nxt, nxt_value in nodes[cur_node]:
            if dp[nxt] > cost + nxt_value:
                dp[nxt] = cost + nxt_value
                heapq.heappush(pq, (cost + nxt_value, nxt))

    return -1
    
print(bfs())