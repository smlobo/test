#include <climits>
#include <iostream>
#include <queue>
#include <sstream>
#include <cctype>
#include <string>
#include <vector>

using namespace std;

string ltrim(const string &s);
string rtrim(const string &s);
vector<string> split(const string &s);

/*
 * Complete the 'computeShortestDeliveryTimes' function below.
 *
 * The function is expected to return an INTEGER_ARRAY.
 * The function accepts following parameters:
 *  1. INTEGER n
 *  2. INTEGER_ARRAY handlingTimes
 *  3. INTEGER m
 *  4. 2D_INTEGER_ARRAY routes
 *  5. INTEGER source
 */

struct Edge {
    int from;
    int to;
    int weight;
};

class RelaxEdge {
public:
    int to;
    int weight;
    bool operator<(const RelaxEdge& other) const {
        return weight > other.weight;
    }
};

vector<int> computeShortestDeliveryTimes(int n, vector<int> handlingTimes, 
    int m, vector<vector<int>> routes, int source) {
    // build adj list
    vector<vector<Edge>> adjList = {};
    for (int i = 0; i < n; i++) {
        adjList.emplace_back();
    }
    for (int i = 0; i < m; i++) {
        vector<int>& rawEdge = routes.at(i);
        int from = rawEdge.at(0);
        int to = rawEdge.at(1);
        int weight = rawEdge.at(2);
        adjList.at(from).push_back({from, to, weight});
    }

    // vector of final distance to
    vector<int> distTo(n, -1);

    // PQ of relaxed edge
    priority_queue<RelaxEdge> minRelaxPQ;

    // Initialize
    distTo[source] = 0;
    minRelaxPQ.push({source, 0});

    // loop until pq is empty
    while (!minRelaxPQ.empty()) {
        RelaxEdge rEdge = minRelaxPQ.top();
        minRelaxPQ.pop();

        // Iterate over outgoing edges
        for (const Edge& e : adjList[rEdge.to]) {
            int fromDist = distTo[e.from] + e.weight;
            if (e.from != source) {
                fromDist += handlingTimes[e.from];
            }
            if (distTo[e.to] == -1 || 
                distTo[e.to] > fromDist) {
                distTo[e.to] = fromDist;
                minRelaxPQ.push({e.to, distTo[e.to]});
            }
        }
    }

    return distTo;
}

int main()
{
    string n_temp;
    getline(cin, n_temp);

    int n = stoi(ltrim(rtrim(n_temp)));

    string handlingTimes_count_temp;
    getline(cin, handlingTimes_count_temp);

    int handlingTimes_count = stoi(ltrim(rtrim(handlingTimes_count_temp)));

    vector<int> handlingTimes(handlingTimes_count);

    for (int i = 0; i < handlingTimes_count; i++) {
        string handlingTimes_item_temp;
        getline(cin, handlingTimes_item_temp);

        int handlingTimes_item = stoi(ltrim(rtrim(handlingTimes_item_temp)));

        handlingTimes[i] = handlingTimes_item;
    }

    string m_temp;
    getline(cin, m_temp);

    int m = stoi(ltrim(rtrim(m_temp)));

    string routes_rows_temp;
    getline(cin, routes_rows_temp);

    int routes_rows = stoi(ltrim(rtrim(routes_rows_temp)));

    string routes_columns_temp;
    getline(cin, routes_columns_temp);

    int routes_columns = stoi(ltrim(rtrim(routes_columns_temp)));

    vector<vector<int>> routes(routes_rows);

    for (int i = 0; i < routes_rows; i++) {
        routes[i].resize(routes_columns);

        string routes_row_temp_temp;
        getline(cin, routes_row_temp_temp);

        vector<string> routes_row_temp = split(rtrim(routes_row_temp_temp));

        for (int j = 0; j < routes_columns; j++) {
            int routes_row_item = stoi(routes_row_temp[j]);

            routes[i][j] = routes_row_item;
        }
    }

    string source_temp;
    getline(cin, source_temp);

    int source = stoi(ltrim(rtrim(source_temp)));

    vector<int> result = computeShortestDeliveryTimes(n, handlingTimes, m, routes, source);

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

vector<string> split(const string &str) {
    istringstream input(str);
    vector<string> tokens;
    string token;
    while (input >> token) {
        tokens.push_back(token);
    }
    return tokens;
}
