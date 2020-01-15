package dev.zmq.m3h.Interface;


import java.util.List;

import dev.zmq.m3h.Sign_In.SignInRequest;
import dev.zmq.m3h.Sign_In.SignInResponse;
import dev.zmq.m3h.Sign_Up.SignUpRequest;
import dev.zmq.m3h.Sign_Up.SignUpResponse;
import dev.zmq.m3h.URL.URL_Constant;
import dev.zmq.m3h.Wallpaper.GalleryResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Interface_API
{
@GET(URL_Constant.GALLERY)
    Call<List<GalleryResponse>> getAllPhotos();

@POST(URL_Constant.SIGN_IN)
    Call<SignInResponse> getLogin(@Body SignInRequest request);

@POST(URL_Constant.SIGN_UP)
    Call<SignUpResponse> userRegister(@Body SignUpRequest request);
}
