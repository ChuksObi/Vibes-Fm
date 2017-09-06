package com.example.chuks.vibefmbenin;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class AboutUsFragment extends Fragment {

    //Initailizing Settings
    Settings settings = new Settings();
    //getting text from setting class
    private final String web = settings.getWebsiteUrl();
    private final String email = settings.getEmailAddress();
    private final String call = settings.getPhoneNo();
    // initializing textView
    private TextView mWebButton;
    private TextView mEmailButton;
    private TextView mCallButton;


    public AboutUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_aboutus, container, false);

        mWebButton = (TextView) v.findViewById(R.id.button_web);
        mEmailButton = (TextView) v.findViewById(R.id.button_mail);
        mCallButton = (TextView) v.findViewById(R.id.button_call);

        mWebButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(settings.getWebsiteUrl())); //URL
                startActivity(browserIntent);
            }
        });

        mEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:")); //only email apps should handle this

                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hi VibesFM");
                try {
                    startActivity(Intent.createChooser(emailIntent, "Send email..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(), "There are no email clients installed.", Toast.LENGTH_LONG).show();

                }
            }
        });

        mCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + call));
                    startActivity(phoneIntent);
                } catch (Exception e) {
                    //To catch for tablets
                    Toast.makeText(getContext(), "No dialer apps installed.", Toast.LENGTH_LONG).show();
                }
            }
        });


        return v;
    }

}
