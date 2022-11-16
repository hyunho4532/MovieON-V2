package com.example.intentname.register;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.intentname.MainActivity;
import com.example.intentname.R;
import com.example.intentname.register.address.SearchActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEtAddress, et_email, et_password;

    private FirebaseAuth mAuth;

    private Button btn_register_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEtAddress = findViewById(R.id.et_address);
        mEtAddress.setFocusable(false);

        btn_register_check = (Button)findViewById(R.id.btn_register_check);

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);

        mAuth = FirebaseAuth.getInstance();

        mEtAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, SearchActivity.class);
                getSearchResult.launch(intent);
            }
        });

        btn_register_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_email.getText().toString().equals("") && !et_password.getText().toString().equals("")) {
                    createUser(et_email.getText().toString(), et_password.getText().toString());
                }
            }
        });
    }

    private void createUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "회원가입 정상 처리되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "회원가입 이상 에러", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private final ActivityResultLauncher<Intent> getSearchResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null) {
                        String data = result.getData().getStringExtra("data");
                        mEtAddress.setText(data);
                    }
                }
            }
    );
}