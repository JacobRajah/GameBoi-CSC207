package com.example.gameboi.FlashColors;

import com.example.gameboi.MathGame.GameFacade;
import com.example.gameboi.UserClasses.User;

import java.util.ArrayList;

class FlashColorsFacade extends GameFacade {

    private FlashColorsOperations gameCalculations;

    FlashColorsFacade(User player){
        super(player);
        if(player.getDifficulty().equals("Hard")){
            gameCalculations = new FlashColorsOperationsHard(player);
        }
        else{
            gameCalculations = new FlashColorsOperations(player);
        }
    }


    public boolean checkHidden(){
        return gameCalculations.checkHidden();
    }

    ArrayList<Integer> generatePattern() {
         return gameCalculations.generatePattern();
    }

    ArrayList<Integer> getCorrectPattern() {
        return gameCalculations.getCorrectPattern();
    }

    boolean isCorrect() {
        return gameCalculations.isCorrect();
    }

    boolean isSubmitted() {
        return gameCalculations.IsSubmitted();
    }

    void setSubmitted(boolean submitted) {
        gameCalculations.setSubmitted(submitted);
    }

    /*This method is responsible for grabbing the current score, increasing its value by 1 and
     * returning a new charsequence*/
    int getNewScore(CharSequence c) {
        return gameCalculations.getNewScore(c);
    }

    ArrayList<Integer> DisplayColors(){
        return gameCalculations.DisplayColors();
    }

    void addColour(int colour){
        gameCalculations.addColour(colour);
    }

    void clearPattern(){
        gameCalculations.clearPattern();
    }

    public void setMultiplier(int multiplier) {
        player.setMultiplier(multiplier);
    }

    /**
     * @return the User's chosen game difficulty
     */
    String getDifficulty() { return player.getDifficulty(); }
}
