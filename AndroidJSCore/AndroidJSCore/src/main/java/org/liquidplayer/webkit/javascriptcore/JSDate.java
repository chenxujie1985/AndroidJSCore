//
// JSDate.java
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

import java.util.Date;

/**
 * Convenience class for managing JavaScript date objects 
 * @since 1.0
 */
public class JSDate extends JSObject {
	/**
	 * Creates a new date object with the current date and time
	 * @param ctx  The JSContext in which to create the date object
	 * @since 1.0
	 * @throws JSException
	 */
	public JSDate(JSContext ctx) throws JSException {
		context = ctx;
		JNIReturnObject jni = this.makeDate(context.ctxRef(), new long[0]);
		if (jni.exception!=0) {
			context.throwJSException(new JSException(new JSValue(jni.exception, context)));
			jni.reference = make(context.ctxRef(), 0L);
		}
		valueRef = jni.reference;
	}
	/**
	 * Creates a new date object, initialized with a Java timestamp
	 * @param ctx  The JSContext in which to create the date object
	 * @param date  The Date with which to initialize the object
	 * @since 1.0
	 * @throws JSException
	 */
	public JSDate(JSContext ctx, Date date) throws JSException {
		context = ctx;
		JSValue time = new JSValue(context, date.getTime());
		long [] args = { time.valueRef() };
		JNIReturnObject jni = this.makeDate(context.ctxRef(), args);
		if (jni.exception!=0) {
			context.throwJSException(new JSException(new JSValue(jni.exception, context)));
			jni.reference = make(context.ctxRef(), 0L);
		}
		valueRef = jni.reference;
	}
}
