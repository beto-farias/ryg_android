package mx.com.dgom.hm.ovhaul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import mx.com.dgom.hm.ovhaul.app.AppController;
import mx.com.dgom.hm.ovhaul.app.MessageResponse;
import mx.com.dgom.hm.ovhaul.app.MessageResponseInterface;

public class RecuperarPasswordActivity extends App2GomActivity {
    private TextView txtUser;
    private Intent intent;
    private AppController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_password);
        intent = getIntent();
        controller=getController();
        txtUser = findViewById(R.id.txt_user);

        String user = intent.getStringExtra("CORREO");

        if(user!=null && !user.isEmpty()){
            txtUser.setText(user);
        }
    }


    public void btnRecuperarAction(View view){
        if(txtUser.getText().toString().isEmpty()){
            slideUpDialogNotification("Debe indicar el nombre de usuario\"");
            return;
        }
        recoverPassword();
    }

    private void recoverPassword(){
        String  usr = txtUser.getText().toString();

        addCover();
        controller.recoverPassword(getApplicationContext(), usr, new MessageResponseInterface() {
            @Override
            public void response(String noInternetError, MessageResponse errorResponse, MessageResponse responseMessage) {
                removeCover();
                if(!validateResponse(noInternetError, errorResponse)){
                    slideUpDialogNotification("Error al intentar recuperar la contraseña");
                    return;
                }

                slideUpDialogNotification(responseMessage.getMessage());
            }
        });
    }

}
