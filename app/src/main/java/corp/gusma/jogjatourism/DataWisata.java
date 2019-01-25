package corp.gusma.jogjatourism;

import com.google.gson.annotations.SerializedName;

class DataWisata {
@SerializedName("nama_pariwisata")
    private String nama;
@SerializedName("alamat_pariwisata")
    private String alamat;
@SerializedName("detail_pariwisata")
    private String detail;
@SerializedName("gambar_pariwisata")
    private String gambar;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
