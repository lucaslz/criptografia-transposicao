/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transposicao;
import java.util.Scanner;
/**
 *
 * @author nobreack
 */
public class Transposicao {
    
    public static String entradaFrase = "frase.txt";
    public static String entradaChave = "chave.txt";    
    public static String saidaCriptografada = "saida.txt";
    public static String frase;
    public static String chave;
    public static char[][] matrizTransposicao;
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Pegando a frase do usuario
        System.out.print("Digite uma frase: ");
        Scanner lerPalavra = new Scanner(System.in);
        String palavra = lerPalavra.nextLine();
        setFrase(palavra);
        
        //Pegando a palavra chave do usuario
        System.out.print("Digite uma chave: ");
        Scanner lerChave = new Scanner(System.in);
        String palavraChave = lerChave.nextLine();
        setChave(palavraChave);

        ManipulaArquivo arquivo = new ManipulaArquivo();
        
        //Escrever frase no arquivo
        arquivo.setNome(getEntradaFrase());
        arquivo.escerver(getFrase());
        
        //Escrever chave no arquivo
        arquivo.setNome(getEntradaChave());
        arquivo.escerver(getChave());
        
        //Transpondo a frase e escrever no arquivo de saida
        String palavraTransposta = transporFrase();
        arquivo.setNome(getSaidaCriptografada());
        arquivo.escerver(palavraTransposta);
        
    }

    public static String getEntradaFrase() {
        return entradaFrase;
    }

    public static String getEntradaChave() {
        return entradaChave;
    }

    public static String getSaidaCriptografada() {
        return saidaCriptografada;
    }

    public static String getFrase() {
        return frase;
    }

    public static void setFrase(String frase) {
        Transposicao.frase = frase;
    }

    public static String getChave() {
        return chave;
    }

    public static char[][] getMatrizTransposicao() {
        return matrizTransposicao;
    }

    public static void setMatrizTransposicao(char[][] matrizTransposicao) {
        Transposicao.matrizTransposicao = matrizTransposicao;
    }

    public static void setChave(String chave) {
        Transposicao.chave = chave;
    }
    
    public static String transporFrase()
    {
        //Definindo valor dos Fors que iram rodar
        int totalFrase = getFrase().length();
        int totalChave = getChave().length();
        int posicaoAtual = 0;
        int linha = 1;
        StringBuilder string = new StringBuilder();
        
        //Setando matriz de transposicao
        setMatrizTransposicao(new char[100][totalChave]);
        
        //Colocando a chave na cabeca do array
        for (int i = 0; i < totalChave; i++) {
            getMatrizTransposicao()[0][i] = getChave().charAt(i);
        }
        
        //Colocando frase na matriz de transposicao
        while(totalFrase > posicaoAtual) {
            for (int i = 0; i < totalChave; i++) {
                if (totalFrase == posicaoAtual) {
                    posicaoAtual--;
                }
                getMatrizTransposicao()[linha][i] = getFrase().charAt(posicaoAtual);                    
                posicaoAtual++;                    
                
            }
           
            if (posicaoAtual >= totalChave) {
                linha++;
            }
        }
        
        for (int i = 0; i < totalChave; i++) {
            for (int j = 0; j < totalChave; j++) {
                if (j != 0) {
                    string.append(getMatrizTransposicao()[j][i]);    
                }
                System.out.print(getMatrizTransposicao()[i][j]);
            }
            System.out.println("");
        }
        
        String palavraCompleta = string.toString();
        
        return palavraCompleta;
    }
}
