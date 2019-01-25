package corp.gusma.jogjatourism;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Value {
@SerializedName("data")
private List<DataWisata> dataWisata;

    public List<DataWisata> getDataWisata() {
        return dataWisata;
    }

    public void setDataWisata(List<DataWisata> dataWisata) {
        this.dataWisata = dataWisata;
    }
}
