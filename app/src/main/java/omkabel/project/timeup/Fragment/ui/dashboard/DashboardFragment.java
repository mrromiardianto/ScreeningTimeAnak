package omkabel.project.timeup.Fragment.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import butterknife.BindView;
import butterknife.ButterKnife;
import omkabel.project.timeup.Features.Menu_Dashboard;
import omkabel.project.timeup.Features.Menu_ListAplikasi;
import omkabel.project.timeup.Features.Menu_ListJadwal;
import omkabel.project.timeup.Features.Menu_Notifikasi;
import omkabel.project.timeup.Features.Menu_Profile;
import omkabel.project.timeup.Features.Menu_Statistic;
import omkabel.project.timeup.R;

public class DashboardFragment extends Fragment {
    @BindView(R.id.Apk_user)
    ImageView ListApk;
    @BindView(R.id.Jadwal_user)
    ImageView Listjadwal;
    @BindView(R.id.Reminder_user)
    ImageView ListPenginat;
    @BindView(R.id.Status_user)
    ImageView Liststatus;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_menu__dashboard, container, false);
        ButterKnife.bind(this,root);
        ListApk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoListApk();
            }
        });
        Listjadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoListJadwal();
            }
        });
        Liststatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoStatus();
            }
        });
        ListPenginat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoReminder();
            }
        });
        return root;
    }

    private void GotoReminder() {
        Intent intent=new Intent(getContext(), Menu_Profile.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void GotoStatus() {
        Intent intent=new Intent(getContext(), Menu_Statistic.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void GotoListJadwal() {
        Intent intent=new Intent(getContext(), Menu_ListJadwal.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void GotoListApk() {
        Intent intent=new Intent(getContext(), Menu_ListAplikasi.class);
        startActivity(intent);
        getActivity().finish();
    }
}