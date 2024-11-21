#include <stdio.h>

#define max 1000000
long long int list[max];
long long int store[max];

void func() {
    long long int i, num, j;
    for (i = 2; i < max; i++)
        list[i] = 1;

    for (i = 2; i < max; i++) {
        j = 2;
        while ((num = j * i) < max) {
            list[num] += i;
            j++;
        }
    }

    store[0] = store[1] = 0;
    for (i = 2; i < max; i++)
        store[i] = store[i - 1] + list[i];
}

int main() {
    long long int T, i, n, j;
    func();
    scanf("%lld", &T);

    for (i = 0; i < T; i++) {
        scanf("%lld", &n);
        printf("%lld\n\n", store[n]);
    }

    return 0;
}
