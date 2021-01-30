package omkabel.project.timeup.Features;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import omkabel.project.timeup.R;
import omkabel.project.timeup.Server.Controller.Controller;
import omkabel.project.timeup.Server.Controller.MyController;
import omkabel.project.timeup.Session.SharedPrefManager;

public class Menu_ResetPassword extends AppCompatActivity implements MyController {
    @BindView(R.id.ResetPassword)
    EditText newpassword;
    @BindView(R.id.ResetPengingat)
    EditText newnamapengingat;
    @BindView(R.id.Btn_Reset)
    Button BtnReset;
    ProgressDialog loading;
    SharedPrefManager sharedPrefManager;
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__reset_password);
        ButterKnife.bind(this);
        sharedPrefManager=new SharedPrefManager(this);
        loading=new ProgressDialog(this);
        controller=new Controller(this);
        BtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validasi();
//                Toast.makeText(Menu_ResetPassword.this, ""+newnamapengingat.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Validasi() {
        loading.setMessage("Mohon Tunggu.....");
        loading.setCancelable(true);
        loading.show();
        if (newpassword.getText().toString().equals("")||newnamapengingat.getText().toString().equals("")){
            loading.dismiss();
            Toast.makeText(this, "Kolom Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else {
            RequestReset(sharedPrefManager.getSP_IMEI(),newnamapengingat.getText().toString(),newpassword.getText().toString());
        }
    }

    private void RequestReset(String sp_imei, String nama, String password) {
        controller.RequestResetPassword(sp_imei,nama,password);
    }

    @Override
    public void ImeiTerdaftar(String imei) {

    }

    @Override
    public void gagalmasuk(String Message) {
        loading.dismiss();
        Toast.makeText(this, ""+Message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ImeiTidakTerdaftar() {

    }

    @Override
    public void berhasilmasuk(String Message) {
        loading.dismiss();
        Intent intent=new Intent(Menu_ResetPassword.this,Menu_Masuk.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void TidakAdaKoneksi(String error_message) {
        loading.dismiss();
        Toast.makeText(this, ""+error_message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void gagalupdate(String pesan) {

    }

    @Override
    public void berhasilupdate(String pesan) {

    }
}