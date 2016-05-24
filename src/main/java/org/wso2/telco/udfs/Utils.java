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

/**
 * This contains all the utility udfs.
 */
public class Utils {

    private final String UNIDENTIFIED_LOGIN = "UNIDENTIFIED_LOGIN";
    private final String HE_LOGIN = "HE_AUTH_SUCCES";
    private final String DEFAULT_LOGIN_SUCCESS = "LOGIN_SUCCES";
    private final String HE_LOGIN_TYPE = "HE";
    private final String USSD_LOGIN_TYPE = "USSD";
    private final String USSD_PIN_LOGIN_TYPE = "PIN";

    /**
     * Returns the Authenticator method when the array of authenticators are given.
     * @param authenticatorMethods authenticator methods in the form [MSISDN, USSD]
     * @return last authenticator method.
     */
    public String getAuthenticator(String authenticatorMethods) {
        String result = authenticatorMethods.replaceAll("[\\[\\]]","");
        if (result != null || !result.isEmpty()) {
            String[] results = result.split(",");
            result = results[results.length - 1];
        }
        return result.trim();
    }

    /**
     * Returns the login type for the given scenario.
     * @param status final login status for the session.
     * @param msisdnHeader if the msisdn header is present or not.
     * @param acrValue the acr value for the session.
     * @return HE for HE logins, USSD for USSD logins, PIN for USSD PIN logins.
     */
    public String getLoginType(String status, boolean msisdnHeader, int acrValue) {
        if (status == null || !status.isEmpty()) {
            status = status.toUpperCase().trim();
            if (status.equals(HE_LOGIN) && msisdnHeader) {
                return HE_LOGIN_TYPE;
            } else if (status.equals(DEFAULT_LOGIN_SUCCESS) && !msisdnHeader) {
                if (acrValue == 2) {
                    return USSD_LOGIN_TYPE;
                } else if (acrValue == 3) {
                    return USSD_PIN_LOGIN_TYPE;
                }
            }
        }
        return UNIDENTIFIED_LOGIN;
    }
}
