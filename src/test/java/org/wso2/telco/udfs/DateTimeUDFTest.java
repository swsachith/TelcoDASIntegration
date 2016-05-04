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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * This class contains the tests for the @link{DateTimeUDF} class.
 */
public class DateTimeUDFTest {

    private DateTimeUDF dateTimeUDF;
    private final long TIMESTAMP = 1461737927771L;
    private final String DATE = "2016-04-27";

    @BeforeClass
    public void setup() {
        dateTimeUDF = new DateTimeUDF();
    }

    @Test
    public void testGetTimestamp() throws Exception {
        long expectedResult =  1461695400000L;
        long returnedTimestamp = dateTimeUDF.getDateTimestamp(TIMESTAMP);
        Assert.assertEquals(returnedTimestamp, expectedResult, "Expected timestamp is different!");
    }

    @Test
    public void testGetDateString() throws Exception {
        String returnedDateString = dateTimeUDF.getDateString(TIMESTAMP);
        Assert.assertEquals(returnedDateString, DATE, "Expected timestamp string is different!");
    }

    @Test
    public void testGetTimeFromDate() throws Exception {
        long timestampForDate = dateTimeUDF.getTimestampForDate(DATE);
        Assert.assertEquals(TIMESTAMP, timestampForDate, "returned timestamp for the data is wrong!");
    }


}
