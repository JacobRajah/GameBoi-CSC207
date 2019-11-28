package com.example.gameboi.MathGame;

import android.content.Intent;

import com.example.gameboi.ScorePages.FlashLoss;
import com.example.gameboi.UserClasses.User;

class MathGameManager implements Gameable{

    private int response = 0;
    private int numRounds = 0;
    private int numLosses = 0;
    private int score;
    private boolean winner = false;
    private MathEquation equation = new MathEquation();
    private User player;

    MathGameManager(User player) {
        this.player = player;
        score = player.getPoints();
    }

    int getResponse() {return response;}


    void submitInput(){
        numRounds += 1;
        if (equation.isAnswerCorrect(response)) {score += 1;}
        else {
            numLosses += 1;
            loseLife();
        }
        clearResponse();
    }

    private void loseLife(){
        if (numLosses >= 3) {player.loseALife();}
    }

    private void updateResponse(int num) {response = response * 10 + num;}

    void newEquation() {
        if (player.isHard()) {
            equation.getHardEquation();
        }
        else {
            equation.getEasyEquation();
        }
    }

    String getEquation() {return equation.getEquation();}

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int getMultiplier() {
        return player.getMultiplier();
    }

    @Override
    public boolean isGameOver() {
        if (numRounds == 5) {
            winner = true;
            player.setPoints(score);
            return true;
        }
        else if (numLosses == 3) {
            player.setPoints(score);
            return true;
        }
        return false;
    }

    @Override
    public boolean isWinner() {return winner;}

    void clickNumButton(int num) {
        if (response < 100000) {updateResponse(num);}
    }

    void clearResponse(){
        response = 0;
    }

    User getPlayer() {return player;}

}
