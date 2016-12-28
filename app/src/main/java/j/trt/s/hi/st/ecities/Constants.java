package j.trt.s.hi.st.ecities;

public class Constants {

    public static final String UTF_8 = "UTF-8";
    public static final String LOG_TAG = "ECityTAG";
    public static final String ID = "id";
    public static final String CITY_NAME = "city_name";
    public static final String GAME_ID = "game_id";



    public class URL{
        public static final String POST = "POST";
        public static final String GAME_MOVE_URL = "http://ecity.org.ua:8080/game/move";
        public static final String AUTH_URL = "http://ecity.org.ua:8080/game/status";
        public static final String GAME_NEW = "http://ecity.org.ua:8080/game/new";
        public static final String LIBRARY_URL = "http://ecity.org.ua:8080/names";
        public static final String GIVE_UP_URL = "http://ecity.org.ua:8080/game/over/giveup";
    }

    public class Authorization{
        public static final String AUTH_FAIL = "Вам не удалось авторизироваться";
        public static final String AUTH = "Authorization";
    }
    public class SendCityRequest{
        public static final String CITY = "city";
        public static final String NAME = "name";
        public static final String ID ="id";
        public static final String LONGITUDE = "longitude";
        public static final String LATITUDE = "latitude";
        public static final String POPULATION = "population";
        public static final String ESTABLISHMENT = "establishment";
        public static final String URL = "url";
        public static final String LAST_CHAR = "lastChar";
        public static final String GAME_STATUS = "gameStatus";
        public static final String GAME_STATUS_CODE = "code";
        public static final String GAME_STATUS_MESSAGE = "message";
    }
}