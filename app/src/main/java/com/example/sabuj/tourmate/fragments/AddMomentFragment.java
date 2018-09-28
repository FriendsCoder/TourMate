package com.example.sabuj.tourmate.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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
import android.widget.Toast;

import com.example.sabuj.tourmate.R;
import com.example.sabuj.tourmate.models.Common;
import com.example.sabuj.tourmate.models.Moment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;

public class AddMomentFragment extends Fragment {
    private EditText etMomentDetails;
    private Button btnMomentSave;
    private ImageButton ibMomentImage, ibCameraDialog, ibGalleryDialog;
    String currentDate, currentTime, momentImage;
    private DatabaseReference refMoment;
    private DatabaseReference refUserMoment;
    private DatabaseReference refUserEventMoment;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat timeFormat;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_CAPTURE_GALLERY = 2;
    private Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_moment, container, false);
        initialization(view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refMoment = FirebaseDatabase.getInstance().getReference("Moments");
        String user = Common.currentUser.getUserName();
        String userMoment = Common.currentEventName;
        refUserMoment = refMoment.child(user);
        refUserEventMoment = refUserMoment.child(userMoment);
        dateFormat = new SimpleDateFormat("d MMM");
        timeFormat = new SimpleDateFormat("h:mm a");

        ibMomentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        btnMomentSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String momentDetails = etMomentDetails.getText().toString();
                refUserEventMoment.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        currentDate = dateFormat.format(Calendar.getInstance().getTime());
                        currentTime = timeFormat.format(Calendar.getInstance().getTime());

                        Moment moment = new Moment(currentDate, currentTime, momentDetails, momentImage);
                        refUserEventMoment.child(momentDetails).setValue(moment);
                        Toast.makeText(getActivity(), "Successfully saved Moments.", Toast.LENGTH_SHORT).show();
                        clearData();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
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
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(galleryIntent, REQUEST_IMAGE_CAPTURE_GALLERY);
                Toast.makeText(getActivity(), "gry clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clearData() {
        etMomentDetails.setText("");
        btnMomentSave.setText("Save Moment");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ibMomentImage.setImageBitmap(imageBitmap);
            momentImage = encodeToBase64(imageBitmap, Bitmap.CompressFormat.JPEG, 100);
        }
        else if (requestCode==REQUEST_IMAGE_CAPTURE_GALLERY && resultCode ==RESULT_OK){
            if (data!=null){
                Uri contentURI=data.getData();
                try{
                    Bitmap imageBitmap=MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),contentURI);
                    ibMomentImage.setImageBitmap(imageBitmap);
                    momentImage = encodeToBase64(imageBitmap, Bitmap.CompressFormat.JPEG, 100);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }

    private void initialization(View view) {
        etMomentDetails = view.findViewById(R.id.etMomentDetails);
        ibMomentImage = view.findViewById(R.id.ibMomentImage);
        btnMomentSave = view.findViewById(R.id.btnMomentSave);
    }
}
