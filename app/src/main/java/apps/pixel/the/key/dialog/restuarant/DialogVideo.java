package apps.pixel.the.key.dialog.restuarant;

import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import apps.pixel.the.key.R;
import apps.pixel.the.key.adapters.restaurant.DialogCityAdapter;
import apps.pixel.the.key.utilities.Constant;

public class DialogVideo extends DialogFragment implements DialogCityAdapter.OnClickHandler, Player.EventListener {


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

    private long currentPosition;

    private void pausePlayer() {
        if (player != null) {
            player.setPlayWhenReady(false);
            player.getPlaybackState();
            currentPosition = player.getCurrentPosition(); //then, save it on the bundle.


        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putLong(Constant.CURRENT_POSITION, currentPosition);

        Log.d("POSITION_TO_SAVE", "onSaveInstanceState: "+ currentPosition);

    }

    private void startPlayer() {
        if (player != null) {
            player.setPlayWhenReady(true);
            player.getPlaybackState();
            if (currentPosition != C.TIME_UNSET) player.seekTo(currentPosition);

        }
    }


//    @Override
//    public void onConfigurationChanged(@NonNull Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//
//        Log.d(TAG, "onConfigurationChanged: " + currentPositionPortrait);
//        if (player != null) {
//            player.seekTo(currentPositionPortrait);
//        }
//
//        int orientation = newConfig.orientation;
//        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
//            Log.d("CHANED_", "PORTRAIT" + currentPositionLandscape);
//            player.seekTo(currentPositionLandscape);
//            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                Log.d("CHANED_", "LANDSCAPE" + currentPositionPortrait);
//                player.seekTo(currentPositionPortrait);
//            }
//        }
//
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This removes black background below corners.
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_Dialog);
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_video_restuarant, container, true);
        if (getDialog() != null && getDialog().getWindow() != null) {

            getDialog().setCanceledOnTouchOutside(true);
            getDialog().setCancelable(true);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            getDialog().getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        }

        //  if (getArguments().hasExtra(KEY_VIDEO_URL)) {
        videoUri = getArguments().getString(Constant.KEY_VIDEO_URL);
        Log.d("videoUri", "getSelectedItemInCatData: " + Constant.BASE_PATH_MEDIA + videoUri);

        initViews(view);

        //  mImg = findViewById(R.id.img);
        mFrame = view.findViewById(R.id.frame);

        setUp();

        currentPosition = C.TIME_UNSET;
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getLong(Constant.CURRENT_POSITION, C.TIME_UNSET);
        }

        Log.d("POSITION_THAT_SAVED", "onSaveInstanceState: "+ currentPosition);
        player.seekTo(currentPosition);

        videoFullScreenPlayer.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
        player.setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);


        imageViewFull = view.findViewById(R.id.imageViewFull);

        int orientation = this.getResources().getConfiguration().orientation;

        try {
            long currentPosition = player.getCurrentPosition();

            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                imageViewFull.setImageDrawable(getResources().getDrawable(R.drawable.exo_controls_fullscreen_enter));

                player.seekTo(currentPosition);
                imageViewFull.setOnClickListener(v -> {
                    getDialog().getOwnerActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                });

            } else {
                player.seekTo(currentPosition);
                imageViewFull.setImageDrawable(getResources().getDrawable(R.drawable.exo_controls_fullscreen_exit));

                imageViewFull.setOnClickListener(v -> {
                    getDialog().getOwnerActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                });
            }


        } catch (NullPointerException ignored) {

        }
        heightOfVideo = videoFullScreenPlayer.getLayoutParams().height;
        Log.d(TAG, "onCreate: " + heightOfVideo);

        mImgExist = view.findViewById(R.id.imageViewExit);
        mImgExist.setOnClickListener(v -> {
            getDialog().getOwnerActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            dismiss();
        });

        mBtnPlayPause = view.findViewById(R.id.play_pause_btn);
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


        mRelative = view.findViewById(R.id.relative);


        //  }


        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@androidx.annotation.Nullable Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()) {
            @Override
            public void onBackPressed() {
                getDialog().getOwnerActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        };
    }

    private void setUp() {
        initializePlayer();
        if (videoUri == null) {
            return;
        }
        buildMediaSource(Uri.parse(videoUri));
    }

    private void initViews(View rootView) {
        videoFullScreenPlayer = rootView.findViewById(R.id.videoFullScreenPlayer);
        videoFullScreenPlayer.setControllerAutoShow(false);
        videoFullScreenPlayer.setControllerHideOnTouch(true);
        videoFullScreenPlayer.hideController();
        spinnerVideoDetails = rootView.findViewById(R.id.spinnerVideoDetails);


        imageViewExit = rootView.findViewById(R.id.imageViewExit);

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
            player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(getContext()), trackSelector, loadControl);
            videoFullScreenPlayer.setPlayer(player);
        }


    }

    private void buildMediaSource(Uri mUri) {
        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(), getString(R.string.app_name)), bandwidthMeter);
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
    public void onPause() {
        super.onPause();

        pausePlayer();
        if (mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        resumePlayer();

    }


    @Override
    public void onDestroy() {
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

//        Log.d(TAG, "onPlayerStateChanged: " + currentPosition);
//        int orientation = this.getResources().getConfiguration().orientation;
//        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
//            currentPositionPortrait = player.getCurrentPosition();
//            player.seekTo(currentPositionLandscape);
//            Log.d(TAG, "onPlayerStateChanged: " + currentPositionLandscape);
//        }
//        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            currentPositionLandscape = player.getCurrentPosition();
//            player.seekTo(currentPositionLandscape);
//            Log.d(TAG, "onPlayerStateChanged: " + currentPositionPortrait);
//
//        }

        switch (playbackState) {

            case Player.STATE_BUFFERING:
                spinnerVideoDetails.setVisibility(View.VISIBLE);
                mBtnPlayPause.setVisibility(View.GONE);
                //player.seekTo(currentPosition);

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
                // player.seekTo(currentPosition);
                break;
            case Player.STATE_READY:
                mBtnPlayPause.setVisibility(View.VISIBLE);
                spinnerVideoDetails.setVisibility(View.GONE);
                //  player.seekTo(currentPosition);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commit();
        } catch (IllegalStateException e) {
            Log.d("ABSDIALOGFRAG", "Exception", e);
        }

    }

    @Override
    public void onClick(int position) {
    }
}





