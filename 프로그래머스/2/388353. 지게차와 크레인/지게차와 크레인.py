from collections import deque
def solution(storage, requests):
    
    N = len(storage)
    M = len(storage[0])
    answer = N * M
    dirs = [[-1,0],[0,1],[1,0],[0,-1]]
    for r in requests:
        if len(r) == 1:
            tmp = []
            for i in range(N):
                for j in range(M):
                    if storage[i][j] == r:
                        if i == 0 or j == 0 or i == N - 1 or j == M - 1:
                            tmp.append((i, j))
                        else:
                            q = deque()
                            v = [[False] * M for _ in range(N)]
                            v[i][j] = True
                            q.append((i, j))
                            
                            while q:
                                cur_row, cur_col = q.popleft()
                                if cur_row == 0 or cur_col == 0 or cur_row == N - 1 or cur_col == M - 1:
                                    tmp.append((i, j))
                                    break
                                for d in range(4):
                                    nr = cur_row + dirs[d][0]
                                    nc = cur_col + dirs[d][1]
                                    if storage[nr][nc] == '.' and not v[nr][nc]:
                                        v[nr][nc] = True
                                        q.append((nr, nc))
                        
            for row, col in tmp:
                tmp_char_list = list(storage[row])
                tmp_char_list[col] = '.'
                answer -= 1
                storage[row] = "".join(tmp_char_list)
        else:
            for i in range(N):
                for j in range(M):
                    if storage[i][j] == r[0]:
                        answer -= 1
                        tmp_char_list = list(storage[i])
                        tmp_char_list[j] = '.'
                        storage[i] = "".join(tmp_char_list)
    return answer