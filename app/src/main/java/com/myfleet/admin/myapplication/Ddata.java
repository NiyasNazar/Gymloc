package com.myfleet.admin.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Ddata extends AppCompatActivity {
    private FirebaseFirestore db;
    private FirestoreRecyclerAdapter<FriendsResponse, FriendsHolder> adapter;
    LinearLayoutManager linearLayoutManager;

    RecyclerView friendList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddata);
        ButterKnife.bind(this);
        friendList=(RecyclerView)findViewById(R.id.recy);
        //linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        friendList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        db = FirebaseFirestore.getInstance();
        getFriendList();

    }

    private void getFriendList() {

        db.collection("GYM")
                .whereEqualTo("gymloc", "Kaloor")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAGs", document.getId() + " => " + document.getData());
                                FriendsResponse city = document.toObject(FriendsResponse.class);
                                Log.i("datazx",city.getGymname());
                                //List<FriendsResponse>datalist
                            }
                        } else {
                            Log.d("TAGs", "Error getting documents: ", task.getException());
                        }
                    }
                });





        Query query = db.collection("GYM").whereEqualTo("gymloc","Kaloor");

        FirestoreRecyclerOptions<FriendsResponse> response = new FirestoreRecyclerOptions.Builder<FriendsResponse>()
                .setQuery(query, FriendsResponse.class)
                .build();
        Log.i("datap",""+response.toString());
        adapter = new FirestoreRecyclerAdapter<FriendsResponse, FriendsHolder>(response) {
            @Override
            protected void onBindViewHolder(@NonNull FriendsHolder holder, int position, @NonNull FriendsResponse model) {

           holder.textName.setText(""+model.getGymname());
             holder.textCompany.setText(""+model.getGymloc());
                Log.i("datap",""+model.getGymloc());
                holder.textName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent is=new Intent(getApplicationContext(),Gym_detailActivity.class);
                        is.putExtra("gymname", model.getGymname());


                        startActivity(is);
                    }
                });

            }

            @NonNull
            @Override
            public FriendsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
                return new FriendsHolder(view);
            }
        };




        adapter.notifyDataSetChanged();
        friendList.setAdapter(adapter);
    }

    public class FriendsHolder extends RecyclerView.ViewHolder {
     //   @BindView(R.id.name1)
        TextView textName;

      //  @BindView(R.id.name2)
        TextView textCompany;

        public FriendsHolder(View itemView) {
            super(itemView);
            textName=(TextView)itemView.findViewById(R.id.name1);
            textCompany=(TextView)itemView.findViewById(R.id.name2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
