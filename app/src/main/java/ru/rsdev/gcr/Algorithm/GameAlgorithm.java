package ru.rsdev.gcr.Algorithm;

import java.util.ArrayList;

import ru.rsdev.gcr.Game.SingleGame;

public class GameAlgorithm {
    //Список мусорных букв, на которые не нечинаются города (ё и й заменяются на соответствующие е и и)
    private char badLetters[] = {'ъ','ь','ы','ц'};
    private char newLetter; //Символ последней буквы

    //Определение последнй валидной буквы
    public char getLastLetter(String inputSTR)
    {
        char letters[] = inputSTR.toCharArray();
        boolean badResult = false;
        newLetter = letters[letters.length-1];
        for(int i=0;i<badLetters.length;i++) {
            if (letters[letters.length - 1] == badLetters[i]) {
                newLetter = letters[letters.length - 2];
                badResult = true;
            }
        }
        if(badResult == true)
        {
            for (int i = 0; i < badLetters.length; i++) {
                if (letters[letters.length - 2] == badLetters[i]) {
                    newLetter = letters[letters.length - 3];
                }
            }
        }
        if(newLetter == 'й')
            newLetter = 'и';
        return newLetter;
    }







    //Получение валидного названия города
    public String getCorrectCityName(String userAnswer)
    {
        String lowerCase = userAnswer.toLowerCase();
        String firstLatter = String.valueOf(lowerCase.charAt(0));
        firstLatter=firstLatter.toUpperCase();
        char mass2[] = new char[lowerCase.length()-1];
        lowerCase.getChars(1,lowerCase.length(),mass2,0);
        String secondLatter = String.valueOf(mass2);
        String newString = firstLatter.concat(secondLatter);
        return newString;
    }

    //Проверка на корректность 1й буквы
    public boolean checkFirstLatter(String newAnswer, String oldAnswer){
        char lastCharOldAnswer = getLastLetter(oldAnswer);
        char firstCharUserAnswer = newAnswer.charAt(0);
        lastCharOldAnswer = Character.toLowerCase(lastCharOldAnswer);
        firstCharUserAnswer = Character.toLowerCase(firstCharUserAnswer);
        return lastCharOldAnswer==firstCharUserAnswer;
    }

    //Проверка на уникальность
    public boolean chekUnic(String userAnswer){
        boolean result=false;
        ArrayList<String> list = SingleGame.getInstance().getAllAnswerList();

        for (int i = 0; i<list.size() ; i++){
            if(list.get(i).equals(userAnswer)) {
                result = true;
                break;
            }
        }



        return result;
    }



}
