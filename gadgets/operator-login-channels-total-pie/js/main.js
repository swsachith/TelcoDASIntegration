var views = [{
    id: "chart-0",
    schema: [{
        "metadata": {
            "names": ["Type", "Count"],
            "types": ["ordinal", "linear"]
        }
    }],
    chartConfig: {
           charts : [{type: "arc",  x : "Count", color : "Type", mode: "donut"}],
            width: 400,
            height: 300
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
        var TABLE_NAME = "COM_WSO2_TELCO_SUMMARY_OPERATOR_LOGIN_CHANNELS";
        var client = new AnalyticsClient().init(null, null, SERVER_URL);
        var searchParams = {
            tableName : TABLE_NAME,
            searchParams : {
              query : "operator:Airtel",
              groupByField:"operator",
              aggregateFields:[
              {
                fields:["he_logins"], 
                aggregate:"SUM",
                alias:"he" 
               },
               {
                fields:["ussd_logins"], 
                aggregate:"SUM",
                alias:"ussd" 
               },
               {
                fields:["sms_logins"], 
                aggregate:"SUM",
                alias:"sms" 
                }
              ]
            }
        };
        client.searchWithAggregates(
            searchParams,
            function(response) {
                var results = [];
                var data = JSON.parse(response.message);
                data.forEach(function(record, i) {
                    var values = record.values;
                    var he = ["HE", values["he"]];
                    var ussd = ["USSD", values["ussd"]];
                    var sms = ["SMS", values["sms"]];
                    results.push(he);
                    results.push(ussd);
                    results.push(sms);
                    console.log(results);
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
