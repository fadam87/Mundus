/*
 * Copyright (c) 2024. See AUTHORS file.
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

package com.mbrlabs.mundus.pluginapi;

import com.mbrlabs.mundus.pluginapi.ui.RootWidget;
import org.pf4j.ExtensionPoint;

public interface MenuExtension extends ExtensionPoint {

    /**
     * @return The item name of plugin in Plugins menu.
     */
    String getMenuName();

    /**
     * Here can setup root widget for plugin.
     */
    void setupDialogRootWidget(RootWidget rootWidget);
}
