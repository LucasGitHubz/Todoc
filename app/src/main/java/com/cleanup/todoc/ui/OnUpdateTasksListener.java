package com.cleanup.todoc.ui;

import android.arch.lifecycle.LifecycleOwner;

public interface OnUpdateTasksListener extends LifecycleOwner {
    void updateTasks();
}
