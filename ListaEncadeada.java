class ListaEncadeada {
    private Node head;

    public ListaEncadeada() {
        this.head = null;
    }

    // Inserir no final da lista
    public void inserir(int codigo) {
        Node novoNode = new Node(codigo);
        if (head == null) {
            head = novoNode;
        } else {
            Node atual = head;
            while (atual.next != null) {
                atual = atual.next;
            }
            atual.next = novoNode;
        }
    }

    // Buscar um elemento na lista
    public boolean buscar(int codigo) {
        Node atual = head;
        while (atual != null) {
            if (atual.codigo == codigo) {
                return true;
            }
            atual = atual.next;
        }
        return false;
    }

    // Contar o número de elementos na lista (para contagem de colisões)
    public int contarElementos() {
        int count = 0;
        Node atual = head;
        while (atual != null) {
            count++;
            atual = atual.next;
        }
        return count;
    }
}
