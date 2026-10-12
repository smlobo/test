#include <iostream>

using namespace std;

string ltrim(const string &);
string rtrim(const string &);



/*
 * Complete the 'processRequestQueueOperations' function below.
 *
 * The function is expected to return an INTEGER_ARRAY.
 * The function accepts following parameters:
 *  1. STRING_ARRAY operations
 *  2. INTEGER_ARRAY values
 */

vector<int> processRequestQueueOperations(vector<string> operations, vector<int> values) {
    vector<int> output = {};

    stack<int> enqueueStack = {};
    stack<int> dequeueStack = {};
    int inputIndex = 0;

    for (int i = 0; i < operations.size(); ++i) {
        if (operations[i] == "enqueue") {
            // Drain dequeue stack
            // while (!dequeueStack.empty()) {
            //     enqueueStack.push(dequeueStack.top());
            //     dequeueStack.pop();
            // }
            // Perform enqueue
            enqueueStack.push(values[inputIndex]);
            // cout << "[enqueue] " << enqueueStack.top() << "\n";
        } else if (operations[i] == "dequeue") {
            // Drain the enqueue stack if empty
            if (dequeueStack.empty()) {
                while (!enqueueStack.empty()) {
                    dequeueStack.push(enqueueStack.top());
                    enqueueStack.pop();
                }
            }
            // Perform dequeue
            output.push_back(dequeueStack.top());
            // cout << "[dequeue] " << dequeueStack.top() << "\n";
            dequeueStack.pop();
        } else if (operations[i] == "peek") {
            // Drain the enqueue stack if empty
            if (dequeueStack.empty()) {
                while (!enqueueStack.empty()) {
                    dequeueStack.push(enqueueStack.top());
                    enqueueStack.pop();
                }
            }
            // Perform peek
            output.push_back(dequeueStack.top());
            // cout << "[peek] " << dequeueStack.top() << "\n";
        } else if (operations[i] == "size") {
            output.push_back(dequeueStack.size() + enqueueStack.size());
        }
        inputIndex++;
    }
    // for (int i = 0; i < output.size(); i++) {
    //     cout << "[" << i << "] " << output[i] << "\n";
    // }
    return output;
}

int main()
{
    string operations_count_temp;
    getline(cin, operations_count_temp);

    int operations_count = stoi(ltrim(rtrim(operations_count_temp)));

    vector<string> operations(operations_count);

    for (int i = 0; i < operations_count; i++) {
        string operations_item;
        getline(cin, operations_item);

        operations[i] = operations_item;
    }

    string values_count_temp;
    getline(cin, values_count_temp);

    int values_count = stoi(ltrim(rtrim(values_count_temp)));

    vector<int> values(values_count);

    for (int i = 0; i < values_count; i++) {
        string values_item_temp;
        getline(cin, values_item_temp);

        int values_item = stoi(ltrim(rtrim(values_item_temp)));

        values[i] = values_item;
    }

    vector<int> result = processRequestQueueOperations(operations, values);

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
