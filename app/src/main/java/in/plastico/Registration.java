package in.plastico;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
    Map<String, String> params;
    CheckBox checkBoxIsmanufacturer;
    EditText editTextNameReg,editTextPhoneReg,editTextEmailReg,editTextPassReg,editTextFirmNameReg,editTextFirmAddReg,editTextPinReg;
    TextInputLayout tilFName,tilFAdd,tilPin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle("Registration");
        params = new HashMap<String, String>();

        checkBoxIsmanufacturer = (CheckBox)findViewById(R.id.checkboxIsManufacturer);
        editTextNameReg = (EditText)findViewById(R.id.editTextNameReg);
        editTextPhoneReg = (EditText)findViewById(R.id.editTextPhoneReg);
        editTextEmailReg = (EditText)findViewById(R.id.editTextEmailReg);
        editTextPassReg = (EditText)findViewById(R.id.editTextPassReg);
        editTextFirmNameReg = (EditText)findViewById(R.id.editTextFirmNameReg);
        editTextFirmAddReg = (EditText)findViewById(R.id.editTextFirmAddReg);
        editTextPinReg = (EditText)findViewById(R.id.editTextPinReg);

        tilFName = (TextInputLayout)findViewById(R.id.tilFName);
        tilFAdd = (TextInputLayout)findViewById(R.id.tilFAdd);
        tilPin = (TextInputLayout)findViewById(R.id.tilPin);


        checkBoxIsmanufacturer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    editTextFirmNameReg.setVisibility(View.GONE);
                    editTextFirmAddReg.setVisibility(View.GONE);
                    editTextPinReg.setVisibility(View.GONE);
                    tilFName.setVisibility(View.GONE);
                    tilFAdd.setVisibility(View.GONE);
                    tilPin.setVisibility(View.GONE);
                }
                else
                {
                    editTextFirmNameReg.setVisibility(View.VISIBLE);
                    editTextFirmAddReg.setVisibility(View.VISIBLE);
                    editTextPinReg.setVisibility(View.VISIBLE);
                    tilFName.setVisibility(View.VISIBLE);
                    tilFAdd.setVisibility(View.VISIBLE);
                    tilPin.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void doRegister(View view)
    {
        Map<String, String> params = new HashMap<String, String>();

        if(checkBoxIsmanufacturer.isChecked())
        {
            params.put("name", String.valueOf(editTextNameReg.getText()));
            params.put("email", String.valueOf(editTextEmailReg.getText()));
            params.put("phone", String.valueOf(editTextPhoneReg.getText()));
            params.put("pass", String.valueOf(editTextPassReg.getText()));
            params.put("fname", String.valueOf(editTextFirmNameReg.getText()));
            params.put("fadd", String.valueOf(editTextFirmAddReg.getText()));
            params.put("pin", String.valueOf(editTextPinReg.getText()));
            params.put("type", "1");
            Toast.makeText(this, "You are manufacturer!"+params.toString(), Toast.LENGTH_SHORT).show();
            Log.d("params all values : ",params.toString());
        }
        else
        {
            params.put("name", String.valueOf(editTextNameReg.getText()));
            params.put("email", String.valueOf(editTextEmailReg.getText()));
            params.put("phone", String.valueOf(editTextPhoneReg.getText()));
            params.put("pass", String.valueOf(editTextPassReg.getText()));
            params.put("type", "0");
            Toast.makeText(this, "You are a regular User!"+params.toString(), Toast.LENGTH_SHORT).show();
            Log.d("params all values : ",params.toString());
        }
        performRegistration(params);
    }
    void performRegistration(final Map<String,String> params)
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://www.plastico.activemedia.in/api/regis.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.d("response :",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String regResponse= jsonObject.getString("response");
                            if(regResponse.equalsIgnoreCase("success"))
                            {
                                Toast.makeText(Registration.this, "Registration Successful!", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(Registration.this, "You have following error :"+response, Toast.LENGTH_SHORT).show();
                            }
                            //Log.d("Reg response:",regResponse);
                            //Toast.makeText(Registration.this, "Login Successful"+response, Toast.LENGTH_SHORT).show();

                        }
                        catch (Exception e)
                        {
                            Toast.makeText(Registration.this, "Error!"+e.toString(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error",error.toString());
                    }
                }
        ){
            @Override
            protected Map<String,String> getParams(){
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }

}
