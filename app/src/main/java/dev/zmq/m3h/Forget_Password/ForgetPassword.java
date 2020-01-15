package dev.zmq.m3h.Forget_Password;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import dev.zmq.m3h.R;

public class ForgetPassword extends Fragment
{
    private View view;
    private CircleImageView circleImageView;
    private EditText edt_email;
    private Button btn_forget_password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate( R.layout.forget_password,container,false);
        initView();
        return view;
    }
    private void initView()
    {
        circleImageView=(CircleImageView)view.findViewById( R.id.circleView_logo);
        edt_email=(EditText)view.findViewById( R.id.edt_forget_password_email );
        btn_forget_password=(Button)view.findViewById( R.id.btn_forget_password_send );

        btn_forget_password.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText( getActivity(),"Forget Password Clicked",Toast.LENGTH_LONG ).show();
            }
        } );
    }

}
