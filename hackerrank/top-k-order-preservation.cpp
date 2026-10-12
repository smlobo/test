#include <iostream>

using namespace std;

string ltrim(const string &);
string rtrim(const string &);



/*
 * Complete the 'getTopKFrequentEvents' function below.
 *
 * The function is expected to return an INTEGER_ARRAY.
 * The function accepts following parameters:
 *  1. INTEGER_ARRAY events
 *  2. INTEGER k
 */

class Event {
public:
    int timestamp_;
    int frequency_;
    Event(int timestamp, int frequency) : timestamp_(timestamp), frequency_(frequency) {}
};

class PQEvent {
public:
    int id_;
    Event event_;
    PQEvent(int id, Event event) : id_(id), event_(event) {}
    bool operator<(const PQEvent& other) const {
        if (event_.frequency_ != other.event_.frequency_) {
            return event_.frequency_ > other.event_.frequency_;
        }
        return event_.timestamp_ < other.event_.timestamp_;
    }
};

vector<int> getTopKFrequentEvents(vector<int> events, int k) {
    // map for counting frequencies
    unordered_map<int, Event> freqMap = {};
    for (size_t i = 0; i < events.size(); i++) {
        cout << "[" << i << "] " << events[i] << "\n";
        auto eventEntry = freqMap.find(events[i]);
        if (eventEntry == freqMap.end()) {
            freqMap.emplace(events[i], Event(i, 1));
        } else {
            eventEntry->second.frequency_ += 1;
        }
    }

    // Priority Q of Events
    priority_queue<PQEvent> minPQ = {};
    for (const auto& eventEntry : freqMap) {
        PQEvent pqEvent = PQEvent(eventEntry.first, eventEntry.second);
        cout << "[" << eventEntry.first << "] " << eventEntry.second.timestamp_ << ", " << eventEntry.second.frequency_ << "\n";
        minPQ.emplace(pqEvent);
        if (minPQ.size() > static_cast<size_t>(k)) {
            minPQ.pop();
        }
    }
    
    // populate the output vector of event ids
    vector<int> ids(k);
    for (int i = k-1; i >= 0; i--) {
        const PQEvent& lowest = minPQ.top();
        ids[i] = lowest.id_;
        minPQ.pop();
    }
    return ids;
}

int main()
{
    string events_count_temp;
    getline(cin, events_count_temp);

    int events_count = stoi(ltrim(rtrim(events_count_temp)));

    vector<int> events(events_count);

    for (int i = 0; i < events_count; i++) {
        string events_item_temp;
        getline(cin, events_item_temp);

        int events_item = stoi(ltrim(rtrim(events_item_temp)));

        events[i] = events_item;
    }

    string k_temp;
    getline(cin, k_temp);

    int k = stoi(ltrim(rtrim(k_temp)));

    vector<int> result = getTopKFrequentEvents(events, k);

    for (size_t i = 0; i < result.size(); i++) {
        cout << result[i];

        if (i != result.size() - 1) {
            cout << "\n";
        }
    }

    cout << "\n";

    return 0;
}

string ltrim(const string &str) {
    size_t i = 0;
    while (i < str.size() && isspace(static_cast<unsigned char>(str[i]))) {
        ++i;
    }
    return str.substr(i);
}

string rtrim(const string &str) {
    size_t i = str.size();
    while (i > 0 && isspace(static_cast<unsigned char>(str[i - 1]))) {
        --i;
    }
    return str.substr(0, i);
}
