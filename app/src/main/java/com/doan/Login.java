package com.doan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText txtUsername, txtPass;
    TextView tvDK,tvTT;
    Button btnLogin;
    ImageButton btnBack;
    private DBHandler dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControls();
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        tvTT.setText("Đăng nhập");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        dbHelper = new DBHandler(this);
        tvDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(view.getContext(),Register.class);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(view.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin tên đăng nhập và mật khẩu từ EditText
                String username = txtUsername.getText().toString().trim();
                String password = txtPass.getText().toString().trim();

                // Kiểm tra xem tên đăng nhập và mật khẩu có rỗng hay không
                if (username.isEmpty()) {
                    // Nếu tên đăng nhập rỗng thì hiển thị thông báo cho người dùng nhập lại
                    Toast.makeText(Login.this, "Vui lòng nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
                }
                else
                if (password.isEmpty()) {
                    // Nếu mật khẩu rỗng thì hiển thị thông báo cho người dùng nhập lại
                    Toast.makeText(Login.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                }
                else
                // Kiểm tra xem tên đăng nhập và mật khẩu có chính xác hay không bằng cách truy vấn vào database
                if (dbHelper.checkUser(username, password)) {
                    // Nếu chính xác thì chuyển sang màn hình chính
                    User.userName = username;
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    // Nếu sai thì hiển thị thông báo cho người dùng nhập lại
                    Toast.makeText(Login.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void addControls()
    {
        tvDK = (TextView) findViewById(R.id.tvDK);
        txtUsername=(EditText) findViewById(R.id.txtUsername);
        txtPass=(EditText) findViewById(R.id.txtPass);
        btnLogin=(Button) findViewById(R.id.btnDK);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        tvTT=(TextView) findViewById(R.id.tvTitle);
    }
}