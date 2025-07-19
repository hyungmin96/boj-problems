import sys
input = sys.stdin.readline

def print_grid(red, blue):
    for row in red:
        print(row)
    print("========")
    for col in blue:
        print(col)

def get_tiles_num(red, blue):
    cnt = 0
    for r in range(6):
        for c in range(4):
            if red[r][c] == 1:
                cnt += 1

    for r in range(4):
        for c in range(6):
            if blue[r][c] == 1:
                cnt += 1
    return cnt

def put(t, x, y, red, blue):
    r, c = 0, 0
    if t == 1:
        while r+1 < 6 and red[r+1][y] == 0:
            r += 1
        while c+1 < 6 and blue[x][c+1] == 0:
            c += 1

        red[r][y] = 1
        blue[x][c] = 1
    elif t == 2:
        while r+1 < 6 and red[r+1][y] == 0 and red[r+1][y+1] == 0:
            r += 1
        while c+2 < 6 and blue[x][c+1] == 0 and blue[x][c+2] == 0:
            c += 1
        
        red[r][y] = 1
        red[r][y+1] = 1
        blue[x][c] = 1
        blue[x][c+1] = 1
    else: 
        #ㅁ
        #ㅁ
        while r+2 < 6 and red[r+1][y] == 0 and red[r+2][y] == 0:
            r += 1
        while c+1 < 6 and blue[x][c+1] == 0 and blue[x+1][c+1] == 0:
            c += 1

        red[r][y] = 1
        red[r+1][y] = 1
        blue[x][c] = 1
        blue[x+1][c] = 1

def check_line(red, blue):
    score = 0

    for r in range(6):
        is_full = True
        for c in range(4):
            if red[r][c] == 0:
                is_full = False
                break

        if is_full:
            score += 1
            for i in range(r, 0, -1):
                for j in range(4):
                    red[i][j] = red[i-1][j]

    for c in range(6):
        is_full = True
        for r in range(4):
            if blue[r][c] == 0:
                is_full = False
                break

        if is_full:
            score += 1
            for i in range(c, 0, -1):
                for j in range(4):
                    blue[j][i] = blue[j][i-1]
                    
    return score

def check_section(red, blue):
    red_cnt, blue_cnt = 0, 0
    for r in range(2):
        for c in range(4):
            if red[r][c] == 1:
                red_cnt += 1
                break

    for c in range(2):
        for r in range(4):
            if blue[r][c] == 1:
                blue_cnt += 1
                break

    if red_cnt > 0:
        for r in range(5, 1, -1):
            for c in range(4):
                red[r][c] = red[r-red_cnt][c]

        for r in range(2):
            for c in range(4):
                red[r][c] = 0

    if blue_cnt > 0:
        for c in range(5, 1, -1):
            for r in range(4):
                blue[r][c] = blue[r][c-blue_cnt]

        for c in range(2):
            for r in range(4):
                blue[r][c] = 0

N = int(input())
cmd = []
for i in range(N):
    lst = list(map(int, input().split()))
    cmd.append(lst)

red = [[0] * 4 for _ in range(6)]
blue = [[0] * 6 for _ in range(4)]

answer = 0
for c in cmd:
    t, x, y = c
    put(t, x, y, red, blue)
    answer += check_line(red, blue)
    check_section(red, blue)

print(answer)
print(get_tiles_num(red, blue))
# print(print_grid(red, blue))

#완료 # 도형 위치시키기(떨어트리기)
# 완료 # red(행)과 col(열)이 완벽한 하나의 줄을 이루면 삭제 후 삭제된 줄 수 만큼 더하기
# 완료 # 삭제된 행과 열 만큼 위 도형 떨어트리기(쭉 떨어지는 거 x)
# 완료 # 특별구역에 들어간 행과 열의 크기만큼 맨 밑(옆)의 줄 삭제, 점수는 안 줌

#반례 및 고려사항
# 동시에 2줄 삭제시
