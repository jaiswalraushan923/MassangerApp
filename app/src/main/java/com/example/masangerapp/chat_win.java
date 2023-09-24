package com.example.masangerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class chat_win extends AppCompatActivity {

    String recieverimg, recieverUid,recieverName,SenderUID;
    CircleImageView profile;
    TextView recieverNName;
    CardView sendbtn;
    EditText textmsg;
    FirebaseAuth firebaseAuth;
     FirebaseDatabase database;

     public static String senderImg;
     public static String recieverIImg;
     String senderRoom, recieverRoom;
     RecyclerView massagesAdapter;
     ArrayList<msgModelclass> massagesArrayList;
     massagesAdapter mmassagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_win);

        database=FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        recieverName=getIntent().getStringExtra("nameeee");
        recieverimg=getIntent().getStringExtra("recieverImg");
        recieverUid=getIntent().getStringExtra("uid");

        massagesArrayList=new ArrayList<>();

        sendbtn= findViewById(R.id.sendbtnn);
        textmsg= findViewById(R.id.textmsg);
        profile =findViewById(R.id.profileimgg);
        recieverNName =findViewById(R.id.recivername);

        massagesAdapter=findViewById(R.id.msgadpter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        massagesAdapter.setLayoutManager(linearLayoutManager);
        mmassagesAdapter =new massagesAdapter(chat_win.this,massagesArrayList);
        massagesAdapter.setAdapter(mmassagesAdapter);

        Picasso.get().load(recieverimg).into(profile);
        recieverNName.setText(""+recieverName);

        SenderUID=firebaseAuth.getUid();
        senderRoom=SenderUID+recieverUid;
        recieverRoom=recieverUid+SenderUID;


        DatabaseReference reference=database.getReference().child("user").child(firebaseAuth.getUid());
        DatabaseReference chatreference = database.getReference().child("chats").child(senderRoom).child("massages");

        chatreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                massagesArrayList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    msgModelclass massages=dataSnapshot.getValue(msgModelclass.class);
                    massagesArrayList.add(massages);
                }
                mmassagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                senderImg=snapshot.child("profilepic").getValue().toString();
                recieverIImg=recieverimg;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String massage=textmsg.getText().toString();
                if(massage.isEmpty()) {
                    Toast.makeText(chat_win.this, "Enter the Massage first", Toast.LENGTH_SHORT).show();
                }

                    textmsg.setText("");
                    Date date=new Date();
                    msgModelclass massagess = new msgModelclass(massage,SenderUID,date.getTime());
                    database=FirebaseDatabase.getInstance();
                    database.getReference().child("chats").child(senderRoom).child("massages").push().setValue(massagess).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            database.getReference().child("chats").child(recieverRoom).child("massages").push().setValue(massagess).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            });
                        }
                    });
            }
        });
    }
}