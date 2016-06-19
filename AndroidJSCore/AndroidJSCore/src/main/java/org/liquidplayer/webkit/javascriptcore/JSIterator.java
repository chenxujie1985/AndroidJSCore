//
// JSIterator.java
// AndroidJSCore project
//
// https://github.com/ericwlange/AndroidJSCore/
//
// Created by Eric Lange
//
/*
 Copyright (c) 2014 Eric Lange. All rights reserved.

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

import java.util.Iterator;

/**
 * A JavaScript iterator interface shadow object
 * @since 3.0
 * @param <T>
 */
public class JSIterator<T> implements Iterator<T> {
    /**
     * Represents the object returned by 'next'
     * @since 3.0
     */
    public class Next {
        protected Next(JSObject next) {
            this.next = next;
        }
        protected final JSObject next;

        /**
         * Tests if there are any more elements in the array
         * @return true if more elements to iterate, false otherwise
         */
        public boolean done() {
            return next.property("done").toBoolean();
        }

        /**
         * Returns the JSValue of the iterated element
         * @return the value returned from next()
         */
        public JSValue value() {
            return next.property("value");
        }

        /**
         * Gets wrapped iterator object
         * @return the wrapped iterator object
         */
        public JSObject getJSObject() {
            return next;
        }
    }

    /**
     * Wraps a JavaScript iterator in a Java iterator
     * @param iterator
     */
    public JSIterator(JSObject iterator) {
        this.iterator = iterator;
        next = _jsnext();
    }
    private final JSObject iterator;
    private Next next = null;

    private Next _jsnext() {
        return new Next(iterator.property("next").toFunction().call(iterator).toObject());
    }

    /**
     * The 'next' JavaScript iterator object
     * @return
     */
    public Next jsnext() {
        Next ret = next;
        next = _jsnext();
        return ret;
    }

    /**
     * Gets the wrapped iterator object
     * @return
     */
    public JSObject getJSObject() {
        return iterator;
    }

    /**
     * @see Iterator#next()
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public T next() {
        return (T) jsnext().value();
    }

    /**
     * @see Iterator#hasNext()
     * @return
     */
    @Override
    public boolean hasNext() {
        return !next.done();
    }

    /**
     * @see Iterator#remove()
     * @throws UnsupportedOperationException always
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}