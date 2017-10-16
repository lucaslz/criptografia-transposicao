/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transposicao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;

/**
 *
 * @author Lucas
 */
public class ManipulaArquivo {
    
    private String nome;
    private File arquivo;
    private BufferedReader ler;
    private BufferedWriter escrever;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public File getArquivo() {
        setarArquivo();
        return arquivo;
    }
    
    public void setarArquivo(){
        this.arquivo = new File(getNome());
    }

    public BufferedReader getLer() {
        return ler;
    }

    public void setLer(BufferedReader ler) {
        this.ler = ler;
    }

    public BufferedWriter getEscrever() {
        return escrever;
    }

    public void setEscrever(BufferedWriter escrever) {
        this.escrever = escrever;
    }
    
    public boolean verificaSeArquivoExiste()
    {
        if (!getArquivo().exists()) {
            try {
                getArquivo().createNewFile();
                return true;
            } catch (IOException ex) {
                printStackTrace("Não foi possivel criar o arquivo");
                return false;
            }
        }
        return true;
    }
    
    public boolean escerver(String palavra)
    {
        if (verificaSeArquivoExiste()) {
            try {
                setEscrever(new BufferedWriter(new FileWriter(getArquivo())));
                getEscrever().append(palavra);
                getEscrever().flush();
                getEscrever().close();
                return true;
            } catch (IOException e) {
                printStackTrace("Não foi possivel escrever no arquivo.");
                return false;
            }
        } else {
            return false;
        }
    }
    
    public ArrayList ler()
    {
        Scanner lerArquivo;
        ArrayList arrayFrases = new ArrayList();
        
        if (verificaSeArquivoExiste()) {
            try {
                setLer(new BufferedReader(new FileReader(getArquivo())));
                lerArquivo = new Scanner(getLer());
                while (lerArquivo.hasNextLine()) {                    
                    arrayFrases.add(lerArquivo.nextLine());
                }
            } catch (IOException ex) {
                printStackTrace("Não foi possivel ler o arquivo.");
            }
        }
        
        return arrayFrases;
    }
}
