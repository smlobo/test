<html>
    <head>
        <title>DB Query</title>
    </head>
    <body>
        <h1>Query</h1>
        <cfquery datasource="test" name="result">
            select * from book_list
        </cfquery>
        <cfdump var="#result#"/>
    </body>
</html>

