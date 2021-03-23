package com.yash10019coder.whatsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.yash10019coder.whatsapp.ChatDetailActivity;
import com.yash10019coder.whatsapp.R;
import com.yash10019coder.whatsapp.models.Users;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    ArrayList<Users> list;
    Context context;

    public UsersAdapter(ArrayList<Users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_show_user,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users users=list.get(position);
        Picasso.get().load(users.getProfile_pic()).placeholder(R.drawable.ic_user).into(holder.imageView);
        holder.username.setText(users.getUsername());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Hiiiiiiiii", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, ChatDetailActivity.class);
                intent.putExtra("userID",users.getId());
//                Log.i("1234567", users.getId().toString());
                intent.putExtra("profilePic",users.getProfile_pic());
                intent.putExtra("username",users.getUsername());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView username,lastmessage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.profile_image);
            username=itemView.findViewById(R.id.username);
            lastmessage=itemView.findViewById(R.id.lastmessage);

        }
    }

}
