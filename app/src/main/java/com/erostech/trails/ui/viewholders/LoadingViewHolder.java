package com.erostech.trails.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.erostech.trails.R;
import com.erostech.trails.callbacks.LoadingViewHolderCallback;
import com.erostech.trails.callbacks.PaginationAdapterCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by erosgarciaponte on 19/4/17.
 */

public class LoadingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.loadmore_progress) ProgressBar progressBar;
    @BindView(R.id.loadmore_retry) ImageButton retryButton;
    @BindView(R.id.loadmore_errortxt) TextView errorText;
    @BindView(R.id.loadmore_error_layout) LinearLayout errorLayout;

    private PaginationAdapterCallback mCallback;
    private LoadingViewHolderCallback mRetryCallback;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        retryButton.setOnClickListener(this);
        errorLayout.setOnClickListener(this);
    }

    public void bind(PaginationAdapterCallback callback, LoadingViewHolderCallback retryCallback, boolean retryPageLoad, String errorMessage) {
        this.mCallback = callback;
        this.mRetryCallback = retryCallback;
        if (retryPageLoad) {
            errorLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            errorText.setText(errorMessage != null ? errorMessage : itemView.getContext().getString(R.string.error_msg_unknown));
        } else {
            errorLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loadmore_retry:
            case R.id.loadmore_error_layout:
                mRetryCallback.showRetry(false, null);
                mCallback.retryPageLoad();
                break;
        }
    }
}
