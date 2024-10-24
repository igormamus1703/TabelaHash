import java.util.Random;

public class TrabalhoTabelaHash {
    public static void main(String[] args) {
        // Tamanhos dos vetores (multiplicados por 10)
        int[] tamanhos = {10000, 100000, 1000000};

        // Funções hash
        TrabalhoTabelaHash trabalho = new TrabalhoTabelaHash();
        
        // Geração de dados com seed fixa para reprodutibilidade
        Registro[] conjuntoDeDados = trabalho.gerarDados(100000, 12345);

        // Testar com diferentes tamanhos de tabelas
        for (int tamanho : tamanhos) {
            System.out.println("Tamanho do vetor: " + tamanho);

            // Criar tabela hash
            TabelaHash tabelaHash = new TabelaHash(tamanho);

            // Testar inserção e medição de tempo e colisões
            long tempoInsercaoInicio = System.nanoTime();
            for (Registro registro : conjuntoDeDados) {
                tabelaHash.inserir(registro, trabalho.hashDivisao(registro.getCodigo(), tamanho));
            }
            long tempoInsercaoFim = System.nanoTime();
            System.out.println("Tempo de inserção: " + (tempoInsercaoFim - tempoInsercaoInicio) + " ns");

            int colisoes = tabelaHash.contarColisoes();
            System.out.println("Colisões: " + colisoes);

            // Testar busca e medição de tempo
            long tempoBuscaInicio = System.nanoTime();
            for (int i = 0; i < 5; i++) {
                tabelaHash.buscar(conjuntoDeDados[i].getCodigo(), trabalho.hashDivisao(conjuntoDeDados[i].getCodigo(), tamanho));
            }
            long tempoBuscaFim = System.nanoTime();
            System.out.println("Tempo de busca: " + (tempoBuscaFim - tempoBuscaInicio) + " ns");

            System.out.println();
        }
    }

    // Função hash (Divisão)
    public int hashDivisao(int key, int tamanhoVetor) {
        return key % tamanhoVetor;
    }

    // Função hash (Multiplicação)
    public int hashMultiplicacao(int key, int tamanhoVetor) {
        double A = 0.6180339887;  // Constante sugerida por Knuth
        return (int) (tamanhoVetor * (key * A % 1));
    }

    // Função hash (Dobramento)
    public int hashDobramento(int key, int tamanhoVetor) {
        String keyString = String.valueOf(key);
        int sum = 0;
        for (int i = 0; i < keyString.length(); i += 3) {
            int part = Integer.parseInt(keyString.substring(i, Math.min(i + 3, keyString.length())));
            sum += part;
        }
        return sum % tamanhoVetor;
    }

    // Geração de dados com seed fixa
    public Registro[] gerarDados(int tamanho, long seed) {
        Random random = new Random(seed);
        Registro[] dados = new Registro[tamanho];
        for (int i = 0; i < tamanho; i++) {
            int codigo = 100000000 + random.nextInt(900000000);  // Gera números de 9 dígitos
            dados[i] = new Registro(codigo);
        }
        return dados;
    }
}
