# ETS Pemrograman Perangkat Bergerak (C)

**Anggota Kelompok:**

1. Ammar Ghozy Tanumijaya - 5025231203
2. Huraira Shenmue Mahanee - 5025231216

---

## Proyek: BookSport App ⚽🏸🏀🎾

BookSport adalah aplikasi mobile berbasis Android untuk memudahkan pemesanan (booking) lapangan olahraga. Aplikasi ini dibangun dengan mengedepankan desain antarmuka yang modern (Dark Theme dengan aksen Lime) untuk memberikan pengalaman pengguna (UX) yang premium dan responsif.

## 📱 Fitur (Telah Diimplementasikan)

Seluruh antarmuka (UI/UX) Frontend aplikasi ini telah dibangun dengan **Jetpack Compose** berdasarkan referensi rancangan Mockup HTML secara Pixel-Perfect. Fitur yang sudah tersedia meliputi:

- **Splash Screen**: Layar pembuka aplikasi dengan efek delay statis.
- **Welcome / Logo Screen**: Halaman pengenalan aplikasi (Onboarding).
- **Register Screen**: Halaman pendaftaran akun baru.
- **Login Screen**: Halaman masuk akun.
  - _Catatan: Terdapat dummy data login. Silakan gunakan kredensial berikut untuk login dan masuk ke Beranda:_
    - **Email:** `admin@gmail.com`
    - **Password:** `123`
- **Home Screen (Beranda)**: Menampilkan panel pencarian, kategori olahraga, dan daftar lapangan (Venue) di sekitar.
- **Venue Detail Screen**: Menampilkan foto hero, fasilitas, dan status jadwal jam sewa secara interaktif.
- **Booking Form Screen**: Formulir pemesanan lapangan (pilihan olahraga, data pemesan, jadwal).
- **Booking Confirmation Screen**: Layar kesuksesan yang memunculkan tiket virtual, _barcode_, dan animasi cincin sukses berputar.

## 🛠️ Tech Stack

- **Bahasa Pemrograman**: Kotlin
- **UI Toolkit**: Jetpack Compose (Material 3)
- **Navigasi**: Navigation Compose
- **Desain Arsitektur**: Modern Android Architecture

## 🎨 Design System

Aplikasi ini secara ketat mengikuti sistem desain yang telah ditentukan:

- **Theme**: Dark Theme
- **Primary Accent**: Lime (`#C8FF00`)
- **Typography**:
  - `Syne` (Untuk Heading / Display Text)
  - `DM Sans` (Untuk Body / Label Text)

## 🚀 Cara Menjalankan Aplikasi

1. Clone repositori ini atau ekstrak file proyek ke direktori lokal Anda.
2. Buka **Android Studio**.
3. Pilih menu **File > Open** dan arahkan ke folder proyek `LapanginBookSportApp`.
4. Tunggu hingga proses **Gradle Sync** selesai.
5. Jalankan aplikasi pada **Emulator** atau **Perangkat Fisik Android** dengan menekan tombol **Run (Shift + F10)**.

## 📂 Alur & Struktur Navigasi Lengkap (End-to-End)

1. `Splash` -> Menampilkan logo sebentar, lalu berpindah otomatis ke `Logo`.
2. `Logo` -> Menyediakan opsi "Mulai Sekarang" (ke `Register`) atau "Masuk" (ke `Login`).
3. `Register` -> Memiliki tautan untuk berpindah ke `Login`.
4. `Login` -> Memiliki validasi dummy. Jika sukses, akan menghapus _backstack_ dan berpindah ke `Home`.
5. `Home` -> Layar beranda. Pengguna dapat mengeklik kartu _venue_ (contoh: GOR Manyar) untuk menuju ke `VenueDetail`.
6. `VenueDetail` -> Menampilkan detail. Menekan "Pesan" membawa pengguna ke `BookingForm`.
7. `BookingForm` -> Layar pengisian data. Menekan "Lanjut ke Pembayaran" akan menuju `BookingConfirmation`.
8. `BookingConfirmation` -> Layar tiket akhir. Menekan tombol "Kembali ke Beranda" akan mereset tumpukan navigasi dan melempar pengguna bersih kembali ke layar `Home`.
