/*
 * Original Guava code is copyright (C) 2015 The Guava Authors.
 * Modifications from Guava are copyright (C) 2015 DiffPlug.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.common.util.concurrent;

import java.io.IOException;
import java.util.concurrent.Semaphore;

import com.google.common.testing.AbstractPackageSanityTests;
import com.google.common.util.concurrent.RateLimiter.SleepingStopwatch;

/**
 * Basic sanity tests for the entire package.
 *
 * @author Ben Yu
 */

public class PackageSanityTests extends AbstractPackageSanityTests {
	private static final SleepingStopwatch NO_OP_STOPWATCH = new SleepingStopwatch() {
		@Override
		long readMicros() {
			return 0;
		}

		@Override
		void sleepMicrosUninterruptibly(long micros) {}
	};

	public PackageSanityTests() {
		setDefault(RateLimiter.class, RateLimiter.create(1.0));
		setDefault(SleepingStopwatch.class, NO_OP_STOPWATCH);
		setDefault(Class.class, IOException.class);
		setDefault(long.class, 0L);
		setDefault(Semaphore.class, new Semaphore(1));
	}
}
