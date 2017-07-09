package com.visitorentrybook.activities.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.visitorentrybook.R;
import com.visitorentrybook.activities.checkIn.CheckInActivity;
import com.visitorentrybook.app.Constants;
import com.visitorentrybook.app.VisitorEntryBook;
import com.visitorentrybook.utils.CameraPermission;
import com.visitorentrybook.utils.Utils;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CameraActivity extends AppCompatActivity implements CameraView {


    @Inject
    CameraPresenter presenter;
    private static final int REQUEST_CAMERA = 2;
    @BindView(R.id.camera)
    ImageView iv_camera;

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_camera);
        ((VisitorEntryBook) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);
        presenter.setView(this);


        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = CameraPermission.checkPermission(CameraActivity.this);
                if (result) {
                    cameraIntent();
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("CAMERA", "Destroy");
    }


    private void cameraIntent() {
        try {
            Intent intent_image_capture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent_image_capture, REQUEST_CAMERA);
        } catch (Exception e) {
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);

        } catch (Exception e) {
        }
    }

    private void onCaptureImageResult(Intent data) {
        try {
            Bitmap bm = (Bitmap) data.getExtras().get("data");
            Constants.visitordata = new HashMap<>();
            Constants.visitordata.put("visitor_img", Utils.BitMapToString(bm));
            startActivity(new Intent(CameraActivity.this, CheckInActivity.class));
        } catch (Exception e) {
        }
    }
}

