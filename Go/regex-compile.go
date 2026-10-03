package main

import (
	"fmt"
	"regexp"
)

func main() {
	// expr := "AE"
	// rX, err := regexp.Compile(expr)
	// if err != nil {
	// 	fmt.Printf("FAIL regexp.Compile(\"%s\") (err: %s)\n", expr, err)
	// } else {
	// 	fmt.Printf("Passed regexp.Compile(\"%s\"):\n", expr)
	// 	fmt.Printf("  AEEEE matches %s? %v\n", expr, rX.MatchString("AEEEE"))
	// 	fmt.Printf("  BE matches %s? %v\n", expr, rX.MatchString("BE"))
	// }

	// expr = "(EP|AE)"
	// rX, err = regexp.Compile(expr)
	// if err != nil {
	// 	fmt.Printf("FAIL regexp.Compile(\"%s\") (err: %s)\n", expr, err)
	// } else {
	// 	fmt.Printf("Passed regexp.Compile(\"%s\"):\n", expr)
	// 	fmt.Printf("  AEEEE matches %s? %v\n", expr, rX.MatchString("AEEEE"))
	// 	fmt.Printf("  BE matches %s? %v\n", expr, rX.MatchString("BE"))
	// 	fmt.Printf("  EP matches %s? %v\n", expr, rX.MatchString("EP"))
	// }

	// expr = ""
	// rX, err = regexp.Compile(expr)
	// if err != nil {
	// 	fmt.Printf("FAIL regexp.Compile(\"%s\") (err: %s)\n", expr, err)
	// } else {
	// 	fmt.Printf("Passed EMPTY regexp.Compile(\"%s\"):\n", expr)
	// }

	// expr = "(EP|AE)"
	// rX, err = regexp.Compile(expr)
	// if err != nil {
	// 	fmt.Printf("FAIL regexp.Compile(\"%s\") (err: %s)\n", expr, err)
	// } else {
	// 	fmt.Printf("Passed regexp.Compile(\"%s\"):\n", expr)
	// 	fmt.Printf("  AEEEE matches %s? %v\n", expr, rX.MatchString("AEEEE"))
	// 	fmt.Printf("  BE matches %s? %v\n", expr, rX.MatchString("BE"))
	// 	fmt.Printf("  EP matches %s? %v\n", expr, rX.MatchString("EP"))
	// }

	expr := "/spotonmedics/patient\\?jwt="
	rX, err := regexp.Compile(expr)
	if err != nil {
		fmt.Printf("FAIL regexp.Compile(\"%s\") (err: %s)\n", expr, err)
	} else {
		fmt.Printf("Passed regexp.Compile(\"%s\"):\n", expr)

		tests := []string {
			"https://physitrack.com/blah/spotonmedics/patient?jwt=xxx&name=private",
			"https://physitrack.com/blah/spotonmedics/doctor?jwt=xxx&name=private",
			"https://x.eu/spotonmedics/patient?jwt=xxx&name=private",
			"https://x.eu/spotonmedics/patient?id=qqq&name=private",
			"https://x.eu/spotonmedics/patient/jwt=xxx&name=private",
			"https://x.eu/zspotonmedics/patient?jwt=xxx&name=private",
		}

		for index, test := range tests {
			fmt.Printf("  [%d] %s ?\t%v\n", index, test, rX.MatchString(test))
		}
	}
}
