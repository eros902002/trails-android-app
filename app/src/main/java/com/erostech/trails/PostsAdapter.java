package com.erostech.trails;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by erosgarciaponte on 18/04/2017.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostViewHolder> {
    private List<Post> mPosts;

    public PostsAdapter(List<Post> postList) {
        mPosts = postList;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        PostViewHolder viewHolder = new PostViewHolder(textView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.bind(mPosts.get(position));
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }
}
