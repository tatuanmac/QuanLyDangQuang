package dev.tatuan.qldh2.UploadNewItem;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import dev.tatuan.qldh2.R;

public class ThemAnh extends AppCompatActivity {
    private Button btnChoose, btnUpload;
    private ImageView imageView;
    private EditText edt_name, edt_type, edt_price, edt_energy, edt_glass, edt_madein;
    private final int PICK_IMAGE_REQUEST = 1;
    private ProgressBar mProgressBar;

    Uri mImageUri;

    String id, name, type, price, glass, energy, madein;


    String HangDongHo;

    //Firebase
    StorageReference mStorage;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_anh);

        //lay ten hang dong ho
        Bundle bundle = getIntent().getExtras();
        HangDongHo = bundle.getString("tenhang");

        mStorage = FirebaseStorage.getInstance().getReference("ImageUpload");
        mDatabase = FirebaseDatabase.getInstance().getReference("Watch").child(HangDongHo);


        //Initialize Views
        btnChoose = (Button) findViewById(R.id.btnChoose);
        btnUpload = (Button) findViewById(R.id.btnUpload);

        //
        imageView = (ImageView) findViewById(R.id.imgView);

        //
        edt_name = findViewById(R.id.edt_name);
        edt_type = findViewById(R.id.edt_type);
        edt_price = findViewById(R.id.edt_price);
        edt_glass = findViewById(R.id.edt_glass);
        edt_energy = findViewById(R.id.edt_energy);
        edt_madein = findViewById(R.id.edt_madein);

        //
        mProgressBar = findViewById(R.id.progressBar);

        //
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = mDatabase.push().getKey();
                name = edt_name.getText().toString().trim();
                type = edt_type.getText().toString().trim();
                price = edt_price.getText().toString().trim();
                glass = edt_glass.getText().toString().trim();
                energy = edt_energy.getText().toString().trim();
                madein = edt_madein.getText().toString().trim();

                uploadImage();

                finish();
            }
        });

    }

    private String getExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadImage() {

        if (mImageUri != null) {
            StorageReference storageReference = mStorage.child(System.currentTimeMillis() + "." + getExtension(mImageUri));

            storageReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(0);
                        }
                    }, 500);

                    Toast.makeText(ThemAnh.this, "Upload Succesfully!", Toast.LENGTH_SHORT).show();

                    ObjectUpload objectUpload = new ObjectUpload(id, taskSnapshot.getDownloadUrl().toString(), name, price, type, glass, madein, energy);
                    mDatabase.child(id).setValue(objectUpload);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {


                    Toast.makeText(ThemAnh.this, e.getMessage() + "", Toast.LENGTH_SHORT).show();


                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {


                    double a = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    mProgressBar.setProgress((int) a);


                }
            });


        } else
            Toast.makeText(this, "No File Selected!", Toast.LENGTH_SHORT).show();
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture Item"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            //link image i post
            mImageUri = data.getData();

            //load image on imageView
            Picasso.with(getApplicationContext()).load(mImageUri).into(imageView);
         }

    }
}
