#include<bits/stdc++.h>

using namespace std;

struct Workshop {
    int startTime;
    int duration;
    int endTime;
};

struct Available_Workshops {
    int n;
    Workshop* workshops;
    int minTime;
    int maxTime;
    int minDuration;
    int maxDuration;
};

Available_Workshops* initialize(int startTime[], int duration[], int n) {
    Available_Workshops* aw = new Available_Workshops();
    aw->n = n;
    aw->workshops = new Workshop[n];
    aw->minTime = 2001;
    aw->maxTime = -1;
    aw->minDuration = 1001;
    aw->maxDuration = -1;

    // Read the workshops data
    for (int i = 0; i < n; i++) {
        aw->workshops[i].startTime = startTime[i];
        aw->workshops[i].duration = duration[i];
        aw->workshops[i].endTime = startTime[i] + duration[i];
        if (aw->minTime > startTime[i])
            aw->minTime = startTime[i];
        if (aw->maxTime < aw->workshops[i].endTime)
            aw->maxTime = aw->workshops[i].endTime;
        if (aw->minDuration > duration[i])
            aw->minDuration = duration[i];
        if (aw->maxDuration < duration[i])
            aw->maxDuration = duration[i];
    }

    cout << aw->minTime << " -> " << aw->maxTime << endl;
    cout << aw->minDuration << " -> " << aw->maxDuration << endl;

    return aw;
}

int CalculateMaxWorkshops(Available_Workshops* aw) {
    // Track used time with an array
    int d = aw->maxTime - aw->minTime;
    bool* usedTime = new bool[d];
    for (int i = 0; i < d; i++)
        usedTime[i] = false;
    
    int maxWorkshops = 0;
    // Start with the shortest duration, and go to the max
    for (int i = aw->minDuration; i <= aw->maxDuration; i++) {
        // Find workshop that matches this duration
        for (int j = 0; j < aw->n; j++) {
            Workshop* ws = &(aw->workshops[j]);
            
            if (ws->duration != i)
                continue;
            
            // Use all 0 duration workshops
            if (i == 0) {
                maxWorkshops++;
                continue;
            }
            
            // Check if timeslot is open
            int index = ws->startTime - aw->minTime;
            bool available = true;
            for (int k = 0; k < i; k++) {
                if (usedTime[index+k]) {
                    available = false;
                    break;
                }
            }
            if (!available)
                continue;
            
            maxWorkshops++;
            // Mark this slot
            for (int k = 0; k < i; k++)
                usedTime[index+k] = true;
        }
        cout << "After dur: " << i << " : " << maxWorkshops << endl;
    }
    
    return maxWorkshops;
}
//Define the structs Workshops and Available_Workshops.
//Implement the functions initialize and CalculateMaxWorkshops

int main(int argc, char *argv[]) {
    int n; // number of workshops
    cin >> n;
    // create arrays of unknown size n
    int* start_time = new int[n];
    int* duration = new int[n];

    for(int i=0; i < n; i++){
        cin >> start_time[i];
    }
    for(int i = 0; i < n; i++){
        cin >> duration[i];
    }

    Available_Workshops * ptr;
    ptr = initialize(start_time,duration, n);
    cout << CalculateMaxWorkshops(ptr) << endl;
    return 0;
}
