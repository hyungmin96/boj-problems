import sys, heapq
input = sys.stdin.readline

N, M = map(int, input().split())

nodes = []
complete_search_list = set()
dp = [[float('inf')] * N for _ in range(N)]

for i in range(N):
    nodes.append(list(map(int, input().strip().split())))

def dijsktra(s):
    pq = []
    dp[s][s] = 0
    heapq.heappush(pq, (s, 0)) # 현재노드, 비용

    while pq:
        cur_node, cur_cost = heapq.heappop(pq)
        for i in range(N):
            if i == cur_node: continue
            if dp[s][i] > cur_cost + nodes[cur_node][i]:
                dp[s][i] = cur_cost + nodes[cur_node][i]
                heapq.heappush(pq, (i, cur_cost + nodes[cur_node][i]))

ans = []
for i in range(M):
    s, e, c = map(int, input().split())
    s -= 1
    e -= 1
    if not s in complete_search_list:
        complete_search_list.add(s)
        dijsktra(s)

    if dp[s][e] > c:
        ans.append("Stay here")
    else:
        ans.append("Enjoy other party")

print("\n".join(map(str, ans)))