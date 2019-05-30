package com.saritasa.clock_knock.features.tasks.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.base.presentation.BaseFragment;
import com.saritasa.clock_knock.features.main.presentation.NavigationListener;
import com.saritasa.clock_knock.features.tasks.di.TasksModule;
import com.saritasa.clock_knock.util.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Fragment for tasks module
 */
public class TasksFragment extends BaseFragment implements TasksView{

    NavigationListener mNavigationListener;

    @Inject
    TasksPresenter mTasksPresenter;
    @BindView(R.id.tvNoTasksMessage)
    TextView mTvNoTasksMessage;
    @BindView(R.id.rvTasks)
    RecyclerView mTasksRecyclerView;
    @BindView(R.id.fabTasks)
    FloatingActionButton mTasksFAB;
    private ItemAdapter<TasksAdapterItem> mItemAdapter;

    private TasksAdapterItem mChosenItem = null;
    private int mChosenPosition = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_tasks, container, false);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if(getActivity() != null){
            if(getActivity() instanceof NavigationListener){
                mNavigationListener = (NavigationListener) getActivity();
            }

            App.get(getActivity())
                    .getAppComponent()
                    .tasksComponentBuilder()
                    .tasksModule(new TasksModule())
                    .build()
                    .inject(this);

            mTasksPresenter.attachView(this);

            setUpRecyclerView();

            mTasksPresenter.onRequest();

            mTasksFAB.setOnClickListener(v -> showAddTaskAlertDialog());
        }

    }

    private void setUpRecyclerView(){
        mItemAdapter = new ItemAdapter<>();
        FastAdapter<TasksAdapterItem> adapter = FastAdapter.with(mItemAdapter);

        adapter.withOnClickListener((v, adapter1, item, position) -> {
            mNavigationListener.goToWorklog(item.getId(), item.getName(), Strings.SHOW_TASK_ACTION);
            return false;
        });

        adapter.withOnLongClickListener((v, adapter1, item, position) -> {
            mChosenItem = item;
            mChosenPosition = position;
            return false;
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mTasksRecyclerView.setLayoutManager(layoutManager);
        registerForContextMenu(mTasksRecyclerView);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mTasksRecyclerView.getContext(),
                                                                                layoutManager.getOrientation());
        mTasksRecyclerView.addItemDecoration(dividerItemDecoration);
        mTasksRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showTasksView(){
        mTasksRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoTasksMessageView(){
        mTvNoTasksMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTasksView(){
        mTasksRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideNoTasksMessageView(){
        mTvNoTasksMessage.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(final String aMessage){
        Toast.makeText(getActivity(), aMessage, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void updateTaskList(final List<TasksAdapterItem> aTasksDomains){
        mItemAdapter.add(aTasksDomains);
        mItemAdapter.getFastAdapter().notifyAdapterDataSetChanged();
        if(mItemAdapter.getAdapterItemCount() > 0){
            hideNoTasksMessageView();
            showTasksView();
        } else{
            hideTasksView();
            showNoTasksMessageView();
        }
    }

    @Override
    public void addTaskToList(@NonNull final TasksAdapterItem aTaskItem){
        mItemAdapter.add(aTaskItem);
        mItemAdapter.getFastAdapter().notifyAdapterDataSetChanged();

        if(mItemAdapter.getAdapterItemCount() == 1){
            hideNoTasksMessageView();
            showTasksView();
        }
    }

    @Override
    public void removeTaskFromList(int aChosenPosition){
        mItemAdapter.remove(aChosenPosition);
        mItemAdapter.getFastAdapter().notifyAdapterDataSetChanged();

        if(mItemAdapter.getAdapterItemCount() == 0){
            hideTasksView();
            showNoTasksMessageView();
        }
    }

    @Override
    public void editTaskInList(@NonNull final TasksAdapterItem aTaskItem, final int aPosition){
        mItemAdapter.set(aPosition, aTaskItem);
        mItemAdapter.getFastAdapter().notifyAdapterDataSetChanged();
    }

    @Override
    public void runTaskOnUiThread(final Runnable aRunnable){
        if(getActivity() != null){
            getActivity().runOnUiThread(aRunnable);
        }
    }

    private void showAddTaskAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_task, null);
        builder.setView(view);
        builder.setCancelable(true);
        builder.setTitle("Add new task");
        builder.setPositiveButton(
                "OK",
                (dialog, id) -> {
                    EditText titleEditText = view.findViewById(R.id.etTitle);
                    String title = titleEditText.getText().toString();
                    EditText summaryEditText = view.findViewById(R.id.etDescription);
                    String summary = summaryEditText.getText().toString();
                    Spinner statusSpinner = view.findViewById(R.id.spStatus);
                    String status = statusSpinner.getSelectedItem().toString();
                    Spinner prioritySpinner = view.findViewById(R.id.spPriority);
                    int priority = prioritySpinner.getSelectedItemPosition();

                    mTasksPresenter.onTaskAdd(title, summary, status, priority);
                    dialog.cancel();
                });

        builder.setNegativeButton(
                "Cancel",
                (dialog, id) -> dialog.cancel());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showEditTaskAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_task, null);

        if(mChosenItem != null){
            EditText titleEditText = view.findViewById(R.id.etTitle);
            titleEditText.setText(mChosenItem.getName());
            EditText summaryEditText = view.findViewById(R.id.etDescription);
            summaryEditText.setText(mChosenItem.getSummary());
            Spinner statusSpinner = view.findViewById(R.id.spStatus);
            ArrayList<String> statusArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.task_status)));
            statusSpinner.setSelection(statusArray.indexOf(mChosenItem.getStatus()));

            Spinner prioritySpinner = view.findViewById(R.id.spPriority);
            ArrayList<String> priorityArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.task_priority)));
            prioritySpinner.setSelection(priorityArray.indexOf(mChosenItem.getStatus()));
        }

        builder.setView(view);
        builder.setCancelable(true);
        builder.setTitle("Edit task");
        builder.setPositiveButton(
                "OK",
                (dialog, id) -> {
                    EditText titleEditText = view.findViewById(R.id.etTitle);
                    String title = titleEditText.getText().toString();
                    EditText summaryEditText = view.findViewById(R.id.etDescription);
                    String summary = summaryEditText.getText().toString();
                    Spinner statusSpinner = view.findViewById(R.id.spStatus);
                    String status = statusSpinner.getSelectedItem().toString();
                    Spinner prioritySpinner = view.findViewById(R.id.spPriority);
                    int priority = prioritySpinner.getSelectedItemPosition();

                    mTasksPresenter.onTaskEdit(mChosenPosition, mChosenItem.getId(), title, summary, status, priority);
                    mChosenItem = null;
                    dialog.cancel();
                });

        builder.setNegativeButton(
                "Cancel",
                (dialog, id) -> {
                    mChosenItem = null;
                    dialog.cancel();
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.item_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.edit:
                showEditTaskAlertDialog();
                break;
            case R.id.delete:
                mTasksPresenter.onTaskDelete(mChosenPosition, mChosenItem);
                break;

            default:
                mChosenItem = null;
                mChosenPosition = -1;
                return super.onContextItemSelected(item);
        }
        return true;
    }

    @Override
    public void onDetach(){
        super.onDetach();
        mNavigationListener = null;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mTasksPresenter.detachView(this);
    }
}
