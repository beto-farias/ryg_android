package mx.com.dgom.hm.ovhaul.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import mx.com.dgom.hm.ovhaul.to.UserTO;

public  class AppConstantes {

        public  static UserTO USER;
        private static final String FIREBASE_TOKEN = "FIREBASE_TOKEN";
        private static final String FIREBASE_TOPIC = "FIREBASE_TOPIC";


        public static  void setFirebaseTopic(String topic, Context ctx){
            setPreference(FIREBASE_TOPIC,topic, ctx);
        }

        public static String getFirebaseTopic(Context ctx){
            return getStringProperty(FIREBASE_TOPIC,ctx);
        }

        public static void setFirebaseToken(String token, Context ctx){
            setPreference(FIREBASE_TOKEN,token, ctx);
        }

        public static String getFirebaseToken(Context ctx){
            return getStringProperty(FIREBASE_TOKEN,ctx);
        }



    //Version que utiliza QA o DEV
    public static  final api ambiente = api.DEV;



    //URL de Servicios
        private static final String API_REST_URL_LOCAL          = "";
        public static final String API_REST_URL_DEV            = "https://dev.2geeksonemonkey.com/ryg/ovhaul-api/web/api/";
        private static final String API_REST_URL_QA             = "";
        public static final String WEB_REST_URL_DEV = "https://dev.2geeksonemonkey.com/ryg/ovhaul/web/";

        //Nombre Servicios
        public static final String API_REST_LOGIN                           = getAPIURL() + "login";
        public static final String API_REST_DASHBOARD                       = getAPIURL() +"dashboard";
        public static final String API_REST_LOCALIDADES_LIST                = getAPIURL() +"localidades-list";
        public static final String API_REST_COMENTARIOS_LIST                = getAPIURL() +"comentarios-tareas-list";
        public static final String API_REST_TAREAS_RESPONSABLE_LIST         = getAPIURL() +"tareas-responsable-list";
        public static final String API_REST_TAREA_ADD_COMMENT               = getAPIURL() +"tarea-add-comentario";
        public static final String API_REST_LOG_LIST                        = getAPIURL() +"log-list";
        public static final String API_REST_ADD_FILE                        = getAPIURL() +"tarea-add-documento";
        public static final String API_REST_GET_FILE_DATA                   = getAPIURL() +"tarea-download-documento";
        public static final String API_REST_GET_FILE_DATA_TAREA             = getAPIURL() +"tarea-download-documento-tarea";
        public static final String API_REST_RECOVER_PASSWORD                = getAPIURL() + "recover-password";
        public static final String API_REST_COMENTARIOS_LOCALIDADES_LIST    = getAPIURL() +"comentarios-localidades-list";
        public static final String API_REST_LOCALIDAD_ADD_COMMENT           = getAPIURL() +"localidad-add-comentario";
        public static final String API_REST_TAREA_UPDATE_TEXTO              = getAPIURL() + "update-tarea-texto";




    //--------------


    public enum api {
        LOCAL,
        QA,
        DEV
    }


    public static String getAPIURL() {
        switch (ambiente) {
            case LOCAL:
                return API_REST_URL_LOCAL;
            case QA:
                return API_REST_URL_QA;
            case DEV:
                return API_REST_URL_DEV;
            default:
                return API_REST_URL_DEV;
        }
    }

    public static String getAPIURLName() {
        switch (ambiente) {
            case LOCAL:
                return "local";
            case QA:
                return "qa";
            case DEV:
                return "dev";
            default:
                return "dev";
        }
    }


    //------------------------- FUNCIONES DE SERVICIO --------------------------

    public static boolean getProperty(String name, Context ctx){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        boolean res = preferences.getBoolean(name, false);
        return res;
    }

    public static String getStringProperty(String name, Context ctx){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        String res = preferences.getString(name, null);
        return res;
    }

    public static void setPreference(String name, boolean val, Context ctx){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(name, val);
        editor.commit();
    }

    public static void setPreference(String name, String val, Context ctx){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(name, val);
        editor.commit();
    }

    public static void setPreference(String name, int val, Context ctx){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(name, val);
        editor.commit();
    }



}
