package huunhan.hn.com.appnhac.Service;

import java.util.List;

import huunhan.hn.com.appnhac.Model.Album;
import huunhan.hn.com.appnhac.Model.Baihat;
import huunhan.hn.com.appnhac.Model.ChuDe;
import huunhan.hn.com.appnhac.Model.ChudeVaTheloai;
import huunhan.hn.com.appnhac.Model.Playlist;
import huunhan.hn.com.appnhac.Model.Quangcao;
import huunhan.hn.com.appnhac.Model.Theloai;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {

    @GET("songbanner.php")
    Call<List<Quangcao>>  getDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> getDataPlaylistCurrentDay();

    @GET("chudevatheloai.php")
    Call<ChudeVaTheloai> getDataChudeVaTheloai();

    @GET("albumhot.php")
    Call<List<Album>> getDataAlbumHot();

    @GET("baihatduocthich.php")
    Call<List<Baihat>> getDataBaihatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> getDanhsachBaihatTheoQuangCao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> getDanhsachBaihatTheoPlaylist(@Field("idplaylist") String idplaylist);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> getDanhsachBaihattheoTheloai(@Field("idtheloai") String idtheloai);

    @GET("danhsachcacplaylist.php")
    Call<List<Playlist>> getDataDanhsachcacPlaylist();

    @GET("tatcachude.php")
    Call<List<ChuDe>> getAllChude();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<Theloai>> getTheloaiTheochude(@Field("idchude") String idchude);

    @GET("tatcaalbum.php")
    Call<List<Album>> getDanhsachAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> getDanhsachbaihattheoAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotthich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<Baihat>> getSearchBaiHat(@Field("tukhoa") String tukhoa);
}
