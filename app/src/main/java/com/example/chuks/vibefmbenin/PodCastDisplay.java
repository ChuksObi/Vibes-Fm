package com.example.chuks.vibefmbenin;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chuks.vibefmbenin.Interface.ItemClickListener;
import com.example.chuks.vibefmbenin.Model.PodList;
import com.example.chuks.vibefmbenin.Model.Podcast;
import com.example.chuks.vibefmbenin.ViewHolder.PodListViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class PodCastDisplay extends AppCompatActivity {

    FirebaseDatabase mDatabase;
    DatabaseReference podcastDisplay,podcastDisplayRecycler;

    Toolbar toolbar;
    AppBarLayout barLayout;

    TextView displayTitle, displayDescription;
    ImageView displayPrimary;

    CollapsingToolbarLayout mCollaspsingToolBarLayout;
    FloatingActionButton mFloatButton;
    ProgressBar mProgressBar;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<PodList, PodListViewHolder> adapter;


    String podID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pod_cast_display);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        barLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        barLayout.setExpanded(true);


        mDatabase = FirebaseDatabase.getInstance();
        podcastDisplay = mDatabase.getReference("Podcast");
        podcastDisplayRecycler = mDatabase.getReference("Audio");

        displayTitle = (TextView) findViewById(R.id.display_title);
        displayDescription = (TextView) findViewById(R.id.display_description);
        displayPrimary = (ImageView) findViewById(R.id.display_primary_image);

        mProgressBar = (ProgressBar) findViewById(R.id.loading_indicator3);

        mCollaspsingToolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        mCollaspsingToolBarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        mCollaspsingToolBarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        mFloatButton = (FloatingActionButton) findViewById(R.id.subscribe);

        recyclerView = (RecyclerView) findViewById(R.id.display_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(PodCastDisplay.this);
        recyclerView.setLayoutManager(layoutManager);

//        mFloatButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(PodCastDisplay.this, MainActivity.class);
//                intent.putExtra("podID",podID);
//                overridePendingTransition(0, 0);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                finish();
//                startActivity(intent);
//            }
//        });


        if (getIntent() != null ) {
            podID = getIntent().getStringExtra("podID");
            Log.i("podID", podID );
            if (!podID.isEmpty() && podID != null) {

               getPodDisplay(podID);

                getPodDisplayRecycler();


            }

        }
    }



    private void getPodDisplay(String podID) {
        podcastDisplay.child(podID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Podcast modelChild = dataSnapshot.getValue(Podcast.class);

                displayTitle.setText(modelChild.getText());
                displayDescription.setText(modelChild.getDescription());
                mCollaspsingToolBarLayout.setTitle(modelChild.getText());
                Picasso.with(getBaseContext()).load(modelChild.getImage()).into(displayPrimary);

                do{
                    Toast.makeText(PodCastDisplay.this, "Subscribed to "+modelChild.getText(), Toast.LENGTH_SHORT).show();
                }while (mFloatButton.isSelected());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getPodDisplayRecycler() {
        adapter = new FirebaseRecyclerAdapter<PodList, PodListViewHolder>(
                PodList.class,
                R.layout.display_card,
                PodListViewHolder.class,
                podcastDisplayRecycler.orderByChild("podID").equalTo(podID)
                //like select * from podcast where podID ==
        ) {
            @Override
            protected void populateViewHolder(PodListViewHolder viewHolder, PodList model, int position) {

                viewHolder.podTitle.setText(model.getPodtitle());
                viewHolder.podOap.setText(model.getPodoap());
                viewHolder.podTime.setText(model.getPodtime());
                viewHolder.podDate.setText(model.getPoddate());
                Picasso.with(getBaseContext()).load(model.getPodimage()).into(viewHolder.podImage);
                mProgressBar.setVisibility(View.GONE);

                final PodList local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                            // This Code is for streaming
                        try {
                            MediaPlayer player = new MediaPlayer();
                            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            player.setDataSource(local.getPodlink());
                            player.prepare();
                            player.start();
                        } catch (Exception e) {
                            // TODO: handle exception
//                        catch (IllegalArgumentException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        } catch (SecurityException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        } catch (IllegalStateException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();

                        }

                        Toast.makeText(PodCastDisplay.this, ""+local.getPodlink(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };
        recyclerView.setAdapter(adapter);
    }

}
