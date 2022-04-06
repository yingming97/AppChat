package net.fpt.appchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import net.fpt.appchat.Adapter.MessAdapter;
import net.fpt.appchat.model.Messenger;
import net.fpt.appchat.model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {
    TextView tvName;
    RecyclerView recyclerView;
    MessAdapter messAdapter;
    User user, userSent;
    EditText edMess;
    Button btn_send;
    private DatabaseReference mDatabase;
    FirebaseUser muser;
    ArrayList<Messenger> lsMess;
    ArrayList<User> lsUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mDatabase = FirebaseDatabase.getInstance().getReference(Messenger.TB_NAME);
        muser = FirebaseAuth.getInstance().getCurrentUser();
        lsMess = new ArrayList<>();
        tvName = findViewById(R.id.tv_name);
        edMess = findViewById(R.id.ed_mess);
        btn_send = findViewById(R.id.btn_send);
        recyclerView = findViewById(R.id.rcv_mess);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        tvName.setText(user.getHoTen());
        messAdapter = new MessAdapter(lsMess, muser.getEmail());
        recyclerView.setAdapter(messAdapter);

        mDatabase.child(muser.getUid()+user.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        lsMess.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            Messenger messages = snapshot1.getValue(Messenger.class);
                            lsMess.add(messages);
                        }
                        messAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nd = edMess.getText().toString();
                Messenger messenger = new Messenger(nd, muser.getEmail());
                mDatabase
                        .child(muser.getUid()+user.getUid())
                        .push().setValue(messenger).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        mDatabase
                                .child(user.getUid()+muser.getUid())
                                .push()
                                .setValue(messenger).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                            }
                        });
                    }
                });
                edMess.setText(null);
            }
        });

    }
}