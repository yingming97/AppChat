package net.fpt.appchat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import net.fpt.appchat.Adapter.UserAdapter;
import net.fpt.appchat.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    UserAdapter userAdapter;
    FirebaseFirestore firestore;
    FirebaseUser user;
    ArrayList<User> lsUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firestore = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        lsUser = new ArrayList();
        lv = findViewById(R.id.ls_user);
        userAdapter = new UserAdapter(getlsUser());
        lv.setAdapter(userAdapter);
        userAdapter.notifyDataSetChanged();
    }

    public ArrayList<User> getlsUser() {
        firestore.collection(User.TB_NAME).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                lsUser.clear();
                for (QueryDocumentSnapshot snapshot : value
                ) {
                    User u = snapshot.toObject(User.class);
                    Log.d("TAG", "onEvent: " + u.toString());
                    if (!u.getEmail().equals(user.getEmail())) {
                        lsUser.add(u);
                        userAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        return lsUser;
    }


}