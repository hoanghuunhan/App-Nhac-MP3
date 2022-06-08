package huunhan.hn.com.appnhac.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ChuDe implements Serializable {

@SerializedName("IdChude")
@Expose
private String idChude;
@SerializedName("TenChude")
@Expose
private String tenChude;
@SerializedName("Hinhchude")
@Expose
private String hinhchude;

public String getIdChude() {
return idChude;
}

public void setIdChude(String idChude) {
this.idChude = idChude;
}

public String getTenChude() {
return tenChude;
}

public void setTenChude(String tenChude) {
this.tenChude = tenChude;
}

public String getHinhchude() {
return hinhchude;
}

public void setHinhchude(String hinhchude) {
this.hinhchude = hinhchude;
}

}