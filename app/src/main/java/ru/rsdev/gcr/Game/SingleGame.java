package ru.rsdev.gcr.Game;

import android.content.Context;

import java.util.ArrayList;

import ru.rsdev.gcr.Algorithm.GameAlgorithm;
import ru.rsdev.gcr.Database.DBHelper;

public class SingleGame {
    private static SingleGame instance;

    private ArrayList<String> userAnswer;//Ответы пользователя
    private ArrayList<String> androidAnswer;//Ответы ПК
    private ArrayList<String> allAnswer;//Все ответы

    public DBHelper dbHelper;
    public GameAlgorithm gameAlgorithm;

    private SingleGame(){
        userAnswer = new ArrayList<>();
        androidAnswer = new ArrayList<>();
        allAnswer =  new ArrayList<>();



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
//        androidAnswer.clear();
//        userAnswer.clear();

        //Добавление первого слова
        addAllAnswer(dbHelper.getRandomCity());
        //addAllAnswer("Кемерово");


    }



    public char getLastLetter(String string){
        return gameAlgorithm.getLastLetter(string);
    }

    public void gameOver(){

    }

    /*

    public String getUserAnswer(int position) {
        return userAnswer.get(position);
    }

    public String getAndroidAnswer(int position) {
        return androidAnswer.get(position);
    }

    public void addUserAnswer(String answer) {
        userAnswer.add(0, answer);
    }

    public void addAndroidAnswer(String answer) {
        androidAnswer.add(0, answer);
    }

    public ArrayList<String> getAndroidAnswerList(){
        return androidAnswer;
    }

    public ArrayList<String> getUserAnswerList(){
        return userAnswer;
    }
*/




    public ArrayList<String> getAllAnswerList(){
        return allAnswer;
    }

    public void addAllAnswer(String answer) {
        allAnswer.add(0, answer);
    }

    public String getAllAnswerItem(Integer i) {
        return allAnswer.get(i);
    }


    public String getPCAnswer(String userAnswer){
        ArrayList<String> listNewAnswer = SingleGame.getInstance().dbHelper.getAnswerPC(userAnswer);

        for (int i=0;i<SingleGame.getInstance().getAllAnswerList().size();i++) {
            for (int j = 0; j < listNewAnswer.size(); j++) {
                String oldAnswer = SingleGame.getInstance().getAllAnswerItem(i);
                String newAnswer = listNewAnswer.get(j);

                if(oldAnswer.equals(newAnswer))
                    listNewAnswer.remove(j);

            }
        }
        if(listNewAnswer.size() == 0){
            //SingleGame.getInstance().gameOver();
            return "GameOver";
        }
        else {
            //Выбор нужного города, проверка на кол-во ответов
            return listNewAnswer.get(0);
        }
    }



}
