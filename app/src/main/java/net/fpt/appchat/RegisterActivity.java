package net.fpt.appchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import net.fpt.appchat.model.User;

public class RegisterActivity extends AppCompatActivity {
    EditText edEmail, edPass, edHoTen;
    Button btnDangKy;
    FirebaseAuth auth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        edEmail = findViewById(R.id.ed_email);
        edPass = findViewById(R.id.ed_pass);
        edHoTen = findViewById(R.id.ed_ho_ten);
        btnDangKy = findViewById(R.id.btn_dang_ky);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edEmail.getText().toString();
                String pass = edPass.getText().toString();
                String hoTen = edHoTen.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(hoTen)) {
                    Toast.makeText(RegisterActivity.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(email, pass, hoTen,"");
                    themUser(user);
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                }
            }
        });
    }

    public void themUser(User user) {
        firestore.collection(User.TB_NAME).document()
                .set(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            creatUser(user);
                            Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    public void creatUser(User user) {
        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPass());
    }
}