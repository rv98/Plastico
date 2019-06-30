package in.plastico;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
	Context context;
	EditText email,password;
	 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		 context = getApplicationContext();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);
		email=(EditText)findViewById(R.id.editTextEmail);
		password=(EditText)findViewById(R.id.editTextPassword);

	}
	public void checkLogin(View view)
	{
		SharedPreferences sharedPref = context.getSharedPreferences(
				getString(R.string.token), Context.MODE_PRIVATE);

		String value = null;
		String token = sharedPref.getString(getString(R.string.token),value);
		if(token == null)
		{
			Toast.makeText(context,"Not Logged in", Toast.LENGTH_SHORT).show();
			SharedPreferences.Editor editor = sharedPref.edit();
			editor.putString(getString(R.string.token),"1245667");
			editor.commit();
			Log.d("Token","token written to the storage");
		}
		else
		{
			Toast.makeText(context, "Logged In", Toast.LENGTH_SHORT).show();
		}
	}
	public void logout(View view)
	{
		SharedPreferences sharedPref = context.getSharedPreferences(
				getString(R.string.token), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.remove(getString(R.string.token));
		editor.commit();
		Log.d("Token","removed logged in token");
	}
	public void login(View view)
	{
		startActivity(new Intent(MainActivity.this,HomeActivity.class));
	}
	public void doLogin(View view)
	{
		final ProgressDialog progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Loading...");
		progressDialog.show();
		StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://www.plastico.activemedia.in/api/login.php",
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

						progressDialog.dismiss();
						Log.d("response :",response);
						try {
							JSONObject jsonObject = new JSONObject(response);
							String loginStatus = jsonObject.getString("response");
							String uid = jsonObject.getString("uid");
							String name = jsonObject.getString("name");
							String type = jsonObject.getString("type");
							String token = jsonObject.getString("token");

							Log.d("loginResponse",loginStatus);
							Log.d("response",response);
							Toast.makeText(MainActivity.this, "Login Successful"+response, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                            intent.putExtra("uid",uid);
                            intent.putExtra("name",name);
                            intent.putExtra("type",type);
                            intent.putExtra("token",token);
							startActivity(intent);
						}
						catch (Exception e)
						{
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
				Map<String, String> params = new HashMap<String, String>();
				params.put("email", String.valueOf(email.getText()));
				params.put("pass", String.valueOf(password.getText()));
				return params;
			}
		};
		Volley.newRequestQueue(this).add(stringRequest);
	}

	public void openRegistration(View view) {
		//Toast.makeText(this, "Registration Page", Toast.LENGTH_SHORT).show();
		startActivity(new Intent(this,Registration.class));
	}
}