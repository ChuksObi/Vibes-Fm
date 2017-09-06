package com.example.chuks.vibefmbenin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PodcastFragment extends Fragment {

//    private RecyclerView mPodCastList;

    public PodcastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_podcast, container, false);

//        mPodCastList = (RecyclerView) v.findViewById(R.id.podcastList);
//        mPodCastList.hasFixedSize();
//        mPodCastList.setLayoutManager(new LinearLayoutManager(getActivity()));


        return v;
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        FirebaseRecyclerAdapter<Pod, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Pod, BlogViewHolder>(
//
//                Pod.class,
//                R.layout.podcast_row,
//                BlogViewHolder.class,
//                mDatabase
//        ) {
//            @Override
//            protected void populateViewHolder(BlogViewHolder viewHolder, Pod model, int position) {
//
//                viewHolder.setTitle(model.getTitle());
//                viewHolder.setDescription(model.getDescription());
//            }
//        };
//
//        mPodCastList.setAdapter(firebaseRecyclerAdapter);
//    }
//
//
//    private static class BlogViewHolder extends RecyclerView.ViewHolder{
//
//        View mview;
//
//        public BlogViewHolder(View itemView) {
//            super(itemView);
//            mview = itemView;
//        }
//
//        public void setTitle(String title){
//
//            TextView post_title = (TextView) mview.findViewById(R.id.pod_title);
//            post_title.setText(title);
//        }
//
//        public void setDescription(String description){
//            TextView post_description = (TextView) mview.findViewById(R.id.pod_description);
//            post_description.setText(description);
//
//        }
//    }
}
