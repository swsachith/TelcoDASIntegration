/*
* Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* WSO2 Inc. licenses this file to you under the Apache License,
* Version 2.0 (the "License"); you may not use this file except
* in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.wso2.telco.udfs;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UtilTest {

    private Utils utils;

    @BeforeMethod
    public void setUp() throws Exception {
        utils = new Utils();
    }

    @Test
    public void testGetAuthenticatorForEmptyBrackets() {
        String value = "[]";
        String result = utils.getAuthenticator(value);
        Assert.assertEquals(result , "" , "Empty brackets returned value is wrong!");
    }

    @Test
    public void testGetAuthenticatorForNullValue() {
        String value = "";
        String result = utils.getAuthenticator(value);
        Assert.assertEquals(result , "" , "Empty brackets returned value is wrong!");
    }

    @Test
    public void testGetAuthenticatorForSetOfValues() {
        String value = "[GSMA,USSD, MSISDN]";
        String result = utils.getAuthenticator(value);
        Assert.assertEquals(result, "MSISDN", "Returned last value is not the expected!");
    }

    @Test
    public void testGetLoginTypeForHE() {
        String status = "HE_AUTH_SUCCES";
        Boolean msisdnHeader = true;
        int acrValue = 2;
        String result = utils.getLoginType(status, msisdnHeader, acrValue);
        Assert.assertEquals(result, "HE", "Did not return the type as HE");
    }

    @Test
    public void testGetLoginTypeForUSSD() {
        String status = "LOGIN_SUCCES";
        Boolean msisdnHeader = false;
        int acrValue = 2;
        String result = utils.getLoginType(status, msisdnHeader, acrValue);
        Assert.assertEquals(result, "USSD", "Did not return the type as USSD");
    }

    @Test
    public void testGetLoginTypeForPIN() {
        String status = "LOGIN_SUCCES";
        Boolean msisdnHeader = false;
        int acrValue = 3;
        String result = utils.getLoginType(status, msisdnHeader, acrValue);
        Assert.assertEquals(result, "PIN", "Did not return the type as PIN");
    }

    @Test
    public void testGetLoginTypeForUnIdentified() {
        String status = "LOGIN_SUCCES";
        Boolean msisdnHeader = true;
        int acrValue = 2;
        String result = utils.getLoginType(status, msisdnHeader, acrValue);
        Assert.assertEquals(result, "UNIDENTIFIED_LOGIN", "Did not return the type as UNIDENTIFIED_LOGIN");
    }
}