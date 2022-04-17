package com.example.nguyenngoctan_19dh110190;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {
    TextView tv1,google_signIn;
    EditText input_email,input_password;
    Button btnSignIn;
    FirebaseAuth fAuth;
    FirebaseDatabase fDatabase;
    String emailPattern="[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        tv1=findViewById(R.id.btnSignUp);
        google_signIn = findViewById(R.id.btnSignInGoogle);

        fAuth = FirebaseAuth.getInstance();
        fDatabase = FirebaseDatabase.getInstance();

        input_email = findViewById(R.id.txtEmail);
        input_password=findViewById(R.id.txtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perforLogin();
            }
        });


        google_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SignInActivity.this,GoogleSignInActivity.class);
                startActivity(intent);
            }
        });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
    private void perforLogin(){
        String email = input_email.getText().toString();
        String password = input_password.getText().toString();

        if(email.isEmpty()){
            if(password.isEmpty()){
                input_email.setError("Vui lòng nhập tài khoản");
                input_password.setError("Vui lòng nhập mật khẩu");
            }else {
                input_email.setError("Vui lòng nhập tài khoản");
            }
        }else if(!email.matches(emailPattern))
        {
            input_email.setError("Tài khoản không hợp lệ");
        }else if(password.length()<6){
            input_password.setError("Độ dài phải từ 6 kí tự trở lên");
        }else {
            fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignInActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignInActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(SignInActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}