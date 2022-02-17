<?php

namespace App\Http\Controllers;

// use App\KasPemasukan;

use App\KasPemasukan as AppKasPemasukan;
use App\UserMan;
use Illuminate\Http\Request;

class KasPemasukan extends Controller
{
    public function index(){

        // return (
        //    AppKasPemasukan::all()
        // );

        return view('pemasukan',[
            "menu1" => "Beranda",
            "menu2" => "Pemasukan",
            "title" => "Pemasukan",
            "kasmasuk"  => AppKasPemasukan::all(),
            "userman"  => Userman::all()
        ]);
    }
}
