/*
 * Original Guava code is copyright (C) 2015 The Guava Authors.
 * Modifications from Guava are copyright (C) 2016 DiffPlug.
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
package com.diffplug.common.math;

import static com.diffplug.common.math.MathTesting.ALL_BIGINTEGER_CANDIDATES;
import static com.diffplug.common.math.MathTesting.FINITE_DOUBLE_CANDIDATES;
import static com.diffplug.common.math.MathTesting.POSITIVE_FINITE_DOUBLE_CANDIDATES;

import java.math.BigInteger;

import junit.framework.TestCase;
import sun.misc.FpUtils;

/**
 * Tests for {@link DoubleUtils}.
 * 
 * @author Louis Wasserman
 */
public class DoubleUtilsTest extends TestCase {
	@SuppressUnderAndroid // no FpUtils
	public void testNextDown() {
		for (double d : FINITE_DOUBLE_CANDIDATES) {
			assertEquals(FpUtils.nextDown(d), DoubleUtils.nextDown(d));
		}
	}

	@SuppressUnderAndroid // TODO(cpovirk): File bug for BigDecimal.doubleValue().
	public void testBigToDouble() {
		for (BigInteger b : ALL_BIGINTEGER_CANDIDATES) {
			if (b.doubleValue() != DoubleUtils.bigToDouble(b)) {
				failFormat(
						"Converting %s to double: expected doubleValue %s but got bigToDouble %s",
						b,
						b.doubleValue(),
						DoubleUtils.bigToDouble(b));
			}
		}
	}

	public void testEnsureNonNegative() {
		assertEquals(0.0, DoubleUtils.ensureNonNegative(0.0));
		for (double positiveValue : POSITIVE_FINITE_DOUBLE_CANDIDATES) {
			assertEquals(positiveValue, DoubleUtils.ensureNonNegative(positiveValue));
			assertEquals(0.0, DoubleUtils.ensureNonNegative(-positiveValue));
		}
		assertEquals(Double.POSITIVE_INFINITY, DoubleUtils.ensureNonNegative(Double.POSITIVE_INFINITY));
		assertEquals(0.0, DoubleUtils.ensureNonNegative(Double.NEGATIVE_INFINITY));
		try {
			DoubleUtils.ensureNonNegative(Double.NaN);
			fail("Expected IllegalArgumentException from ensureNonNegative(Double.NaN)");
		} catch (IllegalArgumentException expected) {}
	}

	private static void failFormat(String template, Object... args) {
		fail(String.format(template, args));
	}
}