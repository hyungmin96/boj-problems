# J, K, M 순서로 진행

N, M = map(int, input().strip().split())
relation = []

for i in range(N):
    relation.append(list(map(int, input().strip().split())))

info = [
    [],
    list(map(int, input().split())),
    list(map(int, input().split()))
]

w_cnt = [0] * 3
v = [False] * N
idx_list = [0, 0, 0]
def dfs(depth, pre, p1, p2, p3):
    if w_cnt[0] == M:
        return True
    
    if w_cnt[1] == M or w_cnt[2] == M:
        return False
    
    if idx_list[0] >= N or idx_list[1] >= 20 or idx_list[2] >= 20:
        return False
    
    if p1 != 0 and p2 != 0:
        if relation[info[p1][idx_list[p1]]-1][info[p2][idx_list[p2]]-1] == 2 or ((p1 > p2) and relation[info[p1][idx_list[p1]]-1][info[p2][idx_list[p2]]-1] == 1):
            idx_list[p1] += 1
            idx_list[p2] += 1
            w_cnt[p1] += 1
            if dfs(depth + 1, pre, p1, p3, p2):
                return True
            w_cnt[p1] -= 1
            idx_list[p1] -= 1
            idx_list[p2] -= 1
        else:
            w_cnt[p2] += 1
            idx_list[p1] += 1
            idx_list[p2] += 1
            if dfs(depth + 1, pre, p2, p3, p1):
                return True
            w_cnt[p2] -= 1
            idx_list[p1] -= 1
            idx_list[p2] -= 1
    else:
        if p1 == 0:
            for i in range(N):
                if v[i]: continue
                v[i] = True
                if relation[i][info[p2][idx_list[p2]]-1] == 2 or ((p1 > p2) and relation[i][info[p2][idx_list[p2]]-1] == 1):
                    idx_list[p1] += 1
                    idx_list[p2] += 1
                    w_cnt[p1] += 1
                    if dfs(depth+1, i, p1, p3, p2): return True
                    w_cnt[p1] -= 1
                    idx_list[p1] -= 1
                    idx_list[p2] -= 1
                else:
                    w_cnt[p2] += 1
                    idx_list[p1] += 1
                    idx_list[p2] += 1
                    if dfs(depth+1, i, p2, p3, p1): return True
                    w_cnt[p2] -= 1
                    idx_list[p1] -= 1
                    idx_list[p2] -= 1
                v[i] = False
        else:
            for i in range(N):
                if v[i]: continue
                v[i] = True
                if relation[info[p1][idx_list[p1]]-1][i] == 2 or ((p1 > p2) and relation[info[p1][idx_list[p1]]-1][i] == 1):
                    idx_list[p1] += 1
                    idx_list[p2] += 1
                    w_cnt[p1] += 1
                    if dfs(depth+1, i, p1, p3, p2): return True
                    w_cnt[p1] -= 1
                    idx_list[p1] -= 1
                    idx_list[p2] -= 1
                else:
                    idx_list[p1] += 1
                    idx_list[p2] += 1
                    w_cnt[p2] += 1
                    if dfs(depth+1, i, p2, p3, p1): return True
                    w_cnt[p2] -= 1
                    idx_list[p1] -= 1
                    idx_list[p2] -= 1
                v[i] = False
    return False

ret = dfs(0, -1, 0, 1, 2)
print(1 if ret else 0)

