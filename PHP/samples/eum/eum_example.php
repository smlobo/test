<html>
<head>
<script>
window.apmeum || (apmeum = {});
apmeum.customerId='emaastesttenant1';
apmeum.baseCollectorUrl='https://slc15gsu.us.oracle.com:4443/APMaaSCollector/external/collector?';
</script>
<script async crossorigin="anonymous" src="https://slc15gsu.us.oracle.com:4443/APMaaSCollector/external/collector/staticlib/apmeum.js"></script>
<script>
function showHint(str) {
    if (str.length == 0) { 
        document.getElementById("txtHint").innerHTML = "";
        return;
    } else {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("txtHint").innerHTML = this.responseText;
            }
        };
        xmlhttp.open("GET", "eum_gethint.php?q=" + str, true);
        xmlhttp.send();
    }
}
</script>
</head>
<body>

<p><b>Start typing a name in the input field below:</b></p>
<form> 
First name: <input type="text" onkeyup="showHint(this.value)">
</form>
<p>Suggestions: <span id="txtHint"></span></p>
</body>
</html>
