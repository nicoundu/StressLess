package cl.pingon.stressless.views.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import cl.pingon.stressless.R;
import cl.pingon.stressless.data.Queries;
import cl.pingon.stressless.models.Pending;
import cl.pingon.stressless.views.main.PendingsFragment;

public class DetailsActivity extends AppCompatActivity {

    private long pending_id;
    private Pending pending;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        pending_id = getIntent().getLongExtra(PendingsFragment.PENDING_ID, 0);

        pending = new Queries().byId(pending_id);

        Log.e("PENDING_ID", pending.getName());

        getSupportActionBar().setTitle(pending.getName());

        editText = findViewById(R.id.descriptionEt);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String description = pending.getDescription();

        if (description != null) {
            editText.setText(description);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        String description;
        description = editText.getText().toString();
        pending.setDescription(description);
        pending.save();
    }


}
