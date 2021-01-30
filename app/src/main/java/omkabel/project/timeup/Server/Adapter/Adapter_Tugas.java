package omkabel.project.timeup.Server.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import omkabel.project.timeup.Features.Menu_ListJadwal;
import omkabel.project.timeup.R;
import omkabel.project.timeup.Server.Controller.Controller;
import omkabel.project.timeup.Server.Controller.MyController;
import omkabel.project.timeup.Server.Item.Item_Tugas;

public class Adapter_Tugas extends RecyclerView.Adapter<Adapter_Tugas .MyViewHolder> implements MyController {
    Context context;
    List<Item_Tugas> menu;
    ProgressDialog loading;
    Controller controller;


    public Adapter_Tugas  (Context context, List<Item_Tugas> data_menu) {
        this.context = context;
        this.menu= data_menu;
    }

    @Override
    public Adapter_Tugas .MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tugas, parent, false);
        Adapter_Tugas .MyViewHolder holder = new Adapter_Tugas .MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Adapter_Tugas .MyViewHolder holder, final int position) {
        holder.NamaTugas.setText(menu.get(position).getNamaTugas());
    }

    private void PopupDelete(String id) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(context);
        dialog.setMessage("Apakah Anda yakin Akan Menghapus Aplikasi dari daftar Jadwal?");
        dialog.setTitle("Pemberitahuan");
        dialog.setPositiveButton("IYA", new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog, int which) {
            delete(id);
        }
        });
        dialog.setNegativeButton("TIDAK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }

    private void delete(String id) {
        loading=new ProgressDialog(context);
        loading.show();
        loading.setMessage("Mohon Tunggu.....");
        loading.setCancelable(false);
        controller=new Controller(this);
        controller.deletejadwal(id);

    }


    @Override
    public int getItemCount() {
        return menu.size();
    }

    @Override
    public void ImeiTerdaftar(String imei) {

    }

    @Override
    public void gagalmasuk(String Message) {
        loading.show();
        Toast.makeText(context, ""+Message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void ImeiTidakTerdaftar() {

    }

    @Override
    public void berhasilmasuk(String Message) {
        loading.show();
        Toast.makeText(context, ""+Message, Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(context, Menu_ListJadwal.class);
        context.startActivity(intent);

    }

    @Override
    public void TidakAdaKoneksi(String error_message) {
        loading.show();
        Toast.makeText(context, ""+error_message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void gagalupdate(String pesan) {

    }

    @Override
    public void berhasilupdate(String pesan) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.Nama_Tugas)
        TextView NamaTugas;
        @BindView(R.id.Kerjakan)
        Button Kerjakan;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}