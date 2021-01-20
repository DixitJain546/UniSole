package com.example.unisole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unisole.builder.RetrofitBuilder;
import com.example.unisole.interfaces.ILoginApi;
import com.example.unisole.models.Token;
import com.example.unisole.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegistrationActivity extends AppCompatActivity {
    private static final String TOKEN_TAG="TOKEN";
    private static final int ROLE_ID=1;
    EditText email;
    EditText password;
    EditText userName;
    EditText confirmPassword;
    Retrofit retrofit= RetrofitBuilder.getInstance();
    ILoginApi iLoginApi=retrofit.create(ILoginApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        email=findViewById(R.id.et_register_email);
        password=findViewById(R.id.et_register_password);
        userName=findViewById(R.id.et_user_name);
        confirmPassword=findViewById(R.id.et_confirm_password);
        findViewById(R.id.img_left_arrow_registraton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegistrationActivity.this,WelcomeActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();

            }
        });

    }
    public void register(){
        if(validate()) {
            User user = new User();
            user.setEmail(email.getText().toString());
            user.setPassword(password.getText().toString());
            user.setUserName(userName.getText().toString());
            Call<Token> responses = iLoginApi.signup(user, ROLE_ID);
            Callback<Token> callback = new Callback<Token>() {
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {
                    SharedPreferences sharedPreferences = getSharedPreferences("com.example.unisole", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(TOKEN_TAG, response.body().getToken());
                    editor.commit();
                    Intent intent = new Intent(RegistrationActivity.this, HomeActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Token> call, Throwable t) {
                    Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                      Log.d("Token : ",t.getMessage());
                }
            };
            responses.enqueue(callback);
        }
    }
    public boolean validate() {
        boolean valid = true;

        String emailLogin = email.getText().toString();
        String passwordLogin = password.getText().toString();
        String user=userName.getText().toString();
        String confirm=confirmPassword.getText().toString();

        if(user.isEmpty())
            userName.setError("Enter a Valid User Name");

        if (emailLogin.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailLogin).matches()) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }

        if (passwordLogin.isEmpty() || passwordLogin.length() < 8 || passwordLogin.length() > 50) {
            password.setError("Password must be of 8 characters");
            valid = false;
        } else {
            password.setError(null);
        }
        if(confirm.isEmpty() || !(confirm.equals(passwordLogin))){
            confirmPassword.setError("Password does not match");
        }

        return valid;
    }
}
