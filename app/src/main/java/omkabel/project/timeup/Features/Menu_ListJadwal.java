package omkabel.project.timeup.Features;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import omkabel.project.timeup.Fragment.Menu_Utama;
import omkabel.project.timeup.R;
import omkabel.project.timeup.Server.Adapter.Adapter_Jadwal;
import omkabel.project.timeup.Server.Adapter.Adapter_Soal;
import omkabel.project.timeup.Server.ApiServices;
import omkabel.project.timeup.Server.InitRetrofit;
import omkabel.project.timeup.Server.Item.Item_Jadwal;
import omkabel.project.timeup.Server.Item.Item_Soal;
import omkabel.project.timeup.Server.Response.Response_Jadwal;
import omkabel.project.timeup.Server.Response.Response_Soal;
import omkabel.project.timeup.Session.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Menu_ListJadwal extends AppCompatActivity {
    @BindView(R.id.list_jadwal)
    RecyclerView recyclerView;
    ProgressDialog loading;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__list_jadwal);
        ButterKnife.bind(this);
        sharedPrefManager=new SharedPrefManager(this);
        loading=new ProgressDialog(Menu_ListJadwal.this);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(llm);
        loading.setMessage("Mohon Tunggu Sebentar.....");
        loading.setCancelable(false);
        loading.show();
        tampil_jadwal();
    }

    private void tampil_jadwal() {
        ApiServices api = InitRetrofit.getInstance().getApi();
        Call<Response_Jadwal> menuCall = api.tampil_jadwal(sharedPrefManager.getSP_IMEI());
        menuCall.enqueue(new Callback<Response_Jadwal>() {
            @Override
            public void onResponse(Call<Response_Jadwal> call, Response<Response_Jadwal> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Item_Jadwal> jadwal= response.body().getJadwal();
                    boolean status = response.body().isStatus();
                    if (status){
                        loading.dismiss();
                        Adapter_Jadwal adapter = new Adapter_Jadwal(Menu_ListJadwal.this, jadwal);
                        recyclerView.setAdapter(adapter);
                    } else {
                        try {
                            loading.dismiss();
                            Toast.makeText(Menu_ListJadwal.this, "Tidak Ada Soal saat ini", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Response_Jadwal> call, Throwable t) {
                try {
                    loading.dismiss();
                    Toast.makeText(Menu_ListJadwal.this, "Server Tidak Merespon", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Intent intent=new Intent(Menu_ListJadwal.this, Menu_Utama.class);
        startActivity(intent);
        finish();
    }
    }
