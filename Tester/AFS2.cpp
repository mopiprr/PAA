#include <iostream>
#include <cmath> // for sqrt function

using namespace std;

int f(int n) {
  int sum = 0;
  int root = sqrt(n);
  // Iterate through all divisors less than or equal to the square root of n
  for (int i = 1; i <= root; ++i) {
    if (n % i == 0) {
      // If i is the square root, only add it once
      if (i == root) {
        sum += i;
      } else {
        sum += i; // Add both i and n/i (except for the square root)
        sum += n / i;
      }
    }
  }
  return sum;
}

int a(int n) {
  if (n <= 1) {
    return 0;
  }
  // Recursively calculate a[n-1]
  return a(n - 1) + f(n);
}

int main() {
  int T;
  cin >> T;

  while (T--) {
    int n;
    cin >> n;

    // Calculate and print a[n]
    cout << a(n) << endl;
  }

  return 0;
}
