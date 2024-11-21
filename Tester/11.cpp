#include<stdio.h>

int main(){
    int t;
    scanf("%d",&t);
    while(t--){
        int n;
        scanf("%d",&n);
        double ans;
        ans = (n) * (n + 3) / (4.0 * (n + 1) * (n + 2));
        printf("%.11f\n",ans + 0.5);
        }
return 0;
}