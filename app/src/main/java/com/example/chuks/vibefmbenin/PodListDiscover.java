package com.example.chuks.vibefmbenin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chuks.vibefmbenin.Model.PodList;
import com.example.chuks.vibefmbenin.ViewHolder.PodListViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PodListDiscover extends Fragment {



//    FirebaseDatabase mDatabase;
//    DatabaseReference podcastList;
//
//    ProgressBar mProgressBar;
//
//    RecyclerView recyclerView;
//    RecyclerView.LayoutManager layoutManager;
//
//    String podID="";

    FirebaseRecyclerAdapter<PodList, PodListViewHolder> adapter;

    View view;

    public PodListDiscover() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pod_list_discover, container, false);
//
//        mDatabase = FirebaseDatabase.getInstance();
//        podcastList = mDatabase.getReference("Audio");
//
//        mProgressBar = (ProgressBar) view.findViewById(R.id.loading_indicator2);
//
//        recyclerView = (RecyclerView) view.findViewById(R.id.pod_list_recycler);
//        recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(this.getActivity());
//        recyclerView.setLayoutManager(layoutManager);

        /*
            Obtain the bundle from the Discover Class
         */

//        Bundle bundle = getArguments();
//        if(bundle != null){
//
//            podID = bundle.getString("podID");
//
//            if (!podID.isEmpty() &&  podID != null)
//
//                loadPodList();
//        }

        return view;
    }

//    private void loadPodList() {
//        adapter = new FirebaseRecyclerAdapter<PodList, PodListViewHolder>(PodList.class, R.layout.podcast_list,
//                PodListViewHolder.class, podcastList.orderByChild("podID").equalTo(podID))
//                //like select * from podcast where podID ==
//        {
//            @Override
//
//            protected void populateViewHolder(final PodListViewHolder viewHolder, PodList model, int position) {
//
//                viewHolder.podTitle.setText(model.getPodtitle());
//                viewHolder.podOap.setText(model.getPodoap());
//                viewHolder.podTime.setText(model.getPodtime());
//                viewHolder.podDate.setText(model.getPoddate());
//                Picasso.with(getActivity().getBaseContext()).load(model.getPodimage()).into(viewHolder.podImage);
//                mProgressBar.setVisibility(View.GONE);
//
//                final PodList local = model;
//                viewHolder.setItemClickListener(new ItemClickListener() {
//                    @Override
//                    public void onClick(View view, int position, boolean isLongClick) {
//
//                        if(view == viewHolder.podImage) {
//                            Toast.makeText(getActivity(), "" + local.getPodID(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//            }
//        };
//
//        recyclerView.setAdapter(adapter);
//    }

}
