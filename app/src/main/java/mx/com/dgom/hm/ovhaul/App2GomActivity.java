package mx.com.dgom.hm.ovhaul;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import mx.com.dgom.hm.ovhaul.app.AppController;
import mx.com.dgom.hm.ovhaul.app.MessageResponse;

public class App2GomActivity extends AppCompatActivity {

    private static final String TAG = "App2GomActivity";

    private static final int XInitial = 0;
    private static final int XFinal = 0;
    private static final int YFinal = 0;
    private static final int duration = 700;
    public final long TIME_OUT =2000;

    private AppController controller;
    private LinearLayout contenedor;

    private int coversCount = 0;
    private ProgressDialog pd;

    public void addCover() {
        this.coversCount++;
        if (this.pd == null) {
            this.pd = new ProgressDialog(this,  R.style.MyAlertDialogStyle);

            this.pd.setMessage(getString(R.string.espere_un_momento));
            this.pd.setCancelable(false);
             }
        if (!this.pd.isShowing()) {
            pd.show();
            this.pd.setContentView(R.layout.custom_progress_dialog);
        }


    }

    public void removeCover() {
        this.coversCount--;
        Log.d(TAG,"Remove cover " + coversCount);
        if (this.coversCount < 0) {
            this.coversCount = 0;
        }
        if (this.pd != null && this.coversCount == 0) {
            this.pd.dismiss();
        }
    }

    protected boolean isWorking() {
        return this.coversCount > 0;
    }

    protected App2GomActivity() {
        controller = new AppController();

    }


    public AppController getController() {
        return new AppController();
    }

    public void setController(AppController controller) {
        this.controller = controller;
    }


    public LinearLayout getLayout() {

        return layout;
    }

    public void setLayout(LinearLayout layout) {
        this.layout = layout;
    }

    private LinearLayout layout;


    enum MessageType{
        SUCCESS,WARNING,ERROR
    }


    public void slideUpDialogNotification(String msg){

        contenedor = new LinearLayout(getApplicationContext());

        contenedor.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
        contenedor.setOrientation(LinearLayout.HORIZONTAL);
        contenedor.setGravity(Gravity.BOTTOM);
        contenedor.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.warning, getTheme()));

        TextView txt = new TextView(getApplicationContext());
        //Agrega propiedades al TextView.
        txt.setText(msg);
        txt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        txt.setGravity(Gravity.CENTER);
        txt.setTextSize(15);
        txt.setTextColor(getApplicationContext().getResources().getColor(R.color.mainFontReverse, getTheme()));
        txt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        contenedor.addView(txt);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int)getApplicationContext().getResources().getDimension(R.dimen.notification_height), Gravity.BOTTOM);
        addContentView(contenedor, params);

        contenedor.bringToFront();

        TranslateAnimation animate = new TranslateAnimation(
                XInitial,
                XFinal,
                contenedor.getHeight(),
                YFinal);
        animate.setDuration(duration);
        //animate.setFillAfter(true);
        contenedor.startAnimation(animate);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                slideDownDialogNotification(contenedor);
            }
        }, TIME_OUT);


    }

    public void slideDownDialogNotification(LinearLayout contenedor){

        TranslateAnimation animate = new TranslateAnimation(
                XInitial,
                XFinal,
                YFinal,
                contenedor.getHeight());
        animate.setDuration(duration);
        contenedor.startAnimation(animate);

        ((ViewGroup) contenedor.getParent()).removeView(contenedor);

    }

    public void closeSoftKeyBoard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }


    public void notInternetErrorManager(String msg){
        removeCover();
        //TODO
    }



    protected boolean validateResponse(String noInternetError, MessageResponse errorResponse){
        removeCover();
        if(noInternetError!=null){
            slideUpDialogNotification(noInternetError);
            return false;
        }

        if(errorResponse!=null){
            slideUpDialogNotification(errorResponse.getMessage());
            return false;
        }

        return true;
    }

}