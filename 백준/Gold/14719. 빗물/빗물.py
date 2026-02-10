import sys
input = sys.stdin.readline

H, W = map(int, input().strip().split())
arr = list(map(int, input().strip().split()))
heights = []
for i in range(1, W - 1):
    max_left, max_right = 0, 0
    
    left = i - 1
    while left >= 0:
        max_left = max(max_left, arr[left])
        left -= 1

    right = i + 1
    while right < W:
        max_right = max(max_right, arr[right])
        right += 1

    heights.append((max_left, max_right))

ans = 0
for i in range(1, W-1):
    ans += max(0, min(heights[i-1][0], heights[i-1][1]) - arr[i])

print(ans)