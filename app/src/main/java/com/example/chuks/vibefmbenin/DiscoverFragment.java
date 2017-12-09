package com.example.chuks.vibefmbenin;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

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
public class DiscoverFragment extends Fragment {


    FirebaseDatabase mDatabase;
    DatabaseReference podcast;

    ProgressBar mProgressBar;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Podcast, MenuViewHolder> adapter;

    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_discover, container, false);

        mDatabase = FirebaseDatabase.getInstance();
        podcast = mDatabase.getReference("Podcast");

        mProgressBar = (ProgressBar) v.findViewById(R.id.loading_indicator);




        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_View);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);




        loadPod();

        return v;
    }

    private void loadPod() {

         adapter = new FirebaseRecyclerAdapter<Podcast, MenuViewHolder>(Podcast.class,
                 R.layout.podcast_row, MenuViewHolder.class, podcast) {
            @Override
            protected void populateViewHolder(final MenuViewHolder viewHolder, Podcast model, int position) {



                viewHolder.textPod.setText(model.getText());
                viewHolder.descriptionPod.setText(model.getDescription());
                viewHolder.oapPod.setText(model.getOap());
                viewHolder.episodePod.setText(model.getEpisode());
                Picasso.with(getActivity().getBaseContext()).load(model.getImage()).into(viewHolder.imageView);
                final Podcast clickItem  = model;
                mProgressBar.setVisibility(View.GONE);


                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        /*getting the activity and sending it to the PodListDiscover Class*/
//                       PodListDiscover podListDiscover = new PodListDiscover();
//                       FragmentManager manager = getFragmentManager();
//                        manager.beginTransaction().replace(R.id.frament_layout, podListDiscover, podListDiscover.getTag()).commit();
//                        /*Obtain the key, because podID is the key, so we get the key of this item*/

                        Toast.makeText(getActivity(), ""+clickItem.getText(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getActivity(), PodCastDisplay.class);
                        intent.putExtra("podID", adapter.getRef(position).getKey());
                        startActivity(intent);

//                        Bundle bundle = new Bundle();
//                        bundle.putString("podID",adapter.getRef(position).getKey());
//                        podListDiscover.setArguments(bundle);



                        
                    }
                });


            }
        };
        recyclerView.setAdapter(adapter);
    }



}
