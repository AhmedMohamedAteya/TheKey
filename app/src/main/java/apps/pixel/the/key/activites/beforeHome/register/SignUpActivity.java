package apps.pixel.the.key.activites.beforeHome.register;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import apps.pixel.the.key.R;
import apps.pixel.the.key.activites.beforeHome.VerficationActivity;
import apps.pixel.the.key.utilities.CairoBoldButton;
import apps.pixel.the.key.utilities.CairoRegularEditText;
import apps.pixel.the.key.utilities.CairoRegularTextView;
import apps.pixel.the.key.utilities.Constant;
import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity {

    private LinearLayoutCompat mLinBack;
    private CircleImageView mImgProfile;
    private CairoRegularEditText mEtName;
    private CairoRegularEditText mEtMail;
    private CairoRegularEditText mEtPhone;
    private CairoRegularEditText mEtAddress;
    private CairoRegularEditText mEtPassword;
    private CairoRegularEditText mEtConfirmPassword;
    private CairoBoldButton mBtnRegister;

    private RegisterPresenter registerPresenter;



    private String image_url="";
    String imageType;
    private final String[] permissions = new String[]{

            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
    };


    @Override
    public void onBackPressed() {
        super.onBackPressed();
       finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();
    }

    private void initViews() {

        mLinBack = findViewById(R.id.lin_back);
        mImgProfile = findViewById(R.id.img_profile);
        mEtName = findViewById(R.id.et_name);
        mEtMail = findViewById(R.id.et_mail);
        mEtPhone = findViewById(R.id.et_phone);
        mEtAddress = findViewById(R.id.et_address);
        mEtPassword = findViewById(R.id.et_password);
        mEtConfirmPassword = findViewById(R.id.et_confirm_password);
        mBtnRegister = findViewById(R.id.btn_register);

        registerPresenter=new RegisterPresenter(this);

        mImgProfile.setOnClickListener(v -> {
            upload_img();
        });

        mLinBack.setOnClickListener(v -> {
            finish();
        });

        mBtnRegister.setOnClickListener(v ->
        {
            registerPresenter.register(
                    mEtName.getText().toString()
                    ,mEtMail.getText().toString()
                    ,mEtPhone.getText().toString()
                    ,mEtAddress.getText().toString()
                    ,mEtPassword.getText().toString()
                    ,mEtConfirmPassword.getText().toString()
                    ,image_url
            );
        });
    }

    private void upload_img() {
        try {
            if(checkPermissions())
            {} else
            {
                //final String[] PERMISSIONS_STORAGE = {Manifest.permission.CAMERA};
                //Asking request Permissions
                //ActivityCompat.requestPermissions(Settings.this, PERMISSIONS_STORAGE, 10);
                //Toast.makeText(Settings.this, "Camera Permission error", Toast.LENGTH_SHORT).show();
                checkPermissions();
            }
        } catch (Exception e) {
            //Toast.makeText(Settings.this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            //e.printStackTrace();
            checkPermissions();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 99:

                if(resultCode==RESULT_OK && data!=null)
                {


                    try
                    {
                        Uri selectedImageURI = data.getData();
                        String path = getPath(selectedImageURI);
                        //uploadImage.setImageURI(selectedImageURI);
                        Bitmap bm = BitmapFactory.decodeFile(path);
                        //BitmapFactory.Options options = new BitmapFactory.Options();
                        //options.inJustDecodeBounds = true;
                        //options.inSampleSize = 3;
                        //bm.decodeFile(path);
                        //BitmapFactory.decodeResource(getResources(), R.mipmap.hqimage, options);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                        byte[] b = baos.toByteArray();
                        image_url= ""+ Base64.encodeToString(b, Base64.DEFAULT);
                        //UploadImage();
                    }catch (Exception e)
                    {
                        //e.printStackTrace();
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case 0:

                Bitmap bitmap;

                if(data!=null)
                {
                    bitmap = (Bitmap) data.getExtras().get("data");

                    // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                    Uri tempUri = getImageUri(this, bitmap);
                    //uploadImage.setImageURI(tempUri);
                    String path = getPath(tempUri);
                    Bitmap bm = BitmapFactory.decodeFile(path);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 100  , baos); //bm is the bitmap object
                    byte[] b = baos.toByteArray();
                    image_url= ""+Base64.encodeToString(b, Base64.DEFAULT);
                    //UploadImage();

                }
                break;

        }
    }
    private Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        byte[] b = bytes.toByteArray();
        String encodedString = Base64.encodeToString(b, Base64.DEFAULT);
        image_url = ""+encodedString;
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    private String getPath(Uri contentUri) {
        String result = null;
        String[] projection = {MediaStore.Images.Media.DATA};

        try {
            Cursor cursor = getApplicationContext().getContentResolver().query(contentUri, projection, null,null,null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(projection[0]);
            result = cursor.getString(columnIndex);
            cursor.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return result;
    }
    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[0]), 100);
            return false;
        }
        else
        {
            PackageManager pm = this.getPackageManager();
            int hasPerm  = pm.checkPermission(Manifest.permission.CAMERA, this.getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                AlertDialog.Builder builder;
                AlertDialog dialog;
                builder = new AlertDialog.Builder(this);

                @SuppressLint("InflateParams")
                View mview = getLayoutInflater().inflate(R.layout.dialog_upload_image, null);
                builder.setView(mview);
                dialog = builder.create();
                Window window = dialog.getWindow();
                if (window != null) {
                    window.setGravity(Gravity.CENTER);
                }
                dialog.show();
                //Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);
                CairoRegularTextView take_photo = mview.findViewById(R.id.take_photo);
                CairoRegularTextView photo_album = mview.findViewById(R.id.photo_album);
                CairoBoldButton cancel = mview.findViewById(R.id.cancel);
                take_photo.setOnClickListener(view -> {
                    dialog.dismiss();
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 0);
                });
                photo_album.setOnClickListener(view -> {
                    dialog.dismiss();
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 99);
                });

                cancel.setOnClickListener(view -> dialog.dismiss());
            }
        }
        return true;
    }


}
