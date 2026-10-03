// N-array tree; next pointer to next brother or next child; direction 
// changes from left to right per level

class node {
  public:
    node *child;
    node *parent_next;
    node *parent_prev;

    node *next;
};

class stack {
  public:
    void push(node *);
    node *pop();
};

extern stack *forward_stack;
extern stack *reverse_stack;

void populate_next(node *a, node *prev) {
  static int forward = 1;

  if (!a)
    return;

  node *c;
  if (forward) {
    while (c = a->child) {
      prev->next = c;
      forward_stack->push(c);
      prev = c;
      c = c->parent_next;
    }
  }
  else {
    while (c = a->child) {
      prev->next = c;
      reverse_stack->push(c);
      prev = c;
      c = c->parent_prev;
    }
  }

  if (forward)
    c = reverse_stack->pop();
  else
    c = forward_stack->pop();

  if (c) {
    populate_next(c, prev);
  }
  else {
    forward = !forward;
    if (forward)
      c = reverse_stack->pop();
    else
      c = forward_stack->pop();
    populate_next(c, c);
  }
}

