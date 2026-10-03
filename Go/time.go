package main

import (
	"fmt"
	"time"
	// "strconv"
)

func main() {
	currentDateTime := time.Now().Local()

	fmt.Println(currentDateTime)
	fmt.Printf("current \"date-time\": %v\n", currentDateTime)
	fmt.Printf("current (string) date-time: %s\n", currentDateTime)
	fmt.Println(currentDateTime.Format("2006-01-02_15.04.05.000-0700"))
	fmt.Println(currentDateTime.Format("2006-01-02_15.04.05.000"), 
		currentDateTime.Format(time.RFC822))
	fmt.Printf("  current date: %s\n", currentDateTime.Format("2006-01-02"))
	fmt.Printf("  current time: %s\n", currentDateTime.Format("15:04:05"))
	fmt.Printf("  current time (RFC3339): %s\n", currentDateTime.Format(time.RFC3339))
	fmt.Printf("  current time (RFC3339Nano): %s\n", currentDateTime.Format(time.RFC3339Nano))

	t2 := currentDateTime.Add(10 * time.Second)
	fmt.Println("10 sec in the future:", t2)

	difference := t2.Sub(currentDateTime)
	fmt.Println("Time difference: ", difference)

	if difference > 9 * time.Second {
		fmt.Println("> 9 sec difference")
	}

	// Back in time
	tBack := currentDateTime.Add(-time.Hour)
	fmt.Println("1 hr ago:", tBack)

	// Unix Nano
	int64Time := time.Now().UnixNano()
	fmt.Println("unix nano time:", int64Time)
	fmt.Printf("unix nano time: %d\n", int64Time)

	// Add a minute
	int64Time += 1000*1000*1000*60
	fmt.Println("in a minute:", time.Unix(0, int64Time))

	// Zero value
	zeroDateTime := time.Time{}
	fmt.Printf("Zero value time: %s\n", zeroDateTime)
	fmt.Printf("Zero value time: %v\n", zeroDateTime.IsZero())
	fmt.Printf("Zero value time in local: %s\n", zeroDateTime.In(time.Local))
	fmt.Printf("Zero value time in local: %v\n", zeroDateTime.In(time.Local).IsZero())

	// Epoch time
 //    millis, _ := strconv.ParseInt("0", 10, 64)
	// epochTime := time.UnixMilli(millis)
	// fmt.Printf("Epoch value time: %s\n", epochTime)

	// if epochTime.After(zeroDateTime) {
	// 	fmt.Printf("Epoch is after zero\n")
	// } else {
	// 	fmt.Printf("Epoch is before zero\n")
	// }

	// Millenium

	yr2000Time, _ := time.Parse(time.RFC3339Nano, "2000-01-01T00:00:00Z")
	fmt.Printf("yr2000: %s\n", yr2000Time)

	if yr2000Time.After(zeroDateTime) {
		fmt.Printf("2000 is after zero\n")
	} else {
		fmt.Printf("2000 is before zero\n")
	}

}
