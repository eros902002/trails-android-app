package com.erostech.trails;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by erosgarciaponte on 18/04/2017.
 */

public class PostViewHolder extends RecyclerView.ViewHolder {
    private TextView mTextView;

    public PostViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView;
    }

    public void bind(Post post) {
        if (post != null && post.getTitle() != null) {
            mTextView.setText(post.getTitle());
        }
    }
}
