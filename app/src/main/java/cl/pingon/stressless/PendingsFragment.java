package cl.pingon.stressless;

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

import cl.pingon.stressless.adapters.PendingsAdapter;
import cl.pingon.stressless.models.Pending;

public class PendingsFragment extends Fragment implements PendingClickListener {

    private PendingsAdapter adapter;

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

        for (int i = 0; i < 20; i++) {
            Pending pending = new Pending();
            pending.setName(String.valueOf(i));
            pending.setDone(false);
            pending.save();
        }

        adapter = new PendingsAdapter(this);
        recyclerView.setAdapter(adapter);

    }

    public void updateList(Pending pending) {
        adapter.update(pending);
        Log.e("PENDING", pending.getName());
    }

    @Override
    public void clickedID(long id) {
        Toast.makeText(getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();
    }
}
