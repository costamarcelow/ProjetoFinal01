package com.senai.projeto_eventos.modelo;

import java.io.Serializable;

public class Local implements Serializable {

    private int id;
    private String localNome;
    private String bairro;
    private String cidade;
    private String  capacidade_pub;

    public Local(int id, String localNome, String bairro, String cidade, String capacidade_pub) {
        this.id = id;
        this.localNome = localNome;
        this.bairro = bairro;
        this.cidade = cidade;
        this.capacidade_pub = capacidade_pub;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalNome() {
        return localNome;
    }

    public void setLocalNome(String localNome) {
        this.localNome = localNome;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCapacidade_pub() {
        return capacidade_pub;
    }

    public void setCapacidade_pub(String capacidade_pub) {
        this.capacidade_pub = capacidade_pub;
    }

    @Override
    public String toString() {
        return "Local{" +
                "id=" + id +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", capacidade_pub=" + capacidade_pub +
                '}';
    }
}
