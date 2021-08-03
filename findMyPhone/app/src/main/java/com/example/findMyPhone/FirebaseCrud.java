package com.example.findMyPhone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseCrud extends AppCompatActivity {

    private FirebaseAuth mAuth;

    String Email,Password;

    EditText emailId,passwordId;

    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailId = (EditText) findViewById(R.id.registerEmail);
        passwordId = (EditText) findViewById(R.id.registerPassword);

    }

    public void Register(View view){

        Email = emailId.getText().toString();
        Password = passwordId.getText().toString();

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            mAuth.signInWithEmailAndPassword(Email, Password);
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(FirebaseCrud.this, "Cadastro feito com sucesso",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(FirebaseCrud.this, MainScreen.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(FirebaseCrud.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static String getUid(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String Uid = user.getUid();
        return Uid;
    }

    public static void Logout(){
        FirebaseAuth.getInstance().signOut();
    }

    public void changeActivityLogin(View view){
        startActivity(new Intent(FirebaseCrud.this, MainActivity.class));
    }
}