package j.trt.s.hi.st.ecities.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import j.trt.s.hi.st.ecities.Constants;
import j.trt.s.hi.st.ecities.Game;
import j.trt.s.hi.st.ecities.R;
import j.trt.s.hi.st.ecities.User;
import j.trt.s.hi.st.ecities.data.AuthResponse;
import j.trt.s.hi.st.ecities.data.AuthTask;
import j.trt.s.hi.st.ecities.data.GetLibraryResponse;
import j.trt.s.hi.st.ecities.data.GetLibraryTask;
import j.trt.s.hi.st.ecities.data.GiveUpTask;
import j.trt.s.hi.st.ecities.data.NewGameResponse;
import j.trt.s.hi.st.ecities.data.NewGameTask;
import j.trt.s.hi.st.ecities.data.SendCityResponse;
import j.trt.s.hi.st.ecities.data.SendCityTask;
import j.trt.s.hi.st.ecities.fragments.AuthFragment;
import j.trt.s.hi.st.ecities.fragments.CityFragment;
import j.trt.s.hi.st.ecities.fragments.GameFragment;
import j.trt.s.hi.st.ecities.fragments.LibraryFragment;
import j.trt.s.hi.st.ecities.fragments.MenuFragment;
import j.trt.s.hi.st.ecities.fragments.RegistrationFragment;
import j.trt.s.hi.st.ecities.fragments.RulesFragment;

public class MainActivity extends AppCompatActivity implements AuthFragment.IOnMyEnterClickListener,
        RegistrationFragment.IOnMyRegisterClickListener, MenuFragment.IOnMyMenuClickListener,
        GameFragment.IOnMyGameClickListener, LibraryFragment.IOnMyLibraryClickListener,
        AuthResponse, NewGameResponse, GetLibraryResponse, SendCityResponse {

    private long startTime = 0;
    private String inputCity, login, password, email, name, surname, city;
    private TextView tvTimer, tvOpponentTurn;
    private EditText etLogin, etPassword, etInputCity;
    private Button btnUpdateCityList;
    private Button btnContinue;
    private Fragment authFragment, registrationFragment, rulesFragment, libraryFragment, cityFragment;
    private GameFragment gameFragment;
    private MenuFragment menuFragment;

    public static boolean hasGame;

    FragmentTransaction fTrans;

    public static User user;
    public static Game myGame;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        authFragment = new AuthFragment();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.flFragmentContainer, authFragment);
        fTrans.commit();
    }

    @Override
    public void onEnterButtonClick() {

        etLogin = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);

        user = new User(etLogin.getText().toString(), etPassword.getText().toString());
        if (!user.login.equals("") && !user.password.equals("")) {
            new AuthTask(this).execute(user.authCertificate);
        } else if (user.login.equals("")) {
            Toast.makeText(this, "Please enter login", Toast.LENGTH_SHORT).show();
        } else if (user.password.equals("")) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Auth Fragment register text click
     */
    @Override
    public void onRegistrationTextClick() {
        registrationFragment = new RegistrationFragment();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.flFragmentContainer, registrationFragment);
        fTrans.addToBackStack("AuthFragment");
        fTrans.commit();
    }

    /**
     * Registration Fragment form submit click
     */
    @Override
    public void onRegisterButtonClick(String login, String password, String email, String name,
                                      String surname, String city) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.city = city;

        getSupportFragmentManager().popBackStack();

        //TODO Add registration server request

        Log.v(Constants.LOG_TAG, "Registration data: " + login + " " + password + " " + email + " " +
                name + " " + surname + " " + city);
    }

    @Override
    public void onContinueButtonClick() {
        gameFragment = new GameFragment();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.flFragmentContainer, gameFragment);
        fTrans.addToBackStack("AuthFragment");
        fTrans.commit();
    }

    @Override
    public void onRulesButtonClick() {
        rulesFragment = new RulesFragment();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.flFragmentContainer, rulesFragment);
        fTrans.addToBackStack("MenuFragment");
        fTrans.commit();
    }

    @Override
    public void onLibraryButtonClick() {
        new GetLibraryTask(this).execute();

    }

    @Override
    public void onSendButtonClick() {
        etInputCity = (EditText) findViewById(R.id.etInputCity);
        inputCity = etInputCity.getText().toString();
        if (!inputCity.equals("")) {
            new SendCityTask(this).execute(inputCity);
            etInputCity.setText("");
        } else {
            Toast.makeText(this, inputCity + "Please enter a city", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void authIsDone(String output) {

        Log.d(Constants.LOG_TAG, "Auth result = " + output);
        myGame = new Game();

        JSONObject jsauth = null;
        JSONObject jsgameStatus = null;

        try {
            jsauth = new JSONObject(output);
            myGame.id = jsauth.getString(Constants.SendCityRequest.ID);
            jsgameStatus = (JSONObject) jsauth.getJSONObject("gameStatus");
            myGame.code = jsgameStatus.getString("code");
            myGame.message = jsgameStatus.getString("message");
        } catch (JSONException e) {
            Toast.makeText(MainActivity.this, Constants.Authorization.AUTH_FAIL, Toast.LENGTH_SHORT);
            Log.d(Constants.LOG_TAG, "JSAuthError = " + e.toString());

        }

        Log.d(Constants.LOG_TAG, "game ID = " + myGame.id + "; code = " + myGame.code + "; message = " + myGame.message);

        if (myGame.id != null & myGame.message.equals("Game exists") & myGame.code.equals("0")) {
            Toast.makeText(this, "Welcome " + user.login, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "У Вас есть созданная игра", Toast.LENGTH_SHORT).show();
            //when user has created game
            hasGame = true;
            menuFragment = new MenuFragment();
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.replace(R.id.flFragmentContainer, menuFragment);
            fTrans.addToBackStack("AuthFragment");
            fTrans.commit();
        } else if (myGame.id.equals("null") & myGame.message.equals("Game doesn't exist") & myGame.code.equals("1")) {
            //when user hasn't games
            menuFragment = new MenuFragment();
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.replace(R.id.flFragmentContainer, menuFragment);
            fTrans.addToBackStack("AuthFragment");
            fTrans.commit();
            Toast.makeText(this, "Welcome " + user.login, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "У Вас нет начатой игры", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNewGameButtonClick() {
        new NewGameTask(this).execute(user.authCertificate);
    }

    @Override
    public void newGameId(String newGameId) {
        Log.d(Constants.LOG_TAG, "new Game id = " + newGameId);
        try {
            JSONObject jsonId = new JSONObject(newGameId);
            myGame.id = jsonId.getString(Constants.ID);
            Log.d(Constants.LOG_TAG, "new Game id = " + myGame.id);
            Toast.makeText(this, "New Game Id = " + myGame.id, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            Log.e(Constants.LOG_TAG, e.toString());
        }
        gameFragment = new GameFragment();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.flFragmentContainer, gameFragment);
        fTrans.addToBackStack("MenuFragment");
        fTrans.commit();
    }

    @Override
    public void getLibrary(String[] library) {
        libraryFragment = new LibraryFragment();
        Bundle b = new Bundle();
        b.putStringArray("library", library);
        libraryFragment.setArguments(b);
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.flFragmentContainer, libraryFragment);
        fTrans.addToBackStack("MenuFragment");
        fTrans.commit();
    }

    //Timer
    CountDownTimer timer = new CountDownTimer(60000, 1000) {

        public void onTick(long millisUntilFinished) {
            tvTimer = (TextView) findViewById(R.id.tvTimer);
            if(tvTimer != null)
                tvTimer.setText("" + millisUntilFinished / 1000);
        }

        public void onFinish() {
            tvTimer = (TextView) findViewById(R.id.tvTimer);
            if(tvTimer != null)
                tvTimer.setText("--");

            gameOver();
        }
    };

    @Override
    public void sendCityResponse(String r) {
//Server returns next city
//        Статусы игры после хода:
//        - 0 - всё прошло хорошо (сервер вернул следующий город)
//        - 1 - игры не существует
//        - 10 - город не существует в базе
//        - 11 - город уже был назван
//        - 12 - введен неправильный город (не на ту букву)
//        - 20 - выиграл пользователь
//        - 21 - выиграл компьютер
        Log.d(Constants.LOG_TAG, "ответ сервера на наш ход = " + r);

        JSONObject response = null;
        JSONObject gameStatus = null;
        JSONObject city = null;
        JSONObject clientCity = null;
        String serverCity = "";
        String cityClient = "";

        try {
            response = new JSONObject(r);
            gameStatus = response.getJSONObject(Constants.SendCityRequest.GAME_STATUS);
            myGame.gameStatusCode = gameStatus.getString(Constants.SendCityRequest.GAME_STATUS_CODE);
            myGame.gameStatusMessage = gameStatus.getString(Constants.SendCityRequest.GAME_STATUS_MESSAGE);
            Log.d(Constants.LOG_TAG, "myGame.code" + myGame.gameStatusCode + myGame.gameStatusMessage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        - 0 - всё прошло хорошо (сервер вернул следующий город)
        if (myGame.gameStatusCode.equals("0")) {
            Log.d(Constants.LOG_TAG, "myGame.responsecode = " + myGame.code);
            try {
                city = response.getJSONObject(Constants.SendCityRequest.CITY);
                serverCity = city.getString(Constants.SendCityRequest.NAME);

                clientCity = response.getJSONObject(Constants.SendCityRequest.CITY_CLIENT);
                cityClient = clientCity.getString(Constants.SendCityRequest.NAME);

                tvOpponentTurn = (TextView) findViewById(R.id.tvOpponentTurn);
                SpannableStringBuilder sb = new SpannableStringBuilder(serverCity);
                ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.textColor));
                sb.setSpan(fcs, serverCity.length()-1, serverCity.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                tvOpponentTurn.setText(sb);

                timer.start();
                gameFragment.addCity(cityClient);
                gameFragment.addCity(serverCity);
                Toast.makeText(MainActivity.this, "Ответ сервера = " + serverCity, Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }

//        - 20 - выиграл пользователь
        } else if (myGame.gameStatusCode.equals("20")) {
            hasGame = false;
            Toast.makeText(MainActivity.this, "Поздравляем Вас! Вы выиграли в этой игре!!!" + myGame.gameStatusMessage, Toast.LENGTH_LONG).show();
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.replace(R.id.flFragmentContainer, menuFragment);
            fTrans.addToBackStack("MenuFragment");
            fTrans.commit();

//        - 1 - игры не существует
        } else if (myGame.gameStatusCode.equals("1")) {
            Toast.makeText(MainActivity.this, "Такой игры не существует!" + myGame.gameStatusMessage, Toast.LENGTH_LONG).show();
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.replace(R.id.flFragmentContainer, menuFragment);
            fTrans.addToBackStack("MenuFragment");
            fTrans.commit();

//        - 10 - город не существует в базе
        } else if (myGame.gameStatusCode.equals("10")) {
            Toast.makeText(MainActivity.this, "Такой город родом не из Украины!" + myGame.gameStatusMessage, Toast.LENGTH_LONG).show();

//        - 11 - город уже был назван
        } else if (myGame.gameStatusCode.equals("11")) {
            Toast.makeText(MainActivity.this, "Этот город уже был назван!" + myGame.gameStatusMessage, Toast.LENGTH_LONG).show();

//        - 12 - введен неправильный город (не на ту букву)
        } else if (myGame.gameStatusCode.equals("12")) {
            Toast.makeText(MainActivity.this, "Ваш город начинается не на ту букву!" + myGame.gameStatusMessage, Toast.LENGTH_LONG).show();

//        - 21 - выиграл компьютер
        } else if (myGame.gameStatusCode.equals("21")) {
            hasGame = false;
            Toast.makeText(MainActivity.this, "К сожалению, Вы проиграли!" + myGame.gameStatusMessage, Toast.LENGTH_LONG).show();
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.replace(R.id.flFragmentContainer, menuFragment);
            fTrans.addToBackStack("MenuFragment");
            fTrans.commit();
        }

    }

    @Override
    public void onGiveUpButtonClick() {
        hasGame = false;
        timer.onFinish();
        new GiveUpTask(this).execute();
    }

    //TODO get city description from server. String city - city clicked
    //Game and Library City click
    @Override
    public void onGameCityClick(String city) {
        cityFragment = new CityFragment();

        Toast.makeText(this, "Выбран город " + city, Toast.LENGTH_SHORT).show();

//        fTrans = getSupportFragmentManager().beginTransaction();
//        fTrans.replace(R.id.flFragmentContainer, cityFragment);
//        fTrans.addToBackStack("MenuFragment");
//        fTrans.commit();
    }

    //Game over
    private void gameOver() {
        hasGame = false;
        getSupportFragmentManager().popBackStack();
        Toast.makeText(this, "Game Over!", Toast.LENGTH_SHORT).show();
    }
}