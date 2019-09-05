// package com.srfaytkn.reactnative;

// import android.app.Activity;
// import android.content.Intent;
// import android.os.Bundle;
// import android.view.WindowManager;
// import android.support.v7.app.AppCompatActivity;
// import
// com.pierfrancescosoffritti.androidyoutubeplayer.player.PlayerConstants.PlayerError;
// import
// com.pierfrancescosoffritti.androidyoutubeplayer.player.PlayerConstants.PlayerState;
// import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
// import
// com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
// import
// com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerFullScreenListener;
// import
// com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
// import javax.annotation.Nonnull;

// public class FullscreenPlayerActivity extends AppCompatActivity {

// public static final String TAG =
// FullscreenPlayerActivity.class.getSimpleName();

// private static YouTubeView youTubeView;
// private YouTubePlayerView youTubePlayerView;
// private YouTubePlayerProps youTubePlayerProps;

// public static Intent newIntent(Activity callerActivity, YouTubeView
// youTubeView) {
// FullscreenPlayerActivity.youTubeView = youTubeView;
// return new Intent(callerActivity, FullscreenPlayerActivity.class);
// }

// @Override
// protected void onCreate(Bundle savedInstanceState) {
// super.onCreate(savedInstanceState);
// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
// WindowManager.LayoutParams.FLAG_FULLSCREEN);
// setContentView(R.layout.activity_fullscreen_player);

// this.youTubePlayerView = findViewById(R.id.player);
// this.youTubePlayerProps = youTubeView.getYouTubePlayerProps();

// getLifecycle().addObserver(youTubePlayerView);
// initYouTubePlayer();
// }

// @Override
// public void onDestroy() {
// super.onDestroy();
// youTubePlayerView.release();
// }

// private void initYouTubePlayer() {
// youTubePlayerView.getPlayerUiController().showYouTubeButton(false);
// youTubePlayerView.enterFullScreen();

// youTubePlayerView.addYouTubePlayerListener(new
// AbstractYouTubePlayerListener() {
// @Override
// public void onReady(@Nonnull YouTubePlayer youTubePlayer) {
// youTubeView.onReadyEvent("FULLSCREEN");

// float currentSecond = youTubePlayerProps.getTracker().getCurrentSecond();

// if (currentSecond == 0) {
// currentSecond = youTubePlayerProps.getStartTime();
// }

// youTubePlayer.loadVideo(youTubePlayerProps.getVideoId(), currentSecond);
// youTubePlayer.addListener(youTubePlayerProps.getTracker());
// }

// @Override
// public void onError(@Nonnull YouTubePlayer youTubePlayer, @Nonnull
// PlayerError error) {
// youTubeView.onErrorEvent(String.valueOf(error));
// }

// @Override
// public void onStateChange(@Nonnull YouTubePlayer youTubePlayer, @Nonnull
// PlayerState state) {
// youTubeView.onChangeStateEvent(String.valueOf(state));
// if (state == PlayerState.ENDED) {
// onBackPressed();
// }
// }
// });

// youTubePlayerView.addFullScreenListener(new YouTubePlayerFullScreenListener()
// {
// @Override
// public void onYouTubePlayerEnterFullScreen() {
// }

// @Override
// public void onYouTubePlayerExitFullScreen() {
// onBackPressed();
// }
// });

// }

// @Override
// protected void onStop() {
// youTubeView.onCloseFullscreenPlayer(youTubePlayerProps);
// super.onStop();
// }
// }
