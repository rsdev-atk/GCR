package ru.rsdev.gcr.Game;

import android.content.Context;

import java.util.ArrayList;

import ru.rsdev.gcr.Algorithm.GameAlgorithm;
import ru.rsdev.gcr.Database.DBHelper;

public class SingleGame {
    private static SingleGame instance;

    private ArrayList<String> userAnswer;//Ответы пользователя
    private ArrayList<String> androidAnswer;//Ответы ПК
    DBHelper dbHelper;
    GameAlgorithm gameAlgorithm;

    private SingleGame(){
        userAnswer = new ArrayList<>();
        androidAnswer = new ArrayList<>();
        gameAlgorithm = new GameAlgorithm();
    }

    public static SingleGame getInstance(){
        if(instance == null){
            instance = new SingleGame();
        }
        return instance;
    }


    public void connectDB(Context context){
        dbHelper = new DBHelper(context);
    }

    public void startNewGame(){
        //Очистка всех переменных
        androidAnswer.clear();
        userAnswer.clear();

        //Добавление первого слова
        addAndroidAnswer(dbHelper.getRandomCity());
    }



    public char getLastLetter(String string){
        return gameAlgorithm.getLastLetter(string);
    }

    public void gameOver(){

    }

    public String getUserAnswer(int position) {
        return userAnswer.get(position);
    }

    public String getAndroidAnswer(int position) {
        return androidAnswer.get(position);
    }

    public void addUserAnswer(String answer) {
        userAnswer.add(answer);
    }

    public void addAndroidAnswer(String answer) {
        androidAnswer.add(answer);
    }

    public ArrayList<String> getAllAndroidAnswer(){
        return androidAnswer;
    }


}
