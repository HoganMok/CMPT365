import pandas as pd

def YCoCgToYUV(Y, Co, Cg):
    R = Y + Co - Cg 
    G = Y + Cg
    B = Y - Co- Cg
    return RGBToYUV(R, G, B)

def RGBToYUV(R, G, B):
    Y = 0.299*R + 0.587*G + 0.114*B
    U = -0.299*R - 0.587*G + 0.886*B
    V = 0.701*R - 0.587*G - 0.114*B
    return round(Y), round(U), round(V)
 
def main():
    df = pd.read_csv('PA2_inputs/q1_input.txt', sep=",",header=None)
    Y, U, V = YCoCgToYUV(df.iloc[0,0], df.iloc[0,1], df.iloc[0,2])
    print(Y,U,V)
    
    
if __name__ == "__main__":
    main()