package ud.example.notificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private static final int ID_MEN_BARRA_NOTIF = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void noti01(View view){
        String mess=getResources().getString(R.string.mensaje01);
        Toast toast1 = Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_SHORT);
        toast1.show();
    }

    public void noti02(View view){
        String mess = getResources().getString(R.string.mensaje01);
        Toast toast1 = Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_SHORT);
        toast1.setGravity(Gravity.CENTER|Gravity.LEFT,10,10);
        toast1.show();
    }

    public void noti03(View view){
        Toast toast1 = new Toast(getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.layout_toast, (ViewGroup) findViewById(R.id.layoutToast));
        TextView txtMsg = (TextView) layout.findViewById(R.id.mensajeLbl);
       // txtMsg.setText(getString(R.string.MensajeNoti03));
        txtMsg.setText(R.string.MensajeNoti03);
        toast1.setView(layout);
        toast1.show();
    }

    public void noti04(View view){
        String ns= Context.NOTIFICATION_SERVICE;
        String CHANNEL_ID = "ud.com.ANDROID";
        NotificationManager ntManager = (NotificationManager) getSystemService(ns);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence namecha = "ANDROID CHANNEL";
            String descCha = "Canal de Notificaciones de ANDROID";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,namecha,importance);
            channel.setDescription(descCha);
            ntManager.createNotificationChannel(channel);

        }

        int icono = android.R.drawable.stat_sys_warning;
        CharSequence textEstado   = getString(R.string.MensajeNoti06);
        CharSequence titulo = getString(R.string.MensajeNoti05);
        CharSequence descripcion = getString(R.string.MensajeNoti07);
        long hora= System.currentTimeMillis();

        Context contexto = getApplicationContext();
        Intent notintent = new Intent(contexto, MainActivity.class);
        PendingIntent contIntent = PendingIntent.getActivity(contexto,0, notintent,0);
        NotificationCompat.Builder nBuilder = (NotificationCompat.Builder)
                new  NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                .setSmallIcon(icono)
                .setLargeIcon((((BitmapDrawable) getResources().getDrawable(R.drawable.info)).getBitmap()))
                .setContentTitle(titulo)
                .setContentText(descripcion)
                .setContentInfo(textEstado)
                .setWhen(hora)
                .setContentIntent(contIntent)
                .setAutoCancel(true)
                .setColor(getResources().getColor(R.color.colorPrimaryDark))
                .setVibrate(new long[] {100, 250, 100, 500})
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        ntManager.notify(ID_MEN_BARRA_NOTIF, nBuilder.build());



    }

    public void noti05(View view){
        String mess=getResources().getString(R.string.mensaje01);
        Snackbar.make(view, mess, Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.MensajeNoti04), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast toast1 = Toast.makeText(getApplicationContext(), "Podria ir al final", Toast.LENGTH_LONG);
                        toast1.show();
                        Log.i("Pilas Aca", "Aqui llegue");
                    }
                })
                .setActionTextColor(getResources().getColor(R.color.colorAccent))
                .show();

    }

}


