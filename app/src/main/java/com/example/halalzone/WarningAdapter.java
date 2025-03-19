package com.example.halalzone;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WarningAdapter extends RecyclerView.Adapter<WarningAdapter.WarningViewHolder> {
    private List<Warning> warningsList;

    public WarningAdapter(List<Warning> warningsList) {
        this.warningsList = warningsList;
    }

    @NonNull
    @Override
    public WarningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_warning, parent, false);
        return new WarningViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WarningViewHolder holder, int position) {
        Warning warning = warningsList.get(position);
        holder.txtBusinessEmail.setText("Business Email: " + warning.getBusinessEmail());
        holder.txtNote.setText("Note: " + warning.getNote());
        holder.txtDate.setText("Date: " + warning.getDate());
    }

    @Override
    public int getItemCount() {
        return warningsList.size();
    }

    static class WarningViewHolder extends RecyclerView.ViewHolder {
        TextView txtBusinessEmail, txtNote, txtDate;

        public WarningViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBusinessEmail = itemView.findViewById(R.id.txtBusinessEmail);
            txtNote = itemView.findViewById(R.id.txtNote);
            txtDate = itemView.findViewById(R.id.txtDate);
        }
    }
}
