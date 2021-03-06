package com.example.gameboi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Random;
import android.widget.Button;

import static android.graphics.Color.*;

public class UserSetter extends AppCompatActivity{

    private User player;
    private String name;
    private String icon;
    private int backgroundColor;
    private int numLives;
    private EditText nameInputField;
    private Button boyBtn;
    private Button pandaBtn;
    private Button snakeBtn;
    private Button whiteBtn;
    private Button greyBtn;
    private Button cyanBtn;
    private Button numLivesBtn1;
    private Button numLivesBtn2;
    private Button numLivesBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setter);
        player = getIntent().getParcelableExtra("player");
        setupUI();
    }

    private void setupUI() {
        nameInputField = findViewById(R.id.userName);
        boyBtn = findViewById(R.id.boyBtn);
        pandaBtn = findViewById(R.id.pandaBtn);
        snakeBtn = findViewById(R.id.snakeBtn);
        setBtnClr(boyBtn, LTGRAY, pandaBtn, LTGRAY, snakeBtn, LTGRAY);
        whiteBtn = findViewById(R.id.whiteBtn);
        greyBtn = findViewById(R.id.greyBtn);
        cyanBtn = findViewById(R.id.cyanBtn);
        setBtnClr(whiteBtn, LTGRAY, greyBtn, LTGRAY, cyanBtn, LTGRAY);
        numLivesBtn1 = findViewById(R.id.numLivesBtn1);
        numLivesBtn2 = findViewById(R.id.numLivesBtn2);
        numLivesBtn3 = findViewById(R.id.numLivesBtn3);
        setBtnClr(numLivesBtn1, LTGRAY, numLivesBtn2, LTGRAY, numLivesBtn3, LTGRAY);
    }

    public void checkNameInput(View view){
        String username = nameInputField.getText().toString();
        if (username.length() <= 6 && username.length() >= 1) {
            name = username;
        }
        else {
            Toast nameTooLong = Toast.makeText(getApplicationContext(),
                    "Usernames must be less than 7 characters and more than 0",
                    Toast.LENGTH_SHORT);
            nameTooLong.show();
        }
    }

    public void randomUserName(View view) {
        String[] randomNames = {"2c00l", "H@xx0r", "Player", "a_name"};
        Random rand = new Random();
        int index = rand.nextInt(4);
        nameInputField.setText(randomNames[index]);
        name = randomNames[index];
    }

    private void setBtnClr(Button btn1, int color1,
                           Button btn2, int color2,
                           Button btn3, int color3) {
        btn1.setBackgroundColor(color1);
        btn2.setBackgroundColor(color2);
        btn3.setBackgroundColor(color3);
    }

    public void boyBTN(View view) {
        icon = "userlogo";
        setBtnClr(boyBtn, DKGRAY, pandaBtn, LTGRAY, snakeBtn, LTGRAY);
    }
    public void pandaBTN(View view) {
        icon = "panda";
        setBtnClr(boyBtn, LTGRAY, pandaBtn, DKGRAY, snakeBtn, LTGRAY);
    }

    public void snakeBTN(View view) {
        icon = "snake";
        setBtnClr(boyBtn, LTGRAY, pandaBtn, LTGRAY, snakeBtn, DKGRAY);
    }
    public void whiteBtn(View view) {
        backgroundColor = WHITE;
        setBtnClr(whiteBtn, DKGRAY, greyBtn, LTGRAY, cyanBtn, LTGRAY);
    }
    public void greyBtn(View view) {
        backgroundColor = GRAY;
        setBtnClr(whiteBtn, LTGRAY, greyBtn, DKGRAY, cyanBtn, LTGRAY);
    }
    public void cyanBtn(View view) {
        backgroundColor = CYAN;
        setBtnClr(whiteBtn, LTGRAY, greyBtn, LTGRAY, cyanBtn, DKGRAY);
    }
    public void oneLife(View view) {
        numLives = 1;
        setBtnClr(numLivesBtn1, DKGRAY, numLivesBtn2, LTGRAY, numLivesBtn3, LTGRAY);
    }
    public void twoLives(View view) {
        numLives = 2;
        setBtnClr(numLivesBtn1, LTGRAY, numLivesBtn2, DKGRAY, numLivesBtn3, LTGRAY);
    }
    public void threeLives(View view) {
        numLives = 3;
        setBtnClr(numLivesBtn1, LTGRAY, numLivesBtn2, LTGRAY, numLivesBtn3, DKGRAY);
    }

    public void submitCustomizations(View view){
        checkNameInput(view);
        if (name != null && icon != null && backgroundColor != 0 && numLives != 0) {
            player.setName(name);
            player.setIcon(icon);
            player.setLives(numLives);
            player.setBackgroundColor(backgroundColor);
            FileManager fm = new FileManager(this);
            fm.saveNewUser(player);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            //Want to save changes made to this user...
        }
    }
}
