package omkabel.project.timeup.Server.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import omkabel.project.timeup.Features.Menu_ListJadwal;
import omkabel.project.timeup.Features.Menu_SettingJadwal;
import omkabel.project.timeup.R;
import omkabel.project.timeup.Server.Controller.Controller;
import omkabel.project.timeup.Server.Controller.MyController;
import omkabel.project.timeup.Server.Item.Item_Jadwal;

public class Adapter_Notifikasi extends RecyclerView.Adapter<Adapter_Notifikasi.MyViewHolder> implements MyController {
    Context context;
    List<Item_Jadwal> menu;
    ProgressDialog loading;
    Controller controller;


    public Adapter_Notifikasi(Context context, List<Item_Jadwal> data_menu) {
        this.context = context;
        this.menu= data_menu;
    }

    @Override
    public Adapter_Notifikasi.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_notifikasi, parent, false);
        Adapter_Notifikasi.MyViewHolder holder = new Adapter_Notifikasi.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Adapter_Notifikasi.MyViewHolder holder, final int position) {
        holder.nama.setText(menu.get(position).getNama()+" "+"Bisa Di buka");
//        holder.Package.setText(menu.get(position).getJammulai()+"--"+menu.get(position).getJamakhir());
//        holder.jammulai.setText(menu.get(position).getJammulai());
//        holder.jamakhir.setText(menu.get(position).getJamakhir());
        String pkg =menu.get(position).getJsonMemberPackage();
        Drawable icon = null;
        try {
            icon = context.getPackageManager().getApplicationIcon(pkg);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        holder.Image.setImageDrawable(icon);

//        holder.upadate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent=new Intent(context, Menu_SettingJadwal.class);
////                intent.putExtra("Package",menu.get(position).getJsonMemberPackage());
////                intent.putExtra("Nama",menu.get(position).getNama());
////                context.startActivity(intent);
//            }
//        });
//        holder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String id=menu.get(position).getId();
//                PopupDelete(id);
//
//            }
//        });
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
        @BindView(R.id.title_notif)
        TextView nama;
//        @BindView(R.id.Package_aplikasi)
//        TextView Package;
        //        @BindView(R.id.Jammulai_aplikasi)
//        TextView jammulai;
//        @BindView(R.id.Jamakhir_aplikasi)
//        TextView jamakhir;
//        @BindView(R.id.Delete_Jadwal)
//        Button delete;
//        @BindView(R.id.Upadate_Jadwal)
//        Button upadate;
        @BindView(R.id.apk_iconnotif)
        ImageView Image;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}