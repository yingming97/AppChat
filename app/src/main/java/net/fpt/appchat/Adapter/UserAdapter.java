package net.fpt.appchat.Adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.fpt.appchat.ChatActivity;
import net.fpt.appchat.R;
import net.fpt.appchat.model.User;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    private ArrayList<User> lsUser;

    public UserAdapter(ArrayList<User> lsUser) {
        this.lsUser = lsUser;
    }


    @Override
    public int getCount() {
        return this.lsUser.size();
    }

    @Override
    public User getItem(int i) {
        return this.lsUser.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v;
        if (view == null) {
            v = View.inflate(viewGroup.getContext(), R.layout.row_user, null);
        } else {
            v = view;
        }
        TextView tvHoTen = v.findViewById(R.id.tv_ho_ten);
        User user = this.lsUser.get(i);
        tvHoTen.setText(user.getHoTen());
        tvHoTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewGroup.getContext(), ChatActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("ls", lsUser);
                viewGroup.getContext().startActivity(intent);
            }
        });
        return v;
    }

}
