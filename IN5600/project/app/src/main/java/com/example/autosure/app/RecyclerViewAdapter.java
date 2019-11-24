package com.example.autosure.app;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autosure.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Integer> mClaimID = new ArrayList<>();
    private ArrayList<String> mClaimTitle = new ArrayList<>();
    private Context mContext;
    private int _sessionid;

    public RecyclerViewAdapter(ArrayList<Integer> mClaimID, ArrayList<String> mClaimTitle, Context mContext, int _sessionid) {
        this.mClaimID = mClaimID;
        this.mClaimTitle = mClaimTitle;
        this.mContext = mContext;
        this._sessionid = _sessionid;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.relativelayouthistorylist, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.claimID.setText(Integer.toString(mClaimID.get(i)));
        viewHolder.claimTitle.setText(mClaimTitle.get(i));

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(mContext, HistorySpecificClaim.class);

                intent.putExtra("com.example.autosure.TITLE", mClaimTitle.get(i));
                intent.putExtra("com.example.autosure.POSITION", mClaimID.get(i));
                intent.putExtra("com.example.autosure.SESSIONID", _sessionid);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mClaimID.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView claimID;
        TextView claimTitle;
        RelativeLayout parentLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            claimID = itemView.findViewById(R.id.claimIDrelative);
            claimTitle = itemView.findViewById(R.id.claimTitlerelative);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }

        @Override
        public void onClick(View v){
            Intent intent = new Intent(mContext, HistorySpecificClaim.class);
            intent.putExtra("com.example.autosure.POSITION", claimID.getText().toString());
            intent.putExtra("com.example.autosure.SESSIONID", _sessionid);
            mContext.startActivity(intent);
        }
    }
}
