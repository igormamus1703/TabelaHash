import java.util.LinkedList;

public class TabelaHash {
    private LinkedList<Registro>[] tabela;
    private int tamanho;

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        tabela = new LinkedList[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    public void inserir(Registro registro, int hashCode) {
        int posicao = hashCode % tamanho;
        tabela[posicao].add(registro);
    }

    public boolean buscar(int codigo, int hashCode) {
        int posicao = hashCode % tamanho;
        for (Registro registro : tabela[posicao]) {
            if (registro.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    public int contarColisoes() {
        int colisoes = 0;
        for (LinkedList<Registro> lista : tabela) {
            if (lista.size() > 1) {
                colisoes += lista.size() - 1;
            }
        }
        return colisoes;
    }
}
