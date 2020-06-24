package com.victoribarra.petagram.restAPI;

public final class ConstantesRestApi {

    public static final String ROOT_URL            = "https://graph.instagram.com/";
    public static final String KEY_GET_INFORMATION = "me/media";
    public static final String ACCESS_TOKEN        = "IGQVJWU2NEMGN5VU9CeXM3dS1qQ0VuNWpzZAFVrQW0zY1ByQk81dUJMUklxUmNETlJOckVBUmRvTUlaUzJ6SlR4VDlVZAXRLUzBhX3VwU0dzclprREtaRWFHMGl6X2JrQ01fVnV6eXlybWs1QTZATOEd6SwZDZD\n";
    public static final String KEY_FIELDS          = "?fields=";
    public static final String FIELDS              = "id,username,media_url,caption";
    public static final String KEY_ACCESS_TOKEN    = "&access_token=";
    public static final String URL_GET_INFORMATION = KEY_GET_INFORMATION+KEY_FIELDS+FIELDS+KEY_ACCESS_TOKEN+ACCESS_TOKEN;
    public static final String URL_DYNAMIC         = KEY_GET_INFORMATION+KEY_FIELDS+FIELDS+KEY_ACCESS_TOKEN;
    // https://graph.instagram.com/me/media?fields=media_url,caption,username&access_token=

    public static  final String USUARIO1 = "viktorconvchica";
    public static  final String USUARIO2 = "mochileroslag";
    public static  final String ACCESS_TOKEN2="IGQVJYdzY4OXJWa2ZA5WmhCV0tiNzBJOHI1bW4xUWp2TzNJTGp1SGpBcDV2MnE5dEdkTjNRb0ctRU9uWXV5TWRhdnRlZAXYzYVpyUmNMOTY0UzdtMU1sQzJ2VVc2TEJGblBOVEFrbE9GaFRVM1k3TUVWUQZDZD";

    // Mi propio endpoint

    public static final String ROOT_URL_HEROKU = "https://peaceful-depths-17891.herokuapp.com/";
    public static final String KEY_POST_REGISTRO = "registrar-usuario";


}
