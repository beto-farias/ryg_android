package mx.com.dgom.hm.ovhaul;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import mx.com.dgom.hm.ovhaul.app.AppConstantes;
import mx.com.dgom.hm.ovhaul.app.AppController;
import mx.com.dgom.hm.ovhaul.app.AppData;
import mx.com.dgom.hm.ovhaul.app.MessageResponse;
import mx.com.dgom.hm.ovhaul.app.MessageResponseInterface;
import mx.com.dgom.hm.ovhaul.to.LoginTO;
import mx.com.dgom.hm.ovhaul.to.UserTO;

public class LoginActivity extends App2GomActivity {

    private static final String TAG = "LoginActivity";

    private EditText txtUsuario;
    private EditText txtPwd;
    private TextView txtVersion;
    private AppController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        controller=getController();

        txtUsuario = findViewById(R.id.txt_user);
        txtPwd = findViewById(R.id.txt_pwd);
        txtVersion = findViewById(R.id.txt_version);

        txtUsuario.setText("mfkleinman@rgl.mx");
        txtPwd.setText("12345678");

        setupVersionUI();
    }

    private void setupVersionUI(){
        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            int verCode = pInfo.versionCode;

            txtVersion.setText("Versión: " + verCode + " " + AppConstantes.getAPIURLName());

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnLoginAction(View view){
        String user = txtUsuario.getText().toString();
        String pwd = txtPwd.getText().toString();

        if(user.isEmpty()){
            slideUpDialogNotification(getResources().getString(R.string.usuario_error));
            return;
        }
        if(pwd.isEmpty()){
            slideUpDialogNotification(getResources().getString(R.string.pwd_error));
            return;
        }

        login(user, pwd);

    }

    public void btnRecuperarAction(View view){
        String user = txtUsuario.getText().toString();

        Intent intent = new Intent(LoginActivity.this, RecuperarPasswordActivity.class);
        intent.putExtra("CORREO", user);
        startActivity(intent);

    }

    private void login(String user, String pwd){
        addCover();
        controller.login(getApplicationContext(), user, pwd,  new MessageResponseInterface<LoginTO>() {
            @Override
            public void response(String noInternetError, MessageResponse errorResponse, MessageResponse<LoginTO> responseMessage) {
                removeCover();

                //Valida si hay error o si puede continuar con el negocio
                if(!validateResponse(noInternetError,errorResponse)){
                    return;
                }

                //Proceso de negocio
                LoginTO to = (LoginTO) responseMessage.getData();

                UserTO user = (UserTO) to.getUsuario();

                AppData.usuario = user;
                AppData.tokenSeguridad = to.getToken_seguridad();


                //Suscribe a los topicos
                manageSubscrition(user.getTxt_auth_item(), user.getId_master_cliente() + "");


                Intent intent;
                intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();


            }
        });
    }


    private void manageSubscrition(String rol, String masterCliente){

        //Desuscribe del topico actual
        String topicActual = AppConstantes.getFirebaseTopic(getApplicationContext());
        if(topicActual != null){
            FirebaseMessaging.getInstance().unsubscribeFromTopic(topicActual);
        }

        //Suscribe al topico nuevo
        switch(rol){
            case "abogado":
            case "asistente":
                String topic = "administracion-sitios";
                AppConstantes.setFirebaseTopic(topic,getApplicationContext());
                FirebaseMessaging.getInstance().subscribeToTopic( topic );
                Log.d(TAG, "Suscrito al rol: " + topic);
                AppConstantes.setFirebaseTopic(topic,getApplicationContext());
            break;

            case "cliente":
            case "director-cliente":
            case "usuario-cliente":
            case "usuario-cliente-junior":
                topic = "cliente-sitio-" + masterCliente ;
                FirebaseMessaging.getInstance().subscribeToTopic( topic );
                Log.d(TAG, "Suscrito al rol: " + rol);
                AppConstantes.setFirebaseTopic(rol,getApplicationContext());
            break;
            default:
                Log.d(TAG, "rol no enconrado");
        }
        //Proceso de registro para topicos

    }

}
