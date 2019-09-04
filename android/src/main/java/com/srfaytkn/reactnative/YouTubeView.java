package com.srfaytkn.reactnative;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.support.v7.app.AppCompatActivity;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.PlayerConstants.PlayerError;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.PlayerConstants.PlayerState;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import javax.annotation.Nonnull;

@SuppressLint("ViewConstructor")
public class YouTubeView extends FrameLayout {

  public static final String TAG = YouTubeView.class.getSimpleName();

  private ReactContext context;
  private YouTubePlayerView youTubePlayerView;
  private YouTubePlayer youTubePlayer;
  private YouTubePlayerProps youTubePlayerProps;

  public YouTubeView(ReactContext context) {
    super(context);
    this.context = context;
    this.youTubePlayerProps = new YouTubePlayerProps();
    init();
  }

  private AppCompatActivity getCurrentActivity() {
    AppCompatActivity currentActivity = (AppCompatActivity) this.context.getCurrentActivity();
    if (currentActivity == null) {
      Log.e(TAG, "currentActivity is null");
      throw new YouTubeSdkException("currentActivity is null");
    }
    return currentActivity;
  }

  private void init() {
    View view = inflate(getContext(), R.layout.yt_view_layout, this);
    this.youTubePlayerView = view.findViewById(R.id.player);

    getCurrentActivity().getLifecycle().addObserver(youTubePlayerView);
    initYouTubePlayer();
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    youTubePlayerView.getPlayerUIController().showYouTubeButton(false);
    // .showFullscreenButton(youTubePlayerProps.isShowFullScreenButton())
    // .showSeekBar(youTubePlayerProps.isShowSeekBar())
    // .showPlayPauseButton(youTubePlayerProps.isShowPlayPauseButton());
  }

  @Override
  protected void onDetachedFromWindow() {
    youTubePlayerView.release();
    super.onDetachedFromWindow();
  }

  private void initYouTubePlayer() {
    youTubePlayerView.addYouTubePlayerListeners(new AbstractYouTubePlayerListener() {

      public void onReady(@Nonnull YouTubePlayer player) {
        onReadyEvent("NORMAL");
        youTubePlayer = player;
        youTubePlayer.addListener(youTubePlayerProps.getTracker());

        // if (youTubePlayerProps.getVideoId() == null) {
        // return;
        // }

        // if (youTubePlayerProps.isAutoPlay()) {
        youTubePlayer.loadVideo("S0Q4gqBUs7c", 0);
        // } else {
        // youTubePlayer.cueVideo("S0Q4gqBUs7c", 0);
        // }

        // if (youTubePlayerProps.isFullscreen()) {
        // youTubePlayerView.enterFullScreen();
        // }
      }

      // @Override
      // public void onError(@Nonnull YouTubePlayer youTubePlayer, @Nonnull
      // PlayerError error) {
      // onErrorEvent(String.valueOf(error));
      // }

      // @Override
      // public void onStateChange(@Nonnull YouTubePlayer youTubePlayer, @Nonnull
      // PlayerState state) {
      // onChangeStateEvent(String.valueOf(state));
      // }
    });

    // youTubePlayerView.addFullScreenListener(new YouTubePlayerFullScreenListener()
    // {
    // @Override
    // public void onYouTubePlayerEnterFullScreen() {
    // onChangeFullscreenEvent(true);
    // if (youTubePlayer != null) {
    // youTubePlayer.pause();
    // }
    // openFullscreenPlayer();
    // }

    // @Override
    // public void onYouTubePlayerExitFullScreen() {
    // onChangeFullscreenEvent(false);
    // seekTo(youTubePlayerProps.getTracker().getCurrentSecond());

    // if (youTubePlayerProps.getTracker().getVideoDuration() >
    // youTubePlayerProps.getTracker().getCurrentSecond()) {
    // play();
    // }
    // }
    // });
  }

  public void onReadyEvent(String type) {
    WritableMap map = Arguments.createMap();
    map.putString("type", type);
    context.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onReady", map);
  }

  // public void onErrorEvent(String error) {
  // WritableMap map = Arguments.createMap();
  // map.putString("error", error);
  // context.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onError",
  // map);
  // }

  // public void onChangeStateEvent(String state) {
  // WritableMap map = Arguments.createMap();
  // map.putString("state", state);
  // context.getJSModule(RCTEventEmitter.class).receiveEvent(getId(),
  // "onChangeState", map);
  // }

  // public void onChangeFullscreenEvent(boolean isFullscreen) {
  // WritableMap map = Arguments.createMap();
  // map.putBoolean("isFullscreen", isFullscreen);
  // context.getJSModule(RCTEventEmitter.class).receiveEvent(getId(),
  // "onChangeFullscreen", map);
  // }

  // public void seekTo(float time) {
  // if (youTubePlayer == null) {
  // return;
  // }
  // // youTubePlayer.seekTo(time);
  // }

  // public void play() {
  // if (youTubePlayer == null) {
  // return;
  // }
  // // youTubePlayer.play();
  // }

  // public void pause() {
  // if (youTubePlayer == null) {
  // return;
  // }
  // // youTubePlayer.pause();
  // }

  public void loadVideo(String videoId, float startTime) {
    if (youTubePlayer == null) {
      return;
    }

    // if (youTubePlayerProps.isAutoPlay()) {
    youTubePlayer.loadVideo("S0Q4gqBUs7c", 0);
    // } else {
    // youTubePlayer.cueVideo(videoId, startTime);
    // }
  }

  public YouTubePlayerProps getYouTubePlayerProps() {
    return youTubePlayerProps;
  }

  // public void openFullscreenPlayer() {
  // //
  // context.startActivity(FullscreenPlayerActivity.newIntent(getCurrentActivity(),
  // // this));
  // }

  // public void onCloseFullscreenPlayer(YouTubePlayerProps youTubePlayerProps) {
  // this.youTubePlayerProps = youTubePlayerProps;
  // youTubePlayerView.exitFullScreen();
  // }
}
