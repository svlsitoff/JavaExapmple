   public class AVLNode<T> extends BinaryNode<T> {
    /**
     * Height of node
     */
    private int      height;
    /**
     * Constructor; creates a node without any children
     *
     * @param Entry  The element contained in this node
     */
    public AVLNode(T Entry){
        super (Entry, null, null);
        height=1;
    }

    /**
     * Constructor; creates a node with children
     *
     * @param Entry  The element contained in this node
     * @param left      Left child
     * @param right      Right child
     */
    public AVLNode(T Entry, AVLNode<T> left, AVLNode<T> right){
        super(Entry,left,right);
        height=1;

    }

       /**
        * Returns node data
        * @return
        */
    public T getData() {
        return super.getData();
    }

       /**
        * Sets node data
        * @param Entry
        */
    public void setData(T Entry){
        super.setData(Entry);
    }

       /**
        * When calling this method,
        * we cast it to an object left Binary node
        * of the current class and return it
        * @return
        */
    public AVLNode<T> getLeftChild() {
        return  (AVLNode<T>) super.getLeftChild();
    }

       /**
        * This method sets the value of the right node
        * @param leftNode
        */
    public void setLeftChild(BinaryNode leftNode) {
        super.setLeftChild(leftNode);
    }

       /**
        * When calling this method,
        * we cast it to an object right Binary node
        * of the current class and return it
        * @return
        */
    public AVLNode<T> getRightChild() {
        return (AVLNode<T>) super.getRightChild();
    }

       /**
        * This method sets the value of the right node
        * @param rightNode
        */
    public void setRightChild(BinaryNode rightNode) {
        super.setRightChild(rightNode);
    }
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
