import Node as NODE

def treeCreationRecursion(dictionary,node):
    #dictionary = dict()
    if (len(dictionary) == 0):
        return node
    elif (node == None):
        keyLeft, valueLeft = dictionary.popitem()
        keyRight, valueRight = dictionary.popitem()
        print("LEFT: ",keyLeft," , "+str(valueLeft)," Right: ",keyRight," , "+str(valueRight))
        parentNode = NODE.Node(dict({"None": keyLeft+keyRight}))
        parentNode.insert(dict({keyLeft: valueLeft}), dict({keyRight:valueRight}))
        treeCreationRecursion(dictionary, parentNode)
    else:
        keyRight, valueRight = dictionary.popitem()
        print("LEFT: None "+str(node.data.get("None"))," Right: ",keyRight," , "+str(valueRight))
        parentNode = NODE.Node(dict({"None": keyRight + node.data.get("None")}))
        parentNode.insert(node, dict({keyRight:valueRight}))
        treeCreationRecursion(dictionary, parentNode)
    return None

def frequencyCount(msg):
    dictionary = dict()
    size = len(msg)
    for i in range(0,size):
        count = 1
        if (size != 0):
            for j in range(1, size):
                if (msg[j] == msg[i]):
                    count+=1
        dictionary.update({count : msg[i]})
    dictionary = dict(reversed(sorted(dictionary.items())))
    return dictionary

def main():
    f = open('PA2_inputs/q3_input.txt', 'r')
    dictionary = frequencyCount(f.readline().strip())
    print(dictionary)
    node = NODE.Node(treeCreationRecursion(dictionary, None))
    print(node.__class__)
    #print("LEFT: None "+str(dict(node.data).get("None")))
    #node.printTree()
    f.close()
    
if __name__ == "__main__":
    main()