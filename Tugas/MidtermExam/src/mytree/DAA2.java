package mytree;

// AVL Tree = Height-Balanced (HB) Tree

public class DAA2 extends DAA1 {

	public static int height(MyTree t) {
        if (t.getEmpty()) {
            return -1; 
        }
        return 1 + Math.max(height(t.getLeft()), height(t.getRight()));
    }
	
	private static int getBalance(MyTree t) {
        if (t.getEmpty()) {
            return 0;
        }
        return height(t.getLeft()) - height(t.getRight());
    }
	
	// 4. isHeightBalanced() [10 points]
	public static boolean isHeightBalanced(MyTree t) {
        return checkBalance(t).isBalanced;
    }
    // Helper class
    private static class BalanceStatusWithHeight {
        boolean isBalanced;
        int height;

        BalanceStatusWithHeight(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }
    // Recursive function to check if the tree is balanced
    private static BalanceStatusWithHeight checkBalance(MyTree t) {
        if (t.getEmpty()) {
            return new BalanceStatusWithHeight(true, -1); 
        }

        BalanceStatusWithHeight leftResult = checkBalance(t.getLeft());
        if (!leftResult.isBalanced) {
            return leftResult;  
        }

        BalanceStatusWithHeight rightResult = checkBalance(t.getRight());
        if (!rightResult.isBalanced) {
            return rightResult;  
        }

        boolean isBalanced = Math.abs(leftResult.height - rightResult.height) <= 1;
        int height = Math.max(leftResult.height, rightResult.height) + 1;
        return new BalanceStatusWithHeight(isBalanced, height);
    }
	
	
	
	// 5. insertHB() [10 points]
	public static MyTree insertHB(int n, MyTree t) {
        if (t.getEmpty()) {
            return new MyTree(n, new MyTree(), new MyTree());
        }
        if (n < t.getValue()) {
            return rebalanceForLeft(new MyTree(t.getValue(), insertHB(n, t.getLeft()), t.getRight()));
        } else if (n > t.getValue()) {
            return rebalanceForRight(new MyTree(t.getValue(), t.getLeft(), insertHB(n, t.getRight())));
        } else {
            return t;
        }
    }

	// rebalanceForLeft is called when the left subtree of t may have
	// grown taller by one notch.
	// If it is indeed taller than the right subtree by two notches,
	// return a height-balanced version of t using single or double rotations.
	// The subtrees of t are assumed to be already height-balanced and
	// no effort is made to rebalance them.
	//
	// Likewise, for the case of the right subtree -> rebalanceForRight
	// Both rebalanceForLeft & rebalanceForRight will be used by insertHB() and deleteHB()
	
	
	// 6. rebalanceForLeft() [15 points]
	private static MyTree rebalanceForLeft(MyTree t) {
		int balance = getBalance(t);
		if (balance > 1) {
            if (getBalance(t.getLeft()) < 0) { 
            	// Left-Right Case
                MyTree leftRotated = new MyTree(t.getLeft().getValue(), t.getLeft().getLeft(), rotateLeft(t.getLeft().getRight()));
                return rotateRight(new MyTree(t.getValue(), leftRotated, t.getRight()));
            }
            // Left-Left Case
            return rotateRight(t);
        }
        return t;
	}
	
	// 7. rebalanceForRight() [15 points]
	private static MyTree rebalanceForRight(MyTree t) {
		// Write your codes in here
		int balance = getBalance(t);
		if (balance < -1) {
            if (getBalance(t.getRight()) > 0) { 
            	// Right-Left Case
                MyTree rightRotated = new MyTree(t.getRight().getValue(), rotateRight(t.getRight().getLeft()), t.getRight().getRight());
                return rotateLeft(new MyTree(t.getValue(), t.getLeft(), rightRotated));
            }
            // Right-Right Case
            return rotateLeft(t);
        }
        return t;
	}
	
	// Helper Methode
	private static MyTree rotateRight(MyTree t) {
        return new MyTree(t.getLeft().getValue(), t.getLeft().getLeft(), new MyTree(t.getValue(), t.getLeft().getRight(), t.getRight()));

	}

	private static MyTree rotateLeft(MyTree t) {
        return new MyTree(t.getRight().getValue(), new MyTree(t.getValue(), t.getLeft(), t.getRight().getLeft()), t.getRight().getRight());

	}
	
	
	
	// 8. deleteHB() [10 points]
	public static MyTree deleteHB(MyTree t, int x) {
		if (t.getEmpty()) {
            return t;
        }

        // Step 1: Perform standard BST delete
        if (x < t.getValue()) {
            t = new MyTree(t.getValue(), deleteHB(t.getLeft(), x), t.getRight());
        } else if (x > t.getValue()) {
            t = new MyTree(t.getValue(), t.getLeft(), deleteHB(t.getRight(), x));
        } else {
            // Node with only one child or no child
            if (t.getLeft().getEmpty() || t.getRight().getEmpty()) {
                MyTree temp = t.getLeft().getEmpty() ? t.getRight() : t.getLeft();
                if (temp.getEmpty()) {
                    // No child case
                    temp = new MyTree(); 
                }
                return temp;
            } else {
                MyTree temp = minValueNode(t.getRight());
                t = new MyTree(temp.getValue(), t.getLeft(), deleteHB(t.getRight(), temp.getValue()));
            }
        }
        if (t.getEmpty()) {
            return t;
        }
        // Step 2: Rebalance the tree
        return rebalance(t);
    }        


	private static MyTree minValueNode(MyTree t) {
    	MyTree current = t;
    	while (!current.getLeft().getEmpty()) {
    	current = current.getLeft();
    	}
    	return current;
	}

	private static MyTree rebalance(MyTree t) {
	    int balance = getBalance(t);
	    // Left heavy
	    if (balance > 1) {
	        if (getBalance(t.getLeft()) >= 0) {
	            return rotateRight(t);
	        } else {
	            t = new MyTree(t.getValue(), rotateLeft(t.getLeft()), t.getRight());
	            return rotateRight(t);
	        }
	    }
	
	    // Right heavy
	    if (balance < -1) {
	        if (getBalance(t.getRight()) <= 0) {
	            return rotateLeft(t);
	        } else {
	            t = new MyTree(t.getValue(), t.getLeft(), rotateRight(t.getRight()));
	            return rotateLeft(t);
	        }
	    }
	
	    return t;
	}
}