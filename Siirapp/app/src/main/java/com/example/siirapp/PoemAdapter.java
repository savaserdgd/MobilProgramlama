package com.example.siirapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PoemAdapter extends RecyclerView.Adapter<PoemAdapter.ViewHolder> {
    private Context context;
    private List<Poem> poemList;

    public PoemAdapter(Context context, List<Poem> poemList) {
        this.context = context;
        this.poemList = poemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_poem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Poem poem = poemList.get(position);
        holder.title.setText(poem.getTitle());
        holder.author.setText(poem.getAuthor());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PoemDetailActivity.class);
            intent.putExtra("title", poem.getTitle());
            intent.putExtra("author", poem.getAuthor());
            intent.putExtra("content", poem.getContent());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return poemList.size();
    }

    public void updateList(List<Poem> newList) {
        poemList = newList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, author;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.poemTitle);
            author = itemView.findViewById(R.id.poemAuthor);
        }
    }
}