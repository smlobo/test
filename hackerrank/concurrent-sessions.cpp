#include <cassert>
#include <iostream>

using namespace std;

string ltrim(const string &);
string rtrim(const string &);
vector<string> split(const string &);



/*
 * Complete the 'computeGroupPeakConcurrency' function below.
 *
 * The function is expected to return a 2D_INTEGER_ARRAY.
 * The function accepts 2D_STRING_ARRAY events as parameter.
 */

vector<vector<int>> computeGroupPeakConcurrency(vector<vector<string>> events) {
    // Sort by timestamps
    std::sort(events.begin(), events.end(), [](const vector<string>& a, const vector<string>& b) {
        int aTime = stoi(a[0]);
        int bTime = stoi(b[0]);
        if (aTime != bTime) {
            return aTime < bTime;
        }
        const string& aType = a[3];
        const string& bType = b[3];
        if (aType != bType) {
            return aType < bType;
        }
        int aUser = stoi(a[1]);
        int bUser = stoi(b[1]);
        if (aUser != bUser) {
            return aUser < bUser;
        }
        int aGroup = stoi(a[2]);
        int bGroup = stoi(b[2]);
        return aGroup < bGroup;
    });

    // hash map of group to current & max concurrent count
    unordered_map<int, pair<int, int>> groupConcurrent = {};

    for (const vector<string>& event : events) {
        int group = stoi(event[2]);
        const string& type = event[3];
        
        // map entry
        auto entry = groupConcurrent.find(group);
        
        // new entry
        if (entry == groupConcurrent.end()) {
            if (type == "login") {
                groupConcurrent[group] = {1, 1};
            }
            else if (type == "logout") {
                groupConcurrent[group] = {-1, -1};
            }
        }
        // Update existing
        else {
            pair<int,int>& values = entry->second;
            if (type == "login") {
                values.first += 1;
                values.second = std::max(values.first, values.second);
            }
            else if (type == "logout") {
                values.first -= 1;
            }
        }
    }
    
    // create the output vector
    vector<vector<int>> groupMax = {};
    groupMax.reserve(groupConcurrent.size());
    for (const pair<const int,pair<int,int>>& entry : groupConcurrent) {
        groupMax.emplace_back(vector<int>{entry.first, entry.second.second});
    }
    
    // Sort by group
    std::sort(groupMax.begin(), groupMax.end(), [](const vector<int>& a, const vector<int>& b) {
        return a[0] < b[0];
    });

    return groupMax;
}

int main()
{
    string events_rows_temp;
    getline(cin, events_rows_temp);

    int events_rows = stoi(ltrim(rtrim(events_rows_temp)));

    string events_columns_temp;
    getline(cin, events_columns_temp);

    int events_columns = stoi(ltrim(rtrim(events_columns_temp)));

    vector<vector<string>> events(events_rows);

    for (int i = 0; i < events_rows; i++) {
        events[i].resize(events_columns);

        string events_row_temp_temp;
        getline(cin, events_row_temp_temp);

        vector<string> events_row_temp = split(rtrim(events_row_temp_temp));

        for (int j = 0; j < events_columns; j++) {
            string events_row_item = events_row_temp[j];

            events[i][j] = events_row_item;
        }
    }

    vector<vector<int>> result = computeGroupPeakConcurrency(events);

    for (size_t i = 0; i < result.size(); i++) {
        for (size_t j = 0; j < result[i].size(); j++) {
            cout << result[i][j];

            if (j != result[i].size() - 1) {
                cout << " ";
            }
        }

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

vector<string> split(const string &str) {
    vector<string> tokens;

    string::size_type start = 0;
    string::size_type end = 0;

    while ((end = str.find(" ", start)) != string::npos) {
        tokens.push_back(str.substr(start, end - start));

        start = end + 1;
    }

    tokens.push_back(str.substr(start));

    return tokens;
}
