<!DOCTYPE HTML>
<html manifest="" lang="en-US">
<head>
    <meta charset="UTF-8">
    <title>GS</title>

    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
    
    <style type="text/css">
         /**
         * Example of an initial loading indicator.
         * It is recommended to keep this as minimal as possible to provide instant feedback
         * while other resources are still being loaded for the first time
         */
        html, body {
            height: 100%;
            background-color: #1985D0
        }
        
        .tarifas{
            color : #eee;
            
        }
        
        
        
        
        .table {
            width: 100%;
            margin-bottom: 20px;
            }
            
            table {
                max-width: 100%;
                background-color: transparent;
                border-collapse: collapse;
                border-spacing: 0;
                }
                
                .table tbody tr.success td {
                        background-color: #dff0d8;
                      }
                      tr {
                display: table-row;
                vertical-align: inherit;
                border-color: inherit;
                }
                
                
        .table tbody tr.warning td {
            background-color: #fcf8e3;
          }
          
          .table-hover tbody tr.warning:hover td {
                background-color: #faf2cc;
              }
              
              .table-hover tbody tr.success:hover td {
            background-color: #d0e9c6;
          }
        #appLoadingIndicator {
            position: absolute;
            top: 50%;
            margin-top: -15px;
            text-align: center;
            width: 100%;
            height: 30px;
            -webkit-animation-name: appLoadingIndicator;
            -webkit-animation-duration: 0.5s;
            -webkit-animation-iteration-count: infinite;
            -webkit-animation-direction: linear;
        }

        #appLoadingIndicator > * {
            background-color: #FFFFFF;
            display: inline-block;
            height: 30px;
            -webkit-border-radius: 15px;
            margin: 0 5px;
            width: 30px;
            opacity: 0.8;
        }

        @-webkit-keyframes appLoadingIndicator{
            0% {
                opacity: 0.8
            }
            50% {
                opacity: 0
            }
            100% {
                opacity: 0.8
            }
        }

        
        
    </style>

    <style type ="text/css">
        .home{text-align:center}
        .home h1{font-weight:bold;font-size:1.2em}
        .home p{color:#666;font-size:0.8em;line-height:1.6em;margin:10px 20px 20px 20px}
        .home img{margin-top:-10px}
        .home h2{color:#999;font-size:0.7em}
    </style>
    <!-- The line below must be kept intact for Sencha Command to build your application -->
    <script id="microloader" type="text/javascript" src="resourceMovil/GS/touch/microloader/development.js"></script>
</head>
<body>
    <div id="appLoadingIndicator">
        <div></div>
        <div></div>
        <div></div>
    </div>
</body>
</html>
