package net.fpt.appchat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.fpt.appchat.R;
import net.fpt.appchat.model.Messenger;

import java.util.ArrayList;

public class MessAdapter extends RecyclerView.Adapter<MessAdapter.MyAdapter> {
    private ArrayList<Messenger> lsMess;
    private final String mailSent;
    public static final int VIEW_TYPE_SEND = 1;
    public static final int VIEW_TYPE_RECIEVED = 2;

    public MessAdapter(ArrayList<Messenger> lsMess, String mailSent) {
        this.lsMess = lsMess;
        this.mailSent = mailSent;
    }

    @NonNull
    @Override
    public MessAdapter.MyAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new MyAdapter(LayoutInflater.from(parent.getContext()).inflate(R.layout.sent_mess, null));
        } else {
            return new MyAdapter(LayoutInflater.from(parent.getContext()).inflate(R.layout.re_mess, null));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessAdapter.MyAdapter holder, int position) {
        Messenger messenger = this.lsMess.get(position);
        holder.tvMess.setText(messenger.getNoiDung());
    }

    @Override
    public int getItemCount() {
        return lsMess.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (lsMess.get(position).getMailSent().equals(mailSent)) {
            return VIEW_TYPE_SEND;
        } else {
            return VIEW_TYPE_RECIEVED;
        }
    }

    public class MyAdapter extends RecyclerView.ViewHolder {
        TextView tvMess;

        public MyAdapter(@NonNull View itemView) {
            super(itemView);
            tvMess = itemView.findViewById(R.id.tv_mess);

        }
    }
}
