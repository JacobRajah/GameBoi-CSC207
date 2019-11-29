package com.example.gameboi.MathGame;

import com.example.gameboi.UserClasses.User;

class MathGameFacade extends GameFacade{

    private MathGameManager mathGameManager;


    MathGameFacade(User player) {
        super();
        gameManager = new MathGameManager(player);
        mathGameManager = (MathGameManager) gameManager;
    }

    void clickNumButton(int num) {mathGameManager.clickNumButton(num);}

    void pressClear(){mathGameManager.clearResponse();}

    void pressEnter(){
        mathGameManager.submitInput();
        mathGameManager.newEquation();
    }

    User getPlayer() {return mathGameManager.getPlayer();}

    boolean isWinner() {return mathGameManager.isWinner();}

    int getResponse(){return mathGameManager.getResponse();}

    String getEquation() {return mathGameManager.getEquation();}

}
