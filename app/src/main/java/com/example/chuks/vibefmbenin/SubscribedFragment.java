package com.example.chuks.vibefmbenin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chuks.vibefmbenin.Interface.ItemClickListener;
import com.example.chuks.vibefmbenin.Model.Podcast;
import com.example.chuks.vibefmbenin.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubscribedFragment extends Fragment {

    FirebaseDatabase mDatabase;
    DatabaseReference subscribedPodcast;


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Podcast, MenuViewHolder> adapter;
    String podID;
    Bundle bundle;


    public SubscribedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subscribed, container, false);

        mDatabase = FirebaseDatabase.getInstance();
        subscribedPodcast = mDatabase.getReference("Podcast");

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_subscribed);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        loadPod();


        bundle = getArguments();
        if (bundle != null){
            podID = getArguments().getString("podID");

        }

        return view;
    }


    private void loadPod() {

        adapter = new FirebaseRecyclerAdapter<Podcast, MenuViewHolder>(Podcast.class,
                R.layout.podcast_row, MenuViewHolder.class, subscribedPodcast) {
            @Override
            protected void populateViewHolder(final MenuViewHolder viewHolder, Podcast model, int position) {



                viewHolder.textPod.setText(model.getText());
                viewHolder.descriptionPod.setText(model.getDescription());
                viewHolder.oapPod.setText(model.getOap());
                viewHolder.episodePod.setText(model.getEpisode());
                Picasso.with(getActivity().getBaseContext()).load(model.getImage()).into(viewHolder.imageView);
                final Podcast clickItem  = model;


                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        /*getting the activity and sending it to the PodListDiscover Class*/
//                       PodListDiscover podListDiscover = new PodListDiscover();
//                       FragmentManager manager = getFragmentManager();
//                        manager.beginTransaction().replace(R.id.frament_layout, podListDiscover, podListDiscover.getTag()).commit();
//                        /*Obtain the key, because podID is the key, so we get the key of this item*/


//                        PlayAndDownload playandDownload = new PlayAndDownload();
//                        FragmentManager manager = getFragmentManager();
//                        manager.beginTransaction().replace(R.id.frament_layout, playandDownload).commit();
//
//                        bundle.putString("podID", podID);
//                        playandDownload.setArguments(bundle);
//
//                        Toast.makeText(getActivity(), ""+clickItem.getText(), Toast.LENGTH_SHORT).show();


//                        Intent intent = new Intent(getActivity(), PodCastDisplay.class);
//                        intent.putExtra("podID", adapter.getRef(position).getKey());
//                        startActivity(intent);




                    }
                });


            }
        };
        recyclerView.setAdapter(adapter);
    }

}
