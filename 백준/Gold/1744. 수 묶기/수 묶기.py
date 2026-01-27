import sys
input = sys.stdin.readline

N = int(input())
arr = sorted(list(map(int, list(input().strip() for _ in range(N)))), reverse=True)

postivie, negative, zeros, one = [], [], 0, 0
for num in arr:
    if num > 1: postivie.append(num)
    elif num == 0: zeros += 1
    elif num == 1: one += 1
    else: negative.append(num)

postivie = sorted(postivie)
negative = sorted(negative, reverse=True)

ans = one
while len(postivie) > 1:
    val1 = postivie.pop()
    val2 = postivie.pop()
    ans += (val1 * val2)

while len(negative) > 1:
    val1 = negative.pop()
    val2 = negative.pop()
    ans += (val1 * val2)

while postivie:
    ans += postivie.pop()

while negative:
    val = negative.pop()
    if zeros <= 0:
        ans += val
    else:
        zeros -= 1

print(ans)