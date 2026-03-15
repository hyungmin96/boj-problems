import sys
input = sys.stdin.readline

arr = list(map(int, input().strip().split()))
board = []

board.append([0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40])
board.append([0,2,4,6,8,10,13,16,19,25,30,35,40])
board.append([0,2,4,6,8,10,12,14,16,18,20,22,24,25,30,35,40])
board.append([0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,28,27,26,25,30,35,40])

v = [[False] * 30 for _ in range(5)]

def check(pos_idx, nxt):

    if nxt >= len(board[pos_idx]):
        return False
    
    if v[pos_idx][nxt]:
        return True
    
    if (pos_idx == 1 and nxt == 9) or (pos_idx == 2 and nxt == 13) or (pos_idx == 3 and nxt == 19):
        return v[1][9] or v[2][13] or v[3][19]
    elif (pos_idx == 1 and nxt == 10) or (pos_idx == 2 and nxt == 14) or (pos_idx == 3 and nxt == 20):
        return v[1][10] or v[2][14] or v[3][20]
    elif (pos_idx == 1 and nxt == 11) or (pos_idx == 2 and nxt == 15) or (pos_idx == 3 and nxt == 21):
        return v[1][11] or v[2][15] or v[3][21]
    elif (pos_idx == 0 and nxt == 20)or (pos_idx == 1 and nxt == 12) or (pos_idx == 2 and nxt == 16) or (pos_idx == 3 and nxt == 22):
        return v[0][20] or v[1][12] or v[2][16] or v[3][22]
    
    return False

def dfs(idx, horse, pos):
    if idx >= len(arr):
        return 0
    
    res = 0
    for i in range(len(horse)):
        tmp_pos = pos[:]
        tmp_horse = horse[:]

        if tmp_horse[i] >= len(board[tmp_pos[i]]):
            continue

        nxt = tmp_horse[i] + arr[idx]
        if tmp_pos[i] == 0:
            if nxt == 5:
                tmp_pos[i] = 1
            elif nxt == 10:
                tmp_pos[i] = 2
            elif nxt == 15:
                tmp_pos[i] = 3
        
        if not check(tmp_pos[i], nxt):
            v[pos[i]][horse[i]] = False
            v[tmp_pos[i]][nxt] = True
            tmp_horse[i] = nxt
            if nxt < len(board[tmp_pos[i]]):
                res = max(res, dfs(idx+1, tmp_horse, tmp_pos) + board[tmp_pos[i]][nxt])
            else:
                res = max(res, dfs(idx+1, tmp_horse, tmp_pos))
                          
            tmp_horse[i] = horse[i]
            v[tmp_pos[i]][nxt] = False
            v[pos[i]][horse[i]] = True

    return res

print(dfs(0, [0,0,0,0], [0,0,0,0]))