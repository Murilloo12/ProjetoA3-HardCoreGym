package br.com.murillo.hardcoregym.application.dynamic.structures;

public class MinhaPilha<T> {
    private Node<T> topo;
    private int tamanho;

    private static class Node<T> {
        T dado;
        Node<T> proximo;

        Node(T dado) {
            this.dado = dado;
        }
    }

    public void push(T item) {
        Node<T> novoNode = new Node<>(item);
        novoNode.proximo = topo;
        topo = novoNode;
        tamanho++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Pilha vazia");
        }
        T item = topo.dado;
        topo = topo.proximo;
        tamanho--;
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Pilha vazia");
        }
        return topo.dado;
    }

    public boolean isEmpty() {
        return topo == null;
    }

    public int tamanho() {
        return tamanho;
    }
}
