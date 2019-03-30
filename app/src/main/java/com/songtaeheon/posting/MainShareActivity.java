package com.songtaeheon.posting;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.songtaeheon.posting.DataModel.PostingInfo;
import com.songtaeheon.posting.PostingProcess.GalleryFragment;
import com.songtaeheon.posting.Utils.Permissions;

public class MainShareActivity extends AppCompatActivity {

    private static final String TAG = "TAGShareActivity";
    private static final int VERIFY_PERMISSIONS_REQUEST = 10001;

    public PostingInfo postingInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        //permission check(camera, read and write external storage)
        if(checkPermissionArray(Permissions.POST_PERMISSIONS)){
            setupFragment();
        }else{
            verifyPermissions(Permissions.POST_PERMISSIONS);
        }
    }

    private void setupFragment(){

        Log.d(TAG, "setupFragment");
        GalleryFragment galleryFragment = new GalleryFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.relLayout1, galleryFragment).commit();

    }


    public void verifyPermissions(String[] permissions){
        Log.d(TAG, "verifyPermissions : verify permissions");
        ActivityCompat.requestPermissions(MainShareActivity.this, permissions, VERIFY_PERMISSIONS_REQUEST);

    }
    //check an array of permissions
    public boolean checkPermissionArray(String[] permissions){
        Log.d(TAG, "checkPermissionArray : checking permissions array");

        for(int i  =0 ; i<permissions.length; i++){
            String check = permissions[i];
            if(!checkPermission(check)){
                return false;
            }
        }
        return true;
    }

    //check single permission
    public boolean checkPermission(String permission){
        Log.d(TAG, "checkPermissionArray : "+ permission);

        int permissionRequest = ActivityCompat.checkSelfPermission(MainShareActivity.this, permission);

        if(permissionRequest != PackageManager.PERMISSION_GRANTED){
            Log.d(TAG, "checkPermissionArray : \n permission is not granted for"+ permission);
            return false;
        }
        Log.d(TAG, "checkPermissionArray : \n permission is granted for"+ permission);

        return true;


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case VERIFY_PERMISSIONS_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    setupFragment();

                } else {

                    Toast.makeText(getApplicationContext(), "no permission. cannot post", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
