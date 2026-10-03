#include <iostream>
#include <vector>
#include <map>
#include <string>
#include <algorithm>
#include <set>
#include <cassert>
using namespace std;

struct Node{
   Node* next;
   Node* prev;
   int value;
   int key;
   Node(Node* p, Node* n, int k, int val) : 
      prev(p),next(n),key(k),value(val) {};
   Node(int k, int val) : 
      prev(NULL),next(NULL),key(k),value(val) {};
};

class Cache{
   
protected: 
   map<int,Node*> mp; //map the key to the node in the linked list
   int cp;  //capacity
   Node* tail; // double linked list tail pointer
   Node* head; // double linked list head pointer
   virtual void set(int, int) = 0; //set function
   virtual int get(int) = 0; //get function

};

class LRUCache : Cache {
public:
    LRUCache(int _cp) {
        cp = _cp;
        tail = nullptr;
        head = nullptr;
    };

    void set(int k, int v) {
        // Does key reside in the map
        if (mp.find(k) != mp.end()) {
            Node *hit = mp[k];

            // Update value
            hit->value = v;

            // Already head
            if (hit == head)
                return;

            // Splice up the DLL
            Node *pNode = hit->prev;
            Node *nNode = hit->next;
            if (pNode)
                pNode->next = nNode;
            if (nNode)
                nNode->prev = pNode;

            // Was tail
            if (hit == tail) {
                tail = pNode;
                assert(nNode == nullptr);
            }

            // Move to the head
            hit->next = head;
            hit->prev = nullptr;
            if (head)
                head->prev = hit;
            head = hit;
        }
        else {
            Node *newN = new Node(k, v);
            mp[k] = newN;

            // Add as head of the DLL
            newN->next = head;
            newN->prev = nullptr;

            // Empty cache
            if (head == nullptr) {
                assert(tail == nullptr);
                assert(mp.size() == 1);
                head = newN;
                tail = newN;
                return;
            }

            // Splice
            head->prev = newN;
            head = newN;

            // Below capacity
            if (mp.size() <= cp)
                return;

            // Evict the LRU
            Node *victim = tail;
            tail = victim->prev;
            tail->next = nullptr;

            mp.erase(victim->key);
        }
    }

    int get(int k) {
        // Key NOT in the map
        if (mp.find(k) == mp.end())
            return -1;

        Node *hit = mp[k];

        // Already head
        if (hit == head)
            return hit->value;

        // Splice up the DLL
        Node *pNode = hit->prev;
        Node *nNode = hit->next;
        if (pNode)
            pNode->next = nNode;
        if (nNode)
            nNode->prev = pNode;

        // Was tail
        if (hit == tail) {
            tail = pNode;
            assert(nNode == nullptr);
        }

        // Move to the head
        hit->next = head;
        hit->prev = nullptr;
        if (head)
            head->prev = hit;
        head = hit;

        return hit->value;
    }

};

int main() {
   int n, capacity,i;
   cin >> n >> capacity;
   LRUCache l(capacity);
   for(i=0;i<n;i++) {
      string command;
      cin >> command;
      if(command == "get") {
         int key;
         cin >> key;
         cout << l.get(key) << endl;
      } 
      else if(command == "set") {
         int key, value;
         cin >> key >> value;
         l.set(key,value);
      }
   }
   return 0;
}
