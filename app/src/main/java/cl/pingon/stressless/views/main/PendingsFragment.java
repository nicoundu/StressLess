package cl.pingon.stressless.views.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cl.pingon.stressless.R;
import cl.pingon.stressless.adapters.PendingClickListener;
import cl.pingon.stressless.adapters.PendingsAdapter;
import cl.pingon.stressless.models.Pending;
import cl.pingon.stressless.views.details.DetailsActivity;

public class PendingsFragment extends Fragment implements PendingClickListener {

    private PendingsAdapter adapter;

    public static final String PENDING_ID = "cl.pingon.stressless.views.main.KEY.PENDING_ID";

    public PendingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.pendingRv);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PendingsAdapter(this);
        recyclerView.setAdapter(adapter);

    }

    public void updateList(Pending pending) {
        adapter.update(pending);
        Log.e("TAG", pending.getName());
    }

    @Override
    public void clickedID(long id) {
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra(PENDING_ID, id);
        startActivity(intent);

    }
}
