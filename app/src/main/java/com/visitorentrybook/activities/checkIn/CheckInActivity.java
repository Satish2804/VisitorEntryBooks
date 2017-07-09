package com.visitorentrybook.activities.checkIn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.visitorentrybook.R;
import com.visitorentrybook.activities.home.HomeActivity;
import com.visitorentrybook.adapter.NothingSelectedSpinnerAdapter;
import com.visitorentrybook.app.Constants;
import com.visitorentrybook.app.VisitorEntryBook;
import com.visitorentrybook.utils.CameraPermission;
import com.visitorentrybook.utils.Utils;
import com.visitorentrybook.widgets.VbEditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.visitorentrybook.app.Constants.device_proof_img;
import static com.visitorentrybook.app.Constants.govt_proof_img;
import static com.visitorentrybook.app.Constants.visitorDataWhole;
import static com.visitorentrybook.app.Constants.visitor_img;
import static com.visitorentrybook.app.Constants.visitordata;

public class CheckInActivity extends AppCompatActivity implements CheckInView {

    private static final String TAG = "CheckInActivity";
    private static final int REQUEST_CAMERA = 2;

    private String pic;
    @Inject
    CheckInPresenter presenter;

    @BindView(R.id.btn_check_in)
    AppCompatButton button_check_in;

    @BindView(R.id.edit_text_firstname)
    VbEditText edit_text_firstname;

    @BindView(R.id.edit_text_lastname)
    VbEditText edit_text_lastname;

    @BindView(R.id.edit_text_mobile)
    VbEditText edit_text_mobile;

    @BindView(R.id.edit_text_company_name)
    VbEditText edit_text_company_name;

    @BindView(R.id.edit_text_whom_to_meet)
    VbEditText edit_text_whom_to_meet;

    @BindView(R.id.edit_text_purpose_of_meet)
    VbEditText edit_text_purpose_of_meet;

    @BindView(R.id.vbauto_govt_issued_proofs)
    Spinner vbauto_govt_issued_proofs;

    @BindView(R.id.switch_device_details)
    SwitchCompat switch_device_details;

    @BindView(R.id.ll_device_proofs)
    LinearLayout ll_device_proofs;

    @BindView(R.id.edit_text_auto_device_proofs)
    VbEditText edit_text_auto_device_proofs;

    @BindView((R.id.image_device))
    SimpleDraweeView simpleDraweeView_device;

    @BindView((R.id.image_proof))
    SimpleDraweeView simpleDraweeView_proof;

    @BindView((R.id.image_vpic))
    SimpleDraweeView simpleDraweeView_vpic;

    private ArrayAdapter<String> dataAdapter;

    private String firstname, lastname, companyname, whomtomeet, purposeofmeet, mobile, device_proof_id;


    @Override
    public void onBackPressed(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        ((VisitorEntryBook) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);
        presenter.setView(this);
        Constants.arrayList_govt_proofs = new ArrayList<>();

        Collections.addAll(Constants.arrayList_govt_proofs, Constants.govt_proofs);
        dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_govt_proofs,Constants.govt_proofs);
        vbauto_govt_issued_proofs.setAdapter(new NothingSelectedSpinnerAdapter(dataAdapter, R.layout.contact_spinner_row_nothing_selected, this));
        vbauto_govt_issued_proofs.setPrompt("Select Govt Proof");
        button_check_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.checkIn();
            }
        });
        switch_device_details.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ll_device_proofs.setVisibility(View.VISIBLE);

                } else {
                    ll_device_proofs.setVisibility(View.GONE);

                }
            }
        });
//        vbauto_govt_issued_proofs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                vbauto_govt_issued_proofs.setSelection(position + 1);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        simpleDraweeView_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = CameraPermission.checkPermission(CheckInActivity.this);
                if (result) {
                    pic="device";
                    cameraIntent();
                }
            }
        });
        simpleDraweeView_proof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = CameraPermission.checkPermission(CheckInActivity.this);
                if (result) {
                    pic="proof";
                    cameraIntent();
                }
            }
        });
        simpleDraweeView_vpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = CameraPermission.checkPermission(CheckInActivity.this);
                if (result) {
                    pic="visitor";
                    cameraIntent();
                }
            }
        });
    }


    public boolean validate() {
        boolean valid = true;

        firstname = edit_text_firstname.getText().toString();
        lastname = edit_text_lastname.getText().toString();
        companyname = edit_text_company_name.getText().toString();
        whomtomeet = edit_text_whom_to_meet.getText().toString();
        mobile = edit_text_mobile.getText().toString();
        purposeofmeet = edit_text_purpose_of_meet.getText().toString();
        device_proof_id = edit_text_auto_device_proofs.getText().toString();

        if(simpleDraweeView_vpic.getDrawable()== ContextCompat.getDrawable(this, R.drawable.visitor_black)){
            Toast.makeText(this, "pls add your pic",Toast.LENGTH_SHORT).show();
        }

        if(simpleDraweeView_proof.getDrawable()== ContextCompat.getDrawable(this, R.drawable.camera_black)){
            Toast.makeText(this, "pls add proof pic",Toast.LENGTH_SHORT).show();
        }

        if (firstname.isEmpty() || firstname.length() < 3) {
            edit_text_firstname.setError("Enter First Name");
            valid = false;
        } else {
            edit_text_firstname.setError(null);
        }

        if (lastname.isEmpty() || lastname.length() < 3) {
            edit_text_lastname.setError("Enter Last Name ");
            valid = false;
        } else {
            edit_text_lastname.setError(null);
        }


        if (companyname.isEmpty() || companyname.length() > 10) {
            edit_text_company_name.setError("enter a valid companyname");
            valid = false;
        } else {
            edit_text_company_name.setError(null);
        }

        if (mobile.isEmpty() || mobile.length() != 10) {
            edit_text_mobile.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            edit_text_mobile.setError(null);
        }

        if (whomtomeet.isEmpty() || whomtomeet.length() < 3) {
            edit_text_whom_to_meet.setError("Enter Vaild Name");
            valid = false;
        } else {
            edit_text_whom_to_meet.setError(null);
        }

        if (purposeofmeet.isEmpty() || purposeofmeet.length() < 3) {
            edit_text_purpose_of_meet.setError("Enter Valid purpose");
            valid = false;
        } else {
            edit_text_purpose_of_meet.setError(null);
        }
        if (switch_device_details.isActivated() && (device_proof_id.isEmpty() || device_proof_id.length() < 3)) {
            edit_text_auto_device_proofs.setError("Enter Valid deviceId");
            valid = false;
        } else {
            edit_text_auto_device_proofs.setError(null);
        }

        return valid;
    }


    @Override
    public void checkIn() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onCheckinFailed();
            return;
        }

        button_check_in.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(CheckInActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();


        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onCheckinSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    private void onCheckinSuccess() {
        if (visitordata == null) {
            visitordata = new HashMap<>();
        }
        visitordata.put("firstname", firstname);
        visitordata.put("lastname", lastname);
        visitordata.put("mobile", mobile);
        visitordata.put("companyname", companyname);
        visitordata.put("whomtomeet", whomtomeet);
        visitordata.put("purposeofmeet", purposeofmeet);
        visitordata.put("device_proof_id", device_proof_id);
        visitorDataWhole.put(mobile, visitordata);

        button_check_in.setEnabled(true);
        startActivity(new Intent(CheckInActivity.this, HomeActivity.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finish();
    }

    private void onCheckinFailed() {
        Toast.makeText(getBaseContext(), "Checkin failed", Toast.LENGTH_LONG).show();

        button_check_in.setEnabled(true);
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
            switch (pic) {
                case "visitor":
                    simpleDraweeView_vpic.setImageBitmap(bm);
                    Constants.visitordata.put("visitor_img", Utils.BitMapToString(bm));
                    break;
                case "proof":
                    simpleDraweeView_proof.setImageBitmap(bm);
                    Constants.visitordata.put("govt_proof_img", Utils.BitMapToString(bm));
                    break;
                default:
                    simpleDraweeView_device.setImageBitmap(bm);
                    Constants.visitordata.put("device_proof_img", Utils.BitMapToString(bm));
                    break;
            }


        } catch (Exception e) {
        }
    }
}
