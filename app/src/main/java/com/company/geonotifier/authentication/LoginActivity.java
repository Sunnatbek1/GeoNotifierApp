package com.company.geonotifier.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.company.geonotifier.R;
import com.company.geonotifier.ui.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
    }

    public void login(View view) {
        EditText editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        auth.signInWithEmailAndPassword(editTextEmailAddress.getText().toString(), editTextPassword.getText().toString())
                .addOnCompleteListener(task ->{
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        this.finish();
                    }
                }).addOnFailureListener(exception -> {
                    Toast toast = Toast.makeText(this, "Incorrect login or password", Toast.LENGTH_LONG);
                    toast.show();
                });
    }

    public void goToRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        this.finish();
    }
}
