package main

import "fmt"

const NMAX int = 11

type pemain struct {
    nama, nomorPunggung, posisi string
    tinggi int
}

type tabPemain [NMAX]pemain

func main() {
    var timnasTabPemain tabPemain
    var nPemain int = 0

    bacaData(&timnasTabPemain, &nPemain)
    cetakPemain(timnasTabPemain, nPemain)
    cetakNamaPemainTertinggi(timnasTabPemain, nPemain)
    cetakNamaPemainTerendah(timnasTabPemain, nPemain)
}

func bacaData(A *tabPemain, n *int) {
    var nama, nomorPunggung, posisi string
    var tinggi int

    fmt.Println("Enter player data (enter 'none' to stop):")
    for *n < NMAX {
        fmt.Print("Enter name: ")
        fmt.Scanln(&nama)
        if nama == "none" {
            break
        }
        fmt.Print("Enter jersey number: ")
        fmt.Scanln(&nomorPunggung)
        fmt.Print("Enter position: ")
        fmt.Scanln(&posisi)
        fmt.Print("Enter height: ")
        fmt.Scanln(&tinggi)

        (*A)[*n] = pemain{nama, nomorPunggung, posisi, tinggi}
        *n++
    }
}

func cetakPemain(A tabPemain, n int) {
    fmt.Println("Data pemain:")
    for i := 0; i < n; i++ {
        fmt.Printf("Name: %s, Jersey Number: %s, Position: %s, Height: %d\n", A[i].nama, A[i].nomorPunggung, A[i].posisi, A[i].tinggi)
    }
}

func cetakNamaPemainTertinggi(A tabPemain, n int) {
    if n == 0 {
        fmt.Println("No players to display.")
        return
    }
    tallest := A[0]
    for i := 1; i < n; i++ {
        if A[i].tinggi > tallest.tinggi {
            tallest = A[i]
        }
    }
    fmt.Printf("Tallest Player: %s\n", tallest.nama)
}

func cetakNamaPemainTerendah(A tabPemain, n int) {
    if n == 0 {
        fmt.Println("No players to display.")
        return
    }
    shortest := A[0]
    for i := 1; i < n; i++ {
        if A[i].tinggi < shortest.tinggi {
            shortest = A[i]
        }
    }
    fmt.Printf("Shortest Player: %s\n", shortest.nama)
}
