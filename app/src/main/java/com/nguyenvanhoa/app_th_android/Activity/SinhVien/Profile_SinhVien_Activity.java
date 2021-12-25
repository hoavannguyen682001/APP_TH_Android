package com.nguyenvanhoa.app_th_android.Activity.SinhVien;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nguyenvanhoa.app_th_android.R;
import com.nguyenvanhoa.app_th_android.databinding.ActivityProfileSinhvienBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Profile_SinhVien_Activity extends AppCompatActivity {
    SimpleDateFormat simpleDateFormat;
    Calendar calendar;

    private ActivityProfileSinhvienBinding binding;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private Uri imageUri = null;
    private int check = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityProfileSinhvienBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        checkEnableEdt();

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        binding.edtNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DATE);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Profile_SinhVien_Activity.this,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(i,i1,i2);
                        binding.edtNgaySinh.setText(simpleDateFormat.format(calendar.getTimeInMillis()));
                    }
                }, nam, thang , ngay);
                datePickerDialog.show();
            }
        });

        loadUserInfo();
        binding.ivPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageAttachMenu();
            }
        });

        binding.tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (check){
                    case 1:{
                        binding.tvUpdate.setText("Cập nhật thông tin");
                        binding.edtLop.setEnabled(true);
                        binding.edtGioiTinh.setEnabled(true);
                        binding.edtNgaySinh.setEnabled(true);
                        binding.edtNganh.setEnabled(true);
                        binding.edtKhoa.setEnabled(true);
                        binding.ivPerson.setEnabled(true);
                        check = 2;
                    }
                    break;
                    case 2:{
                        validateData();
                        progressDialog.setMessage("Đang tải lại dữ liệu");
                        progressDialog.show();
                        binding.tvUpdate.setText("Chỉnh sử thông tin");
                        check = 1;
                    }
                    break;
                    default:{

                    }break;
                }
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void checkEnableEdt(){
        binding.edtEmail.setEnabled(false);
        binding.edtLop.setEnabled(false);
        binding.edtGioiTinh.setEnabled(false);
        binding.edtNgaySinh.setEnabled(false);
        binding.edtNganh.setEnabled(false);
        binding.edtKhoa.setEnabled(false);
        binding.ivPerson.setEnabled(false);
    }

    private void validateData() {
        if(imageUri == null){
            updateUserInfo("");
        }else{
            updateImage();
        }
    }

    private void updateImage() {
        progressDialog.setMessage("Updating profile image");
        progressDialog.show();
        //image path and name, use uid to replace previous
        String filePathAndName = "ProfileImages/"+firebaseAuth.getUid();
        //storage reference
        StorageReference reference = FirebaseStorage.getInstance().getReference(filePathAndName);
        reference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isSuccessful());
                        String uploadedImageUrl = ""+uriTask.getResult();
                        updateUserInfo(uploadedImageUrl);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText( Profile_SinhVien_Activity.this,"Failed to upload image due to "+e.getMessage (), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void showImageAttachMenu() {
        //init/setup popup menu
        PopupMenu popupMenu = new PopupMenu( this, binding.ivPerson);
        popupMenu.getMenu ().add(Menu.NONE, 0,  0, "Camera");
        popupMenu.getMenu ().add(Menu.NONE, 1,  1, "Gallery");
        popupMenu.show();
        //handle menu item clicks
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick (MenuItem item) {
                int which = item.getItemId();
                if(which == 0){
                    pickCamera();
                }else if(which == 1){
                    pickGallery();
                }
                return false;
            }
        });
    }

    private void pickGallery() {
        //intent to pick image from gallery
        Intent intent = new Intent(Intent. ACTION_PICK);
        intent.setType("image/*");
        galleryActivityResultLauncher.launch(intent);
    }

    private void pickCamera() {
        //intent to pick image from camera
        ContentValues values = new ContentValues ();
        values.put (MediaStore.Images.Media. TITLE, "New Pick"); //image title
        values.put (MediaStore.Images.Media.DESCRIPTION, "Sample Image Description");
        imageUri = getContentResolver().insert (MediaStore.Images. Media.EXTERNAL_CONTENT_URI, values);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        cameraActivityResultLauncher.launch(intent);
    }

    private ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            //used to handle result of camera intent
                            //get uri of image
                            if (result.getResultCode() == Activity.RESULT_OK){
                                Intent data = result.getData(); //no need here as in camera case we already have image in imageUri variable
                                binding.ivPerson.setImageURI(imageUri);
                            }else {
                                Toast.makeText(Profile_SinhVien_Activity.this, "Cancel", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
    );

    private ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //used to handle result of camera intent
                    //get uri of image
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        imageUri = data.getData();
                        binding.ivPerson.setImageURI(imageUri);
                    }else {
                        Toast.makeText(Profile_SinhVien_Activity.this, "Cancle", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );


    private void loadUserInfo() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(firebaseAuth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String email = "" + snapshot.child("email").getValue();
                        String name = "" + snapshot.child("name").getValue();
                        String profileImage = "" + snapshot.child("profileImage").getValue();
                        String timestamp = "" + snapshot.child("timestamp").getValue();
                        String uid = "" + snapshot.child("uid").getValue();
                        String userType = "" + snapshot.child("userType").getValue();
                        String classUser = "" + snapshot.child("lopSH").getValue();
                        String dob = "" + snapshot.child("dob").getValue();
                        String gender = "" + snapshot.child("gender").getValue();
                        String nganh = "" + snapshot.child("nganh").getValue();
                        String khoa = "" + snapshot.child("khoa").getValue();

                        binding.tvName.setText(name);
                        binding.edtEmail.setText(email);

                        if(classUser !=""){
                            binding.edtLop.setText(classUser);
                        }
                        if(gender !=""){
                            binding.edtGioiTinh.setText(gender);
                        }
                        if(dob !=""){
                            binding.edtNgaySinh.setText(dob);
                        }
                        if(nganh !=""){
                            binding.edtNganh.setText(nganh);
                        }
                        if(khoa !=""){
                            binding.edtKhoa.setText(khoa);
                        }

                        Glide.with(Profile_SinhVien_Activity.this)
                                .load(profileImage)
                                .placeholder(R.drawable.ic_person)
                                .into(binding.ivPerson);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        progressDialog.dismiss();
    }

    private void updateUserInfo(String imageUrl){
        progressDialog.setMessage("Đang cập nhật thông tin cá nhân...");
        progressDialog.show();

        String classUser = binding.edtLop.getText().toString();
        String dob =  binding.edtNgaySinh.getText().toString();
        String gender =  binding.edtGioiTinh.getText().toString();
        String nganh = binding.edtNganh.getText().toString();
        String khoa = binding.edtKhoa.getText().toString();

        HashMap<String ,Object> hashMap = new HashMap<>();
        hashMap.put("lopSH", classUser);
        hashMap.put("gender", gender);
        hashMap.put("dob", dob);
        hashMap.put("nganh", nganh);
        hashMap.put("khoa", khoa);

        if(imageUri != null){
            hashMap.put("profileImage",""+imageUrl);
        }

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(firebaseAuth.getUid())
                .updateChildren(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(Profile_SinhVien_Activity.this, "Cập nhật thông tin thành công...", Toast.LENGTH_SHORT).show();
                        checkEnableEdt();
                        loadUserInfo();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Profile_SinhVien_Activity.this, "Cập nhật thông tin không thành công...", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}