def solution(n, bans):
    def str_to_num(value):
        cur = 0
        for i in range(len(value)):
            cur += (ord(value[i]) - ord('a') + 1) * (26 ** (len(value) - i - 1))
        return cur - 1
    
    def num_to_str(n):
        tmp = []
        while n >= 0:
            ch = chr(ord('a') + int(n % 26))
            n = (n // 26) - 1
            tmp.append(ch)
            
        tmp.reverse()
        return "".join(map(str, tmp))

    bans.sort(key=lambda x: (len(x), x))
    for b in bans:
        idx = str_to_num(b)
        if idx <= (n-1):
            n += 1
            
    return num_to_str((n-1))