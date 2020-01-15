package dev.zmq.m3h.Sign_Up;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import dev.zmq.m3h.Database.DatabaseClient;
import dev.zmq.m3h.Interface.Interface_API;
import dev.zmq.m3h.Navigation_Menu.Navigation_Menu;
import dev.zmq.m3h.R;
import dev.zmq.m3h.Retrofit_API.Retrofit_API;
import dev.zmq.m3h.Sign_In.SignIn;
import dev.zmq.m3h.URL.URL_Constant;
import retrofit2.Call;
import retrofit2.Callback;

public class SignUp extends Fragment implements View.OnClickListener
{
    private View view;

    private CircleImageView circleImageView;
    private EditText edt_name,edt_email,edt_dob,edt_mobile,edt_password,edt_cnf_password;
    private Button btn_Sign_up,btn_Sign_in;
    private RadioButton radio_male,radio_female,radio_other;
    private DatePickerDialog picker;
    private String fullName,loginId,dateOfBirth,gender,mobileNo,password,cnfPassword;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private SharedPreferences pref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.signup, container, false );
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        pref=getActivity().getSharedPreferences( "M3H",Context.MODE_PRIVATE );
        circleImageView=(CircleImageView)view.findViewById( R.id.circleView_logo );
        edt_name=(EditText)view.findViewById( R.id.edt_sign_up_name );
        edt_email=(EditText)view.findViewById( R.id.edt_sign_up_email );
        edt_dob=(EditText)view.findViewById( R.id.edt_sign_up_dob );
        radio_male=(RadioButton)view.findViewById( R.id.radio_male );
        radio_female=(RadioButton)view.findViewById( R.id.radio_female);
        radio_other=(RadioButton)view.findViewById( R.id.radio_other );
        edt_mobile=(EditText)view.findViewById( R.id.edt_sign_up_mobile_no );
        edt_password=(EditText)view.findViewById( R.id.edt_sign_up_password );
        edt_cnf_password=(EditText)view.findViewById( R.id.edt_sign_up_cnf_password );

        btn_Sign_up=(Button)view.findViewById( R.id.btn_sign_up );
        btn_Sign_in=(Button)view.findViewById( R.id.btn_sign_in );

        edt_dob.setOnClickListener( this );
        btn_Sign_up.setOnClickListener( this );
        btn_Sign_in.setOnClickListener( this );
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v)
    {
        if (v==btn_Sign_up)
        {
            register();
        }
        else if (v==btn_Sign_in)
        {
            getActivity().getSupportFragmentManager().popBackStack();
            loadFragment( new SignIn() );
        }
        else if (v==edt_dob)
        {
            datePicker();
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
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    private void register() {
        fullName=edt_name.getText().toString().trim();
        loginId=edt_email.getText().toString().trim();
        if (radio_male.isChecked())
        {
            gender=radio_male.getText().toString().trim();
        }
        else if (radio_female.isChecked())
        {
            gender=radio_female.getText().toString();
        }
        else if (radio_other.isChecked())
        {
            gender=radio_other.getText().toString();
        }
        mobileNo=edt_mobile.getText().toString().trim();
        password=edt_password.getText().toString().trim();
        cnfPassword=edt_cnf_password.getText().toString().trim();

        if (fullName.isEmpty())
        {
            edt_name.setError( "Enter full name" );
            edt_name.requestFocus();
            return;
        }
        if (loginId.isEmpty())
        {
            edt_email.setError( "Enter Email id" );
            edt_email.requestFocus();
            return;
        }
        if (mobileNo.isEmpty())
        {
            edt_mobile.setError( "Enter Mobile No." );
            edt_mobile.requestFocus();
            return;
        }
        if (password.isEmpty())
        {
            edt_password.setError( "Enter Password" );
            edt_password.requestFocus();
            return;
        }
        if (cnfPassword.isEmpty())
        {
            edt_cnf_password.setError( "Enter Confirm Password" );
            edt_cnf_password.requestFocus();
            return;
        }
        if (password!=null && cnfPassword!=null)
        {
            if (password.equals( cnfPassword ))
            {
                Toast.makeText( getActivity(),"Password matched",Toast.LENGTH_LONG ).show();
            }
            else if (!password.equals(cnfPassword ))
            {
                Toast.makeText( getActivity(),"Password do no match. Try Again",Toast.LENGTH_LONG ).show();
                edt_password.setText( "" );
                edt_cnf_password.setText( "" );
                edt_password.requestFocus();
                return;
            }
        }
        if (isConnection())
        {
            Toast.makeText( getActivity(),"Network Available",Toast.LENGTH_LONG ).show();
            Interface_API interface_api = Retrofit_API.getRetrofit_API().create(Interface_API.class);

            Registration registration=new Registration(fullName,loginId,dateOfBirth,gender,mobileNo,password);
            registration.setFullName(fullName);
            registration.setLoginId(loginId);
            registration.setDateOfBirth(dateOfBirth);
            registration.setGender(gender);
            registration.setMobileNo(mobileNo);
            registration.setPassword(password);
            SignUpRequest request = new SignUpRequest(fullName,loginId,dateOfBirth,gender,mobileNo,password);
            Call<SignUpResponse> response = interface_api.userRegister(request);

            response.enqueue(new Callback<SignUpResponse>()
            {
                @Override
                public void onResponse(Call<SignUpResponse> call, retrofit2.Response<SignUpResponse> response)
                {
                    SignUpResponse resp = response.body();
                    Toast.makeText(getActivity(), resp.getMessage(), Toast.LENGTH_LONG).show();
                    if (resp.getStatus())
                    {
                        SharedPreferences.Editor editor=pref.edit();
                        editor.putBoolean( URL_Constant.SIGN_UP,true);
                        editor.putString(URL_Constant.FULL_NAME,resp.getData().fullName);
                        editor.putString( URL_Constant.LOGIN_Id,resp.getData().loginId );
                        editor.putString(URL_Constant.DATE_OF_BIRTH,resp.getData().dateOfBirth);
                        editor.putString(URL_Constant.GENDER,resp.getData().gender);
                        editor.putString(URL_Constant.MOBILE_NO,resp.getData().mobileNo);
                        editor.putString(URL_Constant.PASSWORD,resp.getData().password);
                        editor.apply();
                        goToLogin();
                    }
                }

                private void goToLogin() {
                loadFragment( new SignIn() );
                }

                @Override
                public void onFailure(Call<SignUpResponse> call, Throwable t)
                {
                    Toast.makeText( getActivity(),t.getMessage(),Toast.LENGTH_LONG ).show();
                }
            });
        }
        else {
            Toast.makeText( getActivity(),"No Network Available",Toast.LENGTH_LONG ).show();
            class SaveRegister extends AsyncTask<Void,Void,Void> {
                @Override
                protected Void doInBackground(Void... voids)
                {
                    User user =new User();
                    user.setName( user.getName() );
                    user.setUserId( user.getUserId() );
                    user.setDob( user.getDob() );
                    user.setGender( user.getGender() );
                    user.setMobile( user.getMobile() );
                    user.setPassword( user.getPassword() );
                    user.setCnfPassword( user.getCnfPassword() );
                    DatabaseClient.getInstance( getActivity() ).getAppDatabase()
                            .dao().insert( user );
                    return null;
                }
                @Override
                protected  void onPostExecute(Void aVoid)
                {
                    super.onPostExecute( aVoid );
                    getActivity().getSupportFragmentManager().popBackStack();
                    Intent intent=new Intent( getActivity(), Navigation_Menu.class );
                    startActivity( intent );
                }
            }
            SaveRegister sr=new SaveRegister();
            sr.execute( );
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void datePicker()
    {
        final  Calendar calendar=Calendar.getInstance();
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int month=calendar.get(Calendar.MONTH);
        int year=calendar.get(Calendar.YEAR);
        picker =new DatePickerDialog( getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                edt_dob.setText( dayOfMonth + "/" + (monthOfYear + 1) + "/" + year );
            }
        },year,month,day );
        picker.show();
    }
    public  void loadFragment(Fragment fragment)
    {
        fragmentManager=getChildFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace( R.id.fl_splash,fragment,"fragment" );
        fragmentTransaction.addToBackStack( "fragment" ).commit();
    }
}
