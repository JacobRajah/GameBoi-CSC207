package com.example.gameboi.ScorePages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gameboi.BonusSpinner.BonusSpinner;
import com.example.gameboi.FileSystem.FileManager;
import com.example.gameboi.R;
import com.example.gameboi.UserClasses.User;

public class LevelResults extends AppCompatActivity {


    /**
     * The current User playing the game, saves an instance of the User to update their stats
     */
    private User player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_results);

        // Store the User to display their stats
        player = getIntent().getParcelableExtra("player");

        // Whether the User won the previous game or lost the previous game
        boolean success = getIntent().getExtras().getBoolean("success");

        // Increase the User's current level as they have just completed a level
        player.incrementCurrLevel();

        // Set the textViews whether the User has won or last the previous game
        if (success) {
            TextView message = findViewById(R.id.winText);
            message.setText(R.string.flashWin1);
        } else {
            TextView message = findViewById(R.id.winLoss);
            message.setText(R.string.flashLoss);

            TextView minusOne = findViewById(R.id.minus1);
            minusOne.setText("-1");
        }

        // Puts the Stats on the TextViews
        setStatTexts();

        // Save the User's stats in the text file
        FileManager fman = new FileManager(this);
        fman.save(player);
    }

    /**
     * Set the number of lives, multiplier, and points that the User has in the textViews
     */
    private void setStatTexts() {
        TextView numLives = findViewById(R.id.lives);
        numLives.setText(String.valueOf(player.getLives()));

        TextView multiplier = findViewById(R.id.currMultiply);
        multiplier.setText(String.valueOf(player.getMultiplier()));

        TextView totalPoint = findViewById(R.id.currScore);
        totalPoint.setText(String.valueOf(player.getPoints()));
    }

    /**
     * Take the User to the next level, or to the leader board when they have finish all three levels
     *
     * @param view The activity view
     */
    public void toNext(View view) {
        Intent intent = new Intent(this, BonusSpinner.class);
        intent.putExtra("player", player);
        startActivity(intent);
    }
}
