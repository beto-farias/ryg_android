package mx.com.dgom.hm.ovhaul.app;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import mx.com.dgom.hm.ovhaul.to.ComentariosLocalidadTO;
import mx.com.dgom.hm.ovhaul.to.ComentariosTareasTO;
import mx.com.dgom.hm.ovhaul.to.DashboardRespTO;
import mx.com.dgom.hm.ovhaul.to.DownloadFileDataTO;
import mx.com.dgom.hm.ovhaul.to.LocalidadTO;
import mx.com.dgom.hm.ovhaul.to.LogTO;
import mx.com.dgom.hm.ovhaul.to.LoginTO;
import mx.com.dgom.hm.ovhaul.to.TareasTO;
import mx.com.dgom.hm.ovhaul.to.UserTO;

public class AppController {
    private static final String TAG="AppController";
    private Gson gson = new Gson();
    Network net = new Network();


    //---------- METODOS DE NEGOCIO ---------------------

    /**
     *
     * @param ctx
     * @param respInterface
     */
    public void login(Context ctx, String username,String password, final MessageResponseInterface<LoginTO> respInterface){
        try{
            JSONObject data = new JSONObject();
            data.put("username",username );
            data.put("password", password);
            data.put("token","askA2MWq9So:APA91bHY-OeaxYOBJ_rNNfsHGi3bNa_HiRDSG5DTITmp6vrSI4J0WSR68UQeb2lcqdaQUCuHKce0xCbxZnBcOJ0lhRVwBC4jPkd0Zpuf1MqGbSIrrem5wT1xe9MxlHAWLBdA8bNHYNPJ" );

            net.jsonObjectRequest(AppConstantes.API_REST_LOGIN, data, new NetworkResponseInterface() {

                @Override
                public void networkResponse(String response, String error) {
                    Type stringType = new TypeToken<MessageResponse<LoginTO<UserTO>>>(){}.getType();
                    processResponse(response,error,respInterface,stringType);

                }
            }, ctx, null);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }


    public void recoverPassword(Context ctx,String username,final MessageResponseInterface respInterface){
        try{
            JSONObject data = new JSONObject();
            data.put("username",username );

            net.jsonObjectRequest( AppConstantes.API_REST_RECOVER_PASSWORD, data, new NetworkResponseInterface() {

                @Override
                public void networkResponse(String response, String error) {
                    Type stringType = new TypeToken<MessageResponse>(){}.getType();
                    processResponse(response,error,respInterface,stringType);

                }
            }, ctx, null);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getDashboard(Context ctx,String tokenUsuario,final MessageResponseInterface<DashboardRespTO> respInterface){
        try{
            JSONObject data = new JSONObject();
            data.put("usuario_token",tokenUsuario );

            net.jsonObjectRequest( AppConstantes.API_REST_DASHBOARD, data, new NetworkResponseInterface() {

                @Override
                public void networkResponse(String response, String error) {
                    Type stringType = new TypeToken<MessageResponse<DashboardRespTO>>(){}.getType();
                    processResponse(response,error,respInterface,stringType);

                }
            }, ctx, AppData.tokenSeguridad);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }


    //--------------LOCALIDADES-------------

    public void getLocalidadesList(Context ctx,String tokenUsuario,final MessageListResponseInterface<LocalidadTO> respInterface){

        net.jsonObjectRequest(AppConstantes.API_REST_LOCALIDADES_LIST +   "?token=" + tokenUsuario, null, new NetworkResponseInterface() {

                @Override
                public void networkResponse(String response, String error) {
                    Type stringType = new TypeToken<ListResponse<LocalidadTO>>(){}.getType();
                    processListResponse(response,error,respInterface,stringType);

                }
            }, ctx, AppData.tokenSeguridad);

    }

    public void getComentariosLocalidadesList(Context ctx,String tokenLocalidad, final MessageListResponseInterface<ComentariosLocalidadTO> respInterface){

        try{
            JSONObject data = new JSONObject();
            data.put("localidad_token",tokenLocalidad);
            data.put("usuario_token",AppData.usuario.getTxt_token());

            net.jsonObjectRequest(AppConstantes.API_REST_COMENTARIOS_LOCALIDADES_LIST, data, new NetworkResponseInterface() {

                @Override
                public void networkResponse(String response, String error) {
                    Type stringType = new TypeToken<ListResponse<ComentariosLocalidadTO>>(){}.getType();
                    processListResponse(response,error,respInterface,stringType);

                }
            }, ctx, AppData.tokenSeguridad);
        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    public void addComentarioLocalidad(Context ctx,String tokenLocalidad, String comentario, final MessageResponseInterface respInterface){
        try{
            JSONObject data = new JSONObject();
            data.put("usuario_token",AppData.usuario.getTxt_token() );
            data.put("localidad_token",tokenLocalidad );
            data.put("comentario",comentario );

            net.jsonObjectRequest( AppConstantes.API_REST_LOCALIDAD_ADD_COMMENT, data, new NetworkResponseInterface() {

                @Override
                public void networkResponse(String response, String error) {
                    Type stringType = new TypeToken<MessageResponse>(){}.getType();
                    processResponse(response,error,respInterface,stringType);

                }
            }, ctx, AppData.tokenSeguridad);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    //-------------TAREAS JURIDICO--------------

    public void getTareasResponsablesList(Context ctx,final MessageListResponseInterface<TareasTO> respInterface){

        try{
            JSONObject data = new JSONObject();
            data.put("usuario_token",AppData.usuario.getTxt_token());

            net.jsonObjectRequest(AppConstantes.API_REST_TAREAS_RESPONSABLE_LIST, data, new NetworkResponseInterface() {

                @Override
                public void networkResponse(String response, String error) {
                    Type stringType = new TypeToken<ListResponse<TareasTO>>(){}.getType();
                    processListResponse(response,error,respInterface,stringType);

                }
            }, ctx, AppData.tokenSeguridad);
        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    // ADD COMENTARIO A TAREA
    public void addComentarioTarea(Context ctx,String tareaToken, String comentario, final MessageResponseInterface respInterface){
        try{
            JSONObject data = new JSONObject();
            data.put("usuario_token",AppData.usuario.getTxt_token() );
            data.put("tarea_token",tareaToken );
            data.put("comentario",comentario );

            net.jsonObjectRequest(AppConstantes.API_REST_TAREA_ADD_COMMENT, data, new NetworkResponseInterface() {

                @Override
                public void networkResponse(String response, String error) {
                    Type stringType = new TypeToken<MessageResponse>(){}.getType();
                    processResponse(response,error,respInterface,stringType);

                }
            }, ctx, AppData.tokenSeguridad);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getFileDataTarea(Context ctx,String tareaToken, final MessageResponseInterface<DownloadFileDataTO> respInterface){
        try{
            JSONObject data = new JSONObject();
            data.put("usuario_token",AppData.usuario.getTxt_token() );
            data.put("tarea_token",tareaToken );

            net.jsonObjectRequest( AppConstantes.API_REST_GET_FILE_DATA_TAREA, data, new NetworkResponseInterface() {

                @Override
                public void networkResponse(String response, String error) {
                    Type stringType = new TypeToken<MessageResponse<DownloadFileDataTO>>(){}.getType();
                    processResponse(response,error,respInterface,stringType);

                }
            }, ctx, AppData.tokenSeguridad);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void setTareaTexto(Context ctx,String tareaToken,String comentario, final MessageResponseInterface respInterface){
        try{
            JSONObject data = new JSONObject();
            data.put("usuario_token",AppData.usuario.getTxt_token() );
            data.put("tarea_token",tareaToken );
            data.put("texto",comentario );

            net.jsonObjectRequest( AppConstantes.API_REST_TAREA_UPDATE_TEXTO, data, new NetworkResponseInterface() {

                @Override
                public void networkResponse(String response, String error) {
                    Type stringType = new TypeToken<MessageResponse>(){}.getType();
                    processResponse(response,error,respInterface,stringType);

                }
            }, ctx, AppData.tokenSeguridad);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void addFile2Task(Context ctx, String fileName, String fileB64,String tareaToken, final MessageResponseInterface respInterface){
        try{
            JSONObject data = new JSONObject();
            data.put("usuario_token",AppData.usuario.getTxt_token() );
            data.put("tarea_token",tareaToken );
            data.put("archivo",fileB64 );
            data.put("nombre",fileName );

            net.jsonObjectRequest( AppConstantes.API_REST_ADD_FILE, data, new NetworkResponseInterface() {

                @Override
                public void networkResponse(String response, String error) {
                    Type stringType = new TypeToken<MessageResponse>(){}.getType();
                    processResponse(response,error,respInterface,stringType);

                }
            }, ctx, AppData.tokenSeguridad);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }


    /**-----------LOGS
     */

    public void getLog(Context ctx,final MessageListResponseInterface<LogTO> respInterface){

        try{
            JSONObject data = new JSONObject();
            data.put("usuario_token",AppData.usuario.getTxt_token());

            net.jsonObjectRequest(AppConstantes.API_REST_LOG_LIST, data, new NetworkResponseInterface() {

                @Override
                public void networkResponse(String response, String error) {
                    Type stringType = new TypeToken<ListResponse<LogTO>>(){}.getType();
                    processListResponse(response,error,respInterface,stringType);

                }
            }, ctx, AppData.tokenSeguridad);
        }catch (JSONException e){
            e.printStackTrace();
        }

    }



    /**
     * Método que procesa la respuesta
     * @param response
     * @param error
     * @param respInterface
     * @param stringType
     */
    private void processResponse(String response, String error,MessageResponseInterface respInterface, Type stringType){

        if(error!=null){
            respInterface.response(error, null, null);
            return ;
        }
        MessageResponse resp = gson.fromJson(response, MessageResponse.class);
        if(resp.getResponseCode()<0){
            respInterface.response(null, resp, null);
            return ;
        }

        respInterface.response(null, null, (MessageResponse) gson.fromJson(response, stringType));

    }

    /**
     * Método que procesa la respuesta de listas
     * @param response
     * @param error
     * @param respInterface
     * @param stringType
     */
    private void processListResponse(String response, String error,MessageListResponseInterface respInterface, Type stringType){
        if(error!=null){
            respInterface.response(error, null, null);
            return ;
        }
        MessageResponse resp = gson.fromJson(response, MessageResponse.class);
        if(resp.getResponseCode()<0){
            respInterface.response(null, resp, null);
            return ;
        }

        respInterface.response(null, null, (ListResponse) gson.fromJson(response, stringType));

    }

}
