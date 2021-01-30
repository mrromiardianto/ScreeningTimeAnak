package omkabel.project.timeup.Server.Response;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import omkabel.project.timeup.Server.Item.Item_Jadwal;
import omkabel.project.timeup.Server.Item.Item_Soal;
public class Response_Jadwal {
    @SerializedName("jadwal")
    private List<Item_Jadwal> jadwal;
    @SerializedName("status")
    private boolean status;

    public void setJadwal(List<Item_Jadwal> jadwal) {
        this.jadwal= jadwal;
    }

    public List<Item_Jadwal> getJadwal()
    {
        return jadwal;
    }
    public void setStatus(boolean status){

        this.status = status;
    }

    public boolean isStatus(){

        return status;
    }

    @Override
    public String toString(){
        return
                "Response_Jadwal{" +
                        "jadwal = '" +jadwal+ '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}
