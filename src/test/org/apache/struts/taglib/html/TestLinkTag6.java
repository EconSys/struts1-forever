/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/taglib/html/TestLinkTag6.java,v 1.3 2003/12/11 05:14:48 jmitchell Exp $
 * $Revision: 1.3 $
 * $Date: 2003/12/11 05:14:48 $
 * 
 * ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
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
package org.apache.struts.taglib.html;

import java.util.Locale;

import javax.servlet.jsp.PageContext;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.cactus.JspTestCase;
import org.apache.struts.Globals;
import org.apache.struts.taglib.SimpleBeanForTesting;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.html.FrameTag</code> class.
 *
 * @author James Mitchell
 */
public class TestLinkTag6 extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestLinkTag6(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestLinkTag6.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestLinkTag6.class);
    }

    private void runMyTest(String whichTest, String locale){
    	pageContext.setAttribute(Globals.LOCALE_KEY, new Locale(locale, locale), PageContext.SESSION_SCOPE);
    	pageContext.setAttribute(Constants.BEAN_KEY, new SimpleBeanForTesting("Test Value"), PageContext.REQUEST_SCOPE);
		request.setAttribute("runTest", whichTest);
        try {
			pageContext.forward("/test/org/apache/struts/taglib/html/TestLinkTag6.jsp");
		}
		catch (Exception e) {
			fail("" + e.getMessage());
		}
    }

    /*
     * Testing FrameTag.
     */

//--------Testing attributes using forward------
    public void testLinkHrefOnblur(){
        runMyTest("testLinkHrefOnblur", "");
    }

    public void testLinkHrefOnclick(){
        runMyTest("testLinkHrefOnclick", "");
    }

    public void testLinkHrefOndblclick(){
        runMyTest("testLinkHrefOndblclick", "");
    }

    public void testLinkHrefOnfocus(){
        runMyTest("testLinkHrefOnfocus", "");
    }

    public void testLinkHrefOnkeydown(){
        runMyTest("testLinkHrefOnkeydown", "");
    }

    public void testLinkHrefOnkeypress(){
        runMyTest("testLinkHrefOnkeypress", "");
    }

    public void testLinkHrefOnkeyup(){
        runMyTest("testLinkHrefOnkeyup", "");
    }

    public void testLinkHrefOnmousedown(){
        runMyTest("testLinkHrefOnmousedown", "");
    }

    public void testLinkHrefOnmousemove(){
        runMyTest("testLinkHrefOnmousemove", "");
    }

    public void testLinkHrefOnmouseout(){
        runMyTest("testLinkHrefOnmouseout", "");
    }

    public void testLinkHrefOnmouseover(){
        runMyTest("testLinkHrefOnmouseover", "");
    }

    public void testLinkHrefOnmouseup(){
        runMyTest("testLinkHrefOnmouseup", "");
    }

    public void testLinkHrefParamIdParamNameNoScope(){
		pageContext.setAttribute("paramName", "paramValue", PageContext.REQUEST_SCOPE);
        runMyTest("testLinkHrefParamIdParamNameNoScope", "");
    }

    public void testLinkHrefParamIdParamNameParamPropertyNoScope(){
    	SimpleBeanForTesting sbft = new SimpleBeanForTesting("paramPropertyValue");
		pageContext.setAttribute("testingParamProperty", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testLinkHrefParamIdParamNameParamPropertyNoScope", "");
    }

    public void testLinkHrefParamIdParamNameApplicationScope(){
		pageContext.setAttribute("paramName", "paramValue", PageContext.APPLICATION_SCOPE);
        runMyTest("testLinkHrefParamIdParamNameApplicationScope", "");
    }

    public void testLinkHrefParamIdParamNameParamPropertyApplicationScope(){
    	SimpleBeanForTesting sbft = new SimpleBeanForTesting("paramPropertyValue");
		pageContext.setAttribute("testingParamProperty", sbft, PageContext.APPLICATION_SCOPE);
        runMyTest("testLinkHrefParamIdParamNameParamPropertyApplicationScope", "");
    }

    public void testLinkHrefParamIdParamNameSessionScope(){
		pageContext.setAttribute("paramName", "paramValue", PageContext.SESSION_SCOPE);
        runMyTest("testLinkHrefParamIdParamNameSessionScope", "");
    }

    public void testLinkHrefParamIdParamNameParamPropertySessionScope(){
    	SimpleBeanForTesting sbft = new SimpleBeanForTesting("paramPropertyValue");
		pageContext.setAttribute("testingParamProperty", sbft, PageContext.SESSION_SCOPE);
        runMyTest("testLinkHrefParamIdParamNameParamPropertySessionScope", "");
    }

    public void testLinkHrefParamIdParamNameRequestScope(){
		pageContext.setAttribute("paramName", "paramValue", PageContext.REQUEST_SCOPE);
        runMyTest("testLinkHrefParamIdParamNameRequestScope", "");
    }

    public void testLinkHrefParamIdParamNameParamPropertyRequestScope(){
    	SimpleBeanForTesting sbft = new SimpleBeanForTesting("paramPropertyValue");
		pageContext.setAttribute("testingParamProperty", sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testLinkHrefParamIdParamNameParamPropertyRequestScope", "");
    }


    public void testLinkHrefStyle(){
        runMyTest("testLinkHrefStyle", "");
    }

    public void testLinkHrefStyleClass(){
        runMyTest("testLinkHrefStyleClass", "");
    }

    public void testLinkHrefStyleId(){
        runMyTest("testLinkHrefStyleId", "");
    }

    public void testLinkHrefTabIndex(){
        runMyTest("testLinkHrefTabIndex", "");
    }

    public void testLinkHrefTarget(){
        runMyTest("testLinkHrefTarget", "");
    }







    public void testLinkHrefTitle(){
        runMyTest("testLinkHrefTitle", "");
    }

    public void testLinkHrefTitleKey(){
        runMyTest("testLinkHrefTitleKey", "");
    }

    public void testLinkHrefTransaction(){
    	pageContext.setAttribute(Globals.TRANSACTION_TOKEN_KEY, "Some_Token_Here", PageContext.SESSION_SCOPE);
        runMyTest("testLinkHrefTransaction", "");
    }




	
}
