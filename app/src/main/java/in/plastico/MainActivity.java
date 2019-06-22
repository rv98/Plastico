package in.plastico;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
	 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);
	}
	public void Login(View view)
	{
		startActivity(new Intent(this,HomeActivity.class));
	}

	public void openRegistration(View view) {
		Toast.makeText(this, "Registration Page", Toast.LENGTH_SHORT).show();
	}
}