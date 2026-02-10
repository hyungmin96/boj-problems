import sys
from collections import deque
input = sys.stdin.readline

N, K = map(int, input().strip().split())
N *= 2
arr = list(map(int, input().strip().split()))

cnt = 0
robots = deque()
robots_pos = [False] * N

def rotate_belt(arr):
    tmp = arr[N-1]
    for i in range(N-1, 0, -1):
        arr[i] = arr[i-1]
    arr[0] = tmp

def rotate_robot(robots):
    global robots_pos
    robots_pos = [False] * N
    for _ in range(len(robots)):
        cur = robots.popleft()
        if cur + 1 == N//2 - 1:
            continue

        robots_pos[cur+1] = True
        robots.append(cur + 1)
        
def move_robot(robots):
    global cnt
    for _ in range(len(robots)):
        cur = robots.popleft()

        if not robots_pos[cur+1] and arr[cur+1] > 0:
            robots_pos[cur] = False
            cur += 1
            arr[cur] -= 1
            if arr[cur] == 0:
                cnt += 1

            if cur == N//2-1:
                continue
        
        robots.append(cur)

def put_robot():
    global cnt
    if arr[0] > 0:
        arr[0] -= 1
        if arr[0] == 0:
            cnt += 1

        robots.append(0)
        robots_pos[0] = True

ans = 0
while cnt < K:
    rotate_belt(arr)
    rotate_robot(robots)

    move_robot(robots)
    put_robot()
    ans += 1

print(ans)