/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/ActionMessages.java,v 1.1 2001/07/12 05:18:31 dwinterfeldt Exp $
 * $Revision: 1.1 $
 * $Date: 2001/07/12 05:18:31 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */


package org.apache.struts.action;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.HashMap;


/**
 * <p>A class that encapsulates messages.  Messages can be either global 
 * or they are specific to a particular bean property.</p>
 *
 * <p>Each individual message is described by an <code>ActionMessage</code>
 * object, which contains a message key (to be looked up in an appropriate
 * message resources database), and up to four placeholder arguments used for
 * parametric substitution in the resulting message.</p>
 *
 * <p><strong>IMPLEMENTATION NOTE</strong> - It is assumed that these objects
 * are created and manipulated only within the context of a single thread.
 * Therefore, no synchronization is required for access to internal
 * collections.</p>
 *
 * @author David Geary
 * @author Craig R. McClanahan
 * @author David Winterfeldt
 * @revision $Revision: 1.1 $ $Date: 2001/07/12 05:18:31 $
 */

public class ActionMessages implements Serializable {


    // ----------------------------------------------------- Manifest Constants


    /**
     * The "property name" marker to use for global messages, as opposed to
     * those related to a specific property.
     */
    public static final String GLOBAL_MESSAGE =
        "org.apache.struts.action.GLOBAL_MESSAGE";


    // ----------------------------------------------------- Instance Variables


    /**
     * The accumulated set of <code>ActionMessage</code> objects (represented
     * as an ArrayList) for each property, keyed by property name.
     */
    protected HashMap messages = new HashMap();


    // --------------------------------------------------------- Public Methods


    /**
     * Add a message to the set of messages for the specified property.
     *
     * @param property 	Property name (or ActionMessages.GLOBAL_MESSAGE)
     * @param message 	The message to be added
     */
    public void add(String property, ActionMessage message) {

        ArrayList list = (ArrayList) messages.get(property);
        if (list == null) {
            list = new ArrayList();
            messages.put(property, list);
        }
        list.add(message);

    }


    /**
     * Clear all messages recorded by this object.
     */
    public void clear() {

        messages.clear();

    }


    /**
     * Return <code>true</code> if there are no messages recorded
     * in this collection, or <code>false</code> otherwise.
     */
    public boolean empty() {

        return (messages.size() == 0);

    }


    /**
     * Return the set of all recorded messages, without distinction
     * by which property the messages are associated with.  If there are
     * no messages recorded, an empty enumeration is returned.
     */
    public Iterator get() {

        if (messages.size() == 0)
            return (Collections.EMPTY_LIST.iterator());
        ArrayList results = new ArrayList();
        Iterator props = messages.keySet().iterator();
        while (props.hasNext()) {
            String prop = (String) props.next();
            Iterator messages = ((ArrayList) this.messages.get(prop)).iterator();
            while (messages.hasNext())
                results.add(messages.next());
        }
        return (results.iterator());

    }


    /**
     * Return the set of messages related to a specific property.
     * If there are no such messages, an empty enumeration is returned.
     *
     * @param property Property name (or ActionMessages.GLOBAL_MESSAGE)
     */
    public Iterator get(String property) {

        ArrayList list = (ArrayList) messages.get(property);
        if (list == null)
            return (Collections.EMPTY_LIST.iterator());
        else
            return (list.iterator());

    }


    /**
     * Return the set of property names for which at least one message has
     * been recorded.  If there are no messages, an empty Iterator is returned.
     * If you have recorded global messages, the String value of
     * <code>ActionMessages.GLOBAL_MESSAGE</code> will be one of the returned
     * property names.
     */
    public Iterator properties() {

        return (messages.keySet().iterator());

    }


    /**
     * Return the number of messages recorded for all properties (including
     * global messages).  <strong>NOTE</strong> - it is more efficient to call
     * <code>empty()</code> if all you care about is whether or not there are
     * any messages at all.
     */
    public int size() {

        int total = 0;
        Iterator keys = messages.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            ArrayList list = (ArrayList) messages.get(key);
            total += list.size();
        }
        return (total);

    }


    /**
     * Return the number of messages associated with the specified property.
     *
     * @param property Property name (or ActionMessages.GLOBAL_MESSAGE)
     */
    public int size(String property) {

        ArrayList list = (ArrayList) messages.get(property);
        if (list == null)
            return (0);
        else
            return (list.size());

    }


}
