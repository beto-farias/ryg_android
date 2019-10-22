package mx.com.dgom.hm.ovhaul;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import mx.com.dgom.hm.ovhaul.adapter.ComentariosTareasAdapter;
import mx.com.dgom.hm.ovhaul.app.AppController;
import mx.com.dgom.hm.ovhaul.app.ListResponse;
import mx.com.dgom.hm.ovhaul.app.MessageListResponseInterface;
import mx.com.dgom.hm.ovhaul.app.MessageResponse;
import mx.com.dgom.hm.ovhaul.app.MessageResponseInterface;
import mx.com.dgom.hm.ovhaul.to.ComentariosTareasTO;
import mx.com.dgom.hm.ovhaul.to.DownloadFileDataTO;
import mx.com.dgom.hm.ovhaul.to.TareasTO;
import mx.com.dgom.hm.ovhaul.utils.DateUtils;

public class TareasDetalleActivity extends App2GomActivity {
    private static final int READ_REQUEST_CODE = 42;
    private int PERMISSION_CODE = 0;
    private TextView txtTarea;
    private TextView txtFecha;
    private TareasTO tarea;
    private Button btnViewFile;
    private Button btnAttachFile;
    private Button btnActualizarTexto;
    private EditText txtTextoTarea;
    private AppController controller;

    //comentarios
    private ListView comentariosList;
    private ComentariosTareasAdapter adapter;
    private ArrayList<ComentariosTareasTO> comentarios = new ArrayList<>();
    private EditText txtComentario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas_detalle);

        txtTarea = findViewById(R.id.txt_tarea);
        txtFecha= findViewById(R.id.txt_fecha);
        btnViewFile = findViewById(R.id.btn_descargar_archivo);
        btnAttachFile= findViewById(R.id.btn_cargar_archivo);
        btnActualizarTexto= findViewById(R.id.btn_actualizar_tarea);
        txtTextoTarea= findViewById(R.id.txt_texto_tarea);
        comentariosList = findViewById(R.id.list_comentarios_tarea);
        txtComentario = findViewById(R.id.txt_add_comentario);

        adapter = new ComentariosTareasAdapter(getApplicationContext(), comentarios);
        comentariosList.setAdapter(adapter);
        controller = getController();

        Intent intent = getIntent();
        tarea = (TareasTO)intent.getSerializableExtra("TAREA");

        setUpTareas();

    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(0,returnIntent);
        finish();
    }



    private void setUpTareas(){
        txtTarea.setText(tarea.getTxt_nombre());
        txtFecha.setText(DateUtils.convertDateToFullString(tarea.getFch_creacion()));
        adapter.setDatos(tarea.getWrkComentariosTareas());

        txtTextoTarea.setText(tarea.getTxt_tarea());

        if(tarea.getId_tipo() == 1){
            if(tarea.getTxt_path() != null && !tarea.getTxt_path().isEmpty()){
                btnViewFile.setVisibility(View.VISIBLE);
                btnAttachFile.setVisibility(View.GONE);
            }else{
                btnViewFile.setVisibility(View.GONE);
                btnAttachFile.setVisibility(View.VISIBLE);
            }
        }else{
            btnActualizarTexto.setVisibility(View.VISIBLE);
            txtTextoTarea.setVisibility(View.VISIBLE);
        }

    }

    public void btnViewFileAction(View view){
        downloadDocumento();
    }

    public void btnAttachFileAction(View view){
        openSelector();
    }


    private void openSelector() {
        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.setType("application/pdf|image/*");

        startActivityForResult(intent, READ_REQUEST_CODE);
    }


    public void setBtnActualizarTextoAction(View view) {
        String msg = txtTextoTarea.getText().toString();

        if (msg.isEmpty()) {
            slideUpDialogNotification("Debe indicar la información ");
            return;
        }

        setTareaTexto(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){

            case READ_REQUEST_CODE:

                if(resultCode==RESULT_OK) {

                    String PathHolder = data.getData().getPath();
                    String mimeType = getContentResolver().getType(data.getData());
                    String extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);

                    //Guardamos el archivo en la base
                    addCover();
                    controller.addFile2Task(getApplicationContext(), PathHolder, encodeFileToBase64(PathHolder), tarea.getTxt_token(), new MessageResponseInterface() {
                        @Override
                        public void response(String noInternetError, MessageResponse errorResponse, MessageResponse responseMessage) {
                            removeCover();
                            if(!validateResponse(noInternetError,errorResponse)){
                                slideUpDialogNotification("Error al guardar el archivo");
                                return;
                            }

                            slideUpDialogNotification(responseMessage.getMessage());
                        }
                    });
                }

        }
    }

    private void validateSendComentario(){
        String msg = txtComentario.getText().toString();

        if(msg.isEmpty()){
            slideUpDialogNotification("Debe indicar el comentario");

            return;
        }

        addComentario(msg);
    }

    public void btnAddCommentAction(View view) {
        validateSendComentario();
    }



    //MARK: ---------- NETWORK --------------------------------------------------


    private void addComentario(String comentario){

       addCover();
        controller.addComentarioTarea(getApplicationContext(), tarea.getTxt_token(), comentario, new MessageResponseInterface() {
            @Override
            public void response(String noInternetError, MessageResponse errorResponse, MessageResponse responseMessage) {
                removeCover();
                if(!validateResponse(noInternetError,errorResponse)){
                    slideUpDialogNotification("Error al guardar el comentario");
                    return;
                }

               // slideUpDialogNotification(responseMessage.getMessage());
                loadTareas();
                txtComentario.setText("");

            }
        });

    }

    // DESCARGA EL DOCUMENTO ANEXO
    private void downloadDocumento(){
        addCover();

        controller.getFileDataTarea(getApplicationContext(), tarea.getTxt_token(), new MessageResponseInterface<DownloadFileDataTO>() {
            @Override
            public void response(String noInternetError, MessageResponse errorResponse, MessageResponse<DownloadFileDataTO> responseMessage) {
                removeCover();
                if(!validateResponse(noInternetError,errorResponse)){
                    slideUpDialogNotification("Error al recuperar el archivo");
                    return;
                }

                DownloadFileDataTO to = responseMessage.getData();

                if(to.getUrl() == null || to.getUrl().isEmpty()){
                    slideUpDialogNotification("Error al recuperar el archivo");
                    return;
                }

                Uri uri = Uri.parse(to.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    // Actualiza el texto de la tarea de texto
    private void setTareaTexto(String msg){

        addCover();

        controller.setTareaTexto(getApplicationContext(), tarea.getTxt_token(), msg, new MessageResponseInterface() {
            @Override
            public void response(String noInternetError, MessageResponse errorResponse, MessageResponse responseMessage) {
                removeCover();
                if(!validateResponse(noInternetError,errorResponse)){
                    slideUpDialogNotification("Error al actualizar la información");
                    return;
                }

                slideUpDialogNotification(responseMessage.getMessage());

            }
        });
    }

    private static String encodeFileToBase64(String filePath) {
        File file = new File(filePath);
        byte[] b = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(b);
            for (int j = 0; j < b.length; j++) {
                System.out.print((char) b[j]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
            e.printStackTrace();
        } catch (IOException e1) {
            System.out.println("Error Reading The File.");
            e1.printStackTrace();
        }

        byte[] byteFileArray = new byte[0];
        try {
            byteFileArray = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String base64String = "";
        if (byteFileArray.length > 0) {
            base64String = android.util.Base64.encodeToString(byteFileArray, android.util.Base64.NO_WRAP);
            Log.i("File Base64 string", "IMAGE PARSE ==>" + base64String);
        }

        return base64String;
    }

    private void loadTareas(){
        addCover();

        controller.getTareasResponsablesList(getApplicationContext(),  new MessageListResponseInterface<TareasTO>() {
            @Override
            public void response(String noInternetError, MessageResponse errorResponse, ListResponse<TareasTO> responseMessage) {
                removeCover();
                if(!validateResponse(noInternetError, errorResponse)){
                    return;
                }
                ArrayList<TareasTO> tareas = responseMessage.getResults();
                for(int i=0;i<tareas.size();i++){
                    if(tareas.get(i).getId_tarea() == tarea.getId_tarea()){
                        tarea = tareas.get(i);
                        break;
                    }
                }
                setUpTareas();


            }
        });
    }
}
