package br.ufc.quixada.scap.DAO;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import br.ufc.quixada.scap.Model.User;

public class UserDAO {
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private static UserDAO instance;
    private UserDAO() {
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }

        return instance;
    }

    public void save(User user) {
        db.collection("Usuarios")
                .document(auth.getUid())
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i("UserDAO", "Usuário cadastrado com sucesso!");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("UserDAO", e.getMessage());
                    }
                });
    }
}
