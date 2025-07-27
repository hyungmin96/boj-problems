def solution(info, n, m):
    answer = 987654321
    v = set()
    def dfs(depth, a_cnt, b_cnt, n, m, info, v):
        nonlocal answer
        if a_cnt >= n or b_cnt >= m:
            return 
        
        if depth >= len(info):
            answer = min(a_cnt, answer)
            return
        
        if (depth, a_cnt, b_cnt) in v:
            return
        
        v.add((depth, a_cnt, b_cnt))
        dfs(depth + 1, a_cnt + info[depth][0], b_cnt, n, m, info, v)
        dfs(depth + 1, a_cnt, b_cnt + info[depth][1], n, m, info, v)
    
        return
    
    dfs(0, 0, 0, n, m, info, v)
    if answer == 987654321:
        answer = -1
    return answer