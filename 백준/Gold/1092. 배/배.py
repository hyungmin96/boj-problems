import sys
input = sys.stdin.readline

N = int(input())
crain = sorted(list(map(int, input().strip().split())))

M = int(input())
boxes = sorted(list(map(int, input().strip().split())))

def solve():
    ans = {}
    if max(crain) < max(boxes):
        return - 1
    
    ans, move_cnt, visit, pos  = 0, 0, [False] * len(boxes), [len(boxes)-1] * len(crain)
    while move_cnt < len(boxes):
        ans += 1
        for i in range(len(crain)-1, -1, -1):
            # i번째 크레인이 옮길 수 있는 박스 찾기
            # 포인터(다음 탐색위치) 갱신.
            while pos[i] >= 0: # ??
                if not visit[pos[i]] and crain[i] >= boxes[pos[i]]:
                    visit[pos[i]] = True
                    pos[i] -= 1
                    move_cnt += 1
                    break
            
                pos[i] -= 1

    return ans

print(solve())