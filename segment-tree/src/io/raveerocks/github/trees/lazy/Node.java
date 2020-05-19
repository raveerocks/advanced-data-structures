package io.raveerocks.github.trees.lazy;

class Node<T> {
        private T item;
        private int leftChild;
        private int rightChild;
        private int parent;

        Node(T item, int parent) {
            this.item = item;
            this.parent = parent;
            this.leftChild=-1;
            this.rightChild=-1;
        }

    public Node(T item, int leftChild, int rightChild, int parent) {
        this.item = item;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.parent = parent;
    }

    public T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }

        public int getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(int leftChild) {
            this.leftChild = leftChild;
        }

        public int getRightChild() {
            return rightChild;
        }

        public void setRightChild(int rightChild) {
            this.rightChild = rightChild;
        }

        public int getParent() { return parent; }

        public void setParent(int parent) { this.parent = parent; }
}