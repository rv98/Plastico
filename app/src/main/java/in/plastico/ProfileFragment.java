package in.plastico;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.plastico.R;

public class ProfileFragment extends Fragment {

    TextView profileUid,profileName,profileType,profileToken;
    String uid="",name="",type="",token="";
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, null);

        profileUid = view.findViewById(R.id.profileUid);
        profileName= view.findViewById(R.id.productName);
        profileType= view.findViewById(R.id.profileType);
        profileToken= view.findViewById(R.id.profileToken);



        try {
            uid = getArguments().getString("uid");
            name = getArguments().getString("name");
            type = getArguments().getString("type");
            token = getArguments().getString("token");
            profileUid.setText(uid);
            profileName.setText(name);
            profileType.setText(type);
            profileToken.setText(token);

        }
        catch (Exception ex)
        {
//            uid = "";
//            name = "";
//            type = "";
//            token = "";
//            profileUid.setText(uid);
////            profileName.setText(name);
//            profileType.setText(type);
//            profileToken.setText(token);
            Log.d("ErrorProfile",ex.toString());
            ex.printStackTrace();
        }

        return view;
    }



}
