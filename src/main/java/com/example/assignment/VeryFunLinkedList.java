package com.example.assignment;

import java.util.Iterator;

public class VeryFunLinkedList<T> implements Iterable<T> {
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            //Stores the node that we will use for the coming iteration.
            private VeryFunNode<T> current = head;

            //Checks if there is anything left to iterate through.
            @Override
            public boolean hasNext() {
                return current != null;
            }

            //Make the current node equal to the next node.
            //Return the contents of the temp variable.
            @Override
            public T next() {
                VeryFunNode<T> temp = current;
                current = current.next;
                return temp.getContents();
            }
        };
    }

    private class VeryFunNode<T> {
        public VeryFunNode<T> next = null;
        private T contents;

        public T getContents() {
            return contents;
        }

        public void setContents(T c) {
            contents = c;
        }

        public VeryFunNode(T contents) {
            this.contents = contents;
        }
    }

    //Create an empty list for nodes to be added to.
    public VeryFunLinkedList() {
        this.head = null;
    }

    public VeryFunNode<T> head = null;

    public void addElement(T e) {
        VeryFunNode<T> fn = new VeryFunNode<>(e);
        fn.next = head;
        head = fn;
    }

    public void removeElement(int index) {
        //E.g. size of 6. Select the 1st node (which is 0th index), take it away from the length, then take away -1,
        //to account for starting from 0
        System.out.println(listLength());
        //If the index is the head (1st node)
        if(index == 0){
            head = head.next;
        } else {
            //Anything after the head, we need to call the Very Fun Node class.
            VeryFunNode<T> temp = head;
            //Go through the Linked List and move the head (temp) down to the next one, until we reach the index.
            for(int i = 1; i < index; i++){
                temp = temp.next;
            }
            //Make the reference of temp equal to the one after the one looking to be deleted, thus creating an orphan.
            temp.next = temp.next.next;
        }
    }


    public int listLength() {
        return lengthRecursively(head);
    }

    private int lengthRecursively(VeryFunNode<T> currNode) {
        if (currNode == null) {
            return 0;
        }
        //Add one everytime a node is found and recursively call the method for the rest of the nodes
        return 1 + lengthRecursively(currNode.next);
    }

    //Start with head here, because head contains all sub-lists.
    //Think of the diagram with sub-lists.
    public boolean containsElement(T e) {
        return containsElementRecursively(e, head);
    }

    private boolean containsElementRecursively(T e, VeryFunNode<T> currNode) {
        //If we end up at null, that means the list doesn't contain the required element.
        if (currNode == null) {
            return false;
        }
        if (currNode.getContents() == e) {
            return true;
        }
        //If not found yet, but there is more of the list (i.e. not null), call the function again
        //with the next node as the current element. Eventually becomes null, therefore returns false.
        return containsElementRecursively(e, currNode.next);
    }

    public T getObject(int index) {
        VeryFunNode<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.getContents();
    }

    public void clearList(){
        head = null;
    }
}