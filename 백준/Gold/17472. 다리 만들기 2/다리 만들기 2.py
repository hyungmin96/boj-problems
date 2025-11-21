import sys
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split(" "))
grid = [[0] * M for _ in range(N)]

for i in range(N):
    grid[i] = list(map(int, input().split(" ")))

def is_out_range(r, c):
    global N, M
    return r < 0 or c < 0 or r >= N or c >= M

def grouping_and_get_edges(r, c, grid, map_edge_dict, group_idx, v):
    dirs = [[-1,0],[0,1],[1,0],[0,-1]]

    q = deque()
    q.append((r,c))
    v[r][c] = True

    if not group_idx in map_edge_dict:
        map_edge_dict[group_idx] = []
    
    while q:
        cur_r, cur_c = q.popleft()
        grid[cur_r][cur_c] = group_idx
        is_edge = False
        for d in range(4):
            nr = cur_r + dirs[d][0]
            nc = cur_c + dirs[d][1]

            if not is_out_range(nr,nc):
                if grid[nr][nc] > 0 and not v[nr][nc]:
                    v[nr][nc] = True
                    q.append((nr, nc))
                    grid[nr][nc] = group_idx
                elif grid[nr][nc] == 0:
                    is_edge = True
                
        if is_edge:
            map_edge_dict[group_idx].append((cur_r, cur_c))
            
    return False

def get_dp_table(grid):
    dirs = [[-1,0],[0,1],[1,0],[0,-1]]
    edge_list = []
    for key in map_edge_dict:
        for r, c in map_edge_dict[key]:
            for d in range(4):
                cur_r, cur_c, island_len = r, c, 0
                while True:
                    nr = cur_r + dirs[d][0]
                    nc = cur_c + dirs[d][1]
                    
                    if is_out_range(nr, nc):
                        break

                    if grid[nr][nc] == 0:
                        cur_r = nr
                        cur_c = nc
                    else:
                        if grid[nr][nc] != key and island_len >= 2:
                            edge_list.append((island_len, key, grid[nr][nc]))
                        break
                    
                    island_len += 1
    return edge_list

group_idx = 0
map_edge_dict = {}
v = [[False] * M for _ in range(N)]

for r in range(N):
    for c in range(M):
        if v[r][c] or grid[r][c] == 0: continue
        group_idx += 1
        grouping_and_get_edges(r, c, grid, map_edge_dict, group_idx, v)

edge_list = get_dp_table(grid)
edge_list.sort()

parent = [i for i in range(group_idx+1)]
def find(node, parent):
    if parent[node] != node:
        parent[node] = find(parent[node], parent)
    
    return parent[node]

def union(a, b, parent):
    a = find(a, parent)
    b = find(b, parent)

    if a == b:
        return False
    
    if a > b:
        parent[a] = b
    else:
        parent[b] = a

    return True

ans, connected = 0, 0
for cost, start, dest in edge_list:
    if union(start, dest, parent):
        connected += 1
        ans += cost


if connected == group_idx - 1:
    print(ans) 
else: print(-1)