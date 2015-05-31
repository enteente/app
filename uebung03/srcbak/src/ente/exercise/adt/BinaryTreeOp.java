package ente.exercise.adt;

public interface BinaryTreeOp<E extends Comparable<E>>{
    
    public void insert(Node<E> n);

    public void insert(E element);

    public void remove(E element);

    public String toString(); 
}
