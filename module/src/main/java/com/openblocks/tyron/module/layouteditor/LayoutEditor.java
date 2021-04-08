package com.openblocks.tyron.module.layouteditor;

import android.content.Context;
import android.view.ViewGroup;

import com.openblocks.moduleinterface.OpenBlocksModule;
import com.openblocks.moduleinterface.callbacks.Logger;
import com.openblocks.moduleinterface.callbacks.SaveCallback;
import com.openblocks.moduleinterface.models.OpenBlocksProjectMetadata;
import com.openblocks.moduleinterface.models.config.OpenBlocksConfig;
import com.openblocks.moduleinterface.projectfiles.OpenBlocksCode;
import com.openblocks.moduleinterface.projectfiles.OpenBlocksLayout;

import java.lang.ref.WeakReference;

public class LayoutEditor implements OpenBlocksModule.ProjectLayoutGUI {

    WeakReference<Context> context;
    Logger l;

    @Override
    public Type getType() {
        return Type.PROJECT_LAYOUT_GUI;
    }

    @Override
    public void initialize(Context context, Logger logger) {
        this.context = new WeakReference<>(context);
        this.l = logger;
    }

    @Override
    public OpenBlocksConfig setupConfig() {
        // TODO: 4/8/21 Implement configuration
        return new OpenBlocksConfig();
    }

    @Override
    public void applyConfig(OpenBlocksConfig config) {
        // TODO: 4/8/21 Implement configuration
    }

    @Override
    public void show(Context context, ViewGroup layout, OpenBlocksCode code_data, OpenBlocksLayout layout_data, OpenBlocksProjectMetadata metadata, SaveCallback<OpenBlocksLayout> saveCallback) {

    }

    @Override
    public OpenBlocksLayout initializeNewLayout() {
        return null;
    }
}
