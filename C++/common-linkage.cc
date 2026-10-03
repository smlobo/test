// RUN: %clang_cc1 -triple x86_64-pc-solaris2.11 -mllvm -sunstudio-compatibility -emit-llvm -o - %s | FileCheck %s

int i1;
// CHECK-DAG: @i1 = common global i32 0
int i2 = 0;
// CHECK-DAG: @i2 = global i32 0

class A { int i; };
A a1;
// CHECK-DAG: @a1 = common global %class.A zeroinitializer
A a2 = A();
// CHECK-DAG: @a2 = global %class.A zeroinitializer

class B { };
class C : public B { };
C c;
// CHECK-DAG: @c = common global %class.C zeroinitializer

class D { virtual void f() { } };
class E : public D { };
E e;
// CHECK-DAG: @e = global

class F { };
class G : public virtual F { };
G g;
// CHECK-DAG: @g = global

class H { public: H() { } };
class I : public H { };
I i;
// CHECK-DAG: @i = global %class.I zeroinitializer

class J { public: J() { } };
J j;
// CHECK-DAG: @j = global %class.J zeroinitializer

struct X {
  static const int V1 = 4;
  static const int V2;
  static int V3;
};
const int X::V1;
// CHECK-DAG: @_ZN1X2V1E = constant i32 4
const int X::V2 = 8;
// CHECK-DAG: @_ZN1X2V2E = constant i32 8
int X::V3;
// CHECK-DAG: @_ZN1X2V3E = common global i32 0

// Extern C stuff
extern "C" {
  int i3;
  // CHECK-DAG: @i3 = common global i32 0
  static int i4;
  // CHECK-DAG: @_ZL2i4 = internal global i32 0
  int f1(int);
  // CHECK-DAG: declare i32 @f1(i32)
}

extern "C" int i5;
// CHECK-DAG: @i5 = common global i32
extern "C" static int i6;
// CHECK-DAG: @_ZL2i6 = internal global i32
static int (*f2)(int);
// CHECK-DAG: @_ZL2f2 = internal global i32 (i32)* null
extern "C" static int (*f3)(int);
// CHECK-DAG: @_ZL2f3 = internal global i32 (i32)* null

void use(const void *);
void forceEmit() {
  use(&X::V1);
  use(&X::V2);
  use(&X::V3);
  use(&i4);
  use(&i5);
  use(&i6);
  use(f1);
  use(f2);
  use(f3);
}
