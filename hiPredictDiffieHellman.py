import math
"""
this func. provides you list of x^n numbers:: ( x mod y) x^1,x^2...etc
trick part is when result of x^n reach 1 it keep in loop in that array
so we can use modulus aritmetic for huge numbers
num_small=x,
num_big=y
"""
def modulus2array(num_small, num_big):
    i = 1
    result = [1.0]
    while True:
        result.append(math.pow(num_small, i) % num_big)
        i += 1
        if math.pow(num_small, i + 1) % num_big == result[1] and math.pow(num_small, i) % num_big == result[0]:
            break
    return result

def main(publicX, publicY, divide, modeBase):
    arr = modulus2array(divide, modeBase)
    return arr[(arr.index(publicX)*arr.index(publicY)) % len(arr)]

print(main(publicX=6, publicY=12, divide=3, modeBase=17))
#print(main(publicX=4, publicY=8, divide=2, modeBase=13))# 2 2 3  mod 13   12
