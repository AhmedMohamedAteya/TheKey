package apps.pixel.the.key.activites.beforeHome.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import apps.pixel.the.key.R;
import apps.pixel.the.key.activites.beforeHome.ForgetPasswordActivity;
import apps.pixel.the.key.activites.beforeHome.register.SignUpActivity;
import apps.pixel.the.key.models.externalLogin.RequestModel;
import apps.pixel.the.key.utilities.CairoBoldButton;
import apps.pixel.the.key.utilities.CairoRegularTextView;

public class SignInActivity extends AppCompatActivity {

    private NestedScrollView mNested;
    private CairoRegularTextView mTxtRegister, mTxtForgotPassword;
    private CairoBoldButton mBtnLogin;

    private TextInputEditText mEtUsername;
    private TextInputEditText mEtPassword;



    LoginPresenter loginPresenter;


    LoginButton loginButton;
    CallbackManager callbackManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initViews();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initViews() {
        mBtnLogin = findViewById(R.id.btn_login);
        mNested = findViewById(R.id.nested);
        mEtUsername = findViewById(R.id.et_username);
        mEtPassword = findViewById(R.id.et_password);



        loginPresenter=new LoginPresenter(this);

/*
        mNested.setOnTouchListener((v, event) -> {
            Constant.hideKeyboardFrom(SignInActivity.this, v);
            return true;
        });
*/


        mTxtRegister = findViewById(R.id.txt_register);
        mTxtRegister.setOnClickListener(v -> {
            startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            Animatoo.animateSwipeLeft(this);
        });

        mTxtForgotPassword = findViewById(R.id.txt_forgt_password);
        mTxtForgotPassword.setOnClickListener(v -> {
            startActivity(new Intent(SignInActivity.this, ForgetPasswordActivity.class));
            Animatoo.animateSwipeLeft(this);
        });


        mBtnLogin.setOnClickListener(v -> {
            loginPresenter.signIn(mEtUsername.getText().toString(),mEtPassword.getText().toString());
        });


        FacebookSdk.sdkInitialize(this );

        callbackManager = CallbackManager.Factory.create();


        loginButton = findViewById(R.id.login_button);
        // If you are using in a fragment, call loginButton.setFragment(this);

        loginButton.setReadPermissions("email");


        /*List < String > permissionNeeds = Collections.singletonList("email");*/
        //loginButton.setReadPermissions(permissionNeeds);
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                //hud.show();
                AccessToken accessToken = loginResult.getAccessToken();
                Log.i("accessToken",accessToken.getToken());
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    String id = object.getString("id");
                                    String first_name = object.getString("first_name");
                                    String last_name = object.getString("last_name");
                                    String Img_URL = "http://graph.facebook.com/" + id + "/picture?type=large";
                                    String email="";
                                    if (object.has("email")) {
                                        email = object.getString("email");
                                    }
                                    Log.i("first_name",object.getString("first_name"));
                                    Log.i("last_name",object.getString("last_name"));
                                    Log.i("email",object.getString("email"));
                                    Log.i("id",object.getString("id"));
                                    RequestModel loginRequestModel=new RequestModel();
                                    loginRequestModel.setEmail(email);
                                    loginRequestModel.setExternalAccessToken(accessToken.getToken());
                                    loginRequestModel.setId(id);
                                    loginRequestModel.setName(first_name+" "+last_name);
                                    loginPresenter.signInWithSocialMedia(loginRequestModel);

                                } catch (Exception e) {
                                    //showSnackBarMessage(e.getMessage());
                                    Toast.makeText(SignInActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,first_name,last_name, email,location,gender");
                request.setParameters(parameters);
                request.executeAsync();
            }
            @Override
            public void onCancel() {
                //hud.dismiss();
                LoginManager.getInstance().logOut();
            }
            @Override
            public void onError(FacebookException exception)
            {
                Toast.makeText(SignInActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                LoginManager.getInstance().logOut();
                //hud.dismiss();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
