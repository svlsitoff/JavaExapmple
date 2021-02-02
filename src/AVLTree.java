/**
 * This class implements an AVL tree,
 * and when entering or deleting data,
 * it rebalances the tree nodes in such
 * a way that the difference in the
 * heights of each subtree does not exceed 1.
 * @param <T>
 */
class AVLTree<T extends Comparable<? super T>>  extends BinarySearchTree<T> {

    /**
     * This method checks and sets the height of the selected node
     * @return
     */

    private void CheckHeightSubTrees(AVLNode current)
    {
        int leftHeight = GetHeightNode(current.getLeftChild());
        int rightHeight = GetHeightNode(current.getRightChild());
        int ht= (leftHeight > rightHeight ? leftHeight : rightHeight) + 1;
        current.setHeight(ht);
    }

    /**
     * This method returns the height of the current node
     * */
    private int GetHeightNode(AVLNode current)
    {
        if (current == null) return 0;
        else return current.getHeight();
    }
    /**
     * This method checks the difference in
     * the heights of the subtrees of the current node
     *
     * */
    private int CalculateBalanceFactor(AVLNode current)
    {
        int left = GetHeightNode(current.getLeftChild());
        int right = GetHeightNode(current.getRightChild());
        int balanceFactor = left - right;
        return balanceFactor;
    }

    /**
     * This method performs double right rotation of the selected node.
     * */
    private AVLNode DoubleRotateRight(AVLNode parent)
    {
        AVLNode child = parent.getRightChild();
        parent.setRightChild(child.getLeftChild());
        child.setLeftChild(parent);
        CheckHeightSubTrees(parent);
        CheckHeightSubTrees(child);
        return child;
    }

    /**
     * This method performs double left rotation of the selected node.
     * */
    private AVLNode DoubleRotateLeft(AVLNode parent)
    {
        AVLNode child = parent.getLeftChild();
        parent.setLeftChild(child.getRightChild());
        child.setRightChild(parent);
        CheckHeightSubTrees(parent);
        CheckHeightSubTrees(child);
        return child;
    }
    /**
     * This method rotates from left to right of the selected node.
      */
    private AVLNode RotateRight(AVLNode parent)
    {
        AVLNode child = parent.getLeftChild();
        parent.setLeftChild(DoubleRotateRight(child));
        return DoubleRotateLeft(parent);
    }

    /**
     * This method rotates from left to right of the selected node.
     * @param parent
     * @return
     */
    private AVLNode RotateLet(AVLNode parent)
    {
        AVLNode child = parent.getRightChild();
        parent.setRightChild(DoubleRotateLeft(child));
        return DoubleRotateRight(parent);
    }
    /**
     * This method inserts the data
     * into the AVL tree. Data
     * is entered by calling a recursive method
     * @param entry
     * @return
     */
    public T add(T entry){

            root = add((AVLNode<T>) root,entry);
            return  entry;
    }

    /**
     * This method balances the tree for the current
     * node. First, we check the balance of the node
     * and if the difference in heights of the
     * subtrees is greater than 1 or less than -1.
     * In this case, the rotation methods are called,
     * which move the links of the nodes in places,
     * as a result of which the difference in
     * heights changes.
     * @param current
     * @return
     */
    private AVLNode BalanceTree(AVLNode current)
    {
        CheckHeightSubTrees(current);
        int balanceFactor = CalculateBalanceFactor(current);
        if (balanceFactor > 1)
        {
            if (CalculateBalanceFactor(current.getLeftChild()) > 0)
            {
                current = DoubleRotateLeft(current);
            }
            else
            {
                current = RotateRight(current);
            }
        }

        else if (balanceFactor < -1)
        {
            if (CalculateBalanceFactor(current.getRightChild()) > 0)
            {
                current = RotateLet(current);
            }
            else
            {
                current = DoubleRotateRight(current);
            }
        }

        return current;
    }


    /**
     * This method introduces a new node
     * into the tree and works with the
     * help of recursion, that is, depending
     * on the size of the data, we compare
     * this data with the current node and,
     * depending on this, move along the links
     * of the nodes until we get to a node that
     * has no descendants , in this case a new
     * node will be created. After that, the
     * balance of the tree nodes is checked and,
     * if necessary, the rotation methods are called.
     * @param node
     * @param nodeData
     * @return
     */
    private AVLNode<T> add(AVLNode<T> node, T nodeData) {

        if (node == null){
            return (new AVLNode<>(nodeData));

        }
        if (nodeData.compareTo(node.getData()) <0 )
        {
            node.setLeftChild( add(node.getLeftChild(), nodeData));
        }else if (nodeData.compareTo(node.getData()) > 0)
        {
            node.setRightChild(add(node.getRightChild(), nodeData));
        }
        return BalanceTree(node);

    }

    /**
     * Recursively traverse the tree to the bottom-left element
     * @param AvlNode
     * @return
     */



    /**
     *     Delete a AvlNode
     * @return
     */
    public T remove(T entry){

        root = remove((AVLNode<T>) root,entry);
        return entry;
    }

    /**
     * This method returns the node with the minimum data value
     * @param node
     * @return
     */
    AVLNode FindMinNode(AVLNode node)
    {
        AVLNode current = node;

        while (current.getLeftChild() != null)
            current = current.getLeftChild();

        return current;
    }
    /**
     *In this method, a recursive search
     * and deletion of a node occurs,
     * as soon as the required data matches
     * the data of the node, then there is
     * a forwarding of links and data
     * exchange between nodes
     * @param node
     * @param nodeData
     * @return
     */
    private AVLNode<T> remove(AVLNode<T> node, T nodeData) {


        if (node == null)
            return node;

        if (nodeData.compareTo(node.getData()) < 0){
            node.setLeftChild( remove(node.getLeftChild(), nodeData));

        }else if (nodeData.compareTo(node.getData()) > 0){
            node.setRightChild(remove(node.getRightChild(), nodeData));

        }else
            {

                AVLNode left = node.getLeftChild();

                AVLNode right = node.getRightChild();

                if (right == null) return left;
                AVLNode rightSubtreeRoot = FindMinNode(right);

                rightSubtreeRoot.setRightChild(rightSubtreeRoot);

                rightSubtreeRoot.setLeftChild(left);
                return BalanceTree(rightSubtreeRoot);
             }
        return BalanceTree(node);
    }
    public static void main(String[] args){
        AVLTree tree = new AVLTree();
        tree.add(1);
    }

}