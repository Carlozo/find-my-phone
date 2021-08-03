package com.example.findMyPhone;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class AlarmCallback {

    public static Context cntx;

    public static void startSnapshot(Context context, String Uid){

        cntx = context;

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        final DocumentReference docRef = db.collection("localization").document(Uid);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {

            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("Snapshot Listen", "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.d("Tem coisa aqui:", "Current data: " + snapshot.getData());
                    db.collection("localization")
                            .whereEqualTo("id",Uid)
                            .whereEqualTo("sound", true)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            Log.d("Documento puxado: ", document.getId() + " => " + document.getData());

                                            Log.d("===>" ,""+MediaPlayer.create(cntx, R.raw.sound_effect));
                                            MediaPlayer mediaPlayer = MediaPlayer.create(cntx, R.raw.sound_effect); // initialize it here
                                            mediaPlayer.start();

                                            DocumentReference Document = db.collection("localization").document(Uid);
                                            Document
                                                    .update("sound", false)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Log.d("Trocou para: ", "falso o estado de sound");
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Log.w("Erro: ", "Error updating document", e);
                                                        }
                                                    });
                                        }
                                    } else {
                                        Log.d("Erro: ", "Error getting documents: ", task.getException());
                                    }
                                }
                            });
                } else {
                    Log.d("É nulo", "Current data: null");
                }
            }
        });
    }
}
