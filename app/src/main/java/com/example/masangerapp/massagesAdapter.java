package com.example.masangerapp;

import static com.example.masangerapp.chat_win.recieverIImg;
import static com.example.masangerapp.chat_win.senderImg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class massagesAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<msgModelclass> massagesAdapterArrayList;
    int ITEM_SEND=1;
    int ITEM_RECIEVE=2;

    public massagesAdapter(Context context, ArrayList<msgModelclass> massagesAdapterArrayList) {
        this.context = context;
        this.massagesAdapterArrayList = massagesAdapterArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==ITEM_SEND){
            View view = LayoutInflater.from(context).inflate(R.layout.sender_layout,parent,false);
            return new senderViewHolder(view);
        }
        else{
            View view = LayoutInflater.from(context).inflate(R.layout.rreciever_layout,parent,false);
            return new recieverViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        msgModelclass massages = massagesAdapterArrayList.get(position);
        if(holder.getClass()==senderViewHolder.class){
            senderViewHolder viewHolder=(senderViewHolder) holder;
            viewHolder.msgtxt.setText(massages.getMassage());
            Picasso.get().load(senderImg).into(viewHolder.circleImageView);

        }
        else{
            recieverViewHolder viewHolder=(recieverViewHolder)  holder;
            viewHolder.msgtxt.setText(massages.getMassage());
            Picasso.get().load(recieverIImg).into(viewHolder.circleImageView);
        }



    }

    @Override
    public int getItemCount() {
        return massagesAdapterArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        msgModelclass massages = massagesAdapterArrayList.get(position);
        if(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid().equals(massages.getSenderid())){
            return ITEM_SEND;
        }
        else{
            return ITEM_RECIEVE;
        }
    }

    class senderViewHolder extends RecyclerView.ViewHolder{
        CircleImageView circleImageView;
        TextView msgtxt;

        public senderViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.profilerggg);
            msgtxt=itemView.findViewById(R.id.msgsendertyp);
        }
    }

    class recieverViewHolder extends RecyclerView.ViewHolder{
        CircleImageView circleImageView;
        TextView msgtxt;

        public recieverViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.pro);
            msgtxt=itemView.findViewById(R.id.recivertextset);
        }
    }

}
