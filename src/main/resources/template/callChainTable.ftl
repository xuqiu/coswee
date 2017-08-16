<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="http://jonmiles.github.io/bootstrap-treeview/js/bootstrap-treeview.js"></script>
    <style type="text/css" id="treeview1-style"> .treeview .list-group-item {
        cursor: pointer
    }

    </style>
</head>
<body>
这个是页面 11
${treeNodeJsonMap?size}
<div class="container">

    <div class="row">
        <div class="col-sm-4">
            <h2>Node Overrides</h2>
            <div id="treeview9" class=""></div>
        </div>
    </div>

    <br/>
</div>
<script src="./bower_components/jquery/dist/jquery.js"></script>
<script src="./js/bootstrap-treeview.js"></script>
<script type="text/javascript">
    $(function () {
        var alternateData = [
            {
                text: 'Parent 1',
                tags: ['2'],
                nodes: [
                    {
                        text: 'Child 1',
                        tags: ['3'],
                        nodes: [
                            {
                                text: 'Grandchild 1',
                                tags: ['6']
                            },
                            {
                                text: 'Grandchild 2',
                                tags: ['3']
                            }
                        ]
                    },
                    {
                        text: 'Child 2',
                        tags: ['3']
                    }
                ]
            },
            {
                text: 'Parent 2',
                tags: ['7']
            },
            {
                text: 'Parent 3',
                icon: 'glyphicon glyphicon-earphone',
                href: '#demo',
                tags: ['11']
            },
            {
                text: 'Parent 4',
                icon: 'glyphicon glyphicon-cloud-download',
                href: '/demo.html',
                tags: ['19'],
                selected: true
            },
            {
                text: 'Parent 5',
                icon: 'glyphicon glyphicon-certificate',
                color: 'pink',
                backColor: 'red',
                href: 'http://www.tesco.com',
                tags: ['available', '0']
            }
        ];

        $('#treeview9').treeview({
            //expandIcon: "glyphicon glyphicon-stop",
            //collapseIcon: "glyphicon glyphicon-unchecked",
            //nodeIcon: "glyphicon glyphicon-log-in",
            //color: "yellow",
            //backColor: "purple",
            //onhoverColor: "orange",
            //borderColor: "red",
            //showBorder: false,
            showTags: true,
            highlightSelected: true,
            selectedColor: "yellow",
            selectedBackColor: "darkorange",
            data: alternateData
        });
    });
</script>
</body>

</html>