
struct block_t {
  word_t header;
  union {
    unsigned char *payload;
    block_t *next;
  }
  block_t *previous;
}


block_t * get_next(block_t *current_free) {
  requires_dbg(!get_alloc(current_free);

  return current_free->next;
}

struct block_t {
  word_t header;
  unsigned char *payload;
}

block_t * get_next(block_t *current_free) {
  requires_dbg(!get_alloc(current_free);

  return (block_t *) ((unsigned char *) current_free + wsize);
}

block_t * get_previous(block_t *current_free) {
  requires_dbg(!get_alloc(current_free);

  return (block_t *) ((unsigned char *) current_free + wsize*2);
}
