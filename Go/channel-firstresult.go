//go:build ignore
package main

import (
	"context"
	"errors"
    "fmt"
    "time"
)

type ResultTuple struct {
	Val int
	Err error
}

// function that takes a job and channel, executes the job, and puts result 
// on the channel
func executeJob(job func() (int, error), resultChannel chan<- ResultTuple) {
	r, e := job()
	resultChannel <- ResultTuple{Val: r, Err: e}
}

func firstResult(ctx context.Context, jobs []func() (int, error)) (int, error) {
	if len(jobs) == 0 {
		return 0, errors.New("no jobs")
	}

	// channel that receives job results
	jobResultChannel := make(chan ResultTuple, len(jobs))

	// iterate over jobs
	for _, job := range jobs {
		go executeJob(job, jobResultChannel)
	}

	// wait on result
	failures := 0
	for failures < len(jobs) {
		select {
		case result := <- jobResultChannel:
			if (result.Err == nil) {
				return result.Val, nil
			}
			failures++
		case <- ctx.Done():
			return 0, ctx.Err()
		}
	}	

	return 0, errors.New("all jobs failed")
}

func main() {
	jobs := []func() (int, error){
	    func() (int, error) {
	    	time.Sleep(200 * time.Millisecond)
	    	return 42, nil
	    },
	    func() (int, error) {
	    	time.Sleep(100 * time.Millisecond)
	    	return 0, errors.New("earlier job failed")
	    },
	}
	v, e := firstResult(context.Background(), jobs)
	if e != nil {
		fmt.Println("Error: ", e)
	}

	fmt.Printf("result: %d\n", v)
}