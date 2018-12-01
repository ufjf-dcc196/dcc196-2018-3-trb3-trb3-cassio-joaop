package com.example.cassi.trab3_cssioejoao.Model;

public class Livro {

    private String titulo,autor, editora,versao,disponibilidade,ano;


    public Livro(String titulo, String autor, String editora, String versao, String ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.versao = versao;
        this.disponibilidade = "DISPONIVEL";
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}
