<?php

namespace App\Http\Controllers;

// use App\KasPemasukan;

use App\KasPemasukan as AppKasPemasukan;
use App\ModelHistory;
use App\ModelMitra;
use App\UserMan;
use Illuminate\Http\Request;

class ControllerPemasukan extends Controller{
    public function index(){
        return view('pemasukan',[
            "menu1" => "Beranda",
            "menu2" => "Pemasukan",
            "title" => "Pemasukan",
            "sumber" => ModelMitra::all(),
            "userman"  => Userman::all(),
            'mitra'    => AppKasPemasukan::mitras()->get()
        ]);
    }

    public function store(Request $req){
        
        $simpan = $req->validate([
            'user_id' => 'required',
            'mitra_id' => 'required',
            'jumlah_pemasukan' => 'required',
            'tanggal_masuk' => 'required'
        ]);

       AppKasPemasukan::create($simpan);
       $req->session()->flash('kasmasuk', ',has been a created');
       return redirect('/masuk');
    }


    public function edit(Request $req){
         $req->validate([
            'user_id' => 'required',
            'mitra_id' => 'required',
            'jumlah_pemasukan' => 'required',
            'tanggal_masuk' => 'required'
        ]);

        AppKasPemasukan::find($req->kasmasuk_id)->update($req->all());
        return redirect('/masuk')->with('masuk','updated has been successfully!');
    }

    public function hapus(Request $req){
        AppKasPemasukan::find($req->id)->delete();
        return redirect('/masuk')->with('masuk','kasmasuk has been deleted!');
    }
}
