package com.example.fuelstation.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fuelstation.HomeActivity;
import com.example.fuelstation.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText email,password;
    Button Login,Reg;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.lemail);
        Reg =findViewById(R.id.btn_Reg);
        password=findViewById(R.id.lpassword);
        Login =findViewById(R.id.btn_LogIn);
        auth= FirebaseAuth.getInstance();

        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, RegisterActivity.class));
                finish();
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Txt_email =email.getText().toString();
                String Txt_password=password.getText().toString();
                if (TextUtils.isEmpty(Txt_email)||TextUtils.isEmpty(Txt_password)){
                    Toast.makeText(Login.this, "you must enter email & password", Toast.LENGTH_SHORT).show();
                }else {
                    auth.signInWithEmailAndPassword(Txt_email,Txt_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Intent intent =new Intent(Login.this, HomeActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        Toast.makeText(Login.this, "Auth failed ", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
