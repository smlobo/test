#!/usr/bin/python3

"""
Charlie has been given an assignment by his Professor to strip the links 
and the text name from the html pages.

A html link is of the form,
<a href="http://www.hackerrank.com">HackerRank</a>  
Where a is the tag and href is an attribute which holds the link charlie 
is interested in. The text name is HackerRank.

Charlie notices that the text name can sometimes be hidden within multiple 
tags
<a href="http://www.hackerrank.com"><h1><b>HackerRank</b></h1></a>
Here, the text name is hidden inside the tags h1 and b.

Help Charlie in listing all the links and the text name of the links.
"""

#import sys
import re

def printSingleLineSolution(htmlString):
	m = re.search(r'(?<=\<a href\=\")([^\"]+)(?=\").*?((?<=\>)[^\<]+(?=\<\/.*a\>))', htmlString)
	if m:
		print("{},{}".format(m.group(1), m.group(2)))

def printSolution(htmlString):
	m = re.findall(r'(?<=\<a href\=\")([^\"]+)(?=\").*?((?<=\>)[^\<]*(?=\<\/.*a\>))', htmlString)
	for link, title in m:
		print("{},{}".format(link, title.lstrip().rstrip()))

"""
for line in sys.stdin:
while True:
	try:
		html = input()
		printSolution(html)
	except EOFError:
		break
"""

num = int(input())
for _ in range(num):
	html = input()
	printSolution(html)

"""
# test 1
tString = '<a href="http://www.hackerrank.com">HackerRank</a>'
printSolution(tString)

# test 2
tString = '<a href="http://www.hackerrank.com"><h1><b>HackerRank</b></h1></a>'
printSolution(tString)

# test 3
tString = '<p><a href="http://www.quackit.com/html/tutorial/html_links.cfm">Example Link</a></p>'
printSolution(tString)

# test 4
tString = '<div class="more-info"><a href="http://www.quackit.com/html/examples/html_links_examples.cfm">More Link Examples...</a></div>'
printSolution(tString)

# test 
tString = '<div class="portal" role="navigation" id=\'p-navigation\'>'
printSolution(tString)

# test 
tString = '<h3>Navigation</h3>'
printSolution(tString)

# test 
tString = '<div class="body">'
printSolution(tString)

# test 
tString = ' <li id="n-mainpage-description"><a href="/wiki/Main_Page" title="Visit the main page [z]" accesskey="z">Main page</a></li>'
printSolution(tString)

# test 
tString = ' <li id="n-contents"><a href="/wiki/Portal:Contents" title="Guides to browsing Wikipedia">Contents</a></li>'
printSolution(tString)

# test 
tString = ' <li id="n-featuredcontent"><a href="/wiki/Portal:Featured_content" title="Featured content  the best of Wikipedia">Featured content</a></li>'
printSolution(tString)

# test 
tString = '<li id="n-currentevents"><a href="/wiki/Portal:Current_events" title="Find background information on current events">Current events</a></li>'
printSolution(tString)

# test 
tString = '<li id="n-randompage"><a href="/wiki/Special:Random" title="Load a random article [x]" accesskey="x">Random article</a></li>'
printSolution(tString)

# test 
tString = '<li id="n-sitesupport"><a href="//donate.wikimedia.org/wiki/Special:FundraiserRedirector?utm_source=donate&utm_medium=sidebar&utm_campaign=C13_en.wikipedia.org&uselang=en" title="Support us">Donate to Wikipedia</a></li>'
printSolution(tString)
"""
