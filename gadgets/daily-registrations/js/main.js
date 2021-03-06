var views = [{
    id: "chart-0",
    schema: [{
        "metadata": {
            "names": ["Day", "operator", "appID", "Count"],
            "types": ["ordinal", "ordinal", "ordinal", "linear"]
        }
    }],
    chartConfig: {
        x : "Day",
          charts : [
            {type: "line", range:"true",  y : "Count", color: "appID"}
          ],
          maxLength: -1,
          width: 500,
          height: 500
    },
    callbacks: [{
        type: "click",
        callback: function() {
            //wso2gadgets.load("chart-1");
            alert("Clicked on bar chart of chart-0. You can implement your own callback handler for this.");
        }
    }],
    subscriptions: [{
        topic: "range-selected",
        callback: function(topic, data, subscriberData) {
            //do some stuff
        }
    }],
    data: function() {
        var SERVER_URL = "/portal/apis/analytics";
        var TABLE_NAME = "COM_WSO2_TELCO_DAILY_REGISTRATIONS_PER_APP_SUMMARY";
        var client = new AnalyticsClient().init(null, null, SERVER_URL);
        var searchParams = {
            tableName : TABLE_NAME,
            searchParams : {
              query : "operator:Airtel",
              start : 0, 
              count : 10,
              sortBy : [{
                "field" : "_timestamp",
                "sortType" : "ASC" //can be DESC or ASC
              }]
            }
        };
        client.search(
            searchParams,
            function(response) {
                var results = [];
                var data = JSON.parse(response.message);
                data.forEach(function(record, i) {
                    var values = record.values;
                    var result = [record["day"], values["operator"], values["appID"], values["regCount"]];
                    results.push(result);
                });
                //Call the framework to draw the chart with received data. Note that data should be in VizGrammar ready format
                wso2gadgets.onDataReady(results);
            },
            function(e) {
                //throw it to upper level 
                onError(e);
            }
        );
    }
}];

$(function() {
    try {
        wso2gadgets.init("#canvas",views);
        var view = wso2gadgets.load("chart-0");
    } catch (e) {
        console.error(e); 
    }

    $("#update").click(function() {
        var data = [
            ["Colombo", 23.4, 45.6, 25.6],
            ["Galle", 12, 65, 56]
        ];
        wso2gadgets.onDataReady(data,"append");
    });

    $("#next").click(function() {
        wso2gadgets.load("chart-1");
    });

    $("#prev").click(function() {
        wso2gadgets.load("chart-0");
    });



});
