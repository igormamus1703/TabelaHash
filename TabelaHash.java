public class TabelaHash {
    private ListaEncadeada[] tabela;
    private int tamanho;

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        tabela = new ListaEncadeada[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ListaEncadeada();  // Inicializa cada lista encadeada
        }
    }

    // Inserir um registro na tabela hash
    public void inserir(Registro registro, int hashCode) {
        int posicao = hashCode % tamanho;
        tabela[posicao].inserir(registro);
    }

    // Buscar um registro na tabela hash
    public boolean buscar(int codigo, int hashCode) {
        int posicao = hashCode % tamanho;
        return tabela[posicao].buscar(codigo);
    }

    // Contar o número de colisões na tabela hash
    public int contarColisoes() {
        int colisoes = 0;
        for (ListaEncadeada lista : tabela) {
            if (lista.contarElementos() > 1) {
                colisoes += lista.contarElementos() - 1;
            }
        }
        return colisoes;
    }
}
