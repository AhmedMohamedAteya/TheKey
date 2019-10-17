package apps.pixel.al.egykey.activites.retaurant.videoActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import apps.pixel.al.egykey.R;
import apps.pixel.al.egykey.utilities.Constant;

import static apps.pixel.al.egykey.utilities.Constant.KEY_VIDEO_URL;


public class VideoRestuarantActivity extends Activity implements Player.EventListener {


    private static final String TAG = "ExoPlayerActivity";
    private static final String KEY_VIDEO_URI = "video_uri";
    PlayerView videoFullScreenPlayer;
    ProgressBar spinnerVideoDetails;
    ImageView imageViewExit;
    String videoUri;
    SimpleExoPlayer player;
    Handler mHandler;
    Runnable mRunnable;
    private int isPlaying = 1;
    private int heightOfVideo;
    //private AppCompatImageView mImg;
    private FrameLayout mFrame;
    private AppCompatImageView mImgExist, mBtnPlayPause;
    private AppCompatImageView imageViewFull;

    private RelativeLayout mRelative;
    private long currentPositionPortrait;
    private long currentPositionLandscape;


    public static Intent getStartIntent(Context context, String videoUri) {
        Intent intent = new Intent(context, VideoRestuarantActivity.class);
        intent.putExtra(KEY_VIDEO_URI, videoUri);
        return intent;
    }

    private void pausePlayer() {
        if (player != null) {
            player.setPlayWhenReady(false);
            player.getPlaybackState();
        }
    }

    private void startPlayer() {
        if (player != null) {
            player.setPlayWhenReady(true);
            player.getPlaybackState();
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d("tag", "config changed");
        super.onConfigurationChanged(newConfig);

//        Log.d(TAG, "onConfigurationChanged: " + currentPositionPortrait);
//        if (player != null) {
//            player.seekTo(currentPositionPortrait);
//        }
//
//        int orientation = newConfig.orientation;
//        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
//            Log.d("CHANED_", "PORTRAIT");
//            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                Log.d("CHANED_", "LANDSCAPE");
//            }
//        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            setTheme(R.style.Theme_TransparentPortraint);
        } else {
            setTheme(R.style.Theme_TransparentLandscape);
        }
        setContentView(R.layout.activity_video_restuarant);


        if (getIntent().hasExtra(KEY_VIDEO_URL)) {
            videoUri = getIntent().getStringExtra(KEY_VIDEO_URL);
            Log.d("videoUri", "getSelectedRestaurantData: " + Constant.BASE_PATH_MEDIA + videoUri);

            initViews();

            //  mImg = findViewById(R.id.img);
            mFrame = findViewById(R.id.frame);

            setUp();

            videoFullScreenPlayer.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
            player.setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);


            imageViewFull = findViewById(R.id.imageViewFull);

            try {
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    imageViewFull.setImageDrawable(getResources().getDrawable(R.drawable.exo_controls_fullscreen_enter));

                    imageViewFull.setOnClickListener(v -> {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    });

                } else {
                    imageViewFull.setImageDrawable(getResources().getDrawable(R.drawable.exo_controls_fullscreen_exit));

                    imageViewFull.setOnClickListener(v -> {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                    });
                }


            } catch (NullPointerException ignored) {

            }
            heightOfVideo = videoFullScreenPlayer.getLayoutParams().height;
            Log.d(TAG, "onCreate: " + heightOfVideo);

            mImgExist = findViewById(R.id.imageViewExit);
            mImgExist.setOnClickListener(v -> {
                onBackPressed();
            });

            mBtnPlayPause = findViewById(R.id.play_pause_btn);
            mBtnPlayPause.setOnClickListener(v -> {

                if (isPlaying == 1) {
                    mBtnPlayPause.setImageDrawable(getResources().getDrawable(R.drawable.exo_icon_play));
                    pausePlayer();
                    isPlaying = 0;
                } else {
                    mBtnPlayPause.setImageDrawable(getResources().getDrawable(R.drawable.exo_controls_pause));
                    resumePlayer();
                    isPlaying = 1;
                }

                if (isPlaying == 3) {
                    player.setPlayWhenReady(true);
                    isPlaying = 1;
                }

            });


            mRelative = findViewById(R.id.relative);


        }

    }

    private void setUp() {
        initializePlayer();
        if (videoUri == null) {
            return;
        }
        buildMediaSource(Uri.parse(videoUri));
    }

    private void initViews() {
        videoFullScreenPlayer = findViewById(R.id.videoFullScreenPlayer);
        videoFullScreenPlayer.setControllerAutoShow(false);
        videoFullScreenPlayer.setControllerHideOnTouch(true);
        videoFullScreenPlayer.hideController();
        spinnerVideoDetails = findViewById(R.id.spinnerVideoDetails);


        imageViewExit = findViewById(R.id.imageViewExit);

    }

    private void initializePlayer() {
        if (player == null) {

            LoadControl loadControl = new DefaultLoadControl();

            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory videoTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(bandwidthMeter);
            TrackSelector trackSelector =
                    new DefaultTrackSelector(videoTrackSelectionFactory);
            // 2. Create the player
            player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(this), trackSelector, loadControl);
            videoFullScreenPlayer.setPlayer(player);
        }


    }

    private void buildMediaSource(Uri mUri) {
        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, getString(R.string.app_name)), bandwidthMeter);
        // This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(mUri);
        // Prepare the player with the source.
        player.prepare(videoSource);
        player.setPlayWhenReady(true);
        player.addListener(this);
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }


    private void resumePlayer() {
        if (player != null) {
            player.setPlayWhenReady(true);
            player.getPlaybackState();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        pausePlayer();
        if (mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        resumePlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {
    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        long currentPosition = player.getContentPosition();

        //  Log.d(TAG, "onPlayerStateChanged: " + currentPosition);
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            currentPositionPortrait = player.getCurrentPosition();
            player.seekTo(currentPositionLandscape);
            Log.d(TAG, "onPlayerStateChanged: " + currentPositionLandscape);
        }
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            currentPositionLandscape = player.getCurrentPosition();
            player.seekTo(currentPositionLandscape);
            Log.d(TAG, "onPlayerStateChanged: " + currentPositionPortrait);

        }

        switch (playbackState) {

            case Player.STATE_BUFFERING:
                spinnerVideoDetails.setVisibility(View.VISIBLE);
                mBtnPlayPause.setVisibility(View.GONE);

                // currentPositionPortrait = player.getBufferedPosition();
                break;
            case Player.STATE_ENDED:
                mBtnPlayPause.setImageDrawable(getResources().getDrawable(R.drawable.exo_controls_repeat_all));
                player.seekTo(0);
                player.setPlayWhenReady(false);
                isPlaying = 3;
                mBtnPlayPause.setVisibility(View.VISIBLE);
                break;
            case Player.STATE_IDLE:
                mBtnPlayPause.setVisibility(View.VISIBLE);
                break;
            case Player.STATE_READY:
                mBtnPlayPause.setVisibility(View.VISIBLE);
                spinnerVideoDetails.setVisibility(View.GONE);

                break;
            default:
                // status = PlaybackStatus.IDLE;
                break;
        }


        // player.seekTo(currentPositionPortrait);

    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}

//            mFrame.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View view, MotionEvent event) {
//
//                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
//                    if (view.getId() != R.id.frame) return false;
//
//                    switch (event.getAction()) {
//                        case MotionEvent.ACTION_MOVE:
//                            params.topMargin = (int) event.getRawY() - view.getHeight();
//                            params.leftMargin = (int) event.getRawX() - (view.getWidth() / 2);
//                            view.setLayoutParams(params);
//                            break;
//
//                        case MotionEvent.ACTION_UP:
//                            params.topMargin = (int) event.getRawY() - view.getHeight();
//                            params.leftMargin = (int) event.getRawX() - (view.getWidth() / 2);
//                            view.setLayoutParams(params);
//                            break;
//
//                        case MotionEvent.ACTION_DOWN:
//                            view.setLayoutParams(params);
//                            break;
//                    }
//
//                    return true;
//                }
//            });


//            mBtnFullScreenEnter.setOnClickListener(v -> {
//
////                if (isInFullScreenMode = false) {
////                    isInFullScreenMode = true;
////
////                    Toast.makeText(this, "MAX", Toast.LENGTH_SHORT).show();
////                    // mRelative.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
////                    //    videoFullScreenPlayer.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
////                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
////                    mBtnFullScreen.setImageDrawable(getResources().getDrawable(R.drawable.exo_controls_fullscreen_exit));
////                } else {
////                    isInFullScreenMode = false;
////
//////                    final float width = getResources().getDimension(R.dimen._200sdp);
//////                    int pixels = (int) (200 * width + 0.5f);
////                    Toast.makeText(this, "MIN", Toast.LENGTH_SHORT).show();
////                    //  mRelative.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
////                    //   videoFullScreenPlayer.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, heightOfVideo));
////                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
////                    mBtnFullScreen.setImageDrawable(getResources().getDrawable(R.drawable.exo_controls_fullscreen_exit));
////
////                }
//            });
