#include <stdio.h>

void print_math(int a, int b) {
    printf("Sum: %d\n", a + b);
    printf("Product: %d\n", a * b);
}

int main() {
    int first, second;

    printf("Enter first number: ");
    scanf("%d", &first);
    printf("Enter second number: ");
    scanf("%d", &second);

    print_math(first, second);

    return 0;
}
