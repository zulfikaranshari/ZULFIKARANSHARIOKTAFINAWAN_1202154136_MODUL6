package com.example.zulfikaranshari.zulfikaransharioktafinawan_1202154136_modul6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PostDetail extends AppCompatActivity {
    private TextView mMail;
    private TextView mTitle;
    private TextView mCaption;
    private ImageView imageView;
    private EditText mComment;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        mMail = (TextView) findViewById(R.id.userMail);
        imageView = (ImageView) findViewById(R.id.imageDetail);
        mComment = (EditText) findViewById(R.id.commentInput);
        mTitle = (EditText) findViewById(R.id.postTitle);
        mCaption = (EditText) findViewById(R.id.postCaption);

        Intent x  = getIntent();
        String email = x.getStringExtra("email");
        String url = x.getStringExtra("uri");
        String title = x.getStringExtra("title");
        String caption = x.getStringExtra("caption");

        Picasso.get().load(url).into(imageView);
        mMail.setText(email);
        mTitle.setText(title);
        mCaption.setText(caption);
    }
}
