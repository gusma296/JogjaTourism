package corp.gusma.jogjatourism;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BaseApiService {
@GET("jsonBootcamp.php")
    Call<Value>getWisata();
}
