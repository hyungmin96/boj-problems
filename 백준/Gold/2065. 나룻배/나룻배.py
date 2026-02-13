import sys
from collections import deque
input = sys.stdin.readline

M, T, N = map(int, input().strip().split())

queue = [deque(), deque()]
for i in range(N):
    time, des = map(str, input().strip().split())
    time = int(time)
    if des == 'left': 
        des = 0
    else:
        des = 1

    queue[des].append((time, i))

# 입력으로 온 사람들의 도착정보, 도착시간, 현재위치
ans, cur_time, cur_des = [0] * N, 0, 0

# 왼쪽/오른쪽 선착장에 기다리는 사람이 한 명이라도 존재할 경우 반복
while queue[0] or queue[1]:

    cnt = 0

    nxt_l = queue[0][0][0] if queue[0] else float('inf')
    nxt_r = queue[1][0][0] if queue[1] else float('inf')

    # 각 선착장에서 먼저 도착한 사람의 시간.
    if cur_time < nxt_l and cur_time < nxt_r:
        cur_time = min(nxt_l, nxt_r)
    
    # 현재 위치에 탑승가능한 승객이 있는 경우.
    if queue[cur_des] and cur_time >= queue[cur_des][0][0]:
        while(queue[cur_des] and cnt < M and cur_time >= queue[cur_des][0][0]):
            cnt += 1
            _, idx = queue[cur_des].popleft()
            # 목적지 이동시간 미리 계산
            ans[idx] = cur_time + T

        cur_des = (cur_des+1) % 2
        cur_time += T

    # 현재 위치에 탑승가능 승객이 없는 경우(반대편 있음)
    else:
        # 다음 위치로 이동
        cur_time += T
        cur_des = (cur_des+1) % 2

for i in ans:
    print(i)