def solution(n, w, num):
    answer = 0
    height = n // w
    if n % w > 0:
        height += 1
        
    arr = [[0] * w for _ in range(height)]
    def make_box_array():
        idx = 1
        is_change = True
        for i in range(height-1, -1, -1):
            if is_change:
                for col in range(w):
                    arr[i][col] = idx
                    idx += 1
                    if idx > n: return
                is_change = False
            else:
                for col in range(w-1, -1, -1):
                    arr[i][col] = idx
                    idx += 1
                    if idx > n: return
                is_change = True
                
    make_box_array()
    pos = (-1, -1)
    for r in range(height):
        for c in range(w):
            if arr[r][c] == num:
                pos = (r, c)
                break
                
    ans = 0
    for i in range(pos[0]+1):
        if arr[i][pos[1]] != 0:
            ans += 1
    
    return ans