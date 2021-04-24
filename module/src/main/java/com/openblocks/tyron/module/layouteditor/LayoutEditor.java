package com.openblocks.tyron.module.layouteditor;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.openblocks.moduleinterface.OpenBlocksModule;
import com.openblocks.moduleinterface.callbacks.Logger;
import com.openblocks.moduleinterface.callbacks.SaveCallback;
import com.openblocks.moduleinterface.models.OpenBlocksProjectMetadata;
import com.openblocks.moduleinterface.models.config.OpenBlocksConfig;
import com.openblocks.moduleinterface.projectfiles.OpenBlocksCode;
import com.openblocks.moduleinterface.projectfiles.OpenBlocksLayout;
import com.tyron.layouteditor.adapters.WidgetsAdapter;
import com.tyron.layouteditor.editor.EditorView;
import com.tyron.layouteditor.models.Widget;
import com.tyron.layouteditor.util.AndroidUtilities;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class LayoutEditor implements OpenBlocksModule.ProjectLayoutGUI {

    WeakReference<Context> context;
    Logger l;

    private final ArrayList<Widget> widgets = new ArrayList<>();

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
        EditorView editorView = new EditorView(context);
        layout.addView(editorView);

        CoordinatorLayout rootLayout = new CoordinatorLayout(context);

        FrameLayout bottom_layout = new FrameLayout(context);
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT,
                AndroidUtilities.dp(300));
        BottomSheetBehavior<View> behavior = new BottomSheetBehavior<>();
        behavior.setHideable(false);
        behavior.setPeekHeight(AndroidUtilities.dp(55));
        params.setBehavior(behavior);
        rootLayout.addView(bottom_layout, params);

        populate();
        RecyclerView recyclerview_widgets = new RecyclerView(context);
        recyclerview_widgets.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerview_widgets.setAdapter(new WidgetsAdapter(widgets, behavior));

        FrameLayout editor_container = new FrameLayout(context);
        editor_container.addView(editorView);
        editor_container.setPaddingRelative(0,0,0, AndroidUtilities.dp(55));
        rootLayout.addView(editor_container);

        layout.addView(rootLayout);
    }

    private void populate() {
        widgets.add(new Widget(Widget.LINEAR_LAYOUT));
        widgets.add(new Widget(Widget.RELATIVE_LAYOUT));
        widgets.add(new Widget(Widget.FRAME_LAYOUT));
        // widgets.add(new Widget(Widget.SCROLLVIEW));

        widgets.add(new Widget(Widget.CARDVIEW));
        widgets.add(new Widget(Widget.CONSTRAINT_LAYOUT));

        widgets.add(new Widget(Widget.BUTTON));
        widgets.add(new Widget(Widget.EDITTEXT));
        widgets.add(new Widget(Widget.PROGRESSBAR));
        widgets.add(new Widget(Widget.TEXTVIEW));
        widgets.add(new Widget(Widget.IMAGEVIEW));
    }

    @Override
    public OpenBlocksLayout initializeNewLayout() {
        return null;
    }
}
