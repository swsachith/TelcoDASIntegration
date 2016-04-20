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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUDF {

    /**
     * Returns the long value for the date to which the timestamp belongs to.
     * Ex: for a long timstamp value for 3/4/2016 12:33:22 it would return the long timstamp value for the date
     * 3/4/2016 00:00:00.
     *
     * @param timestamp timestamp in milliseconds.
     * @return long timestamp value for the date it belongs to.
     * @throws ParseException
     */
    public Long getDateTimestamp(Long timestamp) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(new Date(timestamp));
        Date processedDate = dateFormat.parse(dateString);
        return processedDate.getTime();
    }
}
