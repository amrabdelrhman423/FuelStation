package com.example.fuelstation.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelstation.HomeActivity;
import com.example.fuelstation.R;
import com.example.fuelstation.moduls.benz;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    EditText email,password,username,phone;
    TextView logint;
    Button Bregister;
    FirebaseAuth auth;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email =findViewById(R.id.remail);
        password=findViewById(R.id.rpassword);
        username=findViewById(R.id.rusername);
        phone=findViewById(R.id.rphone);
        Bregister =findViewById(R.id.bttnregister);
        logint=findViewById(R.id.tLogin);
        auth= FirebaseAuth.getInstance();

        Bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Txt_username =username.getText().toString();
                String Txt_email =email.getText().toString();
                String Txt_password=password.getText().toString();
                String Txt_phone=phone.getText().toString();
                if (TextUtils.isEmpty(Txt_email)||TextUtils.isEmpty(Txt_phone)||TextUtils.isEmpty(Txt_password)||TextUtils.isEmpty(Txt_username)){
                    Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }else if (Txt_password.length()<6){
                    Toast.makeText(RegisterActivity.this,"password Must be at least 6 character ",Toast.LENGTH_LONG).show();
                }
                else {
                    register(Txt_email,Txt_password,Txt_username,Txt_phone);
                }
            }
        });

        logint.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent=new Intent(RegisterActivity.this, Login.class);
                startActivity(intent);
                return false;
            }
        });
    }
    private void register(String email, String password, final String username, final String phone){
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user=auth.getCurrentUser();

                              assert user != null;
                            String userid =user.getUid();

                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid);
                            HashMap<String,String> hashMap =new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("username",username);
                            hashMap.put("imageUrl","default");
                            hashMap.put("phone",phone);
                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent intent =new Intent(RegisterActivity.this, HomeActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                            // banzen
                            benz benz1=new benz("سولار","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("benz").child("benz1");
                            reference.setValue(benz1);
                            benz benz2=new benz("بنزين80","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("benz").child("benz2");
                            reference.setValue(benz2);
                            benz benz3=new benz("بنزين92","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("benz").child("benz3");
                            reference.setValue(benz3);
                            benz benz4=new benz("سمر ديزل","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("benz").child("benz4");
                            reference.setValue(benz4);
                            //شحم
                            benz shm1=new benz("شحم كالثيومي","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("shm").child("shm1");
                            reference.setValue(shm1);
                            benz shm2=new benz("شحم متعدد الاغراض","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("shm").child("shm2");
                            reference.setValue(shm2);
                            benz shm3=new benz("شحم ليثومي","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("shm").child("shm3");
                            reference.setValue(shm3);

                            //oil
                            benz oil1=new benz("زيت ديزل سريع مخصوص 50","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("oil").child("oil1");
                            reference.setValue(oil1);
                            benz oil2=new benz("زيت ديزل سريع مخصوص 40","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("oil").child("oil2");
                            reference.setValue(oil2);
                            benz oil3=new benz("زيت ديزل سريع مخصوص 30","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("oil").child("oil3");
                            reference.setValue(oil3);
                            benz oil4=new benz("زيت ديزل سريع مخصوص 10","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("oil").child("oil4");
                            reference.setValue(oil4);
                            benz oil5=new benz("زيت محرك ب/د 50/20","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("oil").child("oil5");
                            reference.setValue(oil5);
                            benz oil6=new benz("زيت هيدروماتيك 3","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("oil").child("oil6");
                            reference.setValue(oil6);
                            benz oil7=new benz("زيت هيدروليكي 68","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("oil").child("oil7");
                            reference.setValue(oil7);
                            benz oil8=new benz("زيت هيدروليكي 46","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("oil").child("oil8");
                            reference.setValue(oil8);
                            benz oil9=new benz("زيت هيدروليكي 32","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("oil").child("oil9");
                            reference.setValue(oil9);
                            benz oil10=new benz("زيت هيدروليكي 22","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("oil").child("oil10");
                            reference.setValue(oil10);
                            benz oil11=new benz("زيت زيت تروس متعدد الدرجات  ","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("oil").child("oil11");
                            reference.setValue(oil11);
                            //liqued
                            benz liq1=new benz("سائل استيول ام","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("liq").child("liq1");
                            reference.setValue(liq1);
                            benz liq2=new benz("سائل فرامل","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("liq").child("liq2");
                            reference.setValue(liq2);
                            benz liq3=new benz("سائل مانع للصدأ","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("liq").child("liq3");
                            reference.setValue(liq3);
                            benz liq4=new benz("سائل ازالة للصدأ","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("liq").child("liq4");
                            reference.setValue(liq4);
                            benz liq5=new benz("سائل مبرد مانع للصدأ","0");
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid)
                                    .child("package").child("liq").child("liq5");
                            reference.setValue(liq5);
                        }
                        else {
                            Toast.makeText(RegisterActivity.this,"you can't register with this email and password",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
