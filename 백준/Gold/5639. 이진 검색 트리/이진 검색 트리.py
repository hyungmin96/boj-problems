import sys
input = sys.stdin.readline
sys.setrecursionlimit(100000)

nums = []
while True:
    value = input()
    if not value or value == '\n': break
    else: nums.append(int(value))
        
idx = 0
def dfs(left, cur, right):
    global idx
    if idx+1 < len(nums):
        next1 = nums[idx+1]
        if left < next1 < cur:
            idx += 1
            dfs(left, next1, cur)

    if idx+1 < len(nums):
        next1 = nums[idx+1]
        if cur < next1 < right:
            idx += 1
            dfs(cur, next1, right)
    print(cur)

dfs(-float('inf'), nums[0], float('inf'))