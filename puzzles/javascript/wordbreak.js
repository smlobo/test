/**
 * @param {string} s
 * @param {set<string>} wordDict
 *   Note: wordDict is a Set object, see:
 *   https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Set
 * @return {boolean}
 */
var wordBreak = function(s, wordDict) {
  if (wordDict.size === 0)
    return false;
  if (s.length === 0)
    return true;
  for (var i=1; i<=s.length; i++) {
    if (wordDict.has(s.substr(0, i))) {
      if (wordBreak(s.slice(i, s.length), wordDict) === true)
        return true;
    }
  }
  return false;
}

dict = new Set();
document.write("a : "+wordBreak("a", dict)+"<br>");
//dict.add("a");
dict.add("bc");
document.write("a : "+wordBreak("a", dict)+"<br>");
document.write("bc : "+wordBreak("bc", dict)+"<br>");
dict.add("bcd");
document.write("xybz : "+wordBreak("xybz", dict)+"<br>");
dict.add("aaaa");
dict.add("aa");
document.write("aaaaaaa : "+wordBreak("aaaaaaa", dict)+"<br>");
dict.add("aaa");
document.write("aaaaaaa : "+wordBreak("aaaaaaa", dict)+"<br>");
for (value of dict) {
  document.write("-> "+value+"<br>");
}
