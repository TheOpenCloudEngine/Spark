/**
 * $RCSfile: ,v $
 * $Revision: $
 * $Date: $
 * 
 * Copyright (C) 2004-2011 Jive Software. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jivesoftware.spark.plugin.flashing;

import java.awt.Window;

import org.jivesoftware.spark.NativeHandler;
import org.jivesoftware.spark.SparkManager;

public class FlashingHandler implements NativeHandler {
	private FlashWindow flasher;

	public FlashingHandler() {
		flasher = new FlashWindow();
	}

	@Override
	public void flashWindow(Window window) {
		FlashingPreference preference = (FlashingPreference) SparkManager.getPreferenceManager().getPreference(FlashingPreference.NAMESPACE);
		if (preference.getPreferences().isFlashingEnabled()) {
			if (preference.getPreferences().getFlashingType().equals(FlashingPreferences.TYPE_CONTINOUS)) {
				flasher.startFlashing(window);
			}
			else if (preference.getPreferences().getFlashingType().equals(FlashingPreferences.TYPE_TEMPORARY)) {
				flasher.flash(window, 1500, 5);
			}
		}
	}

	@Override
	public void flashWindowStopWhenFocused(Window window) {
		flasher.stopFlashing(window);
	}

	@Override
	public boolean handleNotification() {
		return true;
	}

	@Override
	public void stopFlashing(Window window) {
		flasher.stopFlashing( window );
	}

}
