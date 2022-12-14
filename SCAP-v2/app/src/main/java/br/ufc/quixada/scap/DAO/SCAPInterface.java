package br.ufc.quixada.scap.DAO;

import android.content.Context;

import java.util.ArrayList;

import br.ufc.quixada.scap.Model.Atividades;

public interface SCAPInterface {

    static SCAPInterface getInstance(Context context){
        return null;
    }

    boolean addAtividade(Atividades a);
    boolean editAtividade(Atividades a);
    boolean deleteAtividade(int idAtividade);
    Atividades getAtividade(int idAtividade);
    ArrayList<Atividades> getListaAtividades();

    boolean deleteAll();
    boolean init();
    boolean close();
    boolean isStarted();
}
