package huunhan.hn.com.appnhac.Service;

public class APIService {

//        private static String base_url = "http://192.168.60.186/appnhac/";
        private static String base_url = "https://huunhan97.000webhostapp.com/Server/";
        public static Dataservice getService() {
            return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
        }

}
