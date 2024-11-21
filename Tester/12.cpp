#include <stdio.h>

int main() {
    int T;
    double sum;
    scanf("%d", &T);

    for (int i = 0; i < T; i++) {
        int n;
        scanf("%d", &n);

        sum = (3.0 / 4.0) + (1.0 / (2 * (n + 2))) - (1.0 / (2 * (n + 1)));

        printf("Sum for test case %d: %.11f\n", i + 1, sum);
    }

    return 0;
}
