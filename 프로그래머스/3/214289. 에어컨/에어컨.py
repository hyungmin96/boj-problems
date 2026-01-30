import sys
sys.setrecursionlimit(100000)

def solution(temperature, t1, t2, a, b, onboard):
    # 1. 인덱스 처리를 위해 모든 온도를 양수로 변환 (+10) -> 0~50 범위 사용
    OFFSET = 10
    temperature += OFFSET
    t1 += OFFSET
    t2 += OFFSET
    
    N = len(onboard)
    # DP 테이블: dp[시간][온도] = 해당 상태에서의 최소 비용
    dp = [[-1] * 52 for _ in range(N)]
    INF = float('inf')

    def dfs(idx, cur_t):
        # [검증] 현재 시간에 승객이 있는데 온도가 범위를 벗어난 경우 -> 불가능한 경로
        if onboard[idx] == 1 and not (t1 <= cur_t <= t2):
            return INF
        
        # [종료] 마지막 시간까지 무사히 도달했으면 비용 추가 없음
        if idx == N - 1:
            return 0
        
        # [메모이제이션] 이미 계산한 값이면 반환
        if dp[idx][cur_t] != -1:
            return dp[idx][cur_t]
        
        res = INF
        
        # --- 다음 1분 뒤의 온도(next_t)를 만들기 위한 비용 계산 ---
        
        # Case 1: 온도를 1도 올리고 싶을 때 (cur_t + 1)
        if cur_t + 1 <= 50:
            # 실외 온도가 현재보다 높다면, 에어컨 끄면(0원) 저절로 올라감
            cost = 0 if cur_t < temperature else a
            ret = dfs(idx + 1, cur_t + 1)
            if ret != INF:
                res = min(res, cost + ret)
                
        # Case 2: 온도를 1도 내리고 싶을 때 (cur_t - 1)
        if cur_t - 1 >= 0:
            # 실외 온도가 현재보다 낮다면, 에어컨 끄면(0원) 저절로 내려감
            cost = 0 if cur_t > temperature else a
            ret = dfs(idx + 1, cur_t - 1)
            if ret != INF:
                res = min(res, cost + ret)
                
        # Case 3: 온도를 유지하고 싶을 때 (cur_t)
        # 실외 온도가 현재와 같다면, 에어컨 끄면(0원) 유지됨
        cost = 0 if cur_t == temperature else b
        ret = dfs(idx + 1, cur_t)
        if ret != INF:
            res = min(res, cost + ret)
            
        dp[idx][cur_t] = res
        return res

    return dfs(0, temperature)