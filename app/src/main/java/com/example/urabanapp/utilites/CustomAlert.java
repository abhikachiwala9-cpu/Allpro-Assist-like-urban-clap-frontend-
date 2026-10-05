package com.example.urabanapp.utilites;

import static android.os.Build.VERSION_CODES.R;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.example.urabanapp.R;
import com.example.urabanapp.activity.BookActivity;
import com.example.urabanapp.activity.NavigationActivity;


public class CustomAlert {

    public CustomAlert(Activity activity, String title, String message) {

        MediaPlayer mp = MediaPlayer.create(activity, com.example.urabanapp.R.raw.sound);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.start();

        try {
            mp.setDataSource(Environment.getExternalStorageDirectory().getPath() + "/Music/audio.mp3");
            mp.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a custom layout for the alert
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(com.example.urabanapp.R.layout.alert_layout, null);

        // Set the title and message
        TextView titleText = layout.findViewById(com.example.urabanapp.R.id.alertTitle);
        TextView messageText = layout.findViewById(com.example.urabanapp.R.id.alertMessage);
        titleText.setText(title);
        messageText.setText(message);

        // Build the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(layout);

        // Create the dialog
        AlertDialog dialog = builder.create();

        // Set the dialog to appear at the top
        dialog.getWindow().setGravity(Gravity.TOP);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        // Show the dialog
        mp.start();
        dialog.show();

        // Apply the slide-in animation (Top to Bottom)
        ObjectAnimator slideIn = ObjectAnimator.ofFloat(layout, "translationY", -layout.getHeight(), 0);
        slideIn.setDuration(300);
        slideIn.setInterpolator(new DecelerateInterpolator());
        slideIn.start();

        // Dismiss the dialog after 4 seconds with slide-out animation (Bottom to Top)
        new Handler().postDelayed(() -> {
            // Slide-out animation (Bottom to Top)
            ObjectAnimator slideOut = ObjectAnimator.ofFloat(layout, "translationY", 0, -layout.getHeight());
            slideOut.setDuration(300);
            slideOut.setInterpolator(new AccelerateInterpolator());
            slideOut.start();

            // Dismiss the dialog after the slide-out animation
            slideOut.addListener(new android.animation.Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(android.animation.Animator animation) {}

                @Override
                public void onAnimationEnd(android.animation.Animator animation) {
                    dialog.dismiss();
                    mp.stop();
                }

                @Override
                public void onAnimationCancel(android.animation.Animator animation) {}

                @Override
                public void onAnimationRepeat(android.animation.Animator animation) {}
            });
        }, 3000); // 4 seconds delay before dismissing the dialog
    }
}
