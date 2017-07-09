package com.visitorentrybook.widgets;
/*
 * Created by Satish on 24-04-2017.
 */

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.visitorentrybook.R;
import com.visitorentrybook.activities.checkIn.CheckInActivity;
import com.visitorentrybook.activities.home.HomePresenter;
import com.visitorentrybook.model.CheckIn.CheckInVerifyVisitorRequest;

import java.util.HashMap;
import java.util.Map;

import static com.facebook.accountkit.AccountKit.getCurrentAccount;

public class CustomDialog extends DialogFragment {

    HomePresenter homePresenter;

    private String btn_text, title;
    private SurfaceHolder surfaceHolder;
    private int cameraId;
    private int rotation;
    private VbEditText mEditMobile;
    private String mobile;
    Intent intent;
    private static final int FRAMEWORK_REQUEST_CODE = 1;
    ProgressDialog progressDialog;
    private Activity homeActivity;
    private CheckInVerifyVisitorRequest checkInVerifyVisitorRequest;


    private int nextPermissionsRequestCode = 4000;
    private final Map<Integer, OnCompleteListener> permissionsListeners = new HashMap<>();

    private interface OnCompleteListener {
        void onComplete();
    }

    public CustomDialog() {

    }

    @SuppressLint("ValidFragment")
    public CustomDialog(String btn_text, String title, Activity homeActivity, HomePresenter homePresenter) {
        this.btn_text = btn_text;
        this.title = title;
        this.homeActivity = homeActivity;
        this.homePresenter = homePresenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.custom_dialog, container, false);
        intent = new Intent(homeActivity, CheckInActivity.class);
        checkInVerifyVisitorRequest = new CheckInVerifyVisitorRequest();
        final Button mDoneBtn = (Button) mView.findViewById(R.id.btn_done);
        VbTextView mtitle = (VbTextView) mView.findViewById(R.id.text_view_description);
        mEditMobile = (VbEditText) mView.findViewById(R.id.edit_text_mobile);
        mDoneBtn.setText(btn_text);
        mtitle.setText(title);
        mDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
                if (validateMobileNum()) {
                    checkInVerifyVisitorRequest.setvPhone(mobile);
                   homePresenter.onCheckinVerifyVisitor(mobile, checkInVerifyVisitorRequest);
                }
            }
        });
        return mView;
    }






    private boolean validateMobileNum() {

        mobile = mEditMobile.getText().toString();
        if (mobile.isEmpty() || mobile.length() != 10) {
            mEditMobile.setError("Enter Valid Mobile Number");
            return false;
        } else {
            mEditMobile.setError(null);
        }
        return true;
    }

    private void verifyVisitorNum(final LoginType loginType) {
        final Intent intent = new Intent(homeActivity, AccountKitActivity.class);
        final AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder
                = new AccountKitConfiguration.AccountKitConfigurationBuilder(
                loginType,
                AccountKitActivity.ResponseType.TOKEN);
        final AccountKitConfiguration configuration = configurationBuilder.build();
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configuration);
        OnCompleteListener completeListener = new OnCompleteListener() {
            @Override
            public void onComplete() {
                startActivityForResult(intent, FRAMEWORK_REQUEST_CODE);
            }
        };
        switch (loginType) {

            case PHONE:
                if (configuration.isReceiveSMSEnabled()) {
                    final OnCompleteListener receiveSMSCompleteListener = completeListener;
                    completeListener = new OnCompleteListener() {
                        @Override
                        public void onComplete() {
                            requestPermissions(
                                    Manifest.permission.RECEIVE_SMS,
                                    R.string.permissions_receive_sms_title,
                                    R.string.permissions_receive_sms_message,
                                    receiveSMSCompleteListener);
                        }
                    };
                }
                if (configuration.isReadPhoneStateEnabled()) {
                    final OnCompleteListener readPhoneStateCompleteListener = completeListener;
                    completeListener = new OnCompleteListener() {
                        @Override
                        public void onComplete() {
                            requestPermissions(
                                    Manifest.permission.READ_PHONE_STATE,
                                    R.string.permissions_read_phone_state_title,
                                    R.string.permissions_read_phone_state_message,
                                    readPhoneStateCompleteListener);
                        }
                    };
                }
                break;
        }
        completeListener.onComplete();
    }

    private void requestPermissions(
            final String permission,
            final int rationaleTitleResourceId,
            final int rationaleMessageResourceId,
            final OnCompleteListener listener) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            if (listener != null) {
                listener.onComplete();
            }
            return;
        }
        checkRequestPermissions(permission, rationaleTitleResourceId, rationaleMessageResourceId, listener);
    }

    @TargetApi(23)
    private void checkRequestPermissions(
            final String permission,
            final int rationaleTitleResourceId,
            final int rationaleMessageResourceId,
            final OnCompleteListener listener) {
        if (homeActivity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
            if (listener != null) {
                listener.onComplete();
            }
            return;
        }

        final int requestCode = nextPermissionsRequestCode++;
        permissionsListeners.put(requestCode, listener);

        if (shouldShowRequestPermissionRationale(permission)) {
            new AlertDialog.Builder(homeActivity)
                    .setTitle(rationaleTitleResourceId)
                    .setMessage(rationaleMessageResourceId)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(final DialogInterface dialog, final int which) {
                            requestPermissions(new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(final DialogInterface dialog, final int which) {
                            // ignore and clean up the listener
                            permissionsListeners.remove(requestCode);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            requestPermissions(new String[]{permission}, requestCode);
        }
    }

    @TargetApi(23)
    @SuppressWarnings("unused")
    @Override
    public void onRequestPermissionsResult(final int requestCode,
                                           final @NonNull String permissions[],
                                           final @NonNull int[] grantResults) {
        final OnCompleteListener permissionsListener = permissionsListeners.remove(requestCode);
        if (permissionsListener != null
                && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            permissionsListener.onComplete();
        }
    }


}