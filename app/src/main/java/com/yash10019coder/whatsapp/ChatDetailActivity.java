package com.yash10019coder.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.yash10019coder.whatsapp.adapter.ChatAdapter;
import com.yash10019coder.whatsapp.databinding.ActivityChatDetailBinding;
import com.yash10019coder.whatsapp.models.MessageModel;

import java.util.ArrayList;
import java.util.Date;

public class ChatDetailActivity extends AppCompatActivity {

    ActivityChatDetailBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityChatDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        final String senderID = auth.getUid();
        Log.i("hiya", "senderID  " + senderID);
        String receiveID = getIntent().getStringExtra("userID");
        Log.i("hiya", "receiverID  " + receiveID);
        String profilePic = getIntent().getStringExtra("profilePic");
        String username = getIntent().getStringExtra("username");

        binding.username.setText(username);
        Picasso.get().load(profilePic).placeholder(R.drawable.ic_user).into(binding.profileImage);


        final ArrayList<MessageModel> messageModels = new ArrayList<>();

        final ChatAdapter chatAdapter = new ChatAdapter(messageModels, this);
        binding.ChatRecyclerView.setAdapter(chatAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.ChatRecyclerView.setLayoutManager(linearLayoutManager);

        final String senderRoom = senderID + receiveID;
        final String receierRoom = receiveID + senderID;

        database.getReference().child("Chats").child(senderRoom).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                messageModels.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    MessageModel model = snapshot1.getValue(MessageModel.class);
                    messageModels.add(model);
                }
                chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        linearLayoutManager.setStackFromEnd(true);
        binding.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Message = binding.chatBox.getText().toString();

                if (!Message.equals("")) {
                    final MessageModel model = new MessageModel(senderID, Message, new Date().getTime());
//                    model.setTimestamp(new Date().getTime());
                    binding.chatBox.setText("");
                    database.getReference()
                            .child("Chats")
                            .child(senderRoom)
                            .push()
                            .setValue(model)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    database.getReference()
                                            .child("Chats")
                                            .child(receierRoom)
                                            .push()
                                            .setValue(model)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {


                                                }
                                            });

                                }
                            });
                }
            }
        });

    }

    public void back(View view) {
        finish();
    }
}