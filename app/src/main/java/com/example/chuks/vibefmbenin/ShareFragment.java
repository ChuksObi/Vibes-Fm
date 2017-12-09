package com.example.chuks.vibefmbenin;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends Fragment {

    Button mTwitter;
//    Button mFacebook;
    Button mInstagram;
    View v;


    public ShareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_share, container, false);

        mTwitter = (Button) v.findViewById(R.id.twitter_button);
        mInstagram = (Button) v.findViewById(R.id.instagram_button);
//       // mFacebook = (Button) v.findViewById(R.id.facebook_button);
//
//
//
//
        mTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                try {
                    // get the Twitter app if possible
                    getActivity().getPackageManager().getPackageInfo("com.twitter.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=855302779"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    // no Twitter app, revert to browser
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/VibesFM973"));
                }
                startActivity(intent);
            }
        });

        mInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/_u/vibesfm973");
                Intent instagram = new Intent(Intent.ACTION_VIEW, uri);

                instagram.setPackage("com.instagram.android");

                try {
                    startActivity(instagram);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/vibesfm973/")));
                }
            }
        });

//        mFacebook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                callbackManager = CallbackManager.Factory.create();
//            }
//
//
//        });
        return v;

    }

//    private void setImageShare(View view) {
//        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.header);
//        SharePhoto photo = new SharePhoto.Builder()
//                .setBitmap(image)
//                .setCaption("Download Vibes FM app and view our podcasts")
//                .build();
//        SharePhotoContent content = new SharePhotoContent.Builder()
//                .addPhoto(photo)
//                .build();
//
//        mFacebook = (Button) view.findViewById(R.id.facebook_button);
//        mFacebook.setShareContent(content);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Call callbackManager.onActivityResult to pass login result to the LoginManager via callbackManager.
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//    }
//
//    private FacebookCallback<Sharer.Result> callback = new FacebookCallback<Sharer.Result>() {
//        @Override
//        public void onSuccess(Sharer.Result result) {
////            Toast.makeText(getActivity(), "Successfully posted", Toast.LENGTH_SHORT).show();
//            // Write some code to do some operations when you shared content successfully.
//        }
//
//        @Override
//        public void onCancel() {
//            // Write some code to do some operations when you cancel sharing content.
//        }
//
//        @Override
//        public void onError(FacebookException error) {
//
//            // Write some code to do some operations when some error occurs while sharing content.
//        }
//    };



        }