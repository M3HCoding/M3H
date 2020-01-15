package dev.zmq.m3h.Sign_In;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import dev.zmq.m3h.Database.DatabaseClient;
import dev.zmq.m3h.Forget_Password.ForgetPassword;
import dev.zmq.m3h.Interface.Interface_API;
import dev.zmq.m3h.Navigation_Menu.Navigation_Menu;
import dev.zmq.m3h.R;
import dev.zmq.m3h.Retrofit_API.Retrofit_API;
import dev.zmq.m3h.Sign_Up.SignUp;
import dev.zmq.m3h.URL.URL_Constant;
import dev.zmq.m3h.Sign_Up.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends Fragment implements View.OnClickListener
{
    private View view;
    private CircleImageView circleImageView;
    private EditText edt_Username,edt_Password;
    private CheckBox checkBox;
    private TextView txt_forget_password;
    private Button btn_Sign_In,btn_Sign_Up;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        view=inflater.inflate( R.layout.signin,container,false );
        iniView();
        return view;
    }
    private void iniView()
    {
        try {
            pref=getActivity().getSharedPreferences( "M3H",getActivity().MODE_PRIVATE );
            circleImageView=(CircleImageView)view.findViewById( R.id.circleView_logo );
            edt_Username=(EditText)view.findViewById( R.id.edt_username );
            edt_Password=(EditText)view.findViewById( R.id.edt_password );
            checkBox=(CheckBox)view.findViewById( R.id.checkbox_signin );
            txt_forget_password=(TextView)view.findViewById( R.id.txt_forget_password );
            btn_Sign_In=(Button)view.findViewById( R.id.btn_sign_in );
            btn_Sign_Up=(Button)view.findViewById( R.id.btn_sign_up );

            txt_forget_password.setOnClickListener( this );
            btn_Sign_In.setOnClickListener( this );
            btn_Sign_Up.setOnClickListener( this );
        }catch (NullPointerException npe){
            npe.printStackTrace();
        }
        showPassword();
    }
    private void showPassword() {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    // show password
                    edt_Password.setTransformationMethod( PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    edt_Password.setTransformationMethod( HideReturnsTransformationMethod.getInstance());
                }

            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        if (pref.getBoolean( URL_Constant.SIGN_UP, false)) {
            edt_Username.setText(pref.getString(URL_Constant.LOGIN_Id, ""));
            edt_Password.setText(pref.getString(URL_Constant.PASSWORD, ""));
        }
    }

    @Override
    public void onClick(View v)
    {
        if (v==btn_Sign_In)
        {
            getActivity().getSupportFragmentManager().popBackStack();
            login();
        }
        else if (v==btn_Sign_Up)
        {
            getActivity().getSupportFragmentManager().popBackStack();
            loadFragment(new SignUp() );
        }
        else if (v==txt_forget_password)
        {
            getActivity().getSupportFragmentManager().popBackStack();
            loadFragment(new ForgetPassword() );
        }
    }

    public void login()
    {
        final String userId=edt_Username.getText().toString().trim();
        final String password=edt_Password.getText().toString().trim();
        if (userId.isEmpty())
        {
            edt_Username.setError( "Enter Email" );
            edt_Username.requestFocus( );
            return;
        }
        if (password.isEmpty())
        {
            edt_Password.setError( "Enter Password" );
            edt_Password.requestFocus();
            return;
        }
        if (isConnection())
        {
            Toast.makeText( getActivity(),"Network Available",Toast.LENGTH_LONG ).show();
            /*Create handle for the RetrofitInstance interface*/
            Interface_API interface_api= Retrofit_API.getRetrofit_API().create(Interface_API.class );
            final Token token=new Token();
            token.setUserId(userId);
            token.setPassword(password);
            SignInRequest request=new SignInRequest( userId,password );

            Call<SignInResponse> call=interface_api.getLogin(request);
            call.enqueue( new Callback<SignInResponse>(){
                @Override
                public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                    SignInResponse Resp=response.body();
                    Toast.makeText(getActivity(), Resp.getMessage(), Toast.LENGTH_LONG).show();
                }
                @Override
                public void onFailure(Call<SignInResponse> call, Throwable t) {
                    Toast.makeText( getActivity(),"Something went wrong...Please try later!",Toast.LENGTH_LONG ).show();
                }
            } );
        }
        else {
            Toast.makeText( getActivity(),"No Network Available",Toast.LENGTH_LONG ).show();
            class LoginPage extends AsyncTask<Void,Void,Void>
            {
                @Override
                protected Void doInBackground(Void... voids)
                {
                    User user =new User();
                    user.setUserId( user.getUserId() );
                    user.setPassword( user.getPassword() );
                    DatabaseClient.getInstance( getActivity() ).getAppDatabase()
                            .dao().findByUserLogin( userId,password );
                    return null;
                }
                @Override
                protected void onPostExecute(Void aVoid)
                {
                    super.onPostExecute( aVoid );
                    getActivity().getSupportFragmentManager().popBackStack();
                    Intent menu=new Intent( getActivity(), Navigation_Menu.class );
                    startActivity( menu );

                }
            }
            LoginPage lp=new LoginPage();
            lp.execute(  );
        }
    }
    private boolean isConnection() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService( Context.CONNECTIVITY_SERVICE );
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            connected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
            return connected;
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText( getActivity(), "Connectivity Exception", Toast.LENGTH_SHORT ).show();
        }
        return connected;
    }
    public  void loadFragment(Fragment fragment)
    {
        fragmentManager=getFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add( R.id.fl_splash,fragment,"fragment");
        fragmentTransaction.commit();
    }
}
