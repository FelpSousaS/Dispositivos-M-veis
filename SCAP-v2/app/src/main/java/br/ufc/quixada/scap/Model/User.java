package br.ufc.quixada.scap.Model;

public class User {

    private String  id;
    private String nome;
    private String email;


    private String url_foto;


    public User(String id, String nome, String email){
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public User(String url_foto,String id, String nome, String email){
        this.url_foto= url_foto;
        this.id = id;
        this.nome = nome;
        this.email = email;


    }

    public User(){

    }
    public String getUrl_foto() {return url_foto;}

    public void setUrl_foto(String url_foto) {this.url_foto = url_foto;}


    public  String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
