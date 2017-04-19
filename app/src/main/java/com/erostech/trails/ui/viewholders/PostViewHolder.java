package com.erostech.trails.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.erostech.trails.core.data.models.Post;

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
