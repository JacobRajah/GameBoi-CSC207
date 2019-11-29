package com.example.gameboi.BonusSpinner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gameboi.FileSystem.FileManager;
import com.example.gameboi.FlashColors.SimonGame;
import com.example.gameboi.R;
import com.example.gameboi.RockPaperScissors.RockPaperScissors;
import com.example.gameboi.ScorePages.Leaderboard;
import com.example.gameboi.UserClasses.User;

import java.util.Random;

public class BonusSpinner extends AppCompatActivity {

    // The degree is what the spinner lands on
    private int degree = 0;

    // Dedicated class for calculating sections of what the wheel lands on
    private SpinnerCalc spinnerCalc = new SpinnerCalc();

    // Instance of random, to randomly spin the wheel
    private final Random RANDOM = new Random();

    private User player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus_spinner);

        // Unpack and store the User stats to be displayed
        player = getIntent().getParcelableExtra("player");

        // Set the multiplier textView with their current multiplier score
        TextView multi = findViewById(R.id.multi);
        multi.setText(String.valueOf(player.getMultiplier()));
    }

    /**
     * Spins the wheel and calculates which section of the wheel it was landed on
     * @param v View
     */
    public void spin(View v) {
        // The degree that the spinner was on
        int degreeOld = degree % 360;

        // Calculate the random angle that the spinner wheel should rotate and land on
        // Add 720 to allow the spinner to spin at least twice before landing
        degree = RANDOM.nextInt(360) + 720;

        // Rotate animations
        RotateAnimation rotateAnim = new RotateAnimation(degreeOld, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(3600);
        rotateAnim.setFillAfter(true);
        rotateAnim.setInterpolator(new DecelerateInterpolator());

        rotateAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Empty the text view when the animation start
                TextView multiplier = findViewById(R.id.newMulti);
                multiplier.setText("");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Calculate which section of the wheel the arrow is pointing at and multiply it by
                // the User's current multiplier
                int newMultiplier = player.getMultiplier() * spinnerCalc.getWheelSection(360 - (degree % 360));

                // Display the new multiplier that the User's will have
                TextView multiplier = findViewById(R.id.newMulti);
                multiplier.setText(String.valueOf(newMultiplier));

                // Set the User's new multiplier
                player.setMultiplier(newMultiplier);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ImageView spinner = findViewById(R.id.Spinner);
        spinner.startAnimation(rotateAnim);
    }


    /**
     * Takes the user to the next level or to the final leader board if they have completed the game
     */
    public void nextGame(View view) {

        // Save the User's new multiplier as they move onto the next game
        FileManager fileMan = new FileManager(this);
        fileMan.save(player);

        if (player.getLives() == 0) {
            Intent intent = new Intent(this, Leaderboard.class);
            startActivity(intent);
        } else {
            if (player.getCurrLevel() == 1) {
                Intent intent = new Intent(this, SimonGame.class);
                intent.putExtra("player", player);
                startActivity(intent);
            } else if (player.getCurrLevel() == 2) {
                Intent intent = new Intent(this, RockPaperScissors.class);
                intent.putExtra("player", player);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, Leaderboard.class);
                intent.putExtra("player", player);
                startActivity(intent);
            }
        }
    }


}
