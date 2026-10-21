
int foo(int x) {
    return x + 10;
}

int moo(int (*func_ptr)(int)) {
    return (*func_ptr)(100);
}

int bar() {
    int (*f_ptr)(int) = &foo;
    return moo(f_ptr);
}