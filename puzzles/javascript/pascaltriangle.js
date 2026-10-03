function checkAndDisplay() {
  var len = document.getElementById("pLength").value;
  var msg;
  if (isNaN(len) || len > 200 || len <=0) {
     msg = "Invalid length: " + len;
     document.getElementById("message").innerHTML = msg;
     document.getElementById("pyramid").innerHTML = "";
     return;
  }
  else {
     msg = "Building your pyramid of height: ";
  }
  var fullMsg = msg + len;
  document.getElementById("message").innerHTML = fullMsg;

  var prmd = "";
  for (i=1; i<=len; i++) {
    for (j=1; j<=i; j++) {
      prmd = prmd + "*";
      document.getElementById("pyramid").innerHTML = prmd;
      //for (k=0; k<1000000; k++) {
      //}
    }
    prmd = prmd + "<br>";
  }
}
