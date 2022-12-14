
package br.ufc.quixada.scap.Model;

import java.io.Serializable;

public class Atividades implements Serializable {

    private String id;
    private String autor;
    private String tipo_de_atividade;
    private String nome_da_atividade;
    private String descricao_da_atividade;
    private String objetivo_da_atividade;
    private String metodologia_da_atividade;
    private String resultados_da_atividade;
    private String avaliacao_da_atividade;
    private String userId;

    String documentID;


    public Atividades(String userId) {
        this.userId = userId;
    }

    public Atividades(String nome_da_atividade, String descricao_da_atividade, String objetivo_da_atividade, String metodologia_da_atividade, String resultados_da_atividade, String avaliacao_da_atividade) {

        this.nome_da_atividade = nome_da_atividade;
        this.descricao_da_atividade = descricao_da_atividade;
        this.objetivo_da_atividade = objetivo_da_atividade;
        this.metodologia_da_atividade = metodologia_da_atividade;
        this.resultados_da_atividade = resultados_da_atividade;
        this.avaliacao_da_atividade = avaliacao_da_atividade;

    }
    public Atividades(String id,String nome_da_atividade,String autor, String tipo_de_atividade, String descricao_da_atividade, String objetivo_da_atividade, String metodologia_da_atividade, String resultados_da_atividade, String avaliacao_da_atividade) {
        this.id = id;
        this.nome_da_atividade = nome_da_atividade;
        this.autor=autor;
        this.tipo_de_atividade=tipo_de_atividade;
        this.descricao_da_atividade = descricao_da_atividade;
        this.objetivo_da_atividade = objetivo_da_atividade;
        this.metodologia_da_atividade = metodologia_da_atividade;
        this.resultados_da_atividade = resultados_da_atividade;
        this.avaliacao_da_atividade = avaliacao_da_atividade;

    }
    public Atividades(String id,String userId, String autor, String tipo_de_atividade, String nome_da_atividade, String descricao_da_atividade, String objetivo_da_atividade, String metodologia_da_atividade, String resultados_da_atividade, String avaliacao_da_atividade) {
        this.id = id;
        this.userId = userId;
        this.autor = autor;
        this.tipo_de_atividade = tipo_de_atividade;
        this.nome_da_atividade = nome_da_atividade;
        this.descricao_da_atividade = descricao_da_atividade;
        this.objetivo_da_atividade = objetivo_da_atividade;
        this.metodologia_da_atividade = metodologia_da_atividade;
        this.resultados_da_atividade = resultados_da_atividade;
        this.avaliacao_da_atividade = avaliacao_da_atividade;

    }
    public Atividades(String tipo_de_atividade, String nome_da_atividade, String descricao_da_atividade, String objetivo_da_atividade, String metodologia_da_atividade, String resultados_da_atividade, String avaliacao_da_atividade) {
        this.tipo_de_atividade = tipo_de_atividade;
        this.nome_da_atividade = nome_da_atividade;
        this.descricao_da_atividade = descricao_da_atividade;
        this.objetivo_da_atividade = objetivo_da_atividade;
        this.metodologia_da_atividade = metodologia_da_atividade;
        this.resultados_da_atividade = resultados_da_atividade;
        this.avaliacao_da_atividade = avaliacao_da_atividade;

    }

    public Atividades(String nome_da_atividade, String autor) {
        this.nome_da_atividade = nome_da_atividade;
        this.autor = autor;
    }
    public Atividades(String id,String nome_da_atividade, String autor) {
        this.id=id;
        this.nome_da_atividade = nome_da_atividade;
        this.autor = autor;
    }




    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public  String getId() {
        return id;
    }

    public  void setId(String id) {
        this.id = id;
    }

    public String getTipo_de_atividade() {
        return tipo_de_atividade;
    }

    public void setTipo_de_atividade(String tipo_de_atividade) {
        this.tipo_de_atividade = tipo_de_atividade;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNome_da_atividade() {
        return nome_da_atividade;
    }

    public void setNome_da_atividade(String nome_da_atividade) {
        this.nome_da_atividade = nome_da_atividade;
    }

    public String getDescricao_da_atividade() {
        return descricao_da_atividade;
    }

    public void setDescricao_da_atividade(String descricao_da_atividade) {
        this.descricao_da_atividade = descricao_da_atividade;
    }

    public String getObjetivo_da_atividade() {
        return objetivo_da_atividade;
    }

    public void setObjetivo_da_atividade(String objetivo_da_atividade) {
        this.objetivo_da_atividade = objetivo_da_atividade;
    }

    public String getMetodologia_da_atividade() {
        return metodologia_da_atividade;
    }

    public void setMetodologia_da_atividade(String metodologia_da_atividade) {
        this.metodologia_da_atividade = metodologia_da_atividade;
    }

    public String getResultados_da_atividade() {
        return resultados_da_atividade;
    }

    public void setResultados_da_atividade(String resultados_da_atividade) {
        this.resultados_da_atividade = resultados_da_atividade;
    }

    public String getAvaliacao_da_atividade() {
        return avaliacao_da_atividade;
    }

    public void setAvaliacao_da_atividade(String avaliacao_da_atividade) {
        this.avaliacao_da_atividade = avaliacao_da_atividade;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }


    @Override
    public String toString() {
        return "Atividade{" +
                "userId" + userId + "/n" +
                "autor" + autor + "/n" +
                "nomeAtv='" + nome_da_atividade + "\n" +
                ", descricaoAtv='" + descricao_da_atividade + "\n" +
                ", objetivoAtv='" + objetivo_da_atividade + "\n" +
                ", metodologiaAtv='" + metodologia_da_atividade + "\n" +
                ", resultadosAtv='" + resultados_da_atividade + "\n" +
                ", avaliacaoAtv='" + avaliacao_da_atividade + "\n"
                +
                '}';
    }

}
