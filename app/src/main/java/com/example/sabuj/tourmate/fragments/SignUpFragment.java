package com.example.sabuj.tourmate.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.Manifest;

import com.example.sabuj.tourmate.R;
import com.example.sabuj.tourmate.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;


public class SignUpFragment extends Fragment {
    EditText etFullName, etUserName, etPassword, etContactNo, etAddress;
    Button btnRegister;
    private DatabaseReference table_user;
    String userPhoto;
    ImageButton ivUserPhoto,ibCameraDialog,ibGalleryDialog;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    //private FirebaseDatabase database;
    private Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        initialization(view);
        ivUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        table_user = FirebaseDatabase.getInstance().getReference("User");
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(etUserName.getText().toString()).exists()) {
                            Toast.makeText(getActivity(), "Username Already Existed. Try another?", Toast.LENGTH_SHORT).show();
                        } else {
                            User user = new User(userPhoto,
                                    etFullName.getText().toString(),
                                    etUserName.getText().toString(),
                                    etPassword.getText().toString(),
                                    etContactNo.getText().toString(),
                                    etAddress.getText().toString());
                            table_user.child(etUserName.getText().toString()).setValue(user);
                            Toast.makeText(getActivity(), "Successfully Registered.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getActivity(), databaseError.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return view;
    }

    private void selectImage() {

            dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.dialog_camera_galary);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(dialog.getWindow().getAttributes());
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setAttributes(layoutParams);
            dialog.show();
             ibCameraDialog = dialog.findViewById(R.id.ibCameraDialog);
             ibGalleryDialog = dialog.findViewById(R.id.ibGalleryDialog);
            ibCameraDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    Context context = getActivity();
                    if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }
            });

            ibGalleryDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    Toast.makeText(getActivity(), "gry clicked", Toast.LENGTH_SHORT).show();
                }
            });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivUserPhoto.setImageBitmap(imageBitmap);
            userPhoto = encodeToBase64(imageBitmap, Bitmap.CompressFormat.JPEG, 100);
        }
    }
    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }
    private void initialization(View view) {
        ivUserPhoto = view.findViewById(R.id.ivUserPhoto);
        etFullName = view.findViewById(R.id.etFullName);
        etUserName = view.findViewById(R.id.etUserName);
        etPassword = view.findViewById(R.id.etPassword);
        etContactNo = view.findViewById(R.id.etEmmergencyContactNo);
        etAddress = view.findViewById(R.id.etAddress);
        btnRegister = view.findViewById(R.id.btnRegister);
    }
}
