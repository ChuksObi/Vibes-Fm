package com.example.chuks.vibefmbenin;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayAndDownload extends Fragment {


    FirebaseDatabase mDatabase;
    DatabaseReference podcastPlayandDowload,PodcastPlayandDowloadRecycler;

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

    public PlayAndDownload() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play_and_download, container, false);

        toolbar = (Toolbar) view.findViewById(R.id.play_download_toolbar);

        barLayout = (AppBarLayout) view.findViewById(R.id.play_download_app_bar_layout);
        barLayout.setExpanded(true);


        mDatabase = FirebaseDatabase.getInstance();
        podcastPlayandDowload = mDatabase.getReference("Podcast");
        PodcastPlayandDowloadRecycler = mDatabase.getReference("Audio");

        displayTitle = (TextView) view.findViewById(R.id.play_download_display_title);
        displayDescription = (TextView) view.findViewById(R.id.play_download_display_description);
        displayPrimary = (ImageView) view.findViewById(R.id.play_download_display_primary_image);

        mProgressBar = (ProgressBar) view.findViewById(R.id.loading_indicator3);

        mCollaspsingToolBarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.play_download_collapsing);
        mCollaspsingToolBarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        mCollaspsingToolBarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        recyclerView = (RecyclerView) view.findViewById(R.id.display_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        Bundle bundle = getArguments();
        if (bundle != null){
            podID = bundle.getString("podID");

            Log.i("podID", podID);

            if(!podID.isEmpty() && podID == null){
                getPodDisplay(podID);
                PodcastPlayandDowloadRecycler();
            }
        }
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void getPodDisplay(String podID) {
        podcastPlayandDowload.child(podID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Podcast modelChild = dataSnapshot.getValue(Podcast.class);

                displayTitle.setText(modelChild.getText());
                displayDescription.setText(modelChild.getDescription());
                mCollaspsingToolBarLayout.setTitle(modelChild.getText());
                Picasso.with(getActivity().getBaseContext()).load(modelChild.getImage()).into(displayPrimary);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void PodcastPlayandDowloadRecycler() {
        adapter = new FirebaseRecyclerAdapter<PodList, PodListViewHolder>(
                PodList.class,
                R.layout.display_card,
                PodListViewHolder.class,
                PodcastPlayandDowloadRecycler.orderByChild("podID").equalTo(podID)
                //like select * from podcast where podID ==
        ) {
            @Override
            protected void populateViewHolder(PodListViewHolder viewHolder, PodList model, int position) {

                viewHolder.podTitle.setText(model.getPodtitle());
                viewHolder.podOap.setText(model.getPodoap());
                viewHolder.podTime.setText(model.getPodtime());
                viewHolder.podDate.setText(model.getPoddate());
                Picasso.with(getActivity().getBaseContext()).load(model.getPodimage()).into(viewHolder.podImage);
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
//                             TODO: handle exception
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
//
                        }

                        Toast.makeText(getActivity(), ""+local.getPodlink(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };

    }


}
