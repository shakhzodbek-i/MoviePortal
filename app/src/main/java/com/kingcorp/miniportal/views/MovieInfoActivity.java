package com.kingcorp.miniportal.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.kingcorp.miniportal.R;
import com.kingcorp.miniportal.models.MovieFullInfo;
import com.kingcorp.miniportal.utils.Constants;
import com.kingcorp.miniportal.views.interfaces.MovieInfoView;

import io.github.armcha.coloredshadow.ShadowImageView;

public class MovieInfoActivity extends AppCompatActivity implements MovieInfoView {

    private ShadowImageView mPoster;
    private TextView mTitle;
    private TextView mYear;
    private TextView mGenres;
    private TextView mSummary;
    private TextView mCountries;
    private Button mWatchVideoBtn;

    private MovieFullInfo mMovieInfo;
    private String DEMO_VIDEO_LINK = "https://files.itv.uz//assets//help//subscription_needed.mp4";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (getIntent() != null)
            mMovieInfo = getIntent().getParcelableExtra(Constants.MOVIE_FULL_KEY);

        mPoster = findViewById(R.id.poster);
        mTitle = findViewById(R.id.title);
        mYear = findViewById(R.id.year);
        mGenres = findViewById(R.id.genres);
        mSummary = findViewById(R.id.summary);
        mCountries = findViewById(R.id.countries);
        mWatchVideoBtn = findViewById(R.id.watch_video_btn);

        mWatchVideoBtn.setOnClickListener(view -> {
            openVideo();
        });
        setupMovieInfo();
    }



    @Override
    public void setupMovieInfo() {
        mTitle.setText(mMovieInfo.getTitle());
        mYear.setText(String.valueOf(mMovieInfo.getYear()));
        mGenres.setText(mMovieInfo.getGenres());
        mSummary.setText(mMovieInfo.getDescription());
        mCountries.setText(mMovieInfo.getCountries());

        Glide.with(this)
                .load(mMovieInfo.getFiles().getPosterUrl())
                .centerCrop()
                .into(mPoster);
    }

    @Override
    public void openVideo() {
        Intent intent = new Intent(this, VideoActivity.class);
        intent.putExtra(Constants.VIDEO_URL_KEY, DEMO_VIDEO_LINK);
        startActivity(intent);
    }
}
