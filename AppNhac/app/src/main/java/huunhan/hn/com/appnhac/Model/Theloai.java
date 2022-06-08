package huunhan.hn.com.appnhac.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Theloai implements Serializable {

@SerializedName("IdTheloai")
@Expose
private String idTheloai;
@SerializedName("IdKeyChude")
@Expose
private String idKeyChude;
@SerializedName("TenTheloai")
@Expose
private String tenTheloai;
@SerializedName("HinhTheloai")
@Expose
private String hinhTheloai;

public String getIdTheloai() {
return idTheloai;
}

public void setIdTheloai(String idTheloai) {
this.idTheloai = idTheloai;
}

public String getIdKeyChude() {
return idKeyChude;
}

public void setIdKeyChude(String idKeyChude) {
this.idKeyChude = idKeyChude;
}

public String getTenTheloai() {
return tenTheloai;
}

public void setTenTheloai(String tenTheloai) {
this.tenTheloai = tenTheloai;
}

public String getHinhTheloai() {
return hinhTheloai;
}

public void setHinhTheloai(String hinhTheloai) {
this.hinhTheloai = hinhTheloai;
}

}