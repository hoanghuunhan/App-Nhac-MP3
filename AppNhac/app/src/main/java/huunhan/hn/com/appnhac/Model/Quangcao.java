package huunhan.hn.com.appnhac.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Quangcao implements Serializable {

@SerializedName("IdQuangCao")
@Expose
private String idQuangCao;
@SerializedName("hinhanh")
@Expose
private String hinhanh;
@SerializedName("Noidung")
@Expose
private String noidung;
@SerializedName("IdBaihat")
@Expose
private String idBaihat;
@SerializedName("TenBaihat")
@Expose
private String tenBaihat;
@SerializedName("Hinhbaihat")
@Expose
private String hinhbaihat;

public String getIdQuangCao() {
return idQuangCao;
}

public void setIdQuangCao(String idQuangCao) {
this.idQuangCao = idQuangCao;
}

public String getHinhanh() {
return hinhanh;
}

public void setHinhanh(String hinhanh) {
this.hinhanh = hinhanh;
}

public String getNoidung() {
return noidung;
}

public void setNoidung(String noidung) {
this.noidung = noidung;
}

public String getIdBaihat() {
return idBaihat;
}

public void setIdBaihat(String idBaihat) {
this.idBaihat = idBaihat;
}

public String getTenBaihat() {
return tenBaihat;
}

public void setTenBaihat(String tenBaihat) {
this.tenBaihat = tenBaihat;
}

public String getHinhbaihat() {
return hinhbaihat;
}

public void setHinhbaihat(String hinhbaihat) {
this.hinhbaihat = hinhbaihat;
}

}