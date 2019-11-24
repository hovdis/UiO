package com.example.autosure.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.autosure.R;
import com.example.autosure.datamodel.ClaimMessage;

import java.util.ArrayList;

public class RecyclerViewAdapterMessages extends RecyclerView.Adapter<RecyclerViewAdapterMessages.ViewHolder>{
    private ArrayList<ClaimMessage> mMessages = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapterMessages(ArrayList<ClaimMessage> mMessages, Context mContext) {
        this.mMessages = mMessages;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_messages, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.senderTextView.setText("Sender:" + mMessages.get(i).getSender());
        viewHolder.senderDateTextView.setText(mMessages.get(i).getDate());
        viewHolder.textContentTextView.setText(mMessages.get(i).getMessage());
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView senderTextView;
        TextView senderDateTextView;
        TextView textContentTextView;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            senderTextView = itemView.findViewById(R.id.senderName);
            senderDateTextView = itemView.findViewById(R.id.senderDate);
            textContentTextView = itemView.findViewById(R.id.textContent);
            parentLayout = itemView.findViewById(R.id.parent_layout_messages);
        }
    }
}
