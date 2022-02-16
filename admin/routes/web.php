<?php

use App\Http\Controllers\ControllerUserman;
use App\UserMan;
use Illuminate\Support\Facades\Route;
/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('Beranda',[
        "menu1" => "Beranda",
        "menu2" => "Beranda",
        "title" => "Beranda"
    ]);
});

Route::get('/beranda', function () {
    return view('beranda',[
        "menu1" => "Beranda",
        "menu2" => "Beranda",
        "title" => "Beranda"
    ]);
});


Route::get('/user', function () {
    return view('userman',[
        "menu1" => "Beranda",
        "menu2" => "User Management",
        "title" => "Management User",
        "userman" => UserMan::all()
    ]);
});

Route::get('/masuk', function () {
    return view('pemasukan',[
        "menu1" => "Beranda",
        "menu2" => "Pemasukan",
        "title" => "Pemasukan"
    ]);
});

Route::get('/keluar', function () {
    return view('pengeluaran',[
        "menu1" => "Beranda",
        "menu2" => "Pengeluaran",
        "title" => "Pengeluaran"
    ]);
});

Route::get('/sumber_pemasukan', function () {
    return view('sumber_pemasukan',[
        "menu1" => "Beranda",
        "menu2" => "Mitra Kerja",
        "menu3" => "Mitra Kerja",
        "title" => "Sumber Pemasukan"
    ]);
});

Route::get('/report_masuk', function () {
    return view('report_masuk',[
        "menu1" => "Beranda",
        "menu2" => "Content",
        "menu3" => "Dana Masuk",
        "title" => "Dana Masuk"
    ]);
});

Route::get('/report_keluar', function () {
    return view('report_keluar',[
        "menu1" => "Beranda",
        "menu2" => "Content",
        "menu3" => "Dana Keluar",
        "title" => "Dana Keluar"
    ]);
});


Route::post('/add_userman',[
    ControllerUserman::class,'store'
]);

Route::post('/del_userman',[
    ControllerUserman::class,'hapus'
]);



// Route::post('/add_userman',[ControllerUserman::class,'store']);
// Route::post('/login-userman',[ControllerUserman::class,'login']);
// Route::post('/coba',[ControllerUserman::class,'login']);

