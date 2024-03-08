import pandas as pd

d = [[1,0],[3,2]]

def dfToArray(df):
    array = []
    for i in range(len(df)):
        list = []
        for j in range(len(df)):
            list.append(df.iloc[i][j])
        array.append(list)
    return array

def reMap(array):
    output = []
    n = len(array)
    ratio = 51.2
    for i in range(n):
        list = []
        for j in range(n):
            val = int(array[i][j]/ratio)
            list.append(val)
        output.append(list)
    return output

def check(value):
    output = []
    for i in range(len(d)):
        list = []
        for j in range(len(d)):
            #print(value, d[i][j])
            if (value > d[i][j]):
                list.append(1)
            else:
                list.append(0)
        output.append(list)
    return output

#   [?,?]
#   [?,?]

def halfTone(array):
    arrayAfterReMap = reMap(array)
    n = len(array)
    output = []
    count = 0
    for i in range(n):
        list = []
        for j in range(n):
            list1 = []
            #checkOutput = check(arrayAfterReMap[i][j])
            checkOutput = check(array[i][j])
            for x in range (len(d)):
                list2 = []
                for y in range(len(d)):
                    list2.append(checkOutput[x][y])
                    #print(checkOutput[x][y])
                    count+=1
                list1.append(list2)
            list.append(list1)
        output.append(list)
    print(output)

        

def orderDithering(array):
    output = []
    n = len(array)
    for i in range(n):
        list = []
        for j in range(n):
            row = i % 2
            column = j % 2
            if (array[i][j] > d[row][column]):
                list.append(1)
            else:
                list.append(0)
        output.append(list)
    return output
            
def main():
    df = pd.read_csv('PA2_inputs/q2_input.txt', sep=",",header=None)
    array = dfToArray(df)
    orderDitheringOutput = orderDithering(array)
    halfTone(dfToArray(pd.read_csv('PA2_inputs/input.txt', sep=",",header=None)))
    #print(orderDitheringOutput)
    
if __name__ == "__main__":
    main()