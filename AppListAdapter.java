package uz.isoft.myapplock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uz.isoft.myapplock.R;
import uz.isoft.myapplock.model.AppInfo;

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.ViewHolder> {
    List<AppInfo> appList;
    Context context;

    public AppListAdapter(List<AppInfo> appList) {
        this.appList = appList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null)
            context = parent.getContext();
        View view = LayoutInflater.from(context).
                inflate(R.layout.app_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.appName.setText(appList.get(position).getAppName());
        holder.appLogo.setImageDrawable(appList.get(position).getAppLogo());
        if (appList.get(position).isAppStatus()) {
            holder.appStatus.setImageResource(R.drawable.ic_lock);
        } else {
            holder.appStatus.setImageResource(R.drawable.ic_lock_open);
        }

        holder.appStatus.setOnClickListener(view -> {
            if (appList.get(position).isAppStatus()) {
                appList.get(position).setAppStatus(false);
                holder.appStatus.setImageResource(R.drawable.ic_lock_open);
            } else {
                appList.get(position).setAppStatus(true);
                holder.appStatus.setImageResource(R.drawable.ic_lock);
            }
        });


    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView appLogo, appStatus;
        TextView appName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            appLogo = itemView.findViewById(R.id.app_logo);
            appStatus = itemView.findViewById(R.id.app_status);
            appName = itemView.findViewById(R.id.app_name);
        }
    }
}
