<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <script src="http://jonmiles.github.io/bootstrap-treeview/bower_components/jquery/dist/jquery.js"></script>
    <#--<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>-->
    <script src="http://jonmiles.github.io/bootstrap-treeview/js/bootstrap-treeview.js"></script>
    <script src="https://kryogenix.org/code/browser/sorttable/sorttable.js"></script>
    <style type="text/css" id="treeview1-style"> .treeview .list-group-item {
        cursor: pointer
    }
    .bs-docs-header {
        position: relative;
        padding: 30px 0;
        color: #cdbfe3;
        background-f: #61C400;
        background-f: #70b100;
        background-f: #809d00;
        background-f: #908a00;
        background-f: #a07600;
        background-f: #a07600;
        background-f: #b06200;
        background-f: #bf4f00;
        background-f: #cf3b00;
        background-f: #df2800;
        background-f: #FF0000;
        background-f: #FF0000;
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

    </style>
</head>
<body>
<div class="bs-docs-header" id="content" tabindex="-1">
    <div class="container"><h1>Coswee</h1>
        <p>Show JAVA Spring Web program call chains intuitively, for costing analyze.</p>
    </div>
</div>
<div class="container">
    <button id="btn_reload" type="button" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-repeat" aria-hidden="true"></span> Reload Data</button>
    <button id="btn_clear" type="button" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Clear Data</button>
    <button id="btn_statistics" type="button" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-list" aria-hidden="true"></span> Show Method Statistics</button>
    <#list treeNodeJsonMap?keys as key>
        <div class="row">
            <p>
            <h2>${key}</h2>
            <span id="btn_expand_${key_index}" class="glyphicon glyphicon-resize-full" aria-hidden="true"></span>
            </p>
            <div>
                <div id="tree_${key_index}" class=""></div>
            </div>
        </div>
        <#if treeNodeJsonMap[key].callTimesList??>
        <div class="row">
            <div>
                <div class="panel panel-default">
                    <div class="panel-heading">High times calls</div>
                    <table class="table sortable table-hover">
                        <thead>
                        <tr>
                            <th>method</th>
                            <th>times</th>
                            <th>cost</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list treeNodeJsonMap[key].callTimesList as callTimes>
                            <tr>
                                <td>${callTimes.shortName}</td>
                                <td>${callTimes.times}</td>
                                <td>${callTimes.cost}</td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        </#if>
        <script>
            var treeData_${key_index}=[${treeNodeJsonMap[key]?string}];
            $('#tree_${key_index}').treeview({
                //expandIcon: "glyphicon glyphicon-stop",
                //collapseIcon: "glyphicon glyphicon-unchecked",
                //nodeIcon: "glyphicon glyphicon-log-in",
                //color: "yellow",
                //backColor: "purple",
                //onhoverColor: "orange",
                //borderColor: "red",
                //showBorder: false,
                levels: 99,
                showTags: true,
                highlightSelected: true,
                selectedColor: "yellow",
                selectedBackColor: "darkorange",
                data: treeData_${key_index}
            });
            $('#btn_expand_${key_index}').on('click', function (e) {
                $('#tree_${key_index}').treeview('expandAll', { levels: 99});
            });
        </script>
    </#list>
    <br/>
</div>
</body>
<script>
    $('#btn_clear').on('click', function (e) {
        if (confirm("You sure wanna clear all data?")) {
            $.get(window.location.href+"?action=clear", function (result) {
                window.location.reload();
            });
        }
    });
    $('#btn_reload').on('click', function (e) {
        window.location.reload();
    });
    $('#btn_statistics').on('click', function (e) {
        window.location.href = window.location.href+"?action=getStatisticsPage";
    });
</script>
</html>