// 2 lists in sorted order; merge removing duplicate elements

struct node {
  int data;
  struct node *next;
  struct node *sort_next;
};

struct node *merge(struct node *a, struct node *b) {
  struct node *head, *prev, *curr;

  if (a->data < b->data) {
    head = a;
    a = a->next;
  }
  else {
    head = b;
    b = b->next;
  }

  prev = head;
  while (a || b) {
    if (!a) {
      curr = b;
      b = b->next;
    }
    else if (!b) {
      curr = a;
      a = a->next;
    }
    else if (a->data < b->data) {
      curr = a;
      a = a->next;
    }
    else {
      curr = b;
      b = b->next;
    }

    if (curr->data != prev->data) {
      prev->sort_next = curr;
      prev = curr;
    }
  }

  return head;
}
