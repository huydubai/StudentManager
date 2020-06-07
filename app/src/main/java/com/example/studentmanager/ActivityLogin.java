package com.example.studentmanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import DB.DBManager;
import student.Login;

public class ActivityLogin extends AppCompatActivity {
    public static final int RESULT_CODE_SIGN_UP = 1;
    private TextInputLayout layoutUser, layoutpsw;
    private TextInputEditText edUser, edPsw;
    private Button btnLogin, btnSignUp;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        event();
        checkUser();
    }

    private void checkUser() {

    }

    private void event() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getTextUser() && getTextPsw()) {
                    if (checkAccount()){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(ActivityLogin.this, "Kiểm tra lại tài khoản", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivitySignUp.class);
                startActivityForResult(intent, RESULT_CODE_SIGN_UP);
            }
        });
    }

    private boolean checkAccount() {
        String userName = edUser.getText().toString();
        String psw= edPsw.getText().toString();
        if (dbManager.getLogin(userName,psw)){
            return true;
        }
        return false;
    }

    private boolean getTextUser() {
        if (layoutUser.getEditText().getText().toString().isEmpty()) {
            layoutUser.setError("Nhập tên tài khoản");
            return false;
        } else {
            layoutUser.setError(null);
            return true;
        }
    }

    private boolean getTextPsw() {
        if (layoutpsw.getEditText().getText().toString().isEmpty()) {
            layoutpsw.setError("Nhập mật khẩu");
            return false;
        } else {
            layoutpsw.setError(null);
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RESULT_CODE_SIGN_UP && resultCode == RESULT_OK && data != null) {
            String userName = data.getStringExtra("username");
            String psw = data.getStringExtra("psw");
            Login login = new Login(userName, psw);
            dbManager.addLogin(login);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init() {
        dbManager = new DBManager(getApplicationContext());
        layoutUser = findViewById(R.id.layout_userName);
        layoutpsw = findViewById(R.id.layout_password);
        edUser = findViewById(R.id.ed_userName);
        edPsw = findViewById(R.id.edPsw);
        btnLogin = findViewById(R.id.btn_Login);
        btnSignUp = findViewById(R.id.btn_SignUp);
    }

}
