package com.example.sid.imageviewassignment_73;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import static android.content.Intent.ACTION_GET_CONTENT;
import static com.example.sid.imageviewassignment_73.R.id.image;
import static com.example.sid.imageviewassignment_73.R.id.img;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.img);
        btn = (Button) findViewById(R.id.Upload);

    }
    //Finding particualr app
    public void Upload(View v) {
        Intent upload = new Intent(Intent.ACTION_GET_CONTENT);
        upload.setType("image/*");
        startActivityForResult(upload, 100);
    }

    //Getting Image Path and assigning it to Image view
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Uri selectimage = data.getData();
            String[] filepath = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectimage, filepath, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filepath[0]);
            String path = cursor.getString(columnIndex);
            cursor.close();
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            img.setImageBitmap(BitmapFactory.decodeFile(path));
        }
    }
}