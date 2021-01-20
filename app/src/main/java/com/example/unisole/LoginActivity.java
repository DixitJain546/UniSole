package com.example.unisole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.unisole.builder.RetrofitBuilder;
import com.example.unisole.interfaces.ILoginApi;
import com.example.unisole.models.GoogleUser;
import com.example.unisole.models.Token;
import com.example.unisole.models.UserLoginDetail;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private static final String TOKEN_TAG="TOKEN";
    private static final int ROLE_ID=1;
    EditText email;
    EditText password;
    Retrofit retrofit= RetrofitBuilder.getInstance();
    ILoginApi iLoginApi=retrofit.create(ILoginApi.class);

    private  int RC_SIGN_IN =1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.et_login_email);
        password=findViewById(R.id.et_login_password);
        findViewById(R.id.img_left_arrow_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,WelcomeActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        ImageButton signInButton = findViewById(R.id.btn_login_google);



        //signInButton.setSize(SignInButton.SIZE_STANDARD);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        if(account!=null){
//            Log.d("Account Details : ",account.getDisplayName());
//            Log.d("Account Details : ",account.getEmail());
//           // Log.d("Account Details : ",account.getIdToken());
//            //Log.d("Account Details : ",account.getId());
////            Log.d("Account Details : ",account.getServerAuthCode());
//         //   Log.d("Account Details : ",account.getAccount().toString());
//            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
//            startActivity(intent);
//
//        }
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(v.getId())
                {
                    case R.id.btn_login_google:
                        signIn(mGoogleSignInClient);
                        break;
                }
            }
        });
    }

    public void login(){
        if(validate()) {
            UserLoginDetail userLoginDetail = new UserLoginDetail();
            userLoginDetail.setEmail(email.getText().toString());
            userLoginDetail.setPassword(password.getText().toString());
            userLoginDetail.setRole(ROLE_ID);
            Call<Token> responses = iLoginApi.signin(userLoginDetail);
            Callback<Token> callback = new Callback<Token>() {
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {

                    if ("Invalid Credentials".equals(response.body().getToken())) {
                        Toast.makeText(LoginActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
                    } else {
                        SharedPreferences sharedPreferences = getSharedPreferences("com.example.unisole", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(TOKEN_TAG, response.body().getToken());
                        editor.commit();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Token> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            };
            responses.enqueue(callback);
        }
    }
    private void signIn(GoogleSignInClient mGoogleSignInClient) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            GoogleUser googleUser=new GoogleUser();
            googleUser.setAuthType(true);
            googleUser.setEmail(account.getEmail());
            googleUser.setRole(ROLE_ID);
            googleUser.setUserName(account.getDisplayName());
            googleUser.setToken(account.getIdToken());
            Call<Token> responses=iLoginApi.loginWithGoogle(googleUser);
            Callback<Token> callback=new Callback<Token>() {
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {
                    Log.d("Account Details : ",account.getEmail());
                    SharedPreferences sharedPreferences=getSharedPreferences("com.example.unisole", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(TOKEN_TAG,response.body().getToken());
                    editor.commit();
                    Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Token> call, Throwable t) {
                    Log.d("Account Failed : ",account.getEmail());
                    Toast.makeText(LoginActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                }
            };
            responses.enqueue(callback);
            // Signed in successfully, show authenticated UI.
           // Log.d("Account details : ",account.getEmail()+" "+account.getDisplayName());
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("sign=in result : ", "signInResult:failed code=" + e.getStatusCode());
           // updateUI(null);
        }
    }

    public boolean validate() {
        boolean valid = true;

        String emailLogin = email.getText().toString();
        String passwordLogin = password.getText().toString();

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

        return valid;
    }

}