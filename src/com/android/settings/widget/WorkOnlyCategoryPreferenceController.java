/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.widget;

import android.content.Context;

import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;

import java.util.List;

public class WorkOnlyCategoryPreferenceController extends AbstractPreferenceController
        implements PreferenceControllerMixin {

    private final String mKey;
    private final List<AbstractPreferenceController> mChildren;

    public WorkOnlyCategoryPreferenceController(Context context,
            String key, List<AbstractPreferenceController> childrenControllers) {
        super(context);
        mKey = key;
        mChildren = childrenControllers;
    }

    @Override
    public boolean isAvailable() {
        if (mChildren == null || mChildren.isEmpty()) {
            return true;
        }
        // Category is available if any child is available
        for (AbstractPreferenceController controller : mChildren) {
            if (controller.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getPreferenceKey() {
        return mKey;
    }
}
