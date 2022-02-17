<?php

namespace App\Http\Controllers;
use Illuminate\Http\Request;
use App\UserMan;


class ControllerUserman extends Controller
{
    public function store(Request $request){
        $user = new UserMan();
        $user->nama = $request->nama;
        $user->phone = $request->phone;
        $user->alamat = $request->alamat;
        $user->nip = $request->nip;
        $user->jenis_kelamin = $request->jenis_kelamin;
        $user->code = $request->code;
        $user->level = $request->level;
        $user->save();

        if($user){
            $request->session()->flash('user', 'userman Created successfully!');
            return redirect('/user');
        }else{
            $request->session()->flash('user', 'userman Created valied!');
            return redirect('/user');
        }
    }

    public function edit(Request $request){

     
        $request->validate([
            'user_id' => 'required',
            'nama' => 'required',
            'phone' => 'required',
            'alamat' => 'required',
            'nip' => 'required',
            'jenis_kelamin' => 'required',
            'level' => 'required'
        ]);

      UserMan::find($request->user_id)->update($request->all());
      $request->session()->flash("user", "update has been created!");
      return redirect('/user');
    }

    public function hapus(Request $request){
      
        UserMan::find($request->id)->delete();
        return redirect('/user')->with('user','userman has been deleted!');
    }
}
