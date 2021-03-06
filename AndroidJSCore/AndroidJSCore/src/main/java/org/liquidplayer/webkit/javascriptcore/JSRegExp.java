//
// JSRegExp.java
// AndroidJSCore project
//
// https://github.com/ericwlange/AndroidJSCore/
//
// Created by Eric Lange
//
/*
 Copyright (c) 2014-2016 Eric Lange. All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:

 - Redistributions of source code must retain the above copyright notice, this
 list of conditions and the following disclaimer.

 - Redistributions in binary form must reproduce the above copyright notice,
 this list of conditions and the following disclaimer in the documentation
 and/or other materials provided with the distribution.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.liquidplayer.webkit.javascriptcore;

/**
 *  A convenience class for managing JavaScript regular expressions.  See
 *  <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Regular_Expressions">
 *  https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Regular_Expressions</a>
 *  for details on JavaScript regexp.
 * @since 1.0
 */
public class JSRegExp extends JSObject {
	/**
	 * Creates a new JavaScript regular expression
	 * @param ctx  The context in which to create the regular expression
	 * @param pattern  The REGEXP pattern
	 * @param flags  The REGEXP flags
	 * @since 1.0
	 * @throws JSException
	 */
	public JSRegExp(JSContext ctx, String pattern, String flags) throws JSException {
		context = ctx;
		long [] args = { 
				new JSValue(context,pattern).valueRef(),
				new JSValue(context,flags).valueRef(),
		};
		JNIReturnObject jni = makeRegExp(context.ctxRef(), args);
		if (jni.exception!=0) {
			context.throwJSException(new JSException(new JSValue(jni.exception, context)));
			jni.reference = make(context.ctxRef(), 0L);
		}
		valueRef = jni.reference;
	}
	/**
	 * Creates a new JavaScript regular expression
	 * @param ctx  The context in which to create the regular expression
	 * @param pattern  The REGEXP pattern
	 * @since 1.0
	 * @throws JSException
	 */
	public JSRegExp(JSContext ctx, String pattern) throws JSException {
		context = ctx;
		long [] args = { 
				new JSValue(context,pattern).valueRef()
		};
		JNIReturnObject jni = makeRegExp(context.ctxRef(), args);
		if (jni.exception!=0) {
			context.throwJSException(new JSException(new JSValue(jni.exception, context)));
			jni.reference = make(context.ctxRef(), 0L);
		}
		valueRef = jni.reference;
	}
}
