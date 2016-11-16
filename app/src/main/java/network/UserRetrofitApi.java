package network;

import entity.LoginResult;
import entity.User;
import entity.RegisterResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 请求接口，GET/POST
 * Created by Administrator on 2016/11/8.
 */
public interface UserRetrofitApi {
    // 在这个接口里面使用Retrofit来构建接口请求
    @POST("/Handler/UserHandler.ashx?action=register")
    Call<RegisterResult> register(@Body User user);
    @POST("/Handler/UserHandler.ashx?action=login")
    Call<LoginResult> login(@Body User user);


    /**
     * 注解：作用：Retrofit主要是通过注解来进行接口请求的
     *    1.消息的请求方式  @GET,@POST
     *    2.请求路径
     *    3.请求头  @Headers({"Acception-language","",""...}) 添加请求头信息
     *              @Header("Acception-language")String haader    动态修改请求头信息
     *    4.请求体  @Body String body
     *
     *    url 请求路径处理方面
     *      1.Get eg.https://api.github.com/users/list?sort=desc&name=zh
     *      2.Get: eg.https://api.github.com/users/{id}/list?name=dese&age=23   :{id}可替换块，可将他动态替换--->@Path("id")int id
     *      3.表单数据提交：name=....;age=...;      ----->   @FormUrlEncoded ,@Field("name")String name,@Field("age")int age
     *      4.多部分上传：   ----> @Multipart  @Part("xxx")ResponseBody responseBody
     */
//    @POST(BASE_URL)
//    Call<ResponseBody> getS(@Header("Acception-language")String head, @Body String body);

    /**
     * url参数的拼接/也可以将参数写死在Url中
     * @param
     * @return
     */
//    @GET(BASE_URL)
//    Call<ResponseBody> getZ(@Query("key1")String value1,
//                            @Query("key2")int value2,
//                            @QueryMap Map<String ,String> map);
//    @GET(BASE_URL)
//    Call<ResponseBody> getX(@Path("id")int id,
//                            @Query("name")String name,
//                            @Query("age")int age);
//    @POST(BASE_URL)
//    @FormUrlEncoded
//    Call<ResponseBody> getF(@Field("name")String name,
//                            @Field("age")int age);
//    @POST(BASE_URL)
//    @Multipart
//   Call<ResponseBody> getD(@Part("xxx")ResponseBody responseBody);

}
