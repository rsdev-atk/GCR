package ru.rsdev.gcr;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Полноэкранный режим
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initComponents();
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

        //Передача параметров сложности игры и времени

        SingleGame.getInstance().connectDB(this);
        SingleGame.getInstance().startNewGame();





    }
    @Override
    public void onAnswerButtonClick(String answerString) {
        Toast.makeText(this,"111",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAnswerButtonClick() {
        String test = SingleGame.getInstance().getAndroidAnswer(0);
        Toast.makeText(this,test,Toast.LENGTH_SHORT).show();

        String firstAnswer = SingleGame.getInstance().getAndroidAnswer(0);
        char lastChar = SingleGame.getInstance().getLastLetter(firstAnswer);
        topGameFrafment.editText.setText(String.valueOf(lastChar).toUpperCase());
        topGameFrafment.editText.requestFocus();
        topGameFrafment.editText.setSelection(1);



    }



    @Override
    public void onGameStop() {

    }
}
