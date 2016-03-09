package ru.rsdev.gcr;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import ru.rsdev.gcr.Fragment.ListGameFragment;
import ru.rsdev.gcr.Fragment.PreviewFragment;
import ru.rsdev.gcr.Fragment.ResultFragment;
import ru.rsdev.gcr.Fragment.TopGameFrafment;
import ru.rsdev.gcr.Game.SingleGame;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        PreviewFragment.OnStartGameListener, TopGameFrafment.OnAnswer, ResultFragment.OnStopGame{

    //Фрагменты и контейнер для них
    FragmentTransaction fTrans;
    PreviewFragment previewFragment;
    TopGameFrafment topGameFrafment;
    ListGameFragment listGameFragment;
    ResultFragment resultFragment;

    public com.github.clans.fab.FloatingActionMenu floatingActionMenu;
    public com.github.clans.fab.FloatingActionButton fbutton3,fbutton2,fbutton1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Полноэкранный режим
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initComponents();


//        SingleGame.getInstance().addUserAnswer("000");

        showNewGame();

    }

    public void showNewGame(){
        fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.fragmentCont, previewFragment);
        fTrans.commit();
    }

    private void initComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        floatingActionMenu = (com.github.clans.fab.FloatingActionMenu)findViewById(R.id.menu_down);
        fbutton1 = (com.github.clans.fab.FloatingActionButton)findViewById(R.id.fbutton1);
        fbutton2 = (com.github.clans.fab.FloatingActionButton)findViewById(R.id.fbutton2);
        fbutton3 = (com.github.clans.fab.FloatingActionButton)findViewById(R.id.fbutton3);



        fbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Ответ1",Toast.LENGTH_SHORT).show();

                String answerString = topGameFrafment.editText.getText().toString();


                answerString = SingleGame.getInstance().gameAlgorithm.getCorrectCityName(answerString);
                //Проверки ответа на соответствие
                //Верная буква
                String lastAnswer = SingleGame.getInstance().getAllAnswerItem(0);
                if(!SingleGame.getInstance().gameAlgorithm.checkFirstLatter(answerString, lastAnswer)){
                    Toast.makeText(getApplicationContext(), "Неверная первая буква",Toast.LENGTH_SHORT).show();
                }
                else {
                    //Уникальность
                    if(SingleGame.getInstance().gameAlgorithm.chekUnic(answerString))
                    {
                        Toast.makeText(getApplicationContext(), "Повтор",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //Проверка на существование города
                        if(!SingleGame.getInstance().dbHelper.findCityByUserAnswer(answerString)){
                            Toast.makeText(getApplicationContext(), "Город не найден",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //Публикуем верный ответ пользователя
                            SingleGame.getInstance().addAllAnswer(answerString);
                            listGameFragment.showDataInList();
                            showNumberStroke();
                            //Ответ ПК
                            String psAnswer = SingleGame.getInstance().getPCAnswer(answerString);
                            if(psAnswer.equals("GameOver")){
                                SingleGame.getInstance().gameOver();
                            }
                            else {
                                //Публикуем верный ответ ПК
                                SingleGame.getInstance().addAllAnswer(psAnswer);
                                listGameFragment.showDataInList();
                                showFirstChar();
                                showNumberStroke();

                            }
                        }
                    }
                }
            }
        });



        previewFragment = new PreviewFragment();
        topGameFrafment = new TopGameFrafment();
        listGameFragment = new ListGameFragment();
        resultFragment = new ResultFragment();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    //После выбора режима одиночной игры
    public void onPrevFragButtonClick() {
        Toast.makeText(this,"Start game",Toast.LENGTH_SHORT).show();

        //Замена фрагмента Превью на игровые фрагменты
        fTrans = getFragmentManager().beginTransaction();
        //Анимация перехода
        fTrans.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);

        fTrans.replace(R.id.fragmentCont, listGameFragment);
        fTrans.add(R.id.top_fragment, topGameFrafment);
        fTrans.remove(previewFragment);
        fTrans.commit();

        //Показываем плавающее меню
        floatingActionMenu.setVisibility(View.VISIBLE);

        //Передача параметров сложности игры и времени

        SingleGame.getInstance().connectDB(this);
        SingleGame.getInstance().startNewGame();





    }
    //Ответ пользователя
    @Override
    public void onAnswerButtonClick(String answerString) {


        answerString = SingleGame.getInstance().gameAlgorithm.getCorrectCityName(answerString);
        //Проверки ответа на соответствие
        //Верная буква
        String lastAnswer = SingleGame.getInstance().getAllAnswerItem(0);
        if(!SingleGame.getInstance().gameAlgorithm.checkFirstLatter(answerString, lastAnswer)){
            Toast.makeText(this, "Неверная первая буква",Toast.LENGTH_SHORT).show();
        }
        else {
            //Уникальность
            if(SingleGame.getInstance().gameAlgorithm.chekUnic(answerString))
            {
                Toast.makeText(this, "Повтор",Toast.LENGTH_SHORT).show();
            }
            else {
                //Проверка на существование города
                if(!SingleGame.getInstance().dbHelper.findCityByUserAnswer(answerString)){
                    Toast.makeText(this, "Город не найден",Toast.LENGTH_SHORT).show();
                }
                else {
                    //Публикуем верный ответ пользователя
                    SingleGame.getInstance().addAllAnswer(answerString);
                    listGameFragment.showDataInList();
                    showNumberStroke();
                    //Ответ ПК
                    String psAnswer = SingleGame.getInstance().getPCAnswer(answerString);
                    if(psAnswer.equals("GameOver")){
                        SingleGame.getInstance().gameOver();
                    }
                    else {
                        //Публикуем верный ответ ПК
                        SingleGame.getInstance().addAllAnswer(psAnswer);
                        listGameFragment.showDataInList();
                        showFirstChar();
                        showNumberStroke();

                    }
                }
            }
        }
    }

    @Override
    public void onAnswerButtonClick() {
        showFirstChar();
    }

    private void showFirstChar() {
        //int positionAnswer = SingleGame.getInstance().getAllAnswerList().size()-1;
        String userAnswer = SingleGame.getInstance().getAllAnswerItem(0);
        char lastChar = SingleGame.getInstance().getLastLetter(userAnswer);
        topGameFrafment.editText.setText(String.valueOf(lastChar).toUpperCase());
        topGameFrafment.editText.requestFocus();
        topGameFrafment.editText.setSelection(1);

    }

    private void showNumberStroke(){
        topGameFrafment.textView8.setText(String.valueOf(SingleGame.getInstance().getAllAnswerList().size()));
    }


    @Override
    public void onGameStop() {

    }
}
