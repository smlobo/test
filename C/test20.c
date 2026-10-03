// Binary tree with parent pointers, find common parent of 2 nodes

typedef struct n node;

struct n {
  node *right;
  node *left;
  node *parent;
};

node *common_parent(node *a, node *b) {
  if (a == b) return a;

  return common_parent(a->parent, b->parent);
}
