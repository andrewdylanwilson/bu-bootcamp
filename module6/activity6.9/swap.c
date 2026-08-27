#include <stdio.h>

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

// Deliberate test, function receives copies, not addresses
void broken_swap(int a, int b) {
    int temp = a;
    a = b;
    b = temp;
}

int main() {
    int x = 10, y = 20;

    printf("Before swap: x = %d, y = %d\n", x, y);
    swap(&x, &y);
    printf("After swap: x = %d, y = %d\n", x, y);

    // Swap without pointers
    int a = 5, b = 9;
    printf("\nBroken swap: a = %d, b = %d\n", a, b);
    broken_swap(a, b);
    printf("After broken_swap: a = %d, b = %d\n", a, b);

    return 0;
}
