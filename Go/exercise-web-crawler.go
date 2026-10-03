package main

import (
    "fmt"
    "sync"
    "time"
)

type Fetcher interface {
    // Fetch returns the body of URL and
    // a slice of URLs found on that page.
    Fetch(url string) (body string, urls []string, err error)
}

// Crawl uses fetcher to recursively crawl
// pages starting with url, to a maximum of depth.
func Crawl(url string, depth int, fetcher Fetcher) {
    // TODO: Fetch URLs in parallel.
    // TODO: Don't fetch the same URL twice.
    // This implementation doesn't do either:
    if depth <= 0 {
        return
    }
    body, urls, err := fetcher.Fetch(url)
    if err != nil {
        fmt.Println(err)
        return
    }
    fmt.Printf("found: %s %q\n", url, body)
    for _, u := range urls {
        Crawl(u, depth-1, fetcher)
    }
    return
}

func parallelCrawl(url string, depth int, fetcher Fetcher, 
    urlMap map[string]string, mapLock *sync.Mutex, wg *sync.WaitGroup) {

    // Too deep
    if depth <= 0 {
        return
    }
    time.Sleep(time.Second)
    fmt.Printf("@@ %s, %d\n", url, depth)

    body, urls, err := fetcher.Fetch(url)

    // URL not found
    if err != nil {
        //fmt.Println(err)
        mapLock.Lock()
        urlMap[url] = "not found"
        mapLock.Unlock()
        return
    }

    // URL found
    //fmt.Printf("found: %s %q\n", url, body)
    mapLock.Lock()
    urlMap[url] = body
    mapLock.Unlock()

    // Crawl over its URLs
    for _, u := range urls {
        // Has this already been processed?
        mapLock.Lock()
        if _, ok := urlMap[u]; ok {
            mapLock.Unlock()
            continue
        }
        mapLock.Unlock()

        wg.Add(1)
        go func(uu string) {
            parallelCrawl(uu, depth-1, fetcher, urlMap, mapLock, wg)
            wg.Done()
        }(u)
    }

    return
}

func main() {
    Crawl("https://golang.org/", 4, fetcher)

    // Map of processed URLs to Body / "not found"
    urlMap := make(map[string]string)
    // Mutex for locking the map
    var mapLock sync.Mutex
    // WaitGroup for when recursive crawling is done
    var wg sync.WaitGroup 
    
    // Start the crawl
    wg.Add(1)
    go func() {
        parallelCrawl("https://golang.org/", 4, fetcher, urlMap, &mapLock, &wg)
        wg.Done()
    }()

    // Wait for the crawl to complete
    wg.Wait()
    for url, body := range urlMap {
        fmt.Printf("~~> %s %s\n", url, body)
    }
}

// fakeFetcher is Fetcher that returns canned results.
type fakeFetcher map[string]*fakeResult

type fakeResult struct {
    body string
    urls []string
}

func (f fakeFetcher) Fetch(url string) (string, []string, error) {
    if res, ok := f[url]; ok {
        return res.body, res.urls, nil
    }
    return "", nil, fmt.Errorf("not found: %s", url)
}

// fetcher is a populated fakeFetcher.
var fetcher = fakeFetcher{
    "https://golang.org/": &fakeResult{
        "The Go Programming Language",
        []string{
            "https://golang.org/pkg/",
            "https://golang.org/cmd/",
        },
    },
    "https://golang.org/pkg/": &fakeResult{
        "Packages",
        []string{
            "https://golang.org/",
            "https://golang.org/cmd/",
            "https://golang.org/pkg/fmt/",
            "https://golang.org/pkg/os/",
        },
    },
    "https://golang.org/pkg/fmt/": &fakeResult{
        "Package fmt",
        []string{
            "https://golang.org/",
            "https://golang.org/pkg/",
        },
    },
    "https://golang.org/pkg/os/": &fakeResult{
        "Package os",
        []string{
            "https://golang.org/",
            "https://golang.org/pkg/",
            "https://golang.org/pkg/sync/",
            "https://golang.org/pkg/time/",
        },
    },
    "https://golang.org/pkg/sync/": &fakeResult{
        "Package sync",
        []string{
            "https://golang.org/",
            "https://golang.org/pkg/",
        },
    },
}
