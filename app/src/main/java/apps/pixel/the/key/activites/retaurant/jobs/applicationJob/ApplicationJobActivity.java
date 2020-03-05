package apps.pixel.the.key.activites.retaurant.jobs.applicationJob;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import apps.pixel.the.key.R;
import apps.pixel.the.key.dialog.restuarant.DialogCity;
import apps.pixel.the.key.dialog.restuarant.DialogEducational;
import apps.pixel.the.key.dialog.restuarant.DialogExperience;
import apps.pixel.the.key.utilities.CairoBoldButton;
import apps.pixel.the.key.utilities.CairoBoldTextView;
import apps.pixel.the.key.utilities.GetFilePathFromDevice;
import apps.pixel.the.key.utilities.Constant;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class ApplicationJobActivity extends AppCompatActivity {

    public static final int LOAD_RESULTS = 1;
    public static CairoBoldTextView txtCity;
    public static CairoBoldTextView txtExperience;
    public static CairoBoldTextView txtEducation;
    String filePath = "";
    int fileSize;
    File myFile;
    String displayName = "";
    private String strImage = "";
    private CairoBoldButton mBtnUpload;
    private DialogEducational dialogEducational;
    private DialogExperience dialogExperience;
    private DialogCity dialogCity;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            filePath = GetFilePathFromDevice.getPath(this, data.getData());
            Log.d("FILE PATH", "---" + filePath);

            Uri uri = data.getData();
            String uriString = uri.toString();
            myFile = new File(filePath);
            Log.e("File", "---" + myFile);
            Log.e("File SIZE", "---" + Integer.parseInt(String.valueOf(myFile.length() / 1024)));
            fileSize = Integer.parseInt(String.valueOf(myFile.length() / 1024));
            if (fileSize > 10240) {
                Toast.makeText(this, "Please select file less than 10MB.",
                        Toast.LENGTH_SHORT).show();
            } else {

                if (uriString.startsWith("content://")) {
                    Cursor cursor = null;
                    try {
                        cursor = this.getContentResolver().query(uri, null, null, null, null);
                        if (cursor != null && cursor.moveToFirst()) {
                            displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                        }
                    } finally {
                        cursor.close();
                    }
                } else if (uriString.startsWith("file://")) {
                    displayName = myFile.getName();
                }
                Log.d("display name ", ">>" + displayName);
                strImage = Constant.convertFileToByteArray(myFile);

                Log.d("Base64", "---" + strImage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void writePermission() {
        chooseFile();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_job);


        initViews();
    }


    private void chooseFile() {
        try {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*");
            try {
                startActivityForResult(intent, LOAD_RESULTS);

            } catch (ActivityNotFoundException e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initViews() {

        mBtnUpload = findViewById(R.id.choose_file);
        mBtnUpload.setOnClickListener(v -> {
            ApplicationJobActivityPermissionsDispatcher.writePermissionWithPermissionCheck(this);
        });

        dialogCity = new DialogCity();
        dialogEducational = new DialogEducational();
        dialogExperience = new DialogExperience();

        txtCity = findViewById(R.id.txt_city);
        txtCity.setOnClickListener(v -> {
            if (!dialogCity.isAdded()) {
                dialogCity.show(getSupportFragmentManager(), "1");
            }
        });

        txtExperience = findViewById(R.id.txt_experience);
        txtExperience.setOnClickListener(v -> {
            if (!dialogExperience.isAdded()) {
                dialogExperience.show(getSupportFragmentManager(), "2");
            }
        });

        txtEducation = findViewById(R.id.txt_education);
        txtEducation.setOnClickListener(v -> {
            if (!dialogEducational.isAdded()) {
                dialogEducational.show(getSupportFragmentManager(), "3");
            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ApplicationJobActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
