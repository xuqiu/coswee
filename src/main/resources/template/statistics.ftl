<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <script src="http://jonmiles.github.io/bootstrap-treeview/bower_components/jquery/dist/jquery.js"></script>
    <script src="https://kryogenix.org/code/browser/sorttable/sorttable.js"></script>
    <script src="http://www.runoob.com/try/bootstrap/twitter-bootstrap-v2/js/bootstrap-tooltip.js"></script>
    <script src="http://www.runoob.com/try/bootstrap/twitter-bootstrap-v2/js/bootstrap-popover.js"></script>



    <style type="text/css" id="treeview1-style"> .treeview .list-group-item {
        cursor: pointer
    }
    .bs-docs-header {
        position: relative;
        padding: 30px 0;
        color: #cdbfe3;
        text-align: center;
        text-shadow: 0 1px 0 rgba(0,0,0,.1);
        background-color: #6f5499;
        background-image: -webkit-gradient(linear,left top,left bottom,from(#563d7c),to(#6f5499));
        background-image: -webkit-linear-gradient(top,#563d7c 0,#6f5499 100%);
        background-image: -o-linear-gradient(top,#563d7c 0,#6f5499 100%);
        background-image: linear-gradient(to bottom,#563d7c 0,#6f5499 100%);
        filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#563d7c', endColorstr='#6F5499', GradientType=0);
        background-repeat: repeat-x;
        margin-bottom: 40px;
    }
    /*有用别删*/
    .popover {
        max-width: 1280px;
    }
    </style>
</head>
<body>
<div class="bs-docs-header" id="content" tabindex="-1" onclick="delPopover()">
    <div class="container"><h1>Coswee</h1>
        <p>Show JAVA Spring Web program call chains intuitively, for costing analyze.</p>
    </div>
</div>
<div class="container" onclick="delPopover()">
    <button id="btn_back" type="button" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Back to Call Chains</button>
    <button id="btn_reload" type="button" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-repeat" aria-hidden="true"></span> Reload Data</button>
        <div class="row">
            <p>
            <h2>Statistics</h2>
            </p>
            <div>
                <table class="table sortable table-hover">
                    <thead>
                    <tr>
                        <th></th>
                        <th>Method</th>
                        <th>Times</th>
                        <th>Average Cost</th>
                        <th>Min Cost</th>
                        <th>Max Cost</th>
                        <th>All Cost</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list statistics as call>
                    <tr title="${call.name}">
                        <td><a id="detail_${call_index}" class="glyphicon glyphicon-th-list" aria-hidden="true"></a></td>
                        <td>${call.shortName}</td>
                        <td>${call.times}</td>
                        <td>${call.averageCost}</td>
                        <td>${call.minCost}</td>
                        <td>${call.maxCost}</td>
                        <td>${call.allCost}</td>
                    </tr>

                    <script>
                        $(function (){
                            $("#detail_${call_index}").popover({trigger: 'manual',html:true,title: 'Call From for <b>${call.name}</b>', content: $("#rootTable_${call_index}").html()}).on({
                                click: function() {
                                    $("div").remove(".popover");
                                    $(this).popover('toggle');
                                }
                            });
                        });
                    </script>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>


    <br/>
</div>
<div style="display: none">
    <#list statistics as call>
        <div id="rootTable_${call_index}">
            <table class="table sortable table-hover">
                <thead>
                <tr>
                    <th>Call From</th>
                    <th>Times</th>
                    <th>Average Cost</th>
                    <th>Min Cost</th>
                    <th>Max Cost</th>
                    <th>All Cost</th>
                </tr>
                </thead>
                <tbody>
                <#list call.rootInfo?keys as rootKey>
                <tr title="${call.rootInfo[rootKey].name}">
                    <td>${call.rootInfo[rootKey].shortName}</td>
                    <td>${call.rootInfo[rootKey].times}</td>
                    <td>${call.rootInfo[rootKey].averageCost}</td>
                    <td>${call.rootInfo[rootKey].minCost}</td>
                    <td>${call.rootInfo[rootKey].maxCost}</td>
                    <td>${call.rootInfo[rootKey].allCost}</td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </#list>
</div>
</div>
</body>
<script>
    $('#btn_reload').on('click', function (e) {
        window.location.reload();
    });
    $('#btn_back').on('click', function (e) {
        window.location.href = window.location.href.split("?")[0];
    });
</script>
</html>