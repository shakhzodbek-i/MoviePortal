package com.kingcorp.miniportal.views;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kingcorp.miniportal.R;
import com.kingcorp.miniportal.utils.Constants;

public class VideoActivity extends AppCompatActivity {
    private VideoView mVideoPlayer;
    private ProgressBar mProgressBar;
    private MediaController mMediaController;
    private Uri mVideoUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (getIntent() != null)
            mVideoUri = Uri.parse(getIntent().getStringExtra(Constants.VIDEO_URL_KEY));

        mVideoPlayer = findViewById(R.id.player);
        mProgressBar = findViewById(R.id.video_progress);

        setupVideoPlayer();
    }

    private void setupVideoPlayer() {
        mMediaController = new MediaController(this);
        mMediaController.setAnchorView(mVideoPlayer);
        mVideoPlayer.setMediaController(mMediaController);
        mVideoPlayer.setVideoURI(mVideoUri);
        mVideoPlayer.setOnPreparedListener(mediaPlayer -> mProgressBar.setVisibility(View.GONE));
        mVideoPlayer.start();
    }
}
