package com.example.fuelstation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelstation.moduls.benz;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TypesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    DatabaseReference reference;
    ArrayList<benz> benzlist;
    FirebaseAuth auth;
    FirebaseUser user;
    Adapter adapter;
    String userid;
    Context mcontext;
    String child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_types);
        recyclerView =findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        reference=FirebaseDatabase.getInstance().getReference("Users");
        benzlist =new ArrayList<>();
        user=FirebaseAuth.getInstance().getCurrentUser();
        Intent intent =getIntent();
        String type =intent.getStringExtra("name");
        Toast.makeText(TypesActivity.this, type, Toast.LENGTH_SHORT).show();


        switch (type){
            case "بنزين":
                child ="benz";
                break;
            case "شحم":
                child ="shm";
                break;
            case "زيت":
                child="oil";
                break;
            case "سائل" :
                child ="liq";
                break;
            default:
                    child ="benz";
        }

        Query query =reference.child(user.getUid()).child("package").child(child);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    benz benz=new benz();
                    benz.setBenztype(snapshot.child("type").getValue().toString());
                    benz.setBenzvalue(snapshot.child("value").getValue().toString());
                    benzlist.add(benz);
                }
                adapter =new Adapter(mcontext,benzlist);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                adapter.setItemListener(new Adapter.OnClickItemListener() {

                    @Override
                    public void OnItemClick(int position) {
                        benz ben =benzlist.get(position);
                        int i =Integer.parseInt(ben.getBenzvalue());
                        showCustomDialog(ben.getBenztype(),i);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showCustomDialog(final String Type, final int i) {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        WindowManager.LayoutParams ip = new WindowManager.LayoutParams();
        ip.width = WindowManager.LayoutParams.MATCH_PARENT;
        ip.height = WindowManager.LayoutParams.WRAP_CONTENT;
        final Button add = dialog.findViewById(R.id.add_gas);
        final Button conc= dialog.findViewById(R.id.con_gas);
        final Button back= dialog.findViewById(R.id.back_btn);
        final EditText from=dialog.findViewById(R.id.from);
        final EditText To=dialog.findViewById(R.id.to);
        final EditText guest_number =dialog.findViewById(R.id.guest_number);
        final Toast toast = new Toast(getApplicationContext());
        final TextView texttype=dialog.findViewById(R.id.types);
        texttype.setText(Type);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference=FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("package").child(child);
                Query query1 =reference.orderByChild("type").equalTo(Type);
                query1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                            int sum=i+Integer.parseInt(guest_number.getText().toString());
                            snapshot.getRef().child("value").setValue(sum);
                            Toast.makeText(getApplicationContext(), "Add value: "+guest_number.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                toast.cancel();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(ip);
    }

}
