package omkabel.project.timeup.Features;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import omkabel.project.timeup.R;

public class Menu_Dashboard extends AppCompatActivity {
    @BindView(R.id.Apk_user)
    ImageView ListApk;
    @BindView(R.id.Jadwal_user)
    ImageView Listjadwal;
    @BindView(R.id.Reminder_user)
    ImageView ListPenginat;
    @BindView(R.id.Status_user)
    ImageView Liststatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__dashboard);
        ButterKnife.bind(Menu_Dashboard.this);
        ListApk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoListApk();
            }
        });
    }

    private void GotoListApk() {
        Intent intent=new Intent(Menu_Dashboard.this,Menu_ListAplikasi.class);
        startActivity(intent);
        finish();
    }
}