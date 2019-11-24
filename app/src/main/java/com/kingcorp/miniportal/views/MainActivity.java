package com.kingcorp.miniportal.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kingcorp.miniportal.R;
import com.kingcorp.miniportal.adapters.MoviesListAdapter;
import com.kingcorp.miniportal.listeners.PaginationListener;
import com.kingcorp.miniportal.models.MovieFullInfo;
import com.kingcorp.miniportal.models.MoviesListData;
import com.kingcorp.miniportal.presenters.MainPresenterImpl;
import com.kingcorp.miniportal.presenters.interfaces.MainPresenter;
import com.kingcorp.miniportal.utils.Constants;
import com.kingcorp.miniportal.views.interfaces.MainView;


public class MainActivity extends AppCompatActivity implements MainView {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgress;
    private View mNoInternetView;
    private Button mReloadBtn;
    private ProgressBar mFooterProgress;
    private Button mFooterReloadBtn;
    private View mFooter;

    private MoviesListAdapter mAdapter;
    private MainPresenter mPresenter;

    private boolean mIsLoading = false;
    private boolean mIsLastPage = false;
    private int mTotalItem;
    private int mItemPerPage;
    private int mCurrentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mReloadBtn.setOnClickListener(view -> mPresenter.loadMovieList(mCurrentPage));

        mFooterReloadBtn.setOnClickListener(view -> mPresenter.loadMovieList(mCurrentPage));

        mPresenter = new MainPresenterImpl(this);

        setupRecyclerView();

        mPresenter.setupObservers(this);
        mPresenter.loadMovieList(mCurrentPage);
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.recycler);
        mNoInternetView = findViewById(R.id.no_internet_view);
        mProgress = findViewById(R.id.progress_bar);
        mReloadBtn = mNoInternetView.findViewById(R.id.reload_btn);

        mFooter = findViewById(R.id.footer);
        mFooterProgress = findViewById(R.id.progress_footer);
        mFooterReloadBtn = findViewById(R.id.footer_reload);
    }

    private void setupRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MoviesListAdapter(mPresenter.getMovieClickListener());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                mCurrentPage += 1;
                mPresenter.loadMovieList(mCurrentPage);
            }

            @Override
            public int getTotalPageCount() {
                return mTotalItem/mItemPerPage;
            }

            @Override
            public boolean isLastPage() {
                return mIsLastPage;
            }

            @Override
            public boolean isLoading() {
                return mIsLoading;
            }
        });
    }

    @Override
    public void showMovieList(MoviesListData moviesListData) {
        mTotalItem = moviesListData.getTotalItems();
        mItemPerPage = moviesListData.getItemPerPage();

        mAdapter.addAll(moviesListData.getMovies());
    }

    @Override
    public void openMovieFullInfo(MovieFullInfo movie) {
        Intent intent = new Intent(this, MovieInfoActivity.class);
        intent.putExtra(Constants.MOVIE_FULL_KEY, movie);
        startActivity(intent);
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
        mNoInternetView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mNoInternetView.setVisibility(View.GONE);
    }

    @Override
    public void showNoInternetConnection() {
        mProgress.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        mNoInternetView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFooterLoading() {
        mIsLoading = true;
        if (mCurrentPage * mItemPerPage <= mTotalItem) {
            mFooter.setVisibility(View.VISIBLE);
            mFooterProgress.setVisibility(View.VISIBLE);
            mFooterReloadBtn.setVisibility(View.GONE);
        }
        else
            mIsLastPage = true;
    }

    @Override
    public void hideFooterLoading() {
        mIsLoading = false;
        mFooter.setVisibility(View.GONE);
        mFooterProgress.setVisibility(View.GONE);
        mFooterReloadBtn.setVisibility(View.GONE);
    }

    @Override
    public void showFooterNoInternetConnection() {
        mFooter.setVisibility(View.VISIBLE);
        mFooterReloadBtn.setVisibility(View.VISIBLE);
        mFooterProgress.setVisibility(View.GONE);
    }
}
