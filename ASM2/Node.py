class Node:
    def __init__(self, data):
        self.left = None
        self.right = None
        self.data = data  
        
    def insert(self, leftData, rightData):
        self.left = Node(leftData)
        self.right = Node(rightData)
        
    
    def printTree(self):
        if self.left:
            self.left.printTree()
        print(self.data),
        if self.right:
            self.right.printTree()
        