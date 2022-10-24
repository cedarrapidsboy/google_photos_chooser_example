package com.moosedrive.google_photos_chooser_example;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;

public class ChooserActivityResult  implements ActivityResultCallback<ActivityResult> {

    private final MainActivity parentActivity;

    public ChooserActivityResult(MainActivity parentActivity) {
        this.parentActivity = parentActivity;
    }

    @Override
    public void onActivityResult(ActivityResult result) {
        if (result == null) {
            parentActivity.runOnUiThread(() -> {
                parentActivity.clear();
                parentActivity.addString("ActivityResult is null. That's not good.");
            });
        } else if (result.getResultCode() == Activity.RESULT_OK) {
            Intent data = result.getData();
            if (data != null) {
                parentActivity.runOnUiThread(parentActivity::clear);
                if (data.getData() != null) {
                    parentActivity.runOnUiThread(() -> parentActivity.addString(data.getData().toString()));

                } else if (data.getClipData() != null) {
                    for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                        Uri uri = data.getClipData().getItemAt(i).getUri();
                        parentActivity.runOnUiThread(() -> parentActivity.addString(uri.toString()));
                    }
                }
            }
        } else {
            parentActivity.runOnUiThread(() -> {
                parentActivity.clear();
                parentActivity.addString(result.toString());
            });
        }
    }
}
